package org.usfirst.frc.team2733.robot.enumerations;

public enum PIEnum {
	
	//P and I coefficients for swerve MODULE_s
	MODULE_ROTATION_FRONT_RIGHT_P (1),
	MODULE_ROTATION_FRONT_RIGHT_I (0.015),
	MODULE_ROTATION_FRONT_LEFT_P (1),
	MODULE_ROTATION_FRONT_LEFT_I (0.015),
	MODULE_ROTATION_BACK_RIGHT_P (1),
	MODULE_ROTATION_BACK_RIGHT_I (0.015),
	MODULE_ROTATION_BACK_LEFT_P (1),
	MODULE_ROTATION_BACK_LEFT_I (0.015),
	
	MODULE_DRIVE_P (0.18),
	MODULE_DRIVE_I (0);
	
	private double coefficient;
	
	PIEnum(double coefficient){
		this.coefficient = coefficient;
	}
	
	public double getCoefficient(){
		return coefficient;
	}
}
