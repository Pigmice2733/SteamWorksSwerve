
package org.usfirst.frc.team2733.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends SampleRobot {
   
	public static DriveTrain swerveDrive;
	JoystickInput joy;
	
    public Robot() {
       
    }
      
    
    public void robotInit() {
      RobotMap.initSwerve1();
      joy = new JoystickInput(RobotMap.joystickPort1, RobotMap.joystickPort2);
      swerveDrive = new DriveTrain(RobotMap.FL, RobotMap.FR, RobotMap.BL, RobotMap.BR, joy);
    }

    public void autonomous() {
    	
    }

    public void operatorControl() {
      swerveDrive.drive();
    }

    public void test() {
    	SmartDashboard.putNumber("direction", joy.getDirection());
        SmartDashboard.putNumber("speed", joy.getSpeed());
    }
} 
 