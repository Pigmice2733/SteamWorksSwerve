package org.usfirst.frc.team2733.robot.enumerations;

public enum EncoderValues {
    FrontRight(0), BackRight(0), FrontLeft(0), BackLeft(0);

    private double alignmentVal;

    private EncoderValues(double alignmentVal) {
        this.alignmentVal = alignmentVal;
    }

    public double getCoefficient() {
        return alignmentVal;
    }
}
