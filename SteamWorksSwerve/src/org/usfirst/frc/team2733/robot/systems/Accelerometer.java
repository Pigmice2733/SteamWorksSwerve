package org.usfirst.frc.team2733.robot.systems;

public class Accelerometer {/*
                             * The accelerometer will be used to get velocities
                             * that help with swerve math and autonomous
                             * functions
                             */

    public double x, y;
    public double xVel, yVel;
    public double rotVel;// rotational velocity in Radians/second

    public Accelerometer() {
        this.x = 0;
        this.y = 0;
        this.xVel = 0;
        this.yVel = 0;
        this.rotVel = 0;
    }

    public void zeroValues() {
        this.x = 0;
        this.y = 0;
        this.xVel = 0;
        this.yVel = 0;
        this.rotVel = 0;
    }

    public double getXVel() {
        return 0;
    }

    public double getYVel() {
        return 0;
    }

    public double getRotVel() {
        return 0;
    }
}
