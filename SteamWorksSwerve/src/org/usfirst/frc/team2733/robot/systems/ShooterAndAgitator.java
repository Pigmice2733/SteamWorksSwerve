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

    private double shooterSpeed = 1.0;
    private double agitatorSpeed = 1.0;

    private double shooterDelay;
    private boolean shooterOn;
    private boolean agitatorOn;
    private double shooterActivationTime;
    private double agitatorDeactivationTime;

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
        this.shooterActivationTime = 0.0;
        this.agitatorDeactivationTime = 0.0;
    }

    /**
     * Controls shooter and ball agitator motors
     * 
     * @param shoot
     *            Whether the shooter should start firing
     */
    public void update(boolean shoot, double speed) {
        // State control so the shooter wheel has 'shooterDelay' seconds to get
        // up to speed before the agitator kicks in,
        // and the agitator has 'shooterDelay' seconds to wind down before the
        // shooter wheel turns off.

        // If the shooter wheel is off, and the shooter should start firing,
        // turn the shooter wheel on and keep track of the time in started
        if (!shooterOn && shoot) {
            shooterMotor.set(shooterSpeed * speed);
            shooterActivationTime = Timer.getFPGATimestamp();
            shooterOn = true;
            // Once the shooter wheel is on, and the shooter should start
            // firing, and the shooter wheel has had 'shooterDelay' seconds to
            // get up to speed, turn on the agitator
        } else if (shooterOn && shoot && !agitatorOn && (Timer.getFPGATimestamp() - shooterActivationTime) > shooterDelay) {
            agitatorMotor.set(agitatorSpeed);
            agitatorOn = true;
        }

        // If the agitator is on, but the shooter shouldn't be firing, turn the
        // agitator off and keep track of when it was turned off
        if (agitatorOn && !shoot) {
            agitatorMotor.stopMotor();
            agitatorOn = false;
            agitatorDeactivationTime = Timer.getFPGATimestamp();
            // Once the agitator is off, and has had 'shooterDelay' seconds to
            // wind down, and the shooter shouldn't be firing, turn the shooter
            // wheel off
        } else if (!agitatorOn && shooterOn && !shoot && (Timer.getFPGATimestamp() - agitatorDeactivationTime) > shooterDelay) {
            shooterMotor.stopMotor();
            shooterOn = false;
        }
    }
}
