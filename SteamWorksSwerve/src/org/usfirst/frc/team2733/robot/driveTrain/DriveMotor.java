package org.usfirst.frc.team2733.robot.driveTrain;

import com.ctre.CANTalon;

public class DriveMotor {

	private final CANTalon driveMotor;
	
	public DriveMotor(int motorPort){
		driveMotor = new CANTalon(motorPort);
	}
	
	// Get speed to drive at, update PID and motor
	public void update(double speed) {
	    // Convert back from m/s
		driveMotor.set(speed / 3);
	}
	
	public void disable() {
		driveMotor.disable();
	}
}
