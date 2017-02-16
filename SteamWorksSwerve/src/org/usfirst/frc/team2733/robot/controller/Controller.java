package org.usfirst.frc.team2733.robot.controller;

public interface Controller {
	
	/**
	 * @return Speed in m/s the robot should go.
	 */
    public double getSpeed();
    
    /**
     * @return The direction of movement in radians. Forward is 0, clockwise is positive.
     */
    public double getDirection();
    
    /**
     * @return The velocity of the rotation of the robot in radians/second, clockwise is positive.
     */
    public double getRotation();
}
