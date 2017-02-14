package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import edu.wpi.first.wpilibj.Talon;

public class DriveMotor {

	private final Talon driveMotor;
	
	public DriveMotor(int motorPort){
		driveMotor = new Talon(motorPort);
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
