package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.controller.JoystickInput;

import edu.wpi.first.wpilibj.Talon;

public class Intake {

	Talon motor;
	JoystickInput joy;
	public double speed = 0;
	
	public Intake(int motorPort) {
		motor = new Talon(motorPort);
	}
	
	public void setIntake() { 
		//speed = joystick.getIntake();
		motor.set(speed);
	}
}
