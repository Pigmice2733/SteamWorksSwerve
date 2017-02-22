package org.usfirst.frc.team2733.robot.enumerations;

public enum ShooterEnum {
	
	HEIGHT_OFFSET (0),//y
	GRAVITY (9.81),//g
	THETA (1.396263);//0
	
	private double variable;
	
	ShooterEnum(double port){
		this.variable = port;
	}
	
	public double getPort(){
		return variable;
	}
	
}
