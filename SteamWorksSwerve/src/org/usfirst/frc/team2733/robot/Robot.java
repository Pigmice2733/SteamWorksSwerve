package org.usfirst.frc.team2733.robot;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team2733.robot.comm.DataTransfer;
import org.usfirst.frc.team2733.robot.comm.DriveStation;
import org.usfirst.frc.team2733.robot.configuration.PortConfiguration;
import org.usfirst.frc.team2733.robot.configuration.PortConfiguration.Chassis;
import org.usfirst.frc.team2733.robot.systems.AbstractDriveTrain;
import org.usfirst.frc.team2733.robot.systems.Climber;
import org.usfirst.frc.team2733.robot.systems.Intake;
import org.usfirst.frc.team2733.robot.systems.ShooterAndAgitator;
import org.usfirst.frc.team2733.robot.systems.swervedrive.EncoderCalibration;
import org.usfirst.frc.team2733.robot.systems.swervedrive.SwerveDriveTrain;
import org.usfirst.frc.team2733.robot.utilities.GyroCalibrator;
import org.usfirst.frc.team2733.robot.utilities.LinearYaw;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
    //The Correction Coefficient for the Gyro
	double yawDriftAvg = 0;
	double gyroOffset = 0;


	public static AbstractDriveTrain driveTrain;
	public static Autonomous auto;
	public static DataTransfer data;

	public static EncoderCalibration enCal;

	public static DriveStation driveStation;

	public static ShooterAndAgitator shooter;
	public static Climber climber;
	public static Intake intake;

	private LinearYaw linYaw;
	private GyroCalibrator gyroCal;
	//private CameraServer camera;

	@Override
	protected void robotInit() {
		PortConfiguration portConfig = new PortConfiguration(false, null, Chassis.DEVBOT);
		enCal = new EncoderCalibration(true, "/home/lvuser/encoderCalibration.properties");
	    driveTrain = new SwerveDriveTrain(portConfig.getDriveMotorPorts(), portConfig.getRotationMotorPorts(), portConfig.getEncoderPorts(), enCal);

	    //shooter = new ShooterAndAgitator(portConfig.getShooterPort(), portConfig.getAgitatorPort());
		//climber = new Climber(portConfig.getClimberPort());
		//intake = new Intake(portConfig.getIntakePort());
	    //camera = CameraServer.getInstance();
	    //camera.startAutomaticCapture()
		data = new DataTransfer(this);
		linYaw = new LinearYaw(data);
		gyroCal = new GyroCalibrator(linYaw);
		driveStation = new DriveStation(portConfig.getJoytsickOne(), portConfig.getJoystickTwo());
	}

	@Override
	public void disabled() {
		//linYaw.update();
		//gyroCal.resetCal();

		while(isDisabled()) {
			//linYaw.update();
			//gyroCal.updateCal();
		}
	}

	@Override
	public void autonomous() {

	    driveTrain.initialize();
	    gyroOffset = 0 - data.getYaw();

	    // Straighten wheels
		while (isAutonomous() && isEnabled()) {
			driveTrain.ready();
			gyroOffset -= yawDriftAvg;

		    Timer.delay(0.05);
		}

		driveTrain.releaseReources();
	}

	@Override
	public void operatorControl() {

	    driveTrain.initialize();

	    while (isOperatorControl() && isEnabled()) {

	    	gyroCal.update(driveStation.getGyroCalibration());

	    	if(driveStation.getTrigger()) {
	    		double magnitude = 0.1;
		    	double direction = (data.getDirection() > 10)? (3*Math.PI/2) : (data.getDirection() < -10)? (Math.PI/2): 0;
		    	double rotation = 0;
		    	double gyroOffset = 0;
		    	driveTrain.swerveDrive(magnitude, direction, rotation, gyroOffset);
		    	System.out.println("Direction: " + data.getDirection());
	    	} else {

		    	double magnitude = driveStation.getMagnitude() * 0.4;
		    	double direction = driveStation.getDirection();
		    	double rotation = driveStation.getRotation();
		    	double gyroOffset = 0 + driveStation.getManualGyroOffset() + gyroCal.getCurrentHeading();
		    	driveTrain.swerveDrive(magnitude, direction, rotation, gyroOffset);
	    	}


	    	//shooter.update(driveStation.getShooter1(), driveStation.getBallRelease(), driveStation.getUpPower(), driveStation.getDownPower());
	    	//climber.update(driveStation.getClimber());
	    	//intake.update(driveStation.getIntake());
			Timer.delay(0.05);

			if(driveStation.getEncoder(0)){
				enCal.reloadOffsets(false);
			}
			if(driveStation.getEncoder(1)){
				enCal.saveCalibrationFile();
			}
			if(driveStation.getEncoder(2)){
				enCal.reloadOffsets(true);
			}
		}

	    driveTrain.releaseReources();
	}

	@Override
	public void test() {
		Timer.delay(0.1);
	    driveTrain.setup();

	    List<Testable> systemsToTest = new ArrayList<Testable>();

	    TestMode tests = new TestMode(systemsToTest);

	    tests.runTests();

	}

    public static double correctMod(double number, double modulus) {
    	return number < 0 ? number % modulus + modulus : number % modulus;
    }
}
