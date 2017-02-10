package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class DriveMotor {

	private final CANTalon driveMotor;
	private final Encoder encoder;
	private final PID piController;
	
	public DriveMotor(int motorPort, int encoderPortA, int encoderPortB, double P, double I){
		driveMotor = new CANTalon(motorPort);
		encoder = new Encoder(new DigitalInput(encoderPortA), new DigitalInput(encoderPortB));
		// Replace when actual distance per pulse is known
		encoder.setDistancePerPulse(0.000001);
		
		piController = new PID(P, I);
	}
	
	// Get speed to drive at, update PID and motor
	public void update(double speed) {
		double piVal = piController.getVal(encoder.getRate(), speed);
		driveMotor.set(piVal);
	}
}
