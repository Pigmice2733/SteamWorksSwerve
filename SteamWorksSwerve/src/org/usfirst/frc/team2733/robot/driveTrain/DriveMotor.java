package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;

import edu.wpi.first.wpilibj.CANTalon;

public class DriveMotor {

	private final CANTalon driveMotor;
	private final AbsoluteEncoder encoder;
	private final PID piController;
	
	public DriveMotor(int motorPort, int encoderPort, double k, double offSet, double P, double I){
		driveMotor = new CANTalon(motorPort);
		encoder = new AbsoluteEncoder(encoderPort, k, offSet);
		piController = new PID(P, I); 
	}
	
	public void setSpeed(double newSpeed) {
		piController.setPoint(newSpeed);
	}
	
	public void update() {
		double piVal = piController.getVal(encoder.getAbsRotation());
		driveMotor.set(piVal);
	}
}
