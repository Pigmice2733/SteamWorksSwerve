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
	public static Map<WheelPosition, Integer> driveMotorPorts = new HashMap<>();
	
	public static Map<WheelPosition, Integer> rotateMotorPorts = new HashMap<>();
	
	// Pairs of encoder ports for drive motors
	public static Map<WheelPosition, Integer[]> driveEncoderPorts = new HashMap<>();
	
	public static Map<WheelPosition, Integer[]> rotationEncoderPorts = new HashMap<>();
	
	public static int joystickPort1 = 0;
	public static int joystickPort2 = 0;
	
	public static int gyroPort = 0;
	
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
		
		rotationEncoderPorts.put(WheelPosition.FrontLeft, new Integer[]{0, 0});
		rotationEncoderPorts.put(WheelPosition.FrontRight, new Integer[]{0, 0});
		rotationEncoderPorts.put(WheelPosition.BackLeft, new Integer[]{0, 0});
		rotationEncoderPorts.put(WheelPosition.BackRight, new Integer[]{0, 0});
	}

}
