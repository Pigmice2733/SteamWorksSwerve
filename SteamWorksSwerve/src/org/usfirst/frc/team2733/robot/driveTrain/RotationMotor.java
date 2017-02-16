package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;
import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class RotationMotor {

	private final Spark rotationMotor;
	private final Encoder encoder;
	private final PID PIController;
	
	public RotationMotor(int motorPort, int encoderPortA, int encoderPortB, double P, double I){
	    rotationMotor = new Spark(motorPort);
		encoder = new Encoder(encoderPortA, encoderPortB);
		
		// Encoder will output distance as radians
		encoder.setDistancePerPulse(ConversionEnum.RELATIVE_ENCODER_PULSES_TO_RADIANS.getConversion());
		
		PIController = new PID(P, I); 
		PIController.setMaxMin(2 * Math.PI, 0);
		PIController.setContinuous(true);
	}
	
	// Get angle to drive at, update PID and motor
	/*
	 * Angle is from 0*Math.PI to 2*Math.PI
	 */
	public void update(double setAngle) {
		double currentAngle = encoder.getDistance() % (2 * Math.PI);
        setAngle = setAngle % (2 * Math.PI);
        
        double motorSpeed = PIController.getVal(currentAngle, setAngle);
        
        rotationMotor.set(motorSpeed);
	}
	
	public void disable() {
		rotationMotor.disable();
	}
}