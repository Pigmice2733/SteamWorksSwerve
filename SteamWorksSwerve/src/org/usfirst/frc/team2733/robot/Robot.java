package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.comm.JoystickInput;
import org.usfirst.frc.team2733.robot.configuration.PortConfiguration;
import org.usfirst.frc.team2733.robot.configuration.PortConfiguration.Chassis;
import org.usfirst.frc.team2733.robot.systems.AbstractDriveTrain;
import org.usfirst.frc.team2733.robot.systems.Intake;
import org.usfirst.frc.team2733.robot.systems.ShooterAndAgitator;
import org.usfirst.frc.team2733.robot.systems.swervedrive.EncoderCalibration;
import org.usfirst.frc.team2733.robot.systems.swervedrive.SwerveDriveTrain;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
    
    private SwerveDriveTrain driveTrain;
    private EncoderCalibration enCal;
    private JoystickInput joyInput;
    
    
    private ShooterAndAgitator shooter;
	private Intake intake;
    
    
    @Override
    protected void robotInit() {
        PortConfiguration portConfig = new PortConfiguration(false, null, Chassis.COMPBOT);
        enCal = new EncoderCalibration(true, "/home/lvuser/encoderCalibration.properties");
        driveTrain = new SwerveDriveTrain(portConfig.getDriveMotorPorts(), portConfig.getRotationMotorPorts(),
                portConfig.getEncoderPorts(), enCal);

        joyInput = new JoystickInput(portConfig.getJoytsickOne(), portConfig.getJoystickTwo());
        
        // Delay between shooter flywheel starting and agitator kicking in
        double shooterDelay = 0.5;
        shooter = new ShooterAndAgitator(portConfig.getShooterPort(), portConfig.getAgitatorPort(), shooterDelay);
        intake = new Intake(portConfig.getIntakePort());
        driveTrain.initialize();
    }

    @Override
    public void autonomous() {

        driveTrain.initialize();
        
        // Straighten wheels
        while (isAutonomous() && isEnabled()) {
            driveTrain.ready();
            
            Timer.delay(0.05);
        }

        driveTrain.releaseReources();
    }

    @Override
    public void operatorControl() {

        while (isOperatorControl() && isEnabled()) {
            double magnitude = joyInput.getSpeed();
            double direction = joyInput.getDirection();
            double rotation = joyInput.getRotation();
            double gyroOffset = 0;
            
            driveTrain.swerveDrive(magnitude, direction, rotation, gyroOffset);

            shooter.update(joyInput.getShooter(), joyInput.getSpeedMulti());
            
            //intake.update(joyInput.getShooter());
            
            Timer.delay(0.2);
        }
        //driveTrain.releaseReources();
    }
    
    @Override
    public void test() {
    	driveTrain.calibrateEncoders();
    }
}
