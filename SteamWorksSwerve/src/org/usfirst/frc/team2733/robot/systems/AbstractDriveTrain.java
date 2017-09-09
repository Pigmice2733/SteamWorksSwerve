package org.usfirst.frc.team2733.robot.systems;

public abstract class AbstractDriveTrain {

    /**
     * Readies the robots WITHOUT moving any motors
     */
    public abstract void initialize();

    /**
     * Frees resources and cleans up whatever is necessary
     */
    public abstract void releaseReources();

    /**
     * Checks if the drive train is initialized, will return false if
     * initialization fails
     * 
     * @return True if initialized
     */
    public abstract boolean isInitialized();

    /**
     * Readies the drive train for movement, this includes physical movement
     */
    public abstract void ready();

    /**
     * Set drive speed, direction and rotational speed for swerve
     * 
     * @param speed
     *            Target velocity for the robot in m/s
     * @param direction
     *            The direction for the robot to drive (relative to its current
     *            orientation) in radians
     * @param rotation
     *            Target rotational velocity for the robot in radians/s
     * @param gyroOffset
     *            Direction offset in radians
     */
    public abstract void swerveDrive(double speed, double direction, double rotation, double gyroOffset);

    /**
     * Set left speed and right speed for going tank-style
     * 
     * @param leftSpeed
     *            Target speed for left side of drive train
     * @param rightSpeed
     *            Target speed for right side of drive train
     * @param gyroOffset
     *            Gyro value for field-centric commands
     */
    public abstract void tankDrive(double leftSpeed, double rightSpeed, double gyroOffset);

    /**
     * Stop all wheel movement
     */
    public abstract void stopMovement();

    public abstract void setup();

}
