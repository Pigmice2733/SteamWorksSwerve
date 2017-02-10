package org.usfirst.frc.team2733.robot.driveTrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.usfirst.frc.team2733.robot.RobotMap;
import org.usfirst.frc.team2733.robot.swerve.Point;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc.WheelPosition;

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
	
	public SwerveCalc swerveCalc;
	
	public DriveTrain(){//implementation for swerve

		this.joy = new JoystickInput(RobotMap.joystickPort1, RobotMap.joystickPort2);
		
		Map<WheelPosition, Point> swerveDict = new HashMap<>();
		swerveDict.put(WheelPosition.FrontLeft, new Point(-0.5, 0.5));
		swerveDict.put(WheelPosition.FrontRight, new Point(0.5, 0.5));
		swerveDict.put(WheelPosition.BackLeft, new Point(-0.5, -0.5));
		swerveDict.put(WheelPosition.BackRight, new Point(0.5, -0.5));
		
		swerveCalc = new SwerveCalc(swerveDict);
		
		modules.add(new SwerveModule(WheelPosition.FrontLeft, swerveCalc));//Adds wheels to a list
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
		
		Point velocityVector = new Point((-Math.sin(direction) * speed), (Math.cos(direction) * speed)); 
		
		double rotation = joy.getRotation();
		SmartDashboard.putNumber("direction", direction);
        SmartDashboard.putNumber("speed", speed);
		swerveCalc.setAim(velocityVector, rotation);
	}
	
}
