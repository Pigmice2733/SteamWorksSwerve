package org.usfirst.frc.team2733.robot.controller;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInput extends Controller{
	
	Joystick lStick;
	Joystick rStick;
	
	public JoystickInput(int port0, int port1) {
		lStick = new Joystick(port0);
		rStick = new Joystick(port1);
	}
	
	@Override
	public double getVelocity(Direction direction){
	    double vel = 0;
	    
	    if (direction == Direction.X){
	        vel = rStick.getRawAxis(0);
	    } else if (direction == Direction.Y){
	        vel = rStick.getRawAxis(1);
	    }
	    
	    return vel * ConversionEnum.RANGE_TO_M_PER_S.getConversion();
	}
	
	@Override
	public double getDirection() {
		return rStick.getDirectionRadians();
	}
	
	@Override
	public double getRotation() {
		return (lStick.getRawAxis(0) < .1) ? 0 : lStick.getRawAxis(0);
	}
}