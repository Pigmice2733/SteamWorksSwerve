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

	public static Wheel FR;
	/** The front right swerve module. */
	public static Wheel FL;
	/** The back left swerve module. */
	public static Wheel BL; 
	/** The back right swerve module. */
	public static Wheel BR;
	
	public static double minSetpoint = 1.5;
	/** The highest setpoint the steering motor should be allowed to go to. */
	public static double maxSetpoint = 3.5;
	
	public static int joystickPort1 = 0;
	public static int joystickPort2 = 0;


	/** Initialize Talons and encoders for each swerve module. */
	public static void initSwerve1() {//Swerve Initialization for Competition Swerve
		FR = new Wheel("FR", new AnalogInput(4), new CANTalon(11), new Spark(0));//FORMAT: Name, Turning Encoder, Drive Motor, turning Motor
		FL = new Wheel("FL", new AnalogInput(5), new CANTalon(12), new Spark(1));//Same Format for other inits
		BR = new Wheel("BR", new AnalogInput(6), new CANTalon(13), new Spark(2));
		BL = new Wheel("BL", new AnalogInput(7), new CANTalon(14), new Spark(3));
	}
	public static void initSwerve2() {//Swerve Initialization for Development Swerve. The only difference is the speed controller types used.
        FR = new Wheel("FR", new AnalogInput(4), new CANTalon(0), new Talon(0));
        FL = new Wheel("FL", new AnalogInput(5), new CANTalon(1), new Talon(1));
        BR = new Wheel("BR", new AnalogInput(6), new CANTalon(2), new Talon(2));
        BL = new Wheel("BL", new AnalogInput(7), new CANTalon(3), new Talon(3));
	}
	public static void initTank() {//In case we use tank drive, this is a backup. Hopefully we never use this.
		
	}
	
	public static double mapRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return (value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
}

	
}
