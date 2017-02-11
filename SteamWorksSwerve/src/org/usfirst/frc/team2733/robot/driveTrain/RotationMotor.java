package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class RotationMotor {

	private final Spark rotMotor;
	private final Encoder encoder;
	private final PID piController;
	
	public RotationMotor(int motorPort, int encoderPortA, int encoderPortB, double P, double I){
	    rotMotor = new Spark(motorPort);
		encoder = new Encoder(encoderPortA, encoderPortB);
		
		// Encoder will output distance as radians
		encoder.setDistancePerPulse(0.01514020555946888307692840184713);
		
		piController = new PID(P, I); 
		piController.setMaxMin(2 * Math.acos(-1), 0);
		piController.setContinuous(true);
	}
	
	// Get angle to drive at, update PID and motor
	public void update(double angle) {
		double piVal = piController.getVal(encoder.getDistance() % (Math.acos(-1) * 2), angle);
		rotMotor.set(piVal);
	}
	
	public void disable() {
		rotMotor.disable();
	}
}