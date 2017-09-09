package org.usfirst.frc.team2733.robot.utilities;

import edu.wpi.first.wpilibj.Timer;

public class PID {

    // The coefficients for the PID
    private double P, I, D;

    // Keeps track of the complexity of the PID; is it using I and D?
    boolean usingI, usingD;

    // The running total of the success of the movement
    double errorSum, previousError;

    // System time in seconds at last update
    double lastTime;

    boolean continuous;

    double maxInput, minInput;
    
    double maxOutput, minOutput;
    boolean usingOutputClamp;
    
    /**
     * This is the constructor for a PID object that only uses the Proportional
     * component.
     * 
     * @param P_coef The coefficient for the proportional part of the PID function.
     */
    public PID(double P_coef) {
        P = P_coef;

        // Using neither I nor D
        usingI = false;
        usingD = false;

        usingOutputClamp = false;
    }

    /**
     * This is the constructor for a PID object that uses the Proportional and
     * Integral components.
     * 
     * @param P_coef The coefficient for the proportional part of the PID function.
     * @param I_coef The coefficient for the integral part of the PID function.
     */
    public PID(double P_coef, double I_coef) {
        P = P_coef;
        I = I_coef;

        // Using I but not D
        usingI = true;
        usingD = false;

        // Zeros the I component
        errorSum = 0;

        // Zeroes time
        lastTime = 0;
        
        usingOutputClamp = false;
    }

    /**
     * This is the constructor for a PID object that uses the Proportional,
     * Integral, and Derivative components.
     * 
     * @param P_coef  The coefficient for the proportional part of the PID function.
     * @param I_coef The coefficient for the integral part of the PID function.
     * @param D_coef The coefficient for the derivative part of the PID function.
     */
    public PID(double P_coef, double I_coef, double D_coef) {
        P = P_coef;
        I = I_coef;
        D = D_coef;

        // Using both I and D
        usingI = true;
        usingD = true;

        // Zeros the I component
        errorSum = 0;
        // Zeroes previous error value
        previousError = 0;
        // Zeroes time
        lastTime = 0;
        
        usingOutputClamp = false;
    }

    /**
     * Returns the amount the object running the PID should increment to
     * approach the intended value.
     * 
     * @param currentVal Current value of the process.
     * @param aimVal The set point of the process.
     * @return output Value for process using PID controller
     */
    public double getVal(double currentVal, double aimVal) {
        // Current time in seconds
        double currentTime = Timer.getFPGATimestamp();

        // Time elapsed since last update
        double timeChange = currentTime - lastTime;

        // The current error
        double currentError = getContinuousError(aimVal - currentVal);

        // Proportional term
        double returnVal = (currentError * P);

        // Integral term
        if (usingI) {
            errorSum += (currentError * timeChange);

            if (usingOutputClamp) {
                if ((errorSum * I) > maxOutput) {
                    errorSum = (maxOutput / I);
                } else if ((errorSum * I) < minOutput) {
                    errorSum = (minOutput / I);
                }
            }

            returnVal += errorSum * I;
        }

        if (usingD) {
            double derivative = (currentError - previousError) / timeChange;
            returnVal += derivative * D;
        }

        lastTime = currentTime;
        previousError = currentError;

        if (usingOutputClamp) {
            if (returnVal > maxOutput) {
                returnVal = maxOutput;
            } else if (returnVal < minOutput) {
                returnVal = minOutput;
            }
        }
        
        return returnVal;
    }

    /**
     * Uses maxInput and minInput to loop from one to the other, taking the
     * shortest path to the set point rather than going all the way back through
     * zero.
     * 
     * @param error Current error
     * @return New value for error that will cause the system to take the shortest path.
     */
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

    public void setInputBounds(double maxInput, double minInput) {
        this.maxInput = maxInput;
        this.minInput = minInput;
    }

    public void setOutputBounds(double maxOutput, double minOutput) {
        this.maxOutput = maxOutput;
        this.minOutput = minOutput;
        
        usingOutputClamp = true;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }

    public void reset() {
        previousError = 0;
        errorSum = 0;
        lastTime = 0;
    }
}
