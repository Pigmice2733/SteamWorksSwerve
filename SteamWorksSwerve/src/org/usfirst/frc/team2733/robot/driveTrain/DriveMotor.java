package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import com.ctre.CANTalon;

public class DriveMotor {

	private final CANTalon driveMotor;
	
	public DriveMotor(int motorPort){
		driveMotor = new CANTalon(motorPort);
	}
	
	// Get speed to drive at, update PID and motor
	public void update(double speed) {
	    // Convert back from m/s
		driveMotor.set(speed / ConversionEnum.RANGE_TO_M_PER_S.getConversion());
	}
	
	public void disable() {
		driveMotor.disable();
	}
}
