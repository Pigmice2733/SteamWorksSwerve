package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
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
    	while (isOperatorControl() && isEnabled()) {
            driveTrain.drive();
            Timer.delay(0.05);
        }
    }
    
    @Override
    public void test() {
    	AnalogPotentiometer analogPotenZero = new AnalogPotentiometer(PortsEnum.FRONT_LEFT_ROTATION_ANALOG_ENCODER.getPort(), 1, 0);
    	AnalogPotentiometer analogPotenOne = new AnalogPotentiometer(PortsEnum.FRONT_RIGHT_ROTATION_ANALOG_ENCODER.getPort(), 1, 0);
    	AnalogPotentiometer analogPotenTwo = new AnalogPotentiometer(PortsEnum.BACK_LEFT_ROTATION_ANALOG_ENCODER.getPort(), 1, 0);
    	AnalogPotentiometer analogPotenThree = new AnalogPotentiometer(PortsEnum.BACK_RIGHT_ROTATION_ANALOG_ENCODER.getPort(), 1, 0);
		while (isTest() && isEnabled()) {
			String output = "";
			output += ("Analog FL " + correctMod(analogPotenZero.get(), 1) + "\n");
			output += ("Analog FR " + correctMod(analogPotenOne.get(), 1) + "\n");
			output += ("Analog BL " + correctMod(analogPotenTwo.get(), 1) + "\n");
			output += ("Analog BR " + correctMod(analogPotenThree.get(), 1) + "\n");
			System.out.println(output);
			Timer.delay(0.5);
		}
    }
    
    public static double correctMod(double number, double modulus) {
    	return number < 0 ? number % modulus + modulus : number % modulus;
    }
}
