

package org.usfirst.frc.team2733.robot.swerve;

public class Vector_Point_Abomination {
   
    public double x;

    public double y;

    public Vector_Point_Abomination(Vector_Point_Abomination p) {
        this(p.x, p.y);
    }

    public Vector_Point_Abomination(double x, double y) {
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
