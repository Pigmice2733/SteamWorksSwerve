package org.usfirst.frc.team2733.robot.utilities;

import org.usfirst.frc.team2733.robot.comm.DataTransfer;

import edu.wpi.first.wpilibj.Timer;

public class GyroCalibrator {

    private LinearYaw linYaw;
    private double startTime;
    private double startYaw;
    private double slope;
    private boolean lastButton = false;

    public GyroCalibrator(LinearYaw linYaw) {
        this.linYaw = linYaw;
    }

    public void update(boolean button) {
        linYaw.update();
        if (button && !lastButton) {
            resetCal();
            System.out.println("Reset");
        } else if (button && lastButton) {
            updateCal();
            System.out.println("Update");
        }
        lastButton = button;
    }

    public void resetCal() {
        startTime = Timer.getFPGATimestamp();
        startYaw = linYaw.getYaw();
        slope = 0;
    }

    public void updateCal() {
        double now = Timer.getFPGATimestamp();
        double yaw = linYaw.getYaw();

        slope = (yaw - startYaw) / (now - startTime);
    }

    public double getGyroDrift() {
        double now = Timer.getFPGATimestamp();

        return ((now - startTime) * slope) + startYaw;
    }

    public double getCurrentHeading() {
        double yaw = linYaw.getYaw();
        double drift = getGyroDrift();

        return linYaw.compensateYaw(yaw - drift);
    }

}
