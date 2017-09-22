package org.usfirst.frc.team2733.robot.systems;

import edu.wpi.first.wpilibj.Talon;

/**
 * Intake controller
 */
public class Intake {

    Talon motor;

    public static final double speed = 0.08;

    /**
     * Intake controller
     * 
     * @param intakeMotorPort
     *            Port for intake motor
     */
    public Intake(int intakeMotorPort) {
        motor = new Talon(intakeMotorPort);
    }

    /**
     * Trigger intake motor
     * 
     * @param trigger
     *            <i>true</i> turns on intake, <i>false</i> stops it
     */
    public void update(boolean trigger) {
        if (trigger) {
            motor.set(-speed);
        } else {
            motor.set(0);
        }
    }
}
