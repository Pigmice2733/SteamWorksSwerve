package org.usfirst.frc.team2733.robot.swerve;

import java.util.Map;


public class SwerveCalc {

	private Map<WheelPosition, Point> wheelPositions;
	
	private Map<WheelPosition, Point> wheelAims;//the x and y of the point correspond to the velocity and rotation of the wheel aims.
	
	public SwerveCalc(Map<WheelPosition, Point> wheelPositions){
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
		internalSetAim(velocityVector, rotation, centerOfRotation, WheelPosition.FrontRight);
		internalSetAim(velocityVector, rotation, centerOfRotation, WheelPosition.BackRight);
		internalSetAim(velocityVector, rotation, centerOfRotation, WheelPosition.FrontLeft);
		internalSetAim(velocityVector, rotation, centerOfRotation, WheelPosition.BackLeft);
		
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
		internalSetAim(velocityVector, rotation, new Point(0, 0), WheelPosition.FrontRight);
		internalSetAim(velocityVector, rotation, new Point(0, 0), WheelPosition.BackRight);
		internalSetAim(velocityVector, rotation, new Point(0, 0), WheelPosition.FrontLeft);
		internalSetAim(velocityVector, rotation, new Point(0, 0), WheelPosition.BackLeft);
		
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
		wheelAims.put(wheelPosition, new Point(Math.sqrt(Math.pow(Wxi, 2) + Math.pow(Wyi, 2)), 0));
	}
	
	/**
	 * gets the velocity aim for a given wheel
	 * @param wheelPosition
	 * which wheel is the velocity requested for
	 * @return
	 * returns the velocity the given wheel should aim for
	 */
	public double getVelAim(WheelPosition wheelPosition){
		return wheelAims.get(wheelPosition).getX();
	}
	
	/**
	 * gets the rotational aim for a given wheel
	 * @param wheelPosition
	 * which wheel is the rotational requested for
	 * @return
	 * returns the rotational the given wheel should aim for
	 */
	public double getRotAim(WheelPosition wheelPosition){
		return wheelAims.get(wheelPosition).getY();
	}
	
	
	public enum WheelPosition{
		FrontRight,
		BackRight,
		FrontLeft,
		BackLeft
	}
}
