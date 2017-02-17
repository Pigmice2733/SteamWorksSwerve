package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;
import org.usfirst.frc.team2733.robot.enumerations.PIEnum;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc.AngleSpeedObject;

public class SwerveModule {
	
    // angle needs to always be between 0pi and 2pi
	private double speed, angle = 0;
	
	private final WheelPosition wheelPos;
	
	private final SwerveCalc swerveCalc;
	
	private final DriveMotor driveMotor;
	private final RotationMotor rotationMotor;
	
	/**
	 * Holds the motor objects and calculates the optimal movements.
	 * @param wheelPos
	 * @param swerveCalc
	 */
	public SwerveModule(WheelPosition wheelPos, SwerveCalc swerveCalc) {
		int portRotation = -1;
		int portDrive = -1;
		int portEncoder1 = -1;
		int portEncoder2 = -1;
		double analogOffset = 0;
		
		switch(wheelPos) {
		case FrontRight:
			portRotation = PortsEnum.FRONT_RIGHT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.FRONT_RIGHT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_FRONT_RIGHT.getConversion();
			break;
		case BackRight:
			portRotation = PortsEnum.BACK_RIGHT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.BACK_RIGHT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.BACK_RIGHT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.BACK_RIGHT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_BACK_RIGHT.getConversion();
			break;
		case FrontLeft:
			portRotation = PortsEnum.FRONT_LEFT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.FRONT_LEFT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.FRONT_LEFT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.FRONT_LEFT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_FRONT_LEFT.getConversion();
			break;
		case BackLeft:
			portRotation = PortsEnum.BACK_LEFT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.BACK_LEFT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.BACK_LEFT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.BACK_LEFT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_BACK_LEFT.getConversion();
			break;
		default:
			
		}
		driveMotor = new DriveMotor(portDrive);
		rotationMotor = new RotationMotor(portRotation, portEncoder1,
				portEncoder2, PIEnum.MODULE_ROTATION_P.getCoefficient(), 
				PIEnum.MODULE_ROTATION_I.getCoefficient(), 0,
				analogOffset);
		
		this.swerveCalc = swerveCalc;
		this.wheelPos = wheelPos;
	}
	
	//updates the components
	public void update() {
		double targSpeed = swerveCalc.getVelAim(wheelPos);
		double targAngle = swerveCalc.getRotAim(wheelPos);
		
		AngleSpeedObject angleSpeedObject = swerveCalc.calcOptimalHeading(targSpeed, targAngle, angle);
		angle = angleSpeedObject.getAngle();
		speed = angleSpeedObject.getSpeed();
		
		driveMotor.update(speed);
		rotationMotor.update(angle);
	}
	
	//disables the motors
	public void disable() {
		driveMotor.disable();
		rotationMotor.disable();
	}
	
	// For Testing Purpose
	// START
	public double getSpeed() {
	    return speed;
	}
	
	public double getAngle() {
	    return angle;
	}
	// END
	
	public void reset(){
		rotationMotor.reset();
	}
}