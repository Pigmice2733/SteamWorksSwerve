package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.JoystickInput.JoyStickButton;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Shooter {
	
	Talon motor;
	Talon ballRelease;
	
	JoystickInput joy;
	
	double lastTime;
	
	boolean isShooting;
	boolean isAgitating;
	
	double speed = 0.6;
	double windowSpeed = 0.1;
	
	public Shooter(int motorPort, int servoPort, JoystickInput joy) {
		motor = new Talon(motorPort);
		ballRelease = new Talon(servoPort);
		isShooting = false;
		isAgitating = false;
		lastTime = Timer.getFPGATimestamp();
		this.joy = joy;
	}
	
	public void update () {
	    if (isShooting == false && joy.isButtonPressed(JoyStickButton.SHOOTER)) {
	        lastTime = Timer.getFPGATimestamp();
	        isShooting = true;
	        motor.set(speed);
	    } else if (isAgitating == true && !joy.isButtonPressed(JoyStickButton.SHOOTER)) {
	        lastTime = Timer.getFPGATimestamp();
	        isAgitating = false;
	        ballRelease.disable();
	    } else if (isShooting == true && isAgitating == false && (Timer.getFPGATimestamp() - lastTime) > 0.5){
	        if(joy.isButtonPressed(JoyStickButton.SHOOTER)) {
	            isAgitating = true;
	            ballRelease.set(windowSpeed);
	        } else {
	            isShooting = false;
	            motor.disable();
	        }
	    }
	}
} 
