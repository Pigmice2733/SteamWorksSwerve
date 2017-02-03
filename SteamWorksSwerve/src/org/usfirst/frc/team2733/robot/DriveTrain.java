package org.usfirst.frc.team2733.robot;

import java.util.ArrayList;
import java.util.List;

public class DriveTrain {
	
	List<Wheel> modules = new ArrayList<Wheel>();
	
	 public Wheel frontRight, backRight, frontLeft, backLeft;
	
	public DriveTrain(int port0, int port1, int port2, int port3){//implementation for tank
		
	}
	
	public DriveTrain(Wheel FrontLeft, Wheel FrontRight, Wheel BackLeft, Wheel BackRight){//implementation for swerve
		modules.add(FrontLeft);
		modules.add(FrontRight);
		modules.add(BackLeft);
		modules.add(BackRight);
		
	}
}
