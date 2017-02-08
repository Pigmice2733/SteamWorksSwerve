package org.usfirst.frc.team2733.tempStuffThatIsBeingReplaced;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
	
	List<SwerveModule> modules = new ArrayList<SwerveModule>();//Defining ArrayList to hold wheels
	
	public SwerveModule frontRight, backRight, frontLeft, backLeft;//Defining new Wheels
	public static enum DriveMode {
		MAINSWERVE,
		ALTSWERVE,
		TANK,
		DISABLED;
	}
	
	public DriveMode mode;
	public JoystickInput joy;
	
	public DriveTrain(SwerveModule FrontLeft, SwerveModule FrontRight, SwerveModule BackLeft, SwerveModule BackRight, JoystickInput joy){//implementation for swerve

		modules.add(FrontLeft);//Adds wheels to a list
		modules.add(FrontRight);
		modules.add(BackLeft);
		modules.add(BackRight);
		this.joy = joy; 
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
		double direction = RobotMap.mapRange(0, 1, -1, RobotMap.minSetpoint, RobotMap.maxSetpoint);
        SmartDashboard.putNumber("direction", direction);
        SmartDashboard.putNumber("speed", speed);
		for(SwerveModule module: modules) {//For loop goes through Wheels held in ArrayList
			module.driveMotor.set(speed);
			module.steerMotor.pidWrite(direction);
		}
	}
	
}
