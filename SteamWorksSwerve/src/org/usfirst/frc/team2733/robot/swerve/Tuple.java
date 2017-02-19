

package org.usfirst.frc.team2733.robot.swerve;

public class Tuple {
   
    public double x;

    public double y;

    public Tuple(Tuple p) {
        this(p.x, p.y);
    }

    public Tuple(double x, double y) {
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
