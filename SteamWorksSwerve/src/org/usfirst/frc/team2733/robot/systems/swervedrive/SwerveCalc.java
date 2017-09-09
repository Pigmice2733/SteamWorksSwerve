package org.usfirst.frc.team2733.robot.systems.swervedrive;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.utilities.Modulus;

public class SwerveCalc {

    // Holds Cartesian coordinates of each wheel, origin is the center of the
    // drive train
    private Map<WheelPosition, Tuple<Double>> wheelCoordinates = new HashMap<>();

    // The X and Y of the tuple correspond to the velocity and rotation of the
    // wheel aims.
    private Map<WheelPosition, Tuple<Double>> wheelAims = new HashMap<>();

    /**
     * SwerveCalc constructor
     * 
     * @param wheelCoordinates
     *            The Cartesian coordinates of each wheel relative to the center
     *            of the robot
     */
    public SwerveCalc(Map<WheelPosition, Tuple<Double>> wheelCoordinates) {
        this.wheelCoordinates = wheelCoordinates;
    }

    /**
     * Sets the aim of the swerve system
     * 
     * @param velocityVector
     *            Vector holding X and Y speeds for the robot
     * @param rotation
     *            Rotational velocity in radians/second (clockwise is positive)
     */
    public void setAim(Tuple<Double> velocityVector, double rotation) {
        setAim(velocityVector, rotation, new Tuple<Double>(0d, 0d));
    }

    /**
     * Sets the aim of the swerve system, uses specific center of rotation
     * 
     * @param velocityVector
     *            Vector holding X and Y speeds for the robot
     * @param rotation
     *            Rotational velocity in radians/second (clockwise is positive)
     * @param centerOfRotation
     *            X and Y coordinates of the point to rotate around
     */
    public void setAim(Tuple<Double> velocityVector, double rotation, Tuple<Double> centerOfRotation) {

        // Iterate over each wheel and set its target speed and angle
        for (WheelPosition position : wheelCoordinates.keySet()) {
            setWheelAim(velocityVector, rotation, centerOfRotation, position);
        }
    }

    /**
     * Swerve math
     * 
     * @param velocityVector
     *            Vector form of the robot's desired velocity
     * @param rotation
     *            Target rotational velocity
     * @param centerOfRotation
     *            The center of the rotation relative to the center of the robot
     * @param wheelPosition
     *            The WheelPosition of the wheel to calculate for
     */
    private void setWheelAim(Tuple<Double> velocityVector, double rotation, Tuple<Double> centerOfRotation,
            WheelPosition wheelPosition) {

        // Holds the locations of the wheels in relationship to the center of
        // rotation
        double wheelLocX = centerOfRotation.getX() + wheelCoordinates.get(wheelPosition).getX();
        double wheelLocY = centerOfRotation.getY() + wheelCoordinates.get(wheelPosition).getY();

        // These badly named variables are simply middle steps to make the math
        // easier to think about. They are used in the later step
        double Wxi = velocityVector.getX() + (rotation * wheelLocX);
        double Wyi = velocityVector.getY() - (rotation * wheelLocY);

        // Calculate the aims and store
        wheelAims.put(wheelPosition,
                new Tuple<Double>(Math.sqrt(Math.pow(Wxi, 2) + Math.pow(Wyi, 2)), Math.atan2(Wxi, Wyi)));
    }

    /**
     * Calculates correct direction and speed for the motors to turn based on
     * their current speed and direction and the intended speed and direction.
     * 
     * @param targSpeed
     *            Speed for the robot
     * @param targAngle
     *            Target direction of motion
     * @param currentAngle
     *            Current direction
     * @return The optimal target direction and target speed of the robot to
     *         attain the targets
     */
    public Tuple<Double> calcOptimalHeading(double targSpeed, double targAngle, double currentAngle) {
        targAngle = Modulus.modulus(targAngle, 2 * Math.PI);

        double relativeAngle = Modulus.modulus(targAngle - currentAngle, 2 * Math.PI);

        // If the relative angle is in the first 2 quadrants, leave speed and
        // angle alone
        if ((relativeAngle < 0.5 * Math.PI) || (relativeAngle > 1.5 * Math.PI)) {
            return new Tuple<Double>(targSpeed, targAngle);
            // Otherwise, reverse speed and take shorter angle
        } else {
            double angle = Modulus.modulus(targAngle - Math.PI, 2 * Math.PI);
            double speed = -targSpeed;
            return new Tuple<Double>(speed, angle);
        }
    }

    /**
     * Gets the velocity aim for a given wheel
     * 
     * @param wheelPosition
     *            WheelPosition of the wheel the velocity requested for
     * @return The velocity the given wheel should aim for
     */
    public double getVelAim(WheelPosition wheelPosition) {
        return wheelAims.get(wheelPosition).getX();
    }

    /**
     * Gets the rotational aim for a given wheel
     * 
     * @param wheelPosition
     *            WheelPosition of the wheel the rotational requested for
     * @return The rotational position the given wheel should aim for
     */
    public double getRotAim(WheelPosition wheelPosition) {
        return wheelAims.get(wheelPosition).getY();
    }
}
