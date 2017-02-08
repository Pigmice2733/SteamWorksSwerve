package org.usfirst.frc.team2733.tempStuffThatIsBeingReplaced;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
   
	public static DriveTrain swerveDrive;
	JoystickInput joy;
	
    public Robot() {
       
    }
      
    @Override
    public void robotInit() {
      RobotMap.initSwerve1();
      joy = new JoystickInput(RobotMap.joystickPort1, RobotMap.joystickPort2);
      swerveDrive = new DriveTrain(RobotMap.FL, RobotMap.FR, RobotMap.BL, RobotMap.BR, joy);
    }

    @Override
    public void autonomous() {
    	
    }

    @Override
    public void operatorControl() {
      swerveDrive.drive();
    }

    @Override
    public void test() {
    	SmartDashboard.putNumber("direction", joy.getDirection());
        SmartDashboard.putNumber("speed", joy.getSpeed());
    }
} 
 