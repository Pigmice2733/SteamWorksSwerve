package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.comm.DriveStation;
import org.usfirst.frc.team2733.robot.configuration.PortConfiguration;
import org.usfirst.frc.team2733.robot.configuration.PortConfiguration.Chassis;
import org.usfirst.frc.team2733.robot.systems.AbstractDriveTrain;
import org.usfirst.frc.team2733.robot.systems.Climber;
import org.usfirst.frc.team2733.robot.systems.Intake;
import org.usfirst.frc.team2733.robot.systems.ShooterAndAgitator;
import org.usfirst.frc.team2733.robot.systems.swervedrive.EncoderCalibration;
import org.usfirst.frc.team2733.robot.systems.swervedrive.SwerveDriveTrain;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {
    public static AbstractDriveTrain driveTrain;
    
    public static EncoderCalibration enCal;

    public static DriveStation driveStation;

    public static ShooterAndAgitator shooter;
    public static Climber climber;
    public static Intake intake;

    @Override
    protected void robotInit() {
        PortConfiguration portConfig = new PortConfiguration(false, null, Chassis.DEVBOT);
        enCal = new EncoderCalibration(true, "/home/lvuser/encoderCalibration.properties");
        driveTrain = new SwerveDriveTrain(portConfig.getDriveMotorPorts(), portConfig.getRotationMotorPorts(),
                portConfig.getEncoderPorts(), enCal);

        driveStation = new DriveStation(portConfig.getJoytsickOne(), portConfig.getJoystickTwo());
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

        driveTrain.initialize();

        while (isOperatorControl() && isEnabled()) {
            double magnitude = driveStation.getMagnitude();
            double direction = driveStation.getDirection();
            double rotation = driveStation.getRotation();
            double gyroOffset = 0;
            
            driveTrain.swerveDrive(magnitude, direction, rotation, gyroOffset);

            // shooter.update(driveStation.getShooter1(),
            // driveStation.getBallRelease(), driveStation.getUpPower(),
            // driveStation.getDownPower());
            // climber.update(driveStation.getClimber());
            // intake.update(driveStation.getIntake());
            Timer.delay(0.05);
        }
        driveTrain.releaseReources();
    }
}
