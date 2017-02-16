package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.enumerations.PIEnum;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;

public class SwerveModule {
	
    // angle needs to always be between 0pi and 2pi
	private double speed, angle;
	
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
		
		switch(wheelPos) {
		case FrontRight:
			portRotation = PortsEnum.FRONT_RIGHT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.FRONT_RIGHT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			break;
		case BackRight:
			portRotation = PortsEnum.BACK_RIGHT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.BACK_RIGHT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.BACK_RIGHT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.BACK_RIGHT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			break;
		case FrontLeft:
			portRotation = PortsEnum.FRONT_LEFT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.FRONT_LEFT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.FRONT_LEFT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.FRONT_LEFT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			break;
		case BackLeft:
			portRotation = PortsEnum.BACK_LEFT_ROTATION_MOTOR.getPort();
			portDrive = PortsEnum.BACK_LEFT_DRIVE_MOTOR.getPort();
			portEncoder1 = PortsEnum.BACK_LEFT_ROTATION_DIGITAL_ENCODER_ONE.getPort();
			portEncoder2 = PortsEnum.BACK_LEFT_ROTATION_DIGITAL_ENCODER_TWO.getPort();
			break;
		default:
			
		}
		driveMotor = new DriveMotor(portDrive);
		rotationMotor = new RotationMotor(portRotation, portEncoder1,
				portEncoder2, PIEnum.MODULE_ROTATION_P.getCoefficient(), PIEnum.MODULE_ROTATION_I.getCoefficient());
		
		this.swerveCalc = swerveCalc;
		this.wheelPos = wheelPos;
	}
	
	//updates the components
	public void update() {
		double targSpeed = swerveCalc.getVelAim(wheelPos);
		double targAngle = swerveCalc.getRotAim(wheelPos);
		
		calcOptimalHeading(targSpeed, targAngle);
		
		driveMotor.update(speed);
		rotationMotor.update(angle);
	}
	
	//disables the motors
	public void disable() {
		driveMotor.disable();
		rotationMotor.disable();
	}
	
	// Calculates correct direction and speed for the motors to turn based on their current speed and direction and the intended speed and direction.
	public void calcOptimalHeading(double targSpeed, double targAngle) {
	    targAngle = targAngle % (2 * Math.PI);
	    
	    double relativeAngle = (targAngle - angle) % (2 * Math.PI);
	    
	    // If the relative angle is in the first 2 quadrants, leave speed and angle alone
	    if ((relativeAngle < 0.5 * Math.PI) || (relativeAngle > 1.5 * Math.PI)) {
            angle = targAngle;
            speed = targSpeed;
        // Otherwise, reverse speed and take shorter angle
        } else {
            angle = (targAngle - Math.PI) % (2 * Math.PI);
            speed = -targSpeed;
        }
	}
}