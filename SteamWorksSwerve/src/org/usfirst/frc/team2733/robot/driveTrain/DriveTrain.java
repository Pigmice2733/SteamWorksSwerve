package org.usfirst.frc.team2733.robot.driveTrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.usfirst.frc.team2733.robot.controller.JoystickInput;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.swerve.Point;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
	
	List<SwerveModule> modules = new ArrayList<SwerveModule>(); // The swerve-wheel-module-things
	
	public static enum DriveMode {
		MAINSWERVE,
		ALTSWERVE,
		TANK,
		DISABLED;
	}
	
	public DriveMode mode;
	
	public JoystickInput joy;
	
	public Gyro gyro;
	
	public SwerveCalc swerveCalc;
	
	public DriveTrain() {// implementation for swerve

		this.joy = new JoystickInput(PortsEnum.JOYSTICK_ONE.getPort(), PortsEnum.JOYSTICK_TWO.getPort());
		
		Map<WheelPosition, Point> swerveDict = new HashMap<>();
		swerveDict.put(WheelPosition.FrontLeft, new Point(-0.33, 0.33));
		swerveDict.put(WheelPosition.FrontRight, new Point(0.33, 0.33));
		swerveDict.put(WheelPosition.BackLeft, new Point(-0.33, -0.33));
		swerveDict.put(WheelPosition.BackRight, new Point(0.33, -0.33));
		
		
		swerveCalc = new SwerveCalc(swerveDict);
		
		modules.add(new SwerveModule(WheelPosition.FrontLeft, swerveCalc));// Adds wheels to a list
		modules.add(new SwerveModule(WheelPosition.FrontRight, swerveCalc));
		modules.add(new SwerveModule(WheelPosition.BackLeft, swerveCalc));
		modules.add(new SwerveModule(WheelPosition.BackRight, swerveCalc));
	
	}
	
	public void setMode(DriveMode driveMode) {
		SmartDashboard.putString("mode", driveMode.toString());
        // Re-enable SwerveModules after mode changed from Disabled
        if (mode == DriveMode.DISABLED) {
        	for (SwerveModule mod: modules) {
        		mod.disable();
        	}
        }
    }
	
	public void drive() {
	    double speed = joy.getSpeed();
		double direction = joy.getDirection();
		double rotation = joy.getRotation();
		
		// Get degrees, convert to radians
		// TODO: This is gonna be here later because we will have a better gyro and it will be possible then - Xander
        // double headingOffset = Math.toRadians(gyro.getAngle());
		double headingOffset = 0;
        
		/*
		 * This method will have to be exapanded to make use of gyro offset. Unfortunatly that was not done correctly the first time.
		 * This still has the messy remnant of the old code, it will be changed later.
		 * 
		 */
		
		Point velocityVector = getVelocityVector(speed, direction - headingOffset);
		
		SmartDashboard.putNumber("Direction", direction);
        SmartDashboard.putNumber("Speed", speed);
		swerveCalc.setAim(velocityVector, rotation);
		
		for (SwerveModule module : modules) {
			module.update();
		}
	}
	
	public Point getVelocityVector(double speed, double direction) {
		return new Point((-Math.sin(direction) * speed), (Math.cos(direction) * speed));
	}
}
