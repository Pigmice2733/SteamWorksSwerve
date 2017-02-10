package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;

import com.ctre.CANTalon;

public class RotationMotor {

	private final CANTalon rotMotor;
	private final AbsoluteEncoder encoder;
	private final PID piController;
	
	public RotationMotor(int motorPort, int encoderPort, double k, double offSet, double P, double I){
	    rotMotor = new CANTalon(motorPort);
		encoder = new AbsoluteEncoder(encoderPort, k, offSet);
		
		piController = new PID(P, I); 
	}
	
	// Get angle to drive at, update PID and motor
	public void update(double angle) {
		double piVal = piController.getVal(encoder.getAbsRotation(), angle);
		rotMotor.set(piVal);
	}
	
	public void disable() {
		rotMotor.disable();
	}
}