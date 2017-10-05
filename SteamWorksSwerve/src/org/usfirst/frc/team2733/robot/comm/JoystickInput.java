package org.usfirst.frc.team2733.robot.comm;

import org.usfirst.frc.team2733.robot.utilities.Modulus;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickInput {

    // Standard joystick, controls speed and direction for swerve,
    //  left speed for tank
    private Joystick joyOne;
    // Standard joystick, controls rotation for swerve, right speed
    //  for tank, shooter
    private Joystick joyTwo;
    
    public JoystickInput(int port0, int port1) {
        joyOne = new Joystick(port0);
        joyTwo = new Joystick(port1);
    }

    // Get the speed value, always positive
    public double getSpeed() {
        double speed = joyOne.getMagnitude();
        
        // Limit to magnitude 1.0
        speed = (speed > 1.0) ? 1.0 : speed;
        
        // Set deadband
        //speed = (speed < 0.025) ? 0 : speed;

        // Squared sensitivity
        speed *= speed;

        // Limit max speed to 0.6
        speed *= 0.8;

        return speed;
    }
    
    /**
     * Get the direction to joystick is pushed, zero is straight ahead, positive
     * is clock-wise
     * 
     * @return The direction in radians
     */
    public double getDirection() {

        // Left of 12 o'clock is -, right is +
        double radians = joyOne.getDirectionRadians();

        // Convert to 0 - 2PI
        radians = Modulus.modulus(radians, 2 * Math.PI);

        // Reverse direction
        return 2*Math.PI - Modulus.modulus(radians + Math.PI, 2 * Math.PI);
    }

    /**
     * Use a third axis as a speed multiplier. Speed multiplier is from 0.5 -
     * 1.0
     * 
     * @return The speed multiplier
     */
    public double getSpeedMulti() {
        double speedMulti = joyOne.getRawAxis(3);

        speedMulti *= -0.5;
        speedMulti += 1.0;
        
        return (speedMulti > 0.1) ? speedMulti : 0.1;
    }

    /**
     * Get rotation - between +1 and -1
     * 
     * @return Rotation
     */
    public double getRotation() {
        double rotationSpeed = joyOne.getTwist();
        
        //Squared sensitivity
        rotationSpeed = Math.pow(rotationSpeed, 3);
        
        // Deadband
        //rotationSpeed = (Math.abs(rotationSpeed) < 0.3) ? 0 : rotationSpeed;
        
        // Scale speed
        rotationSpeed *= 0.02;
        
        return rotationSpeed;
    }

    /**
     * Whether the shooter control is activated
     * 
     * @return boolean, Whether the shooter control is activated
     */
    public boolean getShooter() {
        return joyOne.getTrigger();
    }

    /**
     * Whether the climber control is activated
     * 
     * @return boolean, Whether the climber control is activated
     */
    public boolean getClimber() {
        return joyOne.getRawButton(11);
    }
    
    /**
     * Tank left speed
     * 
     * @return Tank left speed as double
     */
    public double getTankLeft() {
        double leftSpeed = -joyOne.getY();
        
        // Deadband
        leftSpeed = (leftSpeed < 0.08) ? 0 : leftSpeed;
        
        return leftSpeed;
    }

    /**
     * Tank right speed
     * 
     * @return Tank right speed as double
     */
    public double getTankRight() {
        double rightSpeed = -joyTwo.getY();
        
        // Deadband
        rightSpeed = (rightSpeed < 0.08) ? 0 : rightSpeed;
        
        return rightSpeed;
    }
}