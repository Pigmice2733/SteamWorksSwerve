package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.autonomous.Autonomous;
import org.usfirst.frc.team2733.robot.autonomous.DataTransfer;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	
	public static DriveTrain driveTrain;
	public static Autonomous auto;
	public static DataTransfer data;
	
	@Override
	protected void robotInit() {
		driveTrain = new DriveTrain();
		//auto = new Autonomous(this, driveTrain);
    }

	/** 
	 * Commands available
	 * 
	 */
	@Override
	public void autonomous() {
		
		
		//auto.startAutonomous();
		
		// Left and Right
	    //driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0.05 * Math.PI * (location == RobotInitialLocation.LEFT ? -1 : 1)), 0);
	    // Center
	    //driveTrain.swerveCalc.setAim(driveTrain.getVelocityVector(defaultSpeed, 0), 0);

		while (isAutonomous() && isEnabled()) {
			/*// Movement Update
			double speed = networkTable.getNumber("speed", 0);
			double direction = networkTable.getNumber("direction", 0);
			double rotation = networkTable.getNumber("rotation", 0);
			
			Vector_Point_Abomination velocityVector = driveTrain.getVelocityVector(speed, direction);
			
			driveTrain.swerveCalc.setAim(velocityVector, rotation);
			
			// Ball Shooter - If necessary
			// double ballShooterSpeed = networkTable.getNumber("shooterSpeed", 0);
			
			*/
			
			driveTrain.zeroWheelPositions();
			Timer.delay(0.05);
		}
	}
	
    @Override
    public void operatorControl() {
    	//data = new DataTransfer (this);
    	
    	while (isOperatorControl() && isEnabled()) {
            driveTrain.drive();
            Timer.delay(0.05);
        }
    }
    
    @Override
    public void test() {
    	//data = new DataTransfer(this);
    	
		while (isTest() && isEnabled()) {
			//System.out.println("Y: " + data.getYaw() + "  | P: " + data.getPitch() + "  | R: " + data.getRoll());
			driveTrain.printPotentiometers();
			Timer.delay(0.5);
		}
    }
    
    public static double correctMod(double number, double modulus) {
    	return number < 0 ? number % modulus + modulus : number % modulus;
    }
}
