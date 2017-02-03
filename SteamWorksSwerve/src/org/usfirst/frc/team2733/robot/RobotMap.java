package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class RobotMap {

	public static double P = 1;
	/** I value to be used in internal PID loop. */
	public static double I = 0;
	/** D value to be used in internal PID loop. */
	public static double D = 0;
	/** The lowest setpoint the steering motor should be allowed to go to. */

	public static Wheel FR;
	/** The front right swerve module. */
	public static Wheel FL;
	/** The back left swerve module. */
	public static Wheel BL;
	/** The back right swerve module. */
	public static Wheel BR;

	/** Initialize Talons and encoders for each swerve module. */
	public static void initSwerve1() {
		FR = new Wheel("FR", new AnalogInput(4), new CANTalon(11), new Spark(0));
		FL = new Wheel("FL", new AnalogInput(5), new CANTalon(12), new Spark(1));
		BR = new Wheel("BR", new AnalogInput(6), new CANTalon(13), new Spark(2));
		BL = new Wheel("BL", new AnalogInput(7), new CANTalon(14), new Spark(3));
	}
	public static void initSwerve2() {
        FR = new Wheel("FR", new AnalogInput(4), new CANTalon(0), new Talon(0));
        FL = new Wheel("FL", new AnalogInput(5), new CANTalon(1), new Talon(1));
        BR = new Wheel("BR", new AnalogInput(6), new CANTalon(2), new Talon(2));
        BL = new Wheel("BL", new AnalogInput(7), new CANTalon(3), new Talon(3));
	}
	public static void initTank() {
		
	}
	
}
