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
	    
	    speed = (Math.abs(speed) > 1.0) ? (Math.sin(speed) * 1.0) : speed;
	    
		return speed * ConversionEnum.RANGE_TO_M_PER_S.getConversion();
	}
	
	public double getDirection() {
		return rStick.getDirectionRadians();
	}
	
	public double getRotation() {
		return lStick.getX();
	}
}