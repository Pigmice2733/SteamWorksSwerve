package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.swerve.Point;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Robot extends SampleRobot {
	
	public static DriveTrain driveTrain;
	public NetworkTable networkTable;

	@Override
	protected void robotInit() {
		driveTrain = new DriveTrain();
		networkTable = NetworkTable.getTable("RobotInitalInterface");
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
		// Currently random speed
		double defaultSpeed = 0.2;// If you don't know what to set it to
		
		while (!networkTable.isConnected()) {
			System.out.println("Uhh.. The network table still isn't connected. You should start to panic.");
			Timer.delay(1);
		}
		
		boolean success = false;
		while (success) {
			RobotInitialLocation location = RobotInitialLocation.fromValue((int) networkTable.getNumber("robotInitalLocation", -1));
			if (location == RobotInitialLocation.LEFT || location == RobotInitialLocation.RIGHT) {
				driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0.05 * Math.PI * (location == RobotInitialLocation.LEFT ? -1 : 1)), 0);
				Timer.delay(8.5 / defaultSpeed);
				// TODO: Finish aligning
				success = true;
			} else if (location == RobotInitialLocation.CENTER) {
				driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0), 0);
				Timer.delay(6.5 / defaultSpeed);
				// TODO: Finish aligning
				success = true;
			} else {
				System.out.println("Uhh.. The python script isn't telling us where we are!! PANIC!! I guess we will wait for input from the script.");
				Timer.delay(1);
			}
		}
		
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
    	    driveTrain.drive();
        }
        
    }
}
