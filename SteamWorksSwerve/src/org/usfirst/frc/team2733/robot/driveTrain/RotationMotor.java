package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;
import org.usfirst.frc.team2733.robot.Robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;

public class RotationMotor {

	private final Spark rotationMotor;
	
	private AnalogPotentiometer analogPoten;
	
	//private final AnalogInput anaInput;
	private final PID PIController;
	
	public RotationMotor(int motorPort, double P, double I, int analogPort, double analogOffset) {
	    rotationMotor = new Spark(motorPort);
		
		analogPoten = new AnalogPotentiometer(analogPort, 1, analogOffset);
		
		PIController = new PID(P, I);
		PIController.setMaxMin(2 * Math.PI, 0);
		PIController.setContinuous(true);
	}
	
	// Get angle to drive at, update PID and motor
	/*
	 * Angle is from 0*Math.PI to 2*Math.PI
	 */
	public void update(double setAngle) {
		double currentAngle = Robot.correctMod(analogPoten.get(), 1) * 2 * Math.PI;
        
		System.out.println("angle = " + currentAngle);
        double motorSpeed = PIController.getVal(currentAngle, setAngle);
        
        rotationMotor.set(motorSpeed);
	}
	
	public void disable() {
		rotationMotor.disable();
	}
	
	public void reset(){
		PIController.reset();
		rotationMotor.set(0);
		
	}
}