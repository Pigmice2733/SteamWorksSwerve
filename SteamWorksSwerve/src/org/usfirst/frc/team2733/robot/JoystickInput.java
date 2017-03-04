package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.enumerations.ConversionEnum;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInput{
	
	private double lastDirection;
	private Joystick joy;
	
	public JoystickInput(int port0, int port1) {
		joy = new Joystick(port0);
		lastDirection = 0;
	}
	
	public double getSpeed() {
	    double speed = joy.getMagnitude();
	    
	    speed = (Math.abs(speed) < 0.1) ? 0 : speed;
	    
	    speed *= speed;
	    
	    speed = (Math.abs(speed) > 1.0) ? (Math.signum(speed) * 1.0) : speed;
	    
	    // speed *= 0.4;
	    
		return speed * ConversionEnum.DRIVE_SPEED_RANGE_TO_M_PER_S.getConversion();
	}
	
	public double getSpeedMulti() {
		double speedMulti = joy.getRawAxis(3);
		
		speedMulti *= -0.5;
		speedMulti += 1.0;
		
		if(speedMulti > 0.1) {
			return speedMulti;
		} else {
			return 0.1;
		}
		
	}
	
	public double getDirection() {
		double radians = joy.getDirectionRadians();
		
		radians = (radians < 0) ? ((2 * Math.PI) + radians) : radians;
		
		radians = (joy.getMagnitude() < .1) ? lastDirection : radians;
		
		lastDirection = radians;
		
		return (2 * Math.PI) - radians;
	}
	
	public double getRotation() {
	    double rotationSpeed = -joy.getTwist() * getSpeedMulti();
	    //double rotationSpeed = joy2.getRawAxis(0);
		rotationSpeed = (Math.abs(rotationSpeed) < 0.1) ? 0 : rotationSpeed;
		rotationSpeed *= 0.4;
		return rotationSpeed * ConversionEnum.ROTATION_SPEED_RANGE_TO_M_PER_S.getConversion();
	}
	
	public boolean isButtonPressed(JoyStickButton button) {
	    return joy.getRawButton(button.getRawButtonNumber());
	}
	
	public enum JoyStickButton {
	    CLIMBER(2), SHOOTER(1), INTAKE(3);
	    
	    private int rawButtonNumber;
	    
	    private JoyStickButton(int rawButtonNumber) {
	        this.rawButtonNumber = rawButtonNumber;
	    }
	    
	    public int getRawButtonNumber() {
	        return rawButtonNumber;
	    }
	}
}