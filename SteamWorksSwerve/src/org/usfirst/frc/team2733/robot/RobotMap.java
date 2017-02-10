package org.usfirst.frc.team2733.robot;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team2733.robot.swerve.SwerveCalc.WheelPosition;


public class RobotMap {

	/** P & I values for drive motor PI controllers. */
	public static double PDrive = 0.01;
	public static double IDrive = 0;
	
	/** P & I values for rotation motor PI controllers **/
	public static double PRotate = 0.01;
	public static double IRotate = 0;
	
	// Needs to be replaced
	public static Map<WheelPosition,Integer> driveMotorPorts = new HashMap<>();
	
	public static Map<WheelPosition,Integer> rotateMotorPorts = new HashMap<>();
	
	// Pairs of encoder ports for drive motors
	public static Map<WheelPosition,Integer[]> driveEncoderPorts = new HashMap<>();
	
	public static Map<WheelPosition, Integer> rotationEncoderPorts = new HashMap<>();
	
	public static Map<WheelPosition,Integer[]> rotationEncoderValues = new HashMap<>();
	
	public static double minSetpoint = 1.5;
	/** The highest setpoint the steering motor should be allowed to go to. */
	public static double maxSetpoint = 3.5;

	public static int joystickPort1 = 0;
	public static int joystickPort2 = 0;

	public enum WheelCoords {
		FR(0.3, 0.3),
		FL(-0.3, 0.3),
		BR(-0.3, 0.3),
		BL(-0.3, -0.3);
		
		private double x, y;
		private WheelCoords(double x, double y) {
			this.x = x;
			this.y = y;
		}
		public double getX() { return x;}
		public double getY() { return y;}
	}
	
	public static void initSwerve() {
		/** All values are placeholders **/
		
		driveMotorPorts.put(WheelPosition.FrontLeft, 0);
		driveMotorPorts.put(WheelPosition.FrontRight, 0);
		driveMotorPorts.put(WheelPosition.BackLeft, 0);
		driveMotorPorts.put(WheelPosition.BackRight, 0);
		
		rotateMotorPorts.put(WheelPosition.FrontLeft, 0);
		rotateMotorPorts.put(WheelPosition.FrontRight, 0);
		rotateMotorPorts.put(WheelPosition.BackLeft, 0);
		rotateMotorPorts.put(WheelPosition.BackRight, 0);
		
		driveEncoderPorts.put(WheelPosition.FrontLeft, new Integer[]{0, 0});
		driveEncoderPorts.put(WheelPosition.FrontRight, new Integer[]{0, 0});
		driveEncoderPorts.put(WheelPosition.BackLeft, new Integer[]{0, 0});
		driveEncoderPorts.put(WheelPosition.BackRight, new Integer[]{0, 0});
		
		rotationEncoderPorts.put(WheelPosition.FrontLeft, 0);
		rotationEncoderPorts.put(WheelPosition.FrontRight, 0);
		rotationEncoderPorts.put(WheelPosition.BackLeft, 0);
		rotationEncoderPorts.put(WheelPosition.BackRight, 0);
		
		rotationEncoderValues.put(WheelPosition.FrontLeft, new Integer[]{1, 0});
		rotationEncoderValues.put(WheelPosition.FrontRight, new Integer[]{1, 0});
		rotationEncoderValues.put(WheelPosition.BackLeft, new Integer[]{1, 0});
		rotationEncoderValues.put(WheelPosition.BackRight, new Integer[]{1, 0});
	}

	/** Initialize Talons and encoders for each swerve module for competition. */
	public static void initSwerve1() {// Swerve Initialization for Competition Swerve
		//FR = new SwerveModule(WheelName.FR, new AbsoluteEncoder(4, 1, 0), new CANTalon(11), new Spark(0));// FORMAT: Name, Turning Encoder, Drive Motor, turning Motor
		//FL = new SwerveModule(WheelName.FL, new AbsoluteEncoder(5, 1, 0), new CANTalon(12), new Spark(1));// Same Format for other inits
		//BR = new SwerveModule(WheelName.BR, new AbsoluteEncoder(6, 1, 0), new CANTalon(13), new Spark(2));
		//BL = new SwerveModule(WheelName.BL, new AbsoluteEncoder(7, 1, 0), new CANTalon(14), new Spark(3));
	}
	/**Initialize Talons and encoders for each swerve module for development.*/
	public static void initSwerve2() {// Swerve Initialization for Development Swerve. The only difference is the speed controller types used.
		//FR = new SwerveModule(WheelName.FR, new AbsoluteEncoder(4, 1, 0), new CANTalon(11), new Talon(0));// FORMAT: Name, Turning Encoder, Drive Motor, turning Motor
		//FL = new SwerveModule(WheelName.FL, new AbsoluteEncoder(5, 1, 0), new CANTalon(12), new Talon(1));// Same Format for other inits
		//BR = new SwerveModule(WheelName.BR, new AbsoluteEncoder(6, 1, 0), new CANTalon(13), new Talon(2));
		//BL = new SwerveModule(WheelName.BL, new AbsoluteEncoder(7, 1, 0), new CANTalon(14), new Talon(3));
	}
	
	/**Initialize Talons and encoders for swerve modules in tank drive mode.*/
	public static void initTank() {// In case we use tank drive, this is a backup. Hopefully we never use this.
		
	}
	
	
	public static double mapRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return (value - fromLow) * (toHigh - toLow) / (fromHigh - fromLow) + toLow;
	}

}
