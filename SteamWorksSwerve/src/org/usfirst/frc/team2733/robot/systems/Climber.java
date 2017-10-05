package org.usfirst.frc.team2733.robot.systems;

import edu.wpi.first.wpilibj.Spark;

/**
 * Controller for the climber
 */
public class Climber{

    Spark motor;

    public double speed = 1.0;

    /**
     * Controller for the climber motor
     * 
     * @param climberPort
     *            Motor port for the climber
     */
    public Climber(int climberPort) {
        motor = new Spark(climberPort);
    }

    /**
     * Sets climber motor to speed
     * 
     * @param on
     *            Whether the climber should be on
     */
    public void update(boolean on) {
        motor.set(on ? speed : 0);
    }
}
