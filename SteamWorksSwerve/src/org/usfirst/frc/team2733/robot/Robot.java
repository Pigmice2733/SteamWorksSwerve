package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.driveTrain.AbsoluteEncoder;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.swerve.Point;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Robot extends SampleRobot {
	
	public static DriveTrain driveTrain;
	public AbsoluteEncoder enc;
	public NetworkTable networkTable;

	@Override
	protected void robotInit() {
		driveTrain = new DriveTrain();
		enc = new AbsoluteEncoder(0, 0, 0);
	}
	
	public enum RobotInitialLocation {
		UNKNOWN(0), LEFT(1), CENTER(2), RIGHT(3);// If the robot is on the left, then it is left of the tower. (if you are looking from behind the robot)
		
		private int location;
		
		RobotInitialLocation(int location) {
			this.location = location;
		}
		
		public int getValue() {
			return location;
		}
		
		public static RobotInitialLocation fromValue(int value) {
			switch(value) {
			case -1:
				return UNKNOWN;
			case 0:
				return LEFT;
			case 1:
				return CENTER;
			default:
				return UNKNOWN;
			}
		}
		
	}

	/** 
	 * Commands available
	 * 
	 */
	@Override
	public void autonomous() {
		networkTable = NetworkTable.getTable("RobotInitalInterface");
		RobotInitialLocation location = RobotInitialLocation.fromValue((int) networkTable.getNumber("robotInitalLocation", -1));
		if (location == RobotInitialLocation.LEFT || location == RobotInitialLocation.RIGHT) {
			// driveTrain.swerveCalc
		} else if (location == RobotInitialLocation.CENTER) {
			
		} else {
			// Uh oh
			// This should never happen
		}
		
		networkTable = NetworkTable.getTable("RobotInterface");
		
		// -10 degrees and 28ft or 336 inches
		// Tires are 4in in diameter
		while (isAutonomous() && isEnabled()) {
			// Movement Update
			double speed = networkTable.getNumber("speed", 0);
			double direction = networkTable.getNumber("direction", 0);
			double rotation = networkTable.getNumber("rotation", 0);
			
			Point velocityVector = driveTrain.getVelocityVector(speed, direction);
			
			driveTrain.swerveCalc.setAim(velocityVector, rotation);
			
			// Ball Shooter - If necessary
			// double ballShooterSpeed = networkTable.getNumber("shooterSpeed", 0);
			
			
			Timer.delay(0.05);
		}
	}
	
    @Override
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
    	    //driveTrain.drive();
        	double rot = networkTable.getNumber("rotation", 0);
        	rot = enc.getAbsRotation();
        }
        
    }
}
