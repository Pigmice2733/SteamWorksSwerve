package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.Testable;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * Intake controller
 */
public class Intake implements Testable {

    Talon motor;

    public static final double speed = 0.6;

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

    @Override
    public void test() {
        motor.set(1.0);
        Timer.delay(1);
        motor.stopMotor();
    }
}
