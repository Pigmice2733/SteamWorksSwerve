package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInput {
	
	Joystick lStick;
	Joystick rStick;
	
	public JoystickInput(int port0, int port1) {
		lStick = new Joystick(port0);
		rStick = new Joystick(port1);
	}
	
	public double getSpeed() {
		return lStick.getMagnitude();
	}
	
	public double getDirection() {
		return rStick.getDirectionRadians();
	}
}
 