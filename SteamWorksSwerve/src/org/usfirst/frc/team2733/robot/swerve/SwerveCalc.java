package org.usfirst.frc.team2733.robot.swerve;

import java.awt.Point;
import java.util.Dictionary;

public class SwerveCalc {

	private Dictionary wheelPositions;
	
	public SwerveCalc(Dictionary wheelPositions){
		this.wheelPositions = wheelPositions;
	}
	
	/**
	 * Sets the aim of the swerve system

	 * @param velocityX
	 * in meters/second
	 * @param velocityY
	 * in meters/second
	 * @param rotation
	 * in radians/second (clockwise is positive)
	 * @param centerOfRotation
	 * x/y coordinates of the point to rotate around
	 */
	public void setAim(Point velocityVector, double rotation, Point centerOfRotation){
		internalSetAim(velocityVector, rotation, centerOfRotation);
	}
	
	/**
	 * Sets the aim of the swerve system
	 * @param velocityX
	 * in meters/second
	 * @param velocityY
	 * in meters/second
	 * @param rotation
	 * in radians/second (clockwise is positive)
	 */
	public void setAim(Point velocityVector, double rotation){
		internalSetAim(velocityVector, rotation, new Point(0, 0));
	}
	
	//internal method with the real math
	private void internalSetAim(Point velocityVector, double rotation, Point centerOfRotation){
		//holds the locations of the wheels in relationship to the center of rotation
		double wheelLocX = centerOfRotation.getX() + (double)wheelPositions.get("FrontRightX");
		double wheelLocY = centerOfRotation.getY() + (double)wheelPositions.get("FrontRightY");
		
		//these badly named variables are simply middle steps to make the math easier to think about. They are used in the later step
		double Wxi = velocityVector.getX() + (rotation*wheelLocX);
		double Wyi = velocityVector.getY() - (rotation*wheelLocY);
		
		//calculate the aims
		aimSpeed = Math.sqrt(Math.pow(Wxi, 2) + Math.pow(Wyi, 2));
		aimDir = Math.atan2(Wxi, Wyi);
		
	}
	
	public enum WheelPosition{
		FrontRight,
		BackRight,
		FrontLeft,
		BackLeft
	}
}
