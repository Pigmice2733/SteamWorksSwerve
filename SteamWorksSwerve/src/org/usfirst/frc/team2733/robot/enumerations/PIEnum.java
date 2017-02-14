package org.usfirst.frc.team2733.robot.enumerations;

public enum PIEnum {
	
	//P and I coefficients for swerve MODULE_s
	MODULE_ONE_DRIVE_P (0),
	MODULE_ONE_DRIVE_I (0),
	MODULE_ONE_ROTATION_P (0),
	MODULE_ONE_ROTATION_I (0),
	
	MODULE_TWO_DRIVE_P (0),   
	MODULE_TWO_DRIVE_I (0),   
	MODULE_TWO_ROTATION_P (0),
	MODULE_TWO_ROTATION_I (0),
	
	MODULE_THREE_DRIVE_P (0),   
	MODULE_THREE_DRIVE_I (0),   
	MODULE_THREE_ROTATION_P (0),
	MODULE_THREE_ROTATION_I (0),
	
	MODULE_FOUR_DRIVE_P (0),   
	MODULE_FOUR_DRIVE_I (0),   
	MODULE_FOUR_ROTATION_P (0),
	MODULE_FOUR_ROTATION_I (0),
	
	MODULE_FIVE_DRIVE_P (0),   
	MODULE_FIVE_DRIVE_I (0),   
	MODULE_FIVE_ROTATION_P (0),
	MODULE_FIVE_ROTATION_I (0),
	
	MODULE_SIX_DRIVE_P (0),   
	MODULE_SIX_DRIVE_I (0),   
	MODULE_SIX_ROTATION_P (0),
	MODULE_SIX_ROTATION_I (0),
	
	MODULE_SEVEN_DRIVE_P (0),   
	MODULE_SEVEN_DRIVE_I (0),   
	MODULE_SEVEN_ROTATION_P (0),
	MODULE_SEVEN_ROTATION_I (0),
	
	MODULE_EIGHT_DRIVE_P (0),   
	MODULE_EIGHT_DRIVE_I (0),   
	MODULE_EIGHT_ROTATION_P (0),
	MODULE_EIGHT_ROTATION_I (0),
	
	MODULE_NINE_DRIVE_P (0),   
	MODULE_NINE_DRIVE_I (0),   
	MODULE_NINE_ROTATION_P (0),
	MODULE_NINE_ROTATION_I (0),
	
	MODULE_TEN_DRIVE_P (0),   
	MODULE_TEN_DRIVE_I (0),   
	MODULE_TEN_ROTATION_P (0),
	MODULE_TEN_ROTATION_I (0);
	
	private double coefficient;
	
	PIEnum(double coefficient){
		this.coefficient = coefficient;
	}
	
	public double getCoefficient(){
		return coefficient;
	}
	
	
	
}
