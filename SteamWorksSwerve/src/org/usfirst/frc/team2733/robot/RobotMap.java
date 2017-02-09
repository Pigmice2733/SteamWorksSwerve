package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2733.robot.SwerveModule.WheelName;

import com.ctre.CANTalon;

public class RobotMap {

	/** P value to be used in internal PID loop. */
	public static double P = 1;
	/** I value to be used in internal PID loop. */
	public static double I = 0;
	/** D value to be used in internal PID loop. */
	public static double D = 0;

	public static SwerveModule FR;
	/** The front right swerve module. */
	public static SwerveModule FL;
	/** The back left swerve module. */
	public static SwerveModule BL;
	/** The back right swerve module. */
	public static SwerveModule BR;
	
	public static double minSetpoint = 1.5;
	/** The highest setpoint the steering motor should be allowed to go to. */
	public static double maxSetpoint = 3.5;

	public static int joystickPort1 = 0;
	public static int joystickPort2 = 0;


	/** Initialize Talons and encoders for each swerve module. */
	public static void initSwerve1() {// Swerve Initialization for Competition Swerve
		FR = new SwerveModule(WheelName.FR, new AbsoluteEncoder(4, 1, 0), new CANTalon(11), new Spark(0));// FORMAT: Name, Turning Encoder, Drive Motor, turning Motor
		FL = new SwerveModule(WheelName.FL, new AbsoluteEncoder(5, 1, 0), new CANTalon(12), new Spark(1));// Same Format for other inits
		BR = new SwerveModule(WheelName.BR, new AbsoluteEncoder(6, 1, 0), new CANTalon(13), new Spark(2));
		BL = new SwerveModule(WheelName.BL, new AbsoluteEncoder(7, 1, 0), new CANTalon(14), new Spark(3));
	}

	public static void initSwerve2() {// Swerve Initialization for Development Swerve. The only difference is the speed controller types used.
		FR = new SwerveModule(WheelName.FR, new AbsoluteEncoder(4, 1, 0), new CANTalon(11), new Talon(0));// FORMAT: Name, Turning Encoder, Drive Motor, turning Motor
		FL = new SwerveModule(WheelName.FL, new AbsoluteEncoder(5, 1, 0), new CANTalon(12), new Talon(1));// Same Format for other inits
		BR = new SwerveModule(WheelName.BR, new AbsoluteEncoder(6, 1, 0), new CANTalon(13), new Talon(2));
		BL = new SwerveModule(WheelName.BL, new AbsoluteEncoder(7, 1, 0), new CANTalon(14), new Talon(3));
	}

	public static void initTank() {// In case we use tank drive, this is a backup. Hopefully we never use this.
		
	}
	
	// Feel free to remove this in a few days. It is only hear to prove that basic unit tests are working.
	// Please only remove after confirming that the ant function "test" works
	public static int test() {
		return 3;
	}

	public static double mapRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return (value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
	}

}
