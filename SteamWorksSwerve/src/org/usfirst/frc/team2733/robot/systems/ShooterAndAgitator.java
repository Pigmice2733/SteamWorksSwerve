package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.Testable;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * Shooter systems controller
 */
public class ShooterAndAgitator implements Testable {	
    
	Spark shooterMotor;
	Talon agitatorMotor;
	
	private static double shooterSpeed = 1.0;
	private static double agitatorSpeed = 1.0;
	
	/**
	 * The controller for the shooter and ball agitator
	 * @param shooterPort The port for the shooter motor
	 * @param agitatorPort The port for the ball agitator motor
	 */
	public ShooterAndAgitator(int shooterPort, int agitatorPort) {
		shooterMotor = new Spark(shooterPort);
		agitatorMotor = new Talon(agitatorPort);
	}
	
	/**
	 * Controls shooter and ball agitator motors
	 * @param trigger Controls shooter motor
	 * @param release Controls agitator motor
	 */
	public void update(boolean trigger, boolean release, boolean upSpeed, boolean downSpeed) {
	    
	    if (trigger) {
	        shooterMotor.set(shooterSpeed);
	    } else {
	        shooterMotor.stopMotor();
	    }
	    
	    if (release) {
	        agitatorMotor.set(agitatorSpeed);
	    } else {
	        agitatorMotor.stopMotor();
	    }
	    
	    if(upSpeed) {
	    	shooterSpeed += 0.05;
	    } else if (downSpeed) {
	    	shooterSpeed -= 0.05;
	    }
	}
	
    @Override
    public void test() {
        System.out.println("Running Motors");
        shooterMotor.set(1.0);
        agitatorMotor.set(1.0);
        Timer.delay(1);
        shooterMotor.stopMotor();
        agitatorMotor.stopMotor();
        
    }
} 
