package org.usfirst.frc.team2733.robot.comm;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import org.usfirst.frc.team2733.robot.utilities.Modulus;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class DriveStation {
	
	private final Joystick driverJoyOne;
	private final XboxController driverJoyTwo;
	
	public double offset = 0;
	static boolean lastHeldLeft = false;
	static boolean lastHeldRight = false;
	static boolean lastHeldUpSpeed = false;
	static boolean lastHeldDownSpeed = false;
	
	static boolean triggerLeft = false;
	static boolean triggerRight = false;
	static boolean triggerUpSpeed = false;
	static boolean triggerDownSpeed = false;
	
	public DriveStation(int port0, int port1) {
		driverJoyOne = new Joystick(port0);
		driverJoyTwo = new XboxController(port1);
	}
	
	/**
	 * Get magnitude of vector between the origin and the joystick
	 * @return The magnitude, always from 0.0 - 1.0
	 */
	public double getMagnitude() {
	    double magnitude = Math.sqrt(Math.pow(driverJoyOne.getX(), 2) + Math.pow(driverJoyOne.getY(), 2));
	    
	    // Dead band
	    magnitude = (Math.abs(magnitude) < 0.1) ? 0 : magnitude;
	    
	    // Squared sensitivity
	    magnitude *= magnitude;
	    
	    // Limit joystick to circle, cut off values above 1.0
	    magnitude = (magnitude > 1.0) ? 1.0 : magnitude;
	    
	    return magnitude;
	}
	
	/**
	 * Use a third axis as a speed multiplier.  Speed multiplier is from 0.5 - 1.0
	 * @return The speed multiplier
	 */
	public double getSpeedMulti() {
		double speedMulti = driverJoyOne.getRawAxis(3);
		
		speedMulti *= -0.5;
		speedMulti += 1.0;
		
		return speedMulti;
	}
	
	/**
	 * Get the direction to joystick is pushed, zero is straight ahead, positive is clock-wise
	 * @return The direction in radians
	 */
	public double getDirection() {
	    
	    // Left of 12 o'clock is -, right is +
		double radians = driverJoyOne.getDirectionRadians();

		// Convert to 0 - 2PI
        radians = Modulus.modulus(radians, 2 * Math.PI);

        // Reverse direction
		return (2 * Math.PI) - radians;
	}
	
	/** 
	 * Get the rotation of the joystick
	 * @return The rotation of the joystick
	 */
	public double getRotation() {
	    
	    double rotationSpeed = -driverJoyOne.getTwist() * getSpeedMulti();
	      
	    // Dead band
	    //rotationSpeed = (Math.abs(rotationSpeed) < 0.5) ? 0 : (rotationSpeed - (0.5) * Math.signum(rotationSpeed));
	    rotationSpeed = (Math.abs(rotationSpeed) < 0.15) ? 0: rotationSpeed;
	    
	    rotationSpeed *= 0.1;
	    
	    return rotationSpeed;
	}
	
	public boolean getShooter1() {
	    return driverJoyTwo.getBumper(Hand.kLeft);
	}
	
	public double getClimber() {
	    if(driverJoyTwo.getTriggerAxis(Hand.kLeft) >= 0.1) {
	        return driverJoyTwo.getTriggerAxis(Hand.kLeft);
	    } else if (driverJoyTwo.getTriggerAxis(Hand.kRight) >= 0.1) {
	        return -driverJoyTwo.getTriggerAxis(Hand.kRight);
	    } else {
	        return 0;
	    }
    }
	public boolean getIntake() {
	    return driverJoyTwo.getXButton();
    }
	
    public boolean getBallRelease() {
        return driverJoyTwo.getBumper(Hand.kRight);
    }
    
    /**
     * Testing for the button press for incrementing the power of the shooter
     * Only Reads true once per button press (doesn't return true every time it sees the button is pressed)
     * @return
     */
    public boolean getUpPower() {
    	if(driverJoyOne.getRawButton(7) && !lastHeldUpSpeed) {
    		triggerUpSpeed = true;
    		lastHeldUpSpeed = true;
    	} else if(driverJoyOne.getRawButton(7) && lastHeldUpSpeed){
    		triggerUpSpeed = false;
    		lastHeldUpSpeed = true;
    	} else {
    		triggerUpSpeed = false;
    		lastHeldUpSpeed = false;
    	}
    	
    	return triggerUpSpeed;
    }
    
    /**
     * Testing for the button press for incrementing the power of the shooter
     * Only Reads true once per button press (doesn't return true every time it sees the button is pressed)
     * @return
     */
    public boolean getDownPower() {
    	if(driverJoyOne.getRawButton(9) && !lastHeldDownSpeed) {
    		triggerDownSpeed = true;
    		lastHeldDownSpeed = true;
    	} else if(driverJoyOne.getRawButton(9) && lastHeldDownSpeed){
    		triggerDownSpeed = false;
    		lastHeldDownSpeed = true;
    	} else {
    		triggerDownSpeed = false;
    		lastHeldDownSpeed = false;
    	}
    	
    	return triggerUpSpeed;
    }
    
    /**
     * Testing for the button press for incrementing the rotation of the Gyro
     * Only Reads true once per button press (doesn't return true every time it sees the button is pressed)
     * @return
     */
    public boolean getRightOffset() {
    	if(driverJoyOne.getRawButton(6) && !lastHeldRight) {
    		triggerRight = true;
    		lastHeldRight = true;
    	} else if(driverJoyOne.getRawButton(6) && lastHeldRight){
    		triggerRight = false;
    		lastHeldRight = true;
    	} else {
    		triggerRight = false;
    		lastHeldRight = false;
    	}
    	
    	return triggerRight;
    }
    
    /**
     * Testing for the button press for incrementing the rotation of the Gyro
     * Only Reads true once per button press (doesn't return true every time it sees the button is pressed)
     * @return
     */
    public boolean getLeftOffset() {
    	if(driverJoyOne.getRawButton(5) && !lastHeldLeft) {
    		triggerLeft = true;
    		lastHeldLeft = true;
    	} else if(driverJoyOne.getRawButton(5) && lastHeldLeft){
    		triggerLeft = false;
    		lastHeldLeft = true;
    	} else {
    		triggerLeft = false;
    		lastHeldLeft = false;
    	}
    	return triggerLeft;
    }
    
    public double getGyroReset(double gyroValue) {
        if(driverJoyOne.getRawButton(8)) {
            offset -= gyroValue;
            System.out.println("Reseting Gyro Value from  " + gyroValue + "  to 0");
            return offset;
        } else {
            return 0;
        }
    }
    
    /**
     * Increments the Power of the Gyro
     *  May need to be moved to its own gyro class
     * @return
     */
    public double getManualGyroOffset() {
    	offset += (getLeftOffset())? 0.5/Math.PI : 0;
    	offset -= (getRightOffset())? 0.5/Math.PI : 0;
    	
    	return offset;
    }
    
    public boolean getGyroCalibration() {
    	return driverJoyOne.getRawButton(11);
    }
    
    public boolean getGyroZero() {
    	return driverJoyOne.getRawButton(12);
    }
    
    public boolean getTrigger() {
    	return driverJoyOne.getRawButton(2);
    }
	
    public boolean getEncoder(int e) {
    	if (e == 0){
        	return driverJoyOne.getRawButton(10);
    	} else if (e == 1){
    		return driverJoyOne.getRawButton(3);
    	} else if (e == 2){
    		return driverJoyOne.getRawButton(4);
    	} else {
    		return false;
    	}
    }
}