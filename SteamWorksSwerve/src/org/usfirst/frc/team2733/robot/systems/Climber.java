package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.JoystickInput.JoyStickButton;

import edu.wpi.first.wpilibj.Talon;

public class Climber {

	Talon motor;
	JoystickInput joy;
	public double speed = 1.0;
	
	public Climber(int motorPort, JoystickInput joy) {
		motor = new Talon(motorPort);
		this.joy = joy;
	}
	
	public void update() {
	    if(joy.isButtonPressed(JoyStickButton.CLIMBER)) {
	        motor.set(speed);
	    } else {
	        motor.disable();
	    }
	}
}
