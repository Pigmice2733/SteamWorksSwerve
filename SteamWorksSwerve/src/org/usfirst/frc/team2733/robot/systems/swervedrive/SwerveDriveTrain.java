package org.usfirst.frc.team2733.robot.systems.swervedrive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.systems.AbstractDriveTrain;

import edu.wpi.first.wpilibj.SpeedController;

public class SwerveDriveTrain extends AbstractDriveTrain {

	private SwerveCalc swerveCalc;

	// All swerve modules in the drive train
	private List<SwerveModule> swerveModules;
	
//	private Map<WheelPosition, Integer> driveMotorPorts;  //unused
//	private Map<WheelPosition, Integer> rotationMotorPorts;  //unused
	
	private Map<WheelPosition, SpeedController> driveMotorControllers;
	private Map<WheelPosition, SpeedController> rotationMotorControllers;
	private Map<WheelPosition, Integer> encoderPorts;
	
	private EncoderCalibration encoderCal;
	
	// State variable tracking whether or not resources have been initialized
	private boolean isInitialized;
	
	/**
	 * Construct a swerve drive train
	 * @param wheelCoordinates Cartesian coordinates of each wheel relative to the turning center
	 * @param shouldReadPortsFromFile Whether port values should be read from a file
	 * @param The name of the file to read port values from
	 */
	public SwerveDriveTrain (Map<WheelPosition, SpeedController> driveMotorControllers,
			Map<WheelPosition, SpeedController> rotationMotorControllers,
			Map<WheelPosition, Integer> encoderPorts,
			EncoderCalibration encoderCal) {
		
	    swerveCalc = new SwerveCalc(getSwerveDict());
	    swerveModules = new ArrayList<SwerveModule>();
	    
	    this.driveMotorControllers = driveMotorControllers;
	    this.rotationMotorControllers = rotationMotorControllers;
	    this.encoderPorts = encoderPorts;
	    this.encoderCal = encoderCal;
	    isInitialized = false;
	}
	
	@Override
	public void initialize() {
	    if(isInitialized) {
            System.out.println("Swerve initialze() is called when already initialized\n");
            return;
        }
	    
	    for(WheelPosition pos : WheelPosition.values()) {
	        swerveModules.add(new SwerveModule(driveMotorControllers.get(pos),
	        		rotationMotorControllers.get(pos),
	        		encoderPorts.get(pos),
	        		pos,
	        		swerveCalc,
	        		encoderCal.getEncoderOffset(pos)));
	    }
	    
	    isInitialized = true;
	}

	@Override
	public void releaseReources() {
	    if(!isInitialized) {
            System.out.println("Swerve releaseResources() is called when not initialized\n");
            return;
        }
	    
		stopMovement();
		
		for (SwerveModule module : swerveModules) {
	        module.free();
	    }
	    swerveModules.clear();
	    
	    isInitialized = false;
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}
	
	// Aligns all wheels
	@Override
    public void ready() {
	    if(!isInitialized) {
            System.out.println("Swerve ready() is called before initialize()\n");
            return;
        }
	    for (SwerveModule module : swerveModules) {
            module.zeroPosition();
        } 
    }
	
	/**
	 * Calibrate all encoders and output/save calibration data
	 */
	public void calibrateEncoders() {
	    if(!isInitialized) {
	        initialize();
	    }
	    
	    for(WheelPosition pos : WheelPosition.values()) {
	        double offset = getEncoderValue(pos);
	        encoderCal.setEncoderOffset(pos, -offset);
	    }
	    
	    encoderCal.printEncoderOffsets();
	    
	    encoderCal.saveCalibrationFile();
	    
	    releaseReources();
	}
	
	@Override
	public void setup() {
		calibrateEncoders();
	}

	@Override
	public void stopMovement() {
	    if(!isInitialized) {
            System.out.println("Swerve stopMovement() is called before initialize()\n");
            return;
        }
		for (SwerveModule module : swerveModules) {
			module.stopMotors();
		}
	} 

	@Override
    public void swerveDrive(double magnitude, double direction, double rotation, double gyroOffset) {
        if(!isInitialized) {
            System.out.println("Swerve swerveDrive() is called before initialize()\n");
            return;
        }
        
        // Apply the gyro offset
        direction += gyroOffset;
        
        // Convert magnitude to speed
        double speed = magnitude * ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion();
        
        // Vector representation of speed and direction
        Tuple<Double> velocityVector = getVelocityVector(speed, direction);
        
        // Set swerve modules target speed and angle
        swerveCalc.setAim(velocityVector, rotation);
        
        // Perform update operation of each swerve module
        // Each swerve module will apply PID updates, and set target speed/angle from swerveCalc
        for (SwerveModule module : swerveModules) {
            module.update();
        }
    }
	
	@Override
	public void tankDrive (double leftSpeed, double rightSpeed, double gyroOffset) {
	    if(!isInitialized) {
            System.out.println("Swerve tankDrive() is called before initialize()\n");
            return;
        }
	    /**
	     * TODO: implement tank drive for swerve drive train 
	     */
	}
    
    /**
     * Calculate velocity vector from forward speed and a direction
     * @param speed Forward speed
     * @param direction The direction for the vector to point in radians
     * @return Vector representation of speed and direction
     */
    private static Tuple<Double> getVelocityVector(double speed, double direction) {
        
        // Rotate a forward speed vector by direction radians clockwise
        Tuple<Double> vector = new Tuple<Double>((Math.sin(direction) * speed), (Math.cos(direction) * speed));

        return vector;
    }
    
    /**
     * Get the current encoder value for a specific swerve module
     * @param wheelPos The WheelPosition of the encoder value to return
     * @return The encoder value for the specified swerve module
     */
    private double getEncoderValue(WheelPosition wheelPos) {
    	for (int i = 0; i < swerveModules.size(); i++) {
            if (swerveModules.get(i).getWheelPosition() == wheelPos) {
                return swerveModules.get(i).getEncoderValue();
            }
        }
    	
    	throw new IllegalArgumentException();
    }
    
    /**
     * Get wheel coordinates for the SwerveCalc
     * @return Map of wheel position to Cartesian coordinates relative to robot
     */
    public static Map<WheelPosition, Tuple<Double>> getSwerveDict() {
        Map<WheelPosition, Tuple<Double>> swerveDict = new HashMap<>();
        swerveDict.put(WheelPosition.FrontLeft, new Tuple<Double>(14.0, 19.0));
        swerveDict.put(WheelPosition.FrontRight, new Tuple<Double>(14.0, -19.0));
        swerveDict.put(WheelPosition.BackLeft, new Tuple<Double>(-14.0, 19.0));
        swerveDict.put(WheelPosition.BackRight, new Tuple<Double>(-14.0, -19.0));

        return swerveDict;
    }
}
