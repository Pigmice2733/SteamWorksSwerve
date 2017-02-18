package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;
import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import edu.wpi.first.wpilibj.Talon;

public class DriveMotor {

	private final Talon driveMotor;
	
	private double currentSpeed = 0;
	
	private final PID PIController;
	
	public DriveMotor(int motorPort, double P){
		driveMotor = new Talon(motorPort);
		PIController = new PID(P);
	}
	
	// Get speed to drive at, update PID and motor
	public void update(double speed) {
		currentSpeed = PIController.getVal(currentSpeed, speed);
		
		System.out.println("current speed: " + currentSpeed / ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion());
		
	    // Convert back from m/s
		driveMotor.set(currentSpeed / ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion());
	}
	
	public void disable() {
		driveMotor.disable();
	}
}
