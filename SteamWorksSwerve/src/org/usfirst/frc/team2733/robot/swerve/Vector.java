

package org.usfirst.frc.team2733.robot.swerve;

public class Vector{
   
    public double x;

    public double y;

    public Vector(Vector p) {
        this(p.x, p.y);
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
