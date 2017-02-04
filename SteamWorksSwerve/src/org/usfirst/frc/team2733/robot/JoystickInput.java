package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInput {
	
	Joystick lstick;
	Joystick rstick;
	
	public JoystickInput(int port0, int port1) {
		lstick = new Joystick(port0);
		rstick = new Joystick(port1);
	}
	
	public double getSpeed() {
		return lstick.getMagnitude();
	}
	
	public double getDirection() {
		return rstick.getDirectionDegrees();
	}
}
 