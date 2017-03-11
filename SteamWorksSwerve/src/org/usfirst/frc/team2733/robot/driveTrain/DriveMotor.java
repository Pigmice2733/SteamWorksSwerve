package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;
import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import com.ctre.CANTalon;

public class DriveMotor {

	private final CANTalon driveMotor;
	
	private double currentSpeed;
	
	private final PID PIController;
	
	public DriveMotor(int motorPort, double P, double I){
		driveMotor = new CANTalon(motorPort);
		PIController = new PID(P, I);
		
		currentSpeed = 0;
	}
	
	// Get speed to drive at, update PID and motor
	public void update(double speed) {
		currentSpeed += PIController.getVal(currentSpeed, speed);
		
		System.out.println("current speed: " + currentSpeed / ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion());
		
	    // Convert back from m/s
		driveMotor.set(currentSpeed / ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion());
	}
	
	public void disable() {
		driveMotor.disable();
	}
	
	public void delete() {
	    driveMotor.delete();
	}
}
