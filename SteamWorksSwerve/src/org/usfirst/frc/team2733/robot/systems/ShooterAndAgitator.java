package org.usfirst.frc.team2733.robot.systems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * Shooter systems controller
 */
public class ShooterAndAgitator {

    private Spark shooterMotor;
    private Talon agitatorMotor;

    private double shooterDelay;
    private boolean shooterOn;
    private boolean agitatorOn;
    private double startTime = 0;

    /**
     * The controller for the shooter and ball agitator
     * 
     * @param shooterPort
     *            The port for the shooter motor
     * @param agitatorPort
     *            The port for the ball agitator motor
     * @param shooterDelay
     *            The delay between the shooter flywheel starting and the
     *            agitator kicking in
     */
    public ShooterAndAgitator(int shooterPort, int agitatorPort, double shooterDelay) {
        shooterMotor = new Spark(shooterPort);
        agitatorMotor = new Talon(agitatorPort);
        this.shooterDelay = shooterDelay;
        this.shooterOn = false;
        this.agitatorOn = false;
    }
    
    private boolean hasTimeElapsed(double startTime, double delay) {
    	return (Timer.getFPGATimestamp() - startTime) >= delay;
    }

    /**
     * Controls shooter and ball agitator motors
     * 
     * @param triggerPulled
     *            Whether the shooter should start firing
     * @param shooterSpeed
     *            Speed multiplier for shooter speed, should be 0-1
     */
    public void update(boolean triggerPulled, double shooterSpeed) {
    	if (triggerPulled) {
    		if (!shooterOn) {
    			startTime = Timer.getFPGATimestamp();
    			shooterOn = true;
    		} else if (hasTimeElapsed(startTime, shooterDelay)) {
    			agitatorOn = true;
    		}
    	} else {
    		if (agitatorOn) {
    			startTime = Timer.getFPGATimestamp();
    			agitatorOn = false;
    		} else if (hasTimeElapsed(startTime, shooterDelay)) {
    			shooterOn = false;
    		}
    	}
    	
   		shooterMotor.set(shooterOn ? shooterSpeed : 0);    	
    	agitatorMotor.set(agitatorOn ? 1 : 0);
    }
}
