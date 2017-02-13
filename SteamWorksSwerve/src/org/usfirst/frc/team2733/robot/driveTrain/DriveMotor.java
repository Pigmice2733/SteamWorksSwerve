package org.usfirst.frc.team2733.robot.driveTrain;

import com.ctre.CANTalon;

public class DriveMotor {

	private final CANTalon driveMotor;
	
	public DriveMotor(int motorPort){
		driveMotor = new CANTalon(motorPort);
	}
	
	// Get speed to drive at, update PID and motor
	public void update(double speed) {
		driveMotor.set(speed);
	}
	
	public void disable() {
		driveMotor.disable();
	}
}
