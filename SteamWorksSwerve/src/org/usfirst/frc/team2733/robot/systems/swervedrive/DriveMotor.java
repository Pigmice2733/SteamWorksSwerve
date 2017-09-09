package org.usfirst.frc.team2733.robot.systems.swervedrive;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;
import org.usfirst.frc.team2733.robot.utilities.PID;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Drive motor controller
 */
public class DriveMotor {

    // Drive motor
	private final SpeedController driveMotor;
	
	private double currentSpeed;
	
	// PID controller
	private final PID PIController;
	
	/**
	 * Drive motor constructor
	 * @param motorPort Drive motor port
	 * @param P P coefficient for PID controller
	 * @param I I coefficient for PID controller
	 */
	public DriveMotor(SpeedController speedController, double P, double I){
		driveMotor = speedController;
		
		PIController = new PID(P, I);
		PIController.setOutputBounds(1.0, -1.0);
		
		currentSpeed = 0;
	}
	
	/**
	 * Set target speed and apply PID update
	 * @param speed The motor speed to drive motor at
	 */
	public void update(double speed) {
		currentSpeed += PIController.getVal(currentSpeed, speed);
		
		driveMotor.set(currentSpeed / ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion());
	}
	
	/**
	 * Stop drive motor
	 */
	public void stop() {
		currentSpeed = 0;
		driveMotor.disable();
	}
	
	/**
	 * Free all resources
	 */
	public void free() {
	}
}
