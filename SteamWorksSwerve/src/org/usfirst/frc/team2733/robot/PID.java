package org.usfirst.frc.team2733.robot;

public class PID {

	private double P, I, D;
	
	public PID(double P_coef){//multiple constructors for different implementations of PID
		P = P_coef;
	}
	
	public PID(double P_coef, double I_coef){
		P = P_coef;
		I = I_coef;
	}
	
	public PID(double P_coef, double I_coef, double D_coef){
		P = P_coef;
		I = I_coef;
		D = D_coef;
	}
	
	public double getNextVal(double curVal, double aimVal) {
		double returnVal;
		return 0;
	}
}
