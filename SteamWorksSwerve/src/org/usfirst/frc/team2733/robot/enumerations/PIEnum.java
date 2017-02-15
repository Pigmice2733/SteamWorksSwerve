package org.usfirst.frc.team2733.robot.enumerations;

public enum PIEnum {
	
	//P and I coefficients for swerve MODULE_s
	MODULE_DRIVE_P (0.001),
	MODULE_DRIVE_I (0.001),
	MODULE_ROTATION_P (0.001),
	MODULE_ROTATION_I (0.001);
	
	private double coefficient;
	
	PIEnum(double coefficient){
		this.coefficient = coefficient;
	}
	
	public double getCoefficient(){
		return coefficient;
	}
	
	
	
}
