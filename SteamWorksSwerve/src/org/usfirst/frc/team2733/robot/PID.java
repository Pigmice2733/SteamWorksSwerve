package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.Timer;

public class PID {

	
	//the coefficients for the PID
	private double P, I, D;
	
	//keeps track of the complexity of the PID; is it using I and D?
	boolean usingI, usingD;
	
	//the running total of the success of the movement
	double errorSum, previousError;
	
	// System time in ms at last update
	long lastTime;
	
	boolean continuous;
	
	double maxInput, minInput;
	
	/**
	 * This is the constructor for a PID object that only uses the Proportional component.
	 * @param P_coef
	 * The coefficient for the proportional part of the PID function.
	 */
	public PID(double P_coef){
		P = P_coef;
		
		//using neither I nor D
		usingI = false;
		usingD = false;
		
	}
	
	/**
	 * This is the constructor for a PID object that uses the Proportional and Integral components.
	 * @param P_coef
	 * The coefficient for the proportional part of the PID function.
	 * @param I_coef
	 * The coefficient for the integral part of the PID function.
	 */
	public PID(double P_coef, double I_coef){
		P = P_coef;
		I = I_coef;
		
		//using I but not D
		usingI = true;
		usingD = false;

		//zeros global variable used for the I component
		errorSum = 0;
		
		//zeroes time
		lastTime = 0;
	}
	
	/**
	 * This is the constructor for a PID object that uses the Proportional, Integral, and Derivative components.
	 * @param P_coef
	 * The coefficient for the proportional part of the PID function.
	 * @param I_coef
	 * The coefficient for the integral part of the PID function.
	 * @param D_coef
	 * The coefficient for the derivative part of the PID function.
	 */
	public PID(double P_coef, double I_coef, double D_coef){
		P = P_coef;
		I = I_coef;
		D = D_coef;
		
		//using both I and D
		usingI = true;
		usingD = true;

		//zeros global variable used for the I component
		errorSum = 0;
		//zeroes global storing previous error
		previousError = 0;
		//zeroes time
		lastTime = 0;
	}
	
	/**
	 * Returns the amount the object running the PID should increment to approach the intended value.
	 * @param currentVal
	 * The current state of the object.
	 * @param aimVal
	 * The intended state of the object
	 * @return output value for process using PID controller
	 */
	public double getVal(double currentVal, double aimVal) {
		
		double returnVal = 0;
		
		// Current time in ms
		long currentTime = (long) (Timer.getFPGATimestamp() * 1000);
		
		// Time elapsed since last update
		long timeChange = currentTime - lastTime;
		
		// The current error
		double currentError = getContinuousError(aimVal - currentVal);
		
		// Proportional term
		returnVal += (currentError * P);
		
		errorSum += (currentError * timeChange);
		returnVal += errorSum * I;
		
		/*if(usingI){
		    errorSum += (currentError * timeChange);
			returnVal += errorSum * I;
		}
		
		if(usingD){
		    
		    double derivative = (currentError - previousError)/timeChange;
			returnVal += derivative * D;
		} */
		
		lastTime = currentTime;
		previousError = currentError;
		
		return returnVal;
	}
	
	private double getContinuousError(double error) {
	    if (continuous) {
	      if (Math.abs(error) > (maxInput - minInput) / 2) {
	        if (error > 0) {
	          return error - (maxInput - minInput);
	        } else {
	          return error + (maxInput - minInput);
	        }
	      }
	    }
	    
	    return error;
	}
	
	public void setMaxMin(double maxInput, double minInput) {
	    this.maxInput = maxInput;
	    this.minInput = minInput;
	}
	
	public void setContinuous(boolean continuous) {
	    this.continuous = continuous;
	}
	
	public void reset(){
		previousError = 0;
		errorSum = 0;
		lastTime = 0;
	}
}
