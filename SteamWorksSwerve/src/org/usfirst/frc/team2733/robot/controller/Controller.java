package org.usfirst.frc.team2733.robot.controller;

public class Controller {
	
	public Controller(){
		
	}
	
	/**
	 * @return non-directional speed intended for the robot
	 */
    public double getSpeed(){
    	return 0;
    }
    
    /**
     * @param direction of velocity to return
     * @return velocity for the specified direction
     */
    public double getVelocity(Direction direction){
    	return 0;
    }
    
    /**
     * @return the direction of movement in radians. forward is 0, clockwise is positive.
     */
    public double getDirection(){
    	return 0;
    }
    
    /**
     * @return the velocity of the rotation of the robot. in radians/second, clockwise is positive
     */
    public double getRotation(){
    	return 0;
    }
    
    public enum Direction{
    	X,
    	Y
    }
}
