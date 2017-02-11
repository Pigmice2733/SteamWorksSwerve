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

	/** 
	 * Commands available
	 * 
	 */
	@Override
	public void autonomous() {
		networkTable = NetworkTable.getTable("RobotInterface");
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
