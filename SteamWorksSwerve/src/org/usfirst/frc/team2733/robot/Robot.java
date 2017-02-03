
package org.usfirst.frc.team2733.robot;


import edu.wpi.first.wpilibj.SampleRobot;


public class Robot extends SampleRobot {
   
	public static DriveTrain swerveDrive;
	
    public Robot() {
       
    }
    
    
    public void robotInit() {
      RobotMap.initSwerve1();
      swerveDrive = new DriveTrain(RobotMap.FL, RobotMap.FR, RobotMap.BL, RobotMap.BR);
    }

    public void autonomous() {
    	
    }

    public void operatorControl() {
      swerveDrive.drive();
    }

    public void test() {
    }
} 
 