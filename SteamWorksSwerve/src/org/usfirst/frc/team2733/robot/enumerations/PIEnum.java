package org.usfirst.frc.team2733.robot.enumerations;

public enum PIEnum {
	
	//P and I coefficients for swerve modules
	ModuleOneDriveP (0),
	ModuleOneDriveI (0),
	ModuleOneRotationP (0),
	ModuleOneRotationI (0),
	
	ModuleTwoDriveP (0),   
	ModuleTwoDriveI (0),   
	ModuleTwoRotationP (0),
	ModuleTwoRotationI (0),
	
	ModuleThreeDriveP (0),   
	ModuleThreeDriveI (0),   
	ModuleThreeRotationP (0),
	ModuleThreeRotationI (0),
	
	ModuleFourDriveP (0),   
	ModuleFourDriveI (0),   
	ModuleFourRotationP (0),
	ModuleFourRotationI (0),
	
	ModuleFiveDriveP (0),   
	ModuleFiveDriveI (0),   
	ModuleFiveRotationP (0),
	ModuleFiveRotationI (0),
	
	ModuleSixDriveP (0),   
	ModuleSixDriveI (0),   
	ModuleSixRotationP (0),
	ModuleSixRotationI (0),
	
	ModuleSevenDriveP (0),   
	ModuleSevenDriveI (0),   
	ModuleSevenRotationP (0),
	ModuleSevenRotationI (0),
	
	ModuleEightDriveP (0),   
	ModuleEightDriveI (0),   
	ModuleEightRotationP (0),
	ModuleEightRotationI (0),
	
	ModuleNineDriveP (0),   
	ModuleNineDriveI (0),   
	ModuleNineRotationP (0),
	ModuleNineRotationI (0),
	
	ModuleTenDriveP (0),   
	ModuleTenDriveI (0),   
	ModuleTenRotationP (0),
	ModuleTenRotationI (0);
	
	private double coefficient;
	
	PIEnum(double coefficient){
		this.coefficient = coefficient;
	}
	
	public double getCoefficient(){
		return coefficient;
	}
	
	
	
}
