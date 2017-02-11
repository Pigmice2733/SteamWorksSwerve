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
	}

	/** 
	 * Commands available
	 * 
	 */
	@Override
	public void autonomous() {
		while (isAutonomous() && isEnabled()) {
			networkTable = NetworkTable.getTable("AutoCommand");
			
			String command = networkTable.getString("command", "none");
			
			if (command.equals("movementUpdate")) {
				double speed = networkTable.getNumber("speed", 0);
				double direction = networkTable.getNumber("direction", 0);
				
				double rotation = networkTable.getNumber("rotation", 0);
				
				Point velocityVector = driveTrain.getVelocityVector(speed, direction);
				
				driveTrain.swerveCalc.setAim(velocityVector, rotation);
			} else if (command.equals("")) {
				
			}
			
			Timer.delay(0.05);
		}
	}

    @Override
    public void operatorControl() {
    	driveTrain.drive();
    }

    @Override
    public void test() {
    	
    }
	
}
