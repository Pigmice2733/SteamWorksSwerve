package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.PID;
import org.usfirst.frc.team2733.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class RotationMotor {

	private final CANTalon rotationMotor;
	
	private AnalogPotentiometer analogPoten;
	
	double motorSpeed;
	
	//private final AnalogInput anaInput;
	private final PID PIController;
	
	public RotationMotor(int motorPort, double P, double I, int analogPort, double analogOffset) {
	    rotationMotor = new CANTalon(motorPort);
		
		analogPoten = new AnalogPotentiometer(analogPort, 1, analogOffset);
		
		PIController = new PID(P, I);
		PIController.setMaxMin(2 * Math.PI, 0);
		PIController.setContinuous(true);
		
		motorSpeed = 0;
	}
	
	// Get angle to drive at, update PID and motor
	/*
	 * Angle is from 0*Math.PI to 2*Math.PI
	 */
	public void update(double setAngle) {
		double currentAngle = Robot.correctMod(analogPoten.get(), 1) * 2 * Math.PI;
        
        motorSpeed = PIController.getVal(currentAngle, setAngle);
        
        rotationMotor.set(motorSpeed);
	}
	
	public void disable() {
		rotationMotor.disable();
	}

    public void delete() {
        rotationMotor.delete();
        analogPoten.free();
    }
	
	public void reset() {
		PIController.reset();
		rotationMotor.set(0);
	}
	
	public void printEncoder(String wheelPos) {
		System.out.println(wheelPos + Robot.correctMod(analogPoten.get(), 1));
	}
	
	public double getPoten() {
		return analogPoten.get();
	}
}