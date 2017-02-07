package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class AbsoluteEncoder {
	
	private final AnalogInput analogInput;
	
	private RotationType rotationType = RotationType.Radians;
	
	private double k;
	
	// Pass in the analog channel the encoder is plugged in to.
	// Adjust k till output is correct;
	public AbsoluteEncoder(int inputChannel, int k) {
		analogInput = new AnalogInput(inputChannel);
		this.k = k;
	}
	
	public double getAbsRotation() {
		return analogInput.getValue() * k;
	}
	
	public void setRotationType(RotationType roType) {
		rotationType = roType;
	}
	
	public RotationType getRotationType() {
		return rotationType;
	}
	
	private enum RotationType {
		Degrees,
		Radians
	}
}
