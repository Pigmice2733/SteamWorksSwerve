package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.RobotMap;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc.WheelPosition;

public class SwerveModule {
	
	private double speed, angle;
	
	private final WheelPosition wheelPos;
	
	private final SwerveCalc swerveCalc;
	
	private final DriveMotor driveMotor;
	private final RotationMotor rotationMotor;
	
	public SwerveModule(WheelPosition wheelPos, SwerveCalc swerveCalc) {
		driveMotor = new DriveMotor(RobotMap.driveMotorPorts.get(wheelPos));
		rotationMotor = new RotationMotor(RobotMap.rotateMotorPorts.get(wheelPos), RobotMap.rotationEncoderPorts.get(wheelPos)[0],
				RobotMap.rotationEncoderPorts.get(wheelPos)[1], RobotMap.PRotate, RobotMap.IRotate);
		
		this.swerveCalc = swerveCalc;
		this.wheelPos = wheelPos;
	}
	
	public void update() {
		double targSpeed = swerveCalc.getVelAim(wheelPos);
		double targAngle = swerveCalc.getRotAim(wheelPos);
		
		calcOptimalHeading(targSpeed, targAngle);
		
		driveMotor.update(speed);
		rotationMotor.update(angle);
	}
	
	// -4151.0, -2082.75, -8300
	// 415
	
	public void disable() {
		driveMotor.disable();
		rotationMotor.disable();
	}
	
	public void calcOptimalHeading(double targSpeed, double targAngle) {
		if(Math.abs((targAngle - angle)) > 90.0) {
			angle = (targAngle - (Math.acos(-1))) % (2 * Math.acos(-1));
			speed = -targSpeed;
		} else {
			angle = targAngle;
			speed = targSpeed;
		}
		
	}
}