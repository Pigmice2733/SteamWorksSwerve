package org.usfirst.frc.team2733.robot.driveTrain;

import edu.wpi.first.wpilibj.AnalogInput;

public class AbsoluteEncoder {
	
	private final AnalogInput analogInput;
	
	private RotationType rotationType = RotationType.Radians;
	
	private double k, offset;
	
	// Pass in the analog channel the encoder is plugged in to.
	// Adjust k till output is correct;
	public AbsoluteEncoder(int inputChannel, double k, double offset) {
		analogInput = new AnalogInput(inputChannel);
		this.k = k;
		this.offset = offset;
	}
	
	/**
	 * Gets the rotational value of the encoder.
	 * @return The value of the rotation in either radians or degrees.
	 */
	public double getAbsRotation() {
		double radians = analogInput.getAverageValue() * k + offset;
		if (rotationType == RotationType.Radians){
			return radians;
		} else {
			return Math.toDegrees(radians);
		}
	}
	
	public void setRotationType(RotationType roType) {
		rotationType = roType;
	}
	
	public RotationType getRotationType() {
		return rotationType;
	}
	
	public enum RotationType {
		Degrees,
		Radians
	}
}
