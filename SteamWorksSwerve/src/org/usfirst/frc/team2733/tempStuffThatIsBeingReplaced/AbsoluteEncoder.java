package org.usfirst.frc.team2733.tempStuffThatIsBeingReplaced;

import edu.wpi.first.wpilibj.AnalogInput;

public class AbsoluteEncoder {
	
	private final AnalogInput analogInput;
	
	private RotationType rotationType = RotationType.Radians;
	
	private double k, offset;
	
	// Pass in the analog channel the encoder is plugged in to.
	// Adjust k till output is correct;
	public AbsoluteEncoder(int inputChannel, int k, int offset) {
		analogInput = new AnalogInput(inputChannel);
		this.k = k;
		this.offset = offset;
	}
	
	public double getAbsRotation() {
		return (analogInput.getAverageValue() * k) + offset;
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
