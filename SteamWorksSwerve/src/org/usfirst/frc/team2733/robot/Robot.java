package org.usfirst.frc.team2733.robot;

import java.io.IOException;
import java.util.Properties;

import org.usfirst.frc.team2733.robot.autonomous.Autonomous;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.systems.Climber;
import org.usfirst.frc.team2733.robot.systems.Intake;
import org.usfirst.frc.team2733.robot.systems.ShooterAndBallRelease;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
	
	public static DriveTrain driveTrain;
	public static Autonomous auto;
	public static DataTransfer data;
	public static Climber climber;
	public static Intake intake;
	public static ShooterAndBallRelease shooter;
	
	
	
	@Override
	protected void robotInit() {
		AutoCalibration.logConfigurationFile();
		driveTrain = new DriveTrain();
		//shooter = new ShooterAndBallRelease();
		//climber = new Climber();
		//shooter = new Shooter();
        //climber = new Climber();
        //intake = new Intake();
		//auto = new Autonomous(this, driveTrain);
		data = new DataTransfer(this);
    }
	
	/** 
	 * Commands available
	 * 
	 */
	@Override
	public void autonomous() {
		driveTrain.ready(false);
		
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
        
        driveTrain.unready();
	}
	
    @Override
    public void operatorControl() {
		driveTrain.ready(false);
    	//data = new DataTransfer (this);
    	
    	while (isOperatorControl() && isEnabled()) {
            driveTrain.drive();
            //shooter.update();
            //intake.update();
            //climber.update();
            //climber.update();
            //intake.update();
            //shooter.update();
            //System.out.println(data.getYaw());
            //data.pingTable();
            Timer.delay(0.05);
        }
    	
    	driveTrain.unready();
    }
    
    @Override
    public void test() {
        //calibrateEncoders();
        //setupPoten();
        //portsTest();
    	while(isEnabled() && isTest()) {
    		//networkTableTest();
    	// Don't enable the line below unless you know what you are doing
    	//calibrateEncoders();
    	
    	/*while (isEnabled() && isTest()) {
    		networkTableTest();
>>>>>>> branch 'master' of https://github.com/Pigmice2733/SteamWorksSwerve.git
    		Timer.delay(1);
    	}*/
            //driveTrain.ready(true);
    	    //potentiometerCalibration();
    	    //portsTest();
    	    //calibrateEncoders();
    	}
    	//disablePoten();
    	
    }
    
    public void portsTest() {
        //portTest(PortsEnum.FRONT_RIGHT_ROTATION_MOTOR.getPort());
        portTest(PortsEnum.FRONT_LEFT_ROTATION_MOTOR.getPort());
        portTest(PortsEnum.FRONT_LEFT_DRIVE_MOTOR.getPort());
        // Drive then rotation motors
        // FL, FR, BL, BR
        /*portTest(PortsEnum.FRONT_LEFT_DRIVE_MOTOR.getPort());
        portTest(PortsEnum.FRONT_RIGHT_DRIVE_MOTOR.getPort());
        portTest(PortsEnum.BACK_LEFT_DRIVE_MOTOR.getPort());
        portTest(PortsEnum.BACK_RIGHT_DRIVE_MOTOR.getPort());*/
        // FL, FR, BL, BR
        /*portTest(PortsEnum.FRONT_LEFT_ROTATION_MOTOR.getPort());
        portTest(PortsEnum.FRONT_RIGHT_ROTATION_MOTOR.getPort());
        portTest(PortsEnum.BACK_LEFT_ROTATION_MOTOR.getPort());
        portTest(PortsEnum.BACK_RIGHT_ROTATION_MOTOR.getPort());
        
        portTestSpark(PortsEnum.SHOOTER.getPort());
        portTestSpark(PortsEnum.CLIMBER.getPort());
        portTestTalon(PortsEnum.BALL_RELEASE.getPort());
        portTestTalon(PortsEnum.INTAKE.getPort());*/
        
    }
    
    public void portTest(int port) {
        CANTalon canTalon = new CANTalon(port);
        canTalon.set(1.0);
        Timer.delay(2.0);
        //canTalon.set(0.0);
        //canTalon.delete();
    }
    
    public void portTestTalon(int port) {
        Talon canTalon = new Talon(port);
        canTalon.set(0.35);
        Timer.delay(2.0);
        canTalon.set(0.0);
        canTalon.free();
    }
    
    public void portTestSpark(int port) {
        Spark spark = new Spark(port);
        spark.set(0.35);
        Timer.delay(2.0);
        spark.set(0.0);
        spark.free();
    }
    
    
    public void calibrateEncoders() {

		driveTrain.ready(true);
    	
    	Properties properties = new Properties();
    	properties.setProperty("FL", Double.toString(-driveTrain.getEncoderValue(WheelPosition.FrontLeft)));
    	properties.setProperty("FR", Double.toString(-driveTrain.getEncoderValue(WheelPosition.FrontRight)));
    	properties.setProperty("BL", Double.toString(-driveTrain.getEncoderValue(WheelPosition.BackLeft)));
    	properties.setProperty("BR", Double.toString(-driveTrain.getEncoderValue(WheelPosition.BackRight)));

    	try {
			AutoCalibration.saveConfigurationFile(properties);
	    	AutoCalibration.logConfigurationFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        driveTrain.unready();
    }
    
    
    public static double correctMod(double number, double modulus) {
    	return number < 0 ? number % modulus + modulus : number % modulus;
    }
    AnalogPotentiometer input1;
    AnalogPotentiometer input2;
    AnalogPotentiometer input3;
    AnalogPotentiometer input4;
    public void disablePoten() {
        input1.free();
        input2.free();
        input3.free();
        input4.free();
    }
    public void setupPoten() {
        input1 = new AnalogPotentiometer(0);
        input2 = new AnalogPotentiometer(1);
        input3 = new AnalogPotentiometer(2);
        input4 = new AnalogPotentiometer(3);
    }
    public void potentiometerCalibration() {
        Timer.delay(0.5);
        System.out.println("1: " + input1.get());
        System.out.println("2: " + input2.get());
        System.out.println("3: " + input3.get());
        System.out.println("4: " + input4.get());
    	//driveTrain.printPotentiometers();
    }
    
    
    public void networkTableTest() {
    	while(isEnabled() && isTest()) {
    		String visionTrackingData = "";
    		data.pingTable();
    		/*visionTrackingData += "Distance: " + dataTransfer.getDistance() + "\n";
    		visionTrackingData += "Direction: " + dataTransfer.getDirection() + "\n";*/
    		
    		visionTrackingData += "Gyro: " + data.getYaw() + "\n";
    		
    		System.out.println(visionTrackingData);
    		
    	}
    }
    }
