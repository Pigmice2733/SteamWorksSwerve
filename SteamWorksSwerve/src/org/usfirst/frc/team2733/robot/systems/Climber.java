package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;

import edu.wpi.first.wpilibj.Spark;

public class Climber {

	Spark motor;
	JoystickInput joy;
	public double speed = 1.0;
	
	public Climber(JoystickInput joy) {
	    this.joy = joy;
		motor = new Spark(PortsEnum.CLIMBER.getPort());
	}
	
	public void update() {
	    motor.set(joy.getClimber());
	}
}
