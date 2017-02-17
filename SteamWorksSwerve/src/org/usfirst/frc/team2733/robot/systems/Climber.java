package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.controller.JoystickInput;

import edu.wpi.first.wpilibj.Talon;

public class Climber {

	Talon motor;
	JoystickInput joy;
	public double speed = 0;
	
	public Climber(int motorPort) {
		motor = new Talon(motorPort);
	}
	
	public void setClimber() {
		//double speed = joy.getClimber();
		 motor.set(speed);
	}
	
}
