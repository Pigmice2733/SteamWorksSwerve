package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.controller.JoystickInput;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;

public class Shooter {
	
	AnalogInput shooterEncoder;
	JoystickInput joy;
	Talon motor;
	Talon ballRelease;
	public double speed = 0;
	public double windowSpeed;
	
	public Shooter(int encoderPort, int motorPort, int servoPort, JoystickInput joy) {
		motor = new Talon(motorPort);
		ballRelease = new Talon(servoPort);
		this.shooterEncoder = new AnalogInput(encoderPort);
		this.joy = joy;
	}
	
	public void shoot() {
		//speed = joy.getRev;
		//servoSpeed = joy.getRelease
		motor.set(speed);
		ballRelease.set(windowSpeed);
	}
} 
