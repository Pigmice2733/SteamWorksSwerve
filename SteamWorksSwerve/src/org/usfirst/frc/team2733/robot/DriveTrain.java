package org.usfirst.frc.team2733.robot;

import java.util.ArrayList;
import java.util.List;

public class DriveTrain {
	
	List<Wheel> modules = new ArrayList<Wheel>();//Defining ArrayList to hold wheels
	
	 public Wheel frontRight, backRight, frontLeft, backLeft;//Defining new Wheels
	
	public DriveTrain(int port0, int port1, int port2, int port3){//implementation for tank
		
	}
	
	public DriveTrain(Wheel FrontLeft, Wheel FrontRight, Wheel BackLeft, Wheel BackRight){//implementation for swerve
		modules.add(FrontLeft);//Adds wheels to a list
		modules.add(FrontRight);
		modules.add(BackLeft);
		modules.add(BackRight);
		
	}
	
	
	public void drive() {
		for(Wheel module: modules) {//For loop goes through Wheels held in ArrayList
			module.driveMotor.stopMotor();
			module.steerMotor.pidWrite(0);
		}
	}
	
}
