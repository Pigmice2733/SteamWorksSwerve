package org.usfirst.frc.team2733.robot.swerve;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;

public class SwerveCalc {

	private Map<WheelPosition, Vector_Point_Abomination> wheelPositions = new HashMap<>();
	
	private Map<WheelPosition, Vector_Point_Abomination> wheelAims = new HashMap<>();// the x and y of the point correspond to the velocity and rotation of the wheel aims.
	
	public SwerveCalc(Map<WheelPosition, Vector_Point_Abomination> wheelPositions) {
		this.wheelPositions = wheelPositions;
	}
	
	/**
	 * Sets the aim of the swerve system
	 * 
	 * @param velocityX
	 * in meters/second
	 * @param velocityY
	 * in meters/second
	 * @param rotation
	 * in radians/second (clockwise is positive)
	 * @param centerOfRotation
	 * x/y coordinates of the point to rotate around
	 */
	public void setAim(Vector_Point_Abomination velocityVector, double rotation, Vector_Point_Abomination centerOfRotation) {
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
	public void setAim(Vector_Point_Abomination velocityVector, double rotation) {
		internalSetAim(velocityVector, rotation, new Vector_Point_Abomination(0, 0), WheelPosition.FrontRight);
		internalSetAim(velocityVector, rotation, new Vector_Point_Abomination(0, 0), WheelPosition.BackRight);
		internalSetAim(velocityVector, rotation, new Vector_Point_Abomination(0, 0), WheelPosition.FrontLeft);
		internalSetAim(velocityVector, rotation, new Vector_Point_Abomination(0, 0), WheelPosition.BackLeft);
		
	}
	
	//internal method with the real math
	private void internalSetAim(Vector_Point_Abomination velocityVector, double rotation, Vector_Point_Abomination centerOfRotation, WheelPosition wheelPosition) {
		
		//holds the locations of the wheels in relationship to the center of rotation
		double wheelLocX = centerOfRotation.getX() + wheelPositions.get(wheelPosition).getX();
		double wheelLocY = centerOfRotation.getY() + wheelPositions.get(wheelPosition).getY();
		
		//these badly named variables are simply middle steps to make the math easier to think about. They are used in the later step
		double Wxi = velocityVector.getX() + (rotation*wheelLocX);
		double Wyi = velocityVector.getY() - (rotation*wheelLocY);
		
		//calculate the aims and store
		wheelAims.put(wheelPosition, new Vector_Point_Abomination(Math.sqrt(Math.pow(Wxi, 2) + Math.pow(Wyi, 2)), Math.atan2(Wxi, Wyi)));
	}
	
	public class AngleSpeedObject {
	    private double speed;
	    private double angle;
	    
	    public AngleSpeedObject(double speed, double angle) {
            this.speed = speed;
            this.angle = angle;
        }
	    
	    public double getSpeed() {
	        return speed;
	    }
	    
        public double getAngle() {
            return angle;
        }
	}
	
	// Calculates correct direction and speed for the motors to turn based on their current speed and direction and the intended speed and direction.
    public AngleSpeedObject calcOptimalHeading(double targSpeed, double targAngle, double currentAngle) {
        targAngle = targAngle % (2 * Math.PI);
        
        double relativeAngle = (targAngle - currentAngle) % (2 * Math.PI);
        
        // If the relative angle is in the first 2 quadrants, leave speed and angle alone
        if ((relativeAngle < 0.5 * Math.PI) || (relativeAngle > 1.5 * Math.PI)) {
            double angle = targAngle;
            double speed = targSpeed;
            return new AngleSpeedObject(speed, angle);
        // Otherwise, reverse speed and take shorter angle
        } else {
            double angle = (targAngle - Math.PI) % (2 * Math.PI);
            double speed = -targSpeed;
            return new AngleSpeedObject(speed, angle);
        }
    }
	
	/**
	 * gets the velocity aim for a given wheel
	 * @param wheelPosition
	 * which wheel is the velocity requested for
	 * @return
	 * returns the velocity the given wheel should aim for
	 */
	public double getVelAim(WheelPosition wheelPosition) {
		return wheelAims.get(wheelPosition).getX();
	}
	
	/**
	 * gets the rotational aim for a given wheel
	 * @param wheelPosition
	 * which wheel is the rotational requested for
	 * @return
	 * returns the rotational position the given wheel should aim for
	 */
	public double getRotAim(WheelPosition wheelPosition){
		return wheelAims.get(wheelPosition).getY();
	}
}
