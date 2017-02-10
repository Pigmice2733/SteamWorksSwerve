package org.usfirst.frc.team2733.robot.swerve;

import java.awt.Point;
import java.util.Dictionary;

public class SwerveCalc {

	private Dictionary<WheelPosition, Point> wheelPositions;
	
	private Dictionary<WheelPosition, Point> wheelAims;
	
	public SwerveCalc(Dictionary<WheelPosition, Point> wheelPositions){
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
	private void internalSetAim(Point velocityVector, double rotation, Point centerOfRotation, WheelPosition wheelPosition){
		
		//holds the locations of the wheels in relationship to the center of rotation
		double wheelLocX = centerOfRotation.getX() + wheelPositions.get(wheelPosition).getX();
		double wheelLocY = centerOfRotation.getY() + wheelPositions.get(wheelPosition).getY();
		
		//these badly named variables are simply middle steps to make the math easier to think about. They are used in the later step
		double Wxi = velocityVector.getX() + (rotation*wheelLocX);
		double Wyi = velocityVector.getY() - (rotation*wheelLocY);
		
		//calculate the aims and store
		wheelAims.put("FrontRightVel", Math.sqrt(Math.pow(Wxi, 2) + Math.pow(Wyi, 2)));
		wheelAims.put("FrontRightRot", Math.atan2(Wxi, Wyi));
	}
	
	/**
	 * gets the velocity aim for a given wheel
	 * @param wheelPosition
	 * which wheel is the velocity requested for
	 * @return
	 * returns the velocity the given wheel should aim for
	 */
	public double getVelAim(WheelPosition wheelPosition){
		switch(wheelPosition){
		case FrontRight:
			return wheelAims.get("FrontRightVel");
		case BackRight:
			return wheelAims.get("BackRightVel");
		case FrontLeft:
			return wheelAims.get("FrontLeftVel");
		case BackLeft:
			return wheelAims.get("BackLeftVel");
		default: 
			return 0;
		}
	}
	
	/**
	 * gets the rotational aim for a given wheel
	 * @param wheelPosition
	 * which wheel is the rotational requested for
	 * @return
	 * returns the rotational the given wheel should aim for
	 */
	public double getRotAim(WheelPosition wheelPosition){
		switch(wheelPosition){
		case FrontRight:
			return wheelAims.get("FrontRightRot");
		case BackRight:
			return wheelAims.get("BackRightRot");
		case FrontLeft:
			return wheelAims.get("FrontLeftRot");
		case BackLeft:
			return wheelAims.get("BackLeftRot");
		default: 
			return 0;
		}
	}
	
	
	public enum WheelPosition{
		FrontRight,
		BackRight,
		FrontLeft,
		BackLeft
	}
}
