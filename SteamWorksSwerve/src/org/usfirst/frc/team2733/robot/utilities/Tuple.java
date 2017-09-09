package org.usfirst.frc.team2733.robot.utilities;

public class Tuple<T> {

    public T x;

    public T y;

    public Tuple(Tuple<T> p) {
        this((T) p.x, (T) p.y);
    }

    public Tuple(T x, T y) {
        this.x = (T) x;
        this.y = (T) y;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }
}
