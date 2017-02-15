

package org.usfirst.frc.team2733.robot.swerve;

public class Point{
   
    public double x;

    public double y;

    public Point(Point p) {
        this(p.x, p.y);
    }

    public Point(double x, double y) {
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
