package org.usfirst.frc.team2733.robot.utilities;

import org.usfirst.frc.team2733.robot.comm.DataTransfer;

public class LinearYaw {

    public int rotationCount;
    private double previousYaw;
    private double yaw = 0;

    DataTransfer data;

    public LinearYaw(DataTransfer data) {
        this.data = data;
    }

    public void update() {
        yaw = data.getYaw();
        if (yaw - previousYaw > Math.PI) {
            rotationCount--;
        } else if (yaw - previousYaw < -Math.PI) {
            rotationCount++;
        }
        previousYaw = yaw;

    }

    public double getYaw() {
        return yaw + 2 * Math.PI * rotationCount;
    }

    public double compensateYaw(double yaw) {
        return yaw - (2 * Math.PI * rotationCount);
    }

}
