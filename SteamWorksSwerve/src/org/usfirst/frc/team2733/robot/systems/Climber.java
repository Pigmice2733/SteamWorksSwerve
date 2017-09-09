package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.Testable;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;

/**
 * Controller for the climber
 */
public class Climber implements Testable{

	Spark motor;
	
	public double speed = 1.0;
	
	/**
	 * Controller for the climber motor
	 * @param climberPort Motor port for the climber
	 */
	public Climber(int climberPort) {
		motor = new Spark(climberPort);
	}
	
	/**
	 * Sets climber motor to speed
	 * @param speed The speed to run the climber at
	 */
	public void update(double speed) {
	    motor.set(speed);
	}

    @Override
    public void test() {
        motor.set(1.0);
        Timer.delay(1);
        motor.stopMotor();
    }
}
