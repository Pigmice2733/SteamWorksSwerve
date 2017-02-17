package org.usfirst.frc.team2733.robot.controller;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInput implements Controller{
	
	Joystick lStick;
	Joystick rStick;
	
	public JoystickInput(int port0, int port1) {
		lStick = new Joystick(port0);
		rStick = new Joystick(port1);
	}
	
	public double getSpeed() {
	    double speed = lStick.getMagnitude();
	    
	    speed = (Math.abs(speed) < 0.1) ? 0 : speed;
	    
	    speed *= speed;
	    
	    speed = (Math.abs(speed) > 1.0) ? (Math.signum(speed) * 1.0) : speed;
	    
	    speed *= 0.5;
	    
		return speed * ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion();
	}
	
	public double getDirection() {
		double radians = lStick.getDirectionRadians();
		
		radians = (radians < 0) ? ((2 * Math.PI) + radians) : radians;
		
		return (2 * Math.PI) - radians;
	}
	
	public double getRotation() {
	    double rotationSpeed = rStick.getRawAxis();
		rotationSpeed = (Math.abs(rotationSpeed) < 0.1) ? 0 : rStick.getRawAxis(0);
		return rotationSpeed * ConversionEnum.ROTATION_SPEED_RANGE_TO_M_PER_S.getConversion();
	}
}