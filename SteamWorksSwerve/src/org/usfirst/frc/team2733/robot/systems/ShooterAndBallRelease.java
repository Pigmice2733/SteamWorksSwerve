package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class ShooterAndBallRelease {
	
	Spark motor;
	Talon ballRelease;
	
	JoystickInput joy;
	
	boolean isShooting;
	boolean isAgitating;
	
	double speed1 = 0.75;
    double speed2 = 0.7;
    double speed3 = 0.6;
	double windowSpeed = 0.8;
	
	public ShooterAndBallRelease(JoystickInput joy) {
	    this.joy = joy;
		motor = new Spark(PortsEnum.SHOOTER.getPort());
		ballRelease = new Talon(PortsEnum.BALL_RELEASE.getPort());
		isShooting = false;
		isAgitating = false;
	}
	
	public void update () {
	    if (joy.getShooter1()) {
	        motor.set(speed1);
	    //} else if (joy.getShooter2()) {
	        //motor.set(speed2);
	    //} else if (joy.getShooter3()) {
	      //  motor.set(speed3);
	    } else {
	        motor.disable();
	    }
	    if (joy.getBallRelease()) {
	        ballRelease.set(windowSpeed);
	    } else {
	        ballRelease.disable();
	    }
	    /*if (isShooting == false && joy.getShooter()) {
	        lastTime = Timer.getFPGATimestamp();
	        isShooting = true;
	        motor.set(speed);
	    } else if (isAgitating == true && !joy.getShooter()) {
	        lastTime = Timer.getFPGATimestamp();
	        isAgitating = false;
	        ballRelease.disable();
	    } else if (isShooting == true && isAgitating == false && (Timer.getFPGATimestamp() - lastTime) > 0.5){
	        if(joy.getShooter()) {
	            isAgitating = true;
	            ballRelease.set(windowSpeed);
	        } else {
	            isShooting = false;
	            motor.disable();
	        }
	    }*/
	}
	
	
	
	public void calculateRPM(){
		//       -----------------------------------------------------
		//   2  /                      g * x^2
		//     /   -----------------------------------------------
		//    /    ( 2 * (cos(theta))^2 ) * ( x * tan(theta) - y )
		//   V
		//  ------------------------------------------------------------
		//                          2 * PI * radius
		
		
		
		
	}
	
} 
