package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;
import org.usfirst.frc.team2733.robot.enumerations.PIEnum;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc.AngleSpeedObject;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

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
		int analogPort = -1;
		double analogOffset = 0;
		
		switch(wheelPos) {
		case FrontRight:
			portRotation = PortsEnum.FRONT_RIGHT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.FRONT_RIGHT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogPort = PortsEnum.FRONT_RIGHT_ROTATION_ANALOG_ENCODER.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_FRONT_RIGHT.getConversion();
			break;
		case BackRight:
			portRotation = PortsEnum.BACK_RIGHT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.BACK_RIGHT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.BACK_RIGHT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.BACK_RIGHT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogPort = PortsEnum.BACK_RIGHT_ROTATION_ANALOG_ENCODER.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_BACK_RIGHT.getConversion();
			break;
		case FrontLeft:
			portRotation = PortsEnum.FRONT_LEFT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.FRONT_LEFT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.FRONT_LEFT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.FRONT_LEFT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogPort = PortsEnum.FRONT_LEFT_ROTATION_ANALOG_ENCODER.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_FRONT_LEFT.getConversion();
			break;
		case BackLeft:
			portRotation = PortsEnum.BACK_LEFT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.BACK_LEFT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.BACK_LEFT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.BACK_LEFT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			analogPort = PortsEnum.BACK_LEFT_ROTATION_ANALOG_ENCODER.getPort();
			analogOffset = ConversionEnum.ANALOG_POTENTIONOMETER_WHEEL_BACK_LEFT.getConversion();
			break;
		default:
			
		}
		
		PIEnum P_Coef;
		PIEnum I_Coef;
		
		switch (wheelPos) {
		case FrontRight:
			P_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_P;
			I_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_I;
			break;
		case FrontLeft:
			P_Coef = PIEnum.MODULE_ROTATION_FRONT_LEFT_P;
			I_Coef = PIEnum.MODULE_ROTATION_FRONT_LEFT_I;
			break;
		case BackRight:
			P_Coef = PIEnum.MODULE_ROTATION_BACK_RIGHT_P;
			I_Coef = PIEnum.MODULE_ROTATION_BACK_RIGHT_I;
			break;
		case BackLeft:
			P_Coef = PIEnum.MODULE_ROTATION_BACK_LEFT_P;
			I_Coef = PIEnum.MODULE_ROTATION_BACK_LEFT_I;
			break;
		default:
			P_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_P;
			I_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_I;
			break;
		}
		
		driveMotor = new DriveMotor(portDrive, PIEnum.MODULE_DRIVE_P.getCoefficient(), PIEnum.MODULE_DRIVE_I.getCoefficient());
		rotationMotor = new RotationMotor(portRotation, P_Coef.getCoefficient(), 
				I_Coef.getCoefficient(), analogPort,
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

	public void zeroPosition() {
		driveMotor.update(0);
		rotationMotor.update(0);
	}
	
	public void printEncoderValue(){
		rotationMotor.printEncoder(wheelPos.toString() + ": ");
	}
	public void printActualEncoderValue(){
		rotationMotor.printEncoder(wheelPos.toString() + ": ");
	}
	
}