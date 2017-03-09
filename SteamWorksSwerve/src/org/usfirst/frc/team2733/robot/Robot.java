package org.usfirst.frc.team2733.robot;

import java.io.IOException;
import java.util.Properties;

import org.usfirst.frc.team2733.robot.autonomous.Autonomous;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	
	public static DriveTrain driveTrain;
	public static Autonomous auto;
	public static DataTransfer data;
	
	@Override
	protected void robotInit() {
		AutoCalibration.logConfigurationFile();
		driveTrain = new DriveTrain();
		//auto = new Autonomous(this, driveTrain);
		data = new DataTransfer(this);
    }

	/** 
	 * Commands available
	 * 
	 */
	@Override
	public void autonomous() {
		driveTrain.ready(false);
		
		//auto.startAutonomous();
		
		// Left and Right
	    //driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0.05 * Math.PI * (location == RobotInitialLocation.LEFT ? -1 : 1)), 0);
	    // Center
	    //driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0), 0);

		while (isAutonomous() && isEnabled()) {
			/*// Movement Update
			double speed = networkTable.getNumber("speed", 0);
			double direction = networkTable.getNumber("direction", 0);
			double rotation = networkTable.getNumber("rotation", 0);
			
			Vector_Point_Abomination velocityVector = driveTrain.getVelocityVector(speed, direction);
			
			driveTrain.swerveCalc.setAim(velocityVector, rotation);
			
			// Ball Shooter - If necessary
			// double ballShooterSpeed = networkTable.getNumber("shooterSpeed", 0);
			
			*/
			
			driveTrain.zeroWheelPositions();
			Timer.delay(0.05);
		}
	}
	
    @Override
    public void operatorControl() {
		driveTrain.ready(false);
    	//data = new DataTransfer (this);
    	
    	while (isOperatorControl() && isEnabled()) {
            driveTrain.drive();
            System.out.println(data.getYaw());
            //data.pingTable();
            Timer.delay(0.05);
        }
    }
    
    @Override
    public void test() {
    	while(isEnabled() && isTest()) {
    		//networkTableTest();
    	// Don't enable the line below unless you know what you are doing
    	//calibrateEncoders();
    	
    	/*while (isEnabled() && isTest()) {
    		networkTableTest();
>>>>>>> branch 'master' of https://github.com/Pigmice2733/SteamWorksSwerve.git
    		Timer.delay(1);
    	}*/
    	}
    }
    
    public void calibrateEncoders() {

		driveTrain.ready(true);
    	
    	Properties properties = new Properties();
    	properties.setProperty("FL", Double.toString(-driveTrain.getEncoderValue(WheelPosition.FrontLeft)));
    	properties.setProperty("FR", Double.toString(-driveTrain.getEncoderValue(WheelPosition.FrontRight)));
    	properties.setProperty("BL", Double.toString(-driveTrain.getEncoderValue(WheelPosition.BackLeft)));
    	properties.setProperty("BR", Double.toString(-driveTrain.getEncoderValue(WheelPosition.BackRight)));

    	try {
			AutoCalibration.saveConfigurationFile(properties);
	    	AutoCalibration.logConfigurationFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static double correctMod(double number, double modulus) {
    	return number < 0 ? number % modulus + modulus : number % modulus;
    }
    
    public void potentiometerCalibration() {
    	driveTrain.printPotentiometers();
    }
    
    public void networkTableTest() {
    	while(isEnabled() && isTest()) {
    		String visionTrackingData = "";
    		data.pingTable();
    		/*visionTrackingData += "Distance: " + dataTransfer.getDistance() + "\n";
    		visionTrackingData += "Direction: " + dataTransfer.getDirection() + "\n";*/
    		
    		visionTrackingData += "Gyro: " + data.getYaw() + "\n";
    		
    		System.out.println(visionTrackingData);
    		
    	}
    }
    }
