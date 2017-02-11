package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;

import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	
	public static DriveTrain swerveDrive;
	
	@Override
	protected void robotInit() {
		swerveDrive = new DriveTrain();
	}

	@Override
	public void autonomous() {
		
	}

    @Override
    public void operatorControl() {
    	swerveDrive.drive();
    }

    @Override
    public void test() {
    	
    }
	
}
