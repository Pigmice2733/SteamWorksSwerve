package org.usfirst.frc.team2733.robot.systems;

import edu.wpi.first.wpilibj.Talon;

public class Shooter {
	
	Talon motor;
	Talon ballRelease;
	public double speed = 0.6;
	public double windowSpeed = 0.1;
	
	public Shooter(int motorPort, int servoPort) {
		motor = new Talon(motorPort);
		ballRelease = new Talon(servoPort);
	}
	
	public void startShooting() {
		motor.set(speed);
		ballRelease.set(windowSpeed);
	}
	
	public void stopShooting() {
	    motor.set(0);
	    ballRelease.set(0);
	}
} 
