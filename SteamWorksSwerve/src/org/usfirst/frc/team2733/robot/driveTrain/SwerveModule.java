package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.RobotMap;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc.WheelPosition;

public class SwerveModule {
	
	private double currentSpeed, currentAngle;
	
	private final WheelPosition wheelPos;
	
	private final SwerveCalc swerveCalc;
	
	private final DriveMotor driveMotor;
	private final RotationMotor rotationMotor;
	
	public SwerveModule(WheelPosition wheelPos, SwerveCalc swerveCalc) {
		driveMotor = new DriveMotor(RobotMap.driveMotorPorts.get(wheelPos), RobotMap.driveEncoderPorts.get(wheelPos)[0],
				RobotMap.driveEncoderPorts.get(wheelPos)[1], RobotMap.PDrive, RobotMap.IDrive);
		rotationMotor = new RotationMotor(RobotMap.rotateMotorPorts.get(wheelPos), RobotMap.rotationEncoderPorts.get(wheelPos)[0],
				RobotMap.rotationEncoderPorts.get(wheelPos)[1], RobotMap.PRotate, RobotMap.IRotate);
		
		this.swerveCalc = swerveCalc;
		this.wheelPos = wheelPos;
	}
	
	public void update() {
		currentSpeed = swerveCalc.getVelAim(wheelPos);
		currentAngle = swerveCalc.getRotAim(wheelPos);
		driveMotor.update(currentSpeed);
		rotationMotor.update(currentAngle);
	}
	
	public void disable() {
		driveMotor.disable();
		rotationMotor.disable();
	}
}