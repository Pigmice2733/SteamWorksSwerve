package org.usfirst.frc.team2733.robot.enumerations;

public enum ConversionEnum {
    RELATIVE_ENCODER_PULSES_TO_RADIANS (0.016495291750503),
    // Next 2 values need to be decided on
    DRIVE_SPEED_RANGE_TO_M_PER_S (3),
	ROTATION_SPEED_RANGE_TO_M_PER_S (3),
	ANALOG_POTENTIONOMETER_WHEEL_FRONT_RIGHT(0),// Not correct
	ANALOG_POTENTIONOMETER_WHEEL_BACK_RIGHT(0),// Not correct
	ANALOG_POTENTIONOMETER_WHEEL_FRONT_LEFT(-0.6158361820908574),
	ANALOG_POTENTIONOMETER_WHEEL_BACK_LEFT(-0.9520962983633753);
    
    private double conversion;
    
    ConversionEnum(double conversion){
        this.conversion = conversion;
    }
    
    public double getConversion(){
        return conversion;
    }
}