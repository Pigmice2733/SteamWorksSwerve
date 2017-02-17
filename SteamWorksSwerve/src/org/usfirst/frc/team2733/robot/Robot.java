package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	
	public static DriveTrain driveTrain;
	public static Autonomous auto;
	
	@Override
	protected void robotInit() {
		driveTrain = new DriveTrain();
		auto = new Autonomous(this, driveTrain);
	}

	/** 
	 * Commands available
	 * 
	 */
	@Override
	public void autonomous() {
		// Currently random speed
		//double defaultSpeed = 0.2;// If you don't know what to set it to
		
		auto.startAutonomous();
		
		// Left and Right
	    //driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0.05 * Math.PI * (location == RobotInitialLocation.LEFT ? -1 : 1)), 0);
	    // Center
	    //driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0), 0);
		
	}
	
    @Override
    public void operatorControl() {
    	while (isOperatorControl() && isEnabled()) {
            driveTrain.drive();
            Timer.delay(.01);
        }
    }
    
    @Override
    public void test() {
    	AnalogPotentiometer analogPotenZero = new AnalogPotentiometer(0, 1, 0);
    	AnalogPotentiometer analogPotenOne = new AnalogPotentiometer(1, 1, 0);
    	AnalogPotentiometer analogPotenTwo = new AnalogPotentiometer(2, 1, 0);
    	AnalogPotentiometer analogPotenThree = new AnalogPotentiometer(3, 1, 0);
		while (isTest() && isEnabled()) {
			System.out.println("Analog Input Zero \"Accumulated Value\"" + analogPotenZero.get());
			System.out.println("Analog Input One \"Accumulated Value\"" + analogPotenOne.get());
			System.out.println("Analog Input Two \"Accumulated Value\"" + analogPotenTwo.get());
			System.out.println("Analog Input Three \"Accumulated Value\"" + analogPotenThree.get());
			Timer.delay(0.5);
		}
    }
}
