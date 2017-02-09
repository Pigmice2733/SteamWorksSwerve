package org.usfirst.frc.team2733.robot;

public class PID {

	
	//the coefficients for the PID
	private double P, I, D;
	
	//keeps track of the complexity of the PID; is it using I and D?
	boolean usingI, usingD;
	
	//last frame's value of the currentVal. This is compared to the value given in the getIncrement method
	double lastFrameVal;
	
	//the running total of the success of the movement
	double integral;
	
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

		//zeros global variables used for the I component
		lastFrameVal = 0;
		integral = 0;
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

		//zeros global variables used for the I component
		lastFrameVal = 0;
		integral = 0;
	}
	
	/**
	 * Returns the amount the object running the PID should increment to approach the intended value.
	 * @param currentVal
	 * The current state of the object.
	 * @param aimVal
	 * The intended state of the object
	 * @return
	 */
	public double getIncrement(double currentVal, double aimVal) {
		
		double returnVal = 0;
		
		returnVal += calcP(currentVal, aimVal);
		
		if(usingI){
			returnVal += calcI(currentVal, aimVal);
		}
		
		if(usingD){
			returnVal += calcD(currentVal, aimVal);
		}
		
		return returnVal;
	}
	
	//calculates the P based component of the increment
	private double calcP(double currentVal, double aimVal){
		return (aimVal - currentVal) * P;
	}
	
	//calculates the I based component of the increment
	private double calcI(double currentVal, double aimVal){
		integral += aimVal - (lastFrameVal - currentVal);
		
		lastFrameVal = currentVal;
		return ;
	}
	
	//calculates the D based component of the increment
	private double calcD(double currentVal, double aimVal){
		return 0;
	}
}
