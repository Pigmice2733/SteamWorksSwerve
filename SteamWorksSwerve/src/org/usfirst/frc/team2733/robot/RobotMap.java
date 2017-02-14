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
	public static int joystickPort2 = 1;

	public static int gyroPort = 0;
	
	// Feel free to remove this in a few days. It is only hear to prove that basic unit tests are working.
	// Please only remove after confirming that the ant function "test" works
	public static int test() {
		return 3;
	}
	
	public static void initSwerve() {
		/** All values are placeholders **/
		
		driveMotorPorts.put(WheelPosition.FrontLeft, 2);
		driveMotorPorts.put(WheelPosition.FrontRight, 1);
		driveMotorPorts.put(WheelPosition.BackLeft, 6);
		driveMotorPorts.put(WheelPosition.BackRight, 5);
		
		rotateMotorPorts.put(WheelPosition.FrontLeft, 3);
		rotateMotorPorts.put(WheelPosition.FrontRight, 0);
		rotateMotorPorts.put(WheelPosition.BackLeft, 7);
		rotateMotorPorts.put(WheelPosition.BackRight, 4);
		
		/*driveEncoderPorts.put(WheelPosition.FrontLeft, new Integer[]{0, 0});
		driveEncoderPorts.put(WheelPosition.FrontRight, new Integer[]{0, 0});
		driveEncoderPorts.put(WheelPosition.BackLeft, new Integer[]{0, 0});
		driveEncoderPorts.put(WheelPosition.BackRight, new Integer[]{0, 0});*/
		
		rotationEncoderPorts.put(WheelPosition.FrontLeft, new Integer[]{2, 3});
		rotationEncoderPorts.put(WheelPosition.FrontRight, new Integer[]{0, 1});
		rotationEncoderPorts.put(WheelPosition.BackLeft, new Integer[]{6, 7});
		rotationEncoderPorts.put(WheelPosition.BackRight, new Integer[]{4, 5});
	}

}
