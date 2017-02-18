package org.usfirst.frc.team2733.robot.driveTrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.JoystickInput.JoyStickButton;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.swerve.Vector_Point_Abomination;
import org.usfirst.frc.team2733.robot.systems.Climber;
import org.usfirst.frc.team2733.robot.systems.Intake;
import org.usfirst.frc.team2733.robot.systems.Shooter;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
	
	List<SwerveModule> modules = new ArrayList<SwerveModule>(); // The swerve-wheel-module-things
	
	public static enum DriveMode {
		MAINSWERVE,
		ALTSWERVE,
		TANK,
		DISABLED;
	}
	
	public DriveMode mode;
	
	public JoystickInput joy;
	
	public Gyro gyro;
	
	public SwerveCalc swerveCalc;
	
	private Climber climber;
	private Shooter shooter;
	private Intake intake;
	
	public DriveTrain() {// implementation for swerve

		this.joy = new JoystickInput(PortsEnum.JOYSTICK_ONE.getPort(), PortsEnum.JOYSTICK_TWO.getPort());
		
		swerveCalc = new SwerveCalc(getSwerveDict());
		
		/*climber = new Climber(PortsEnum.CLIMBER.getPort(), joy);
		shooter = new Shooter(PortsEnum.SHOOTER.getPort(), PortsEnum.BALL_RELEASE.getPort(), joy);
		intake = new Intake(PortsEnum.INTAKE.getPort(), joy);*/
		
		modules.add(new SwerveModule(WheelPosition.FrontLeft, swerveCalc));// Adds wheels to a list
		modules.add(new SwerveModule(WheelPosition.FrontRight, swerveCalc));
		modules.add(new SwerveModule(WheelPosition.BackLeft, swerveCalc));
		modules.add(new SwerveModule(WheelPosition.BackRight, swerveCalc));
	
	}
	
	public static Map<WheelPosition, Vector_Point_Abomination> getSwerveDict() {
	    Map<WheelPosition, Vector_Point_Abomination> swerveDict = new HashMap<>();
        swerveDict.put(WheelPosition.FrontLeft, new Vector_Point_Abomination(0.33, 0.33));
        swerveDict.put(WheelPosition.FrontRight, new Vector_Point_Abomination(0.33, -0.33));
        swerveDict.put(WheelPosition.BackLeft, new Vector_Point_Abomination(-0.33, 0.33));
        swerveDict.put(WheelPosition.BackRight, new Vector_Point_Abomination(-0.33, -0.33));
        
        return swerveDict;
	}
	
	public void setMode(DriveMode driveMode) {
		SmartDashboard.putString("mode", driveMode.toString());
        // Re-enable SwerveModules after mode changed from Disabled
        if (mode == DriveMode.DISABLED) {
        	for (SwerveModule mod: modules) {
        		mod.disable();
        	}
        }
    }
	
	public void drive() {
	    climber.update();
		shooter.update();
		intake.update();
		
		// Get degrees, convert to radians
		// TODO: This is gonna be here later because we will have a better gyro and it will be possible then - Xander
        // double headingOffset = Math.toRadians(gyro.getAngle());
		double headingOffset = 0;
        
        double speed = joy.getSpeed();
        
        double direction = joy.getDirection() - headingOffset;
        double rotation = joy.getRotation();
		
		Vector_Point_Abomination velocityVector = getVelocityVector(speed, direction - headingOffset);
		
		SmartDashboard.putNumber("Direction", direction);
        SmartDashboard.putNumber("Speed", speed);
		swerveCalc.setAim(velocityVector, rotation);
		
		for (SwerveModule module : modules) {
			module.update();
		}
	}
	
	public void moveSideways (double speed) {
	    
	    double direction = 0.5 * Math.PI;
	    
	    if(speed < 0) {
	        speed = -speed;
	        direction = -direction;
	    }
	    
	    Vector_Point_Abomination velocityVector = getVelocityVector(speed, direction);
	    
	    swerveCalc.setAim(velocityVector, 0);
	    
	    for (SwerveModule module : modules) {
            module.update();
        }
	}
	
	public void moveForwards (double speed) {
	    
	}
	
	public void stopMovement () {
	    for (SwerveModule module : modules) {
            module.disable();
        }
	}
	
	public static Vector_Point_Abomination getVelocityVector(double speed, double direction) {
        // Align our coordinate system with left-handed Cartesian coordinate system
		direction -= 2 * Math.PI;
		
		Vector_Point_Abomination vector = new Vector_Point_Abomination((Math.sin(direction) * speed), (Math.cos(direction) * speed));
		
		System.out.println(vector.getX() + "  :  " + vector.getY());
		
		return vector;
	}

	public void zeroWheelPositions() {
		for (SwerveModule module : modules) {
			module.zeroPosition();
		}
	}
}
