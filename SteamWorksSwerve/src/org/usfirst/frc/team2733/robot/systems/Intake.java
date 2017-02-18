package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.controller.JoystickInput;

import edu.wpi.first.wpilibj.Talon;

public class Intake {

	Talon motor;
	JoystickInput joy;
	public double speed = 0.1;
	
	public Intake(int motorPort) {
		motor = new Talon(motorPort);
	}
	
	public void startIntake() { 
		motor.set(speed);
	}
    
    public void stopIntake() { 
        motor.set(0);
    }
}
