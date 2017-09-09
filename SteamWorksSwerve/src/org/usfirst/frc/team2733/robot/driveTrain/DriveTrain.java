package org.usfirst.frc.team2733.robot.driveTrain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.usfirst.frc.team2733.robot.DataTransfer;
import org.usfirst.frc.team2733.robot.AutoCalibration;
import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.TankDrive;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;
import org.usfirst.frc.team2733.robot.swerve.Tuple;
import org.usfirst.frc.team2733.robot.systems.Climber;
import org.usfirst.frc.team2733.robot.systems.Intake;
import org.usfirst.frc.team2733.robot.systems.ShooterAndBallRelease;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {

    List<SwerveModule> modules = new ArrayList<SwerveModule>(); // The
                                                                // swerve-wheel-module-things

    public static enum DriveMode {
        MAINSWERVE, ALTSWERVE, TANK, DISABLED;
    }

    public DriveMode mode;

    public JoystickInput joy;

    public Gyro gyro;

    // public TankDrive tankDrive;
    public SwerveCalc swerveCalc;

    private Climber climber;
    private ShooterAndBallRelease shooter;
    private Intake intake;

    public DataTransfer data;

    public DriveTrain() {// implementation for swerve

        this.joy = new JoystickInput(PortsEnum.JOYSTICK_ONE.getPort(), PortsEnum.JOYSTICK_TWO.getPort());
        // intake = new Intake(joy);
        // climber = new Climber(joy);
        // shooter = new ShooterAndBallRelease(joy);

        // tankDrive = new TankDrive(joy);
        swerveCalc = new SwerveCalc(getSwerveDict());

        /*
         * climber = new Climber(PortsEnum.CLIMBER.getPort(), joy); shooter =
         * new Shooter(PortsEnum.SHOOTER.getPort(),
         * PortsEnum.BALL_RELEASE.getPort(), joy); intake = new
         * Intake(PortsEnum.INTAKE.getPort(), joy);
         */
    }

    /*
     * defaultConfig - use the defaultConfig or not
     */
    public void ready(boolean defaultConfig) {
        Properties properties;
        if (defaultConfig) {
            properties = AutoCalibration.getDefaultConfigurationFile();
        } else {
            try {
                properties = AutoCalibration.getConfigurationFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error reading config file using the default");
                properties = AutoCalibration.getDefaultConfigurationFile();
            }
        }

        modules.add(new SwerveModule(WheelPosition.FrontLeft, swerveCalc,
                Double.parseDouble(properties.getProperty("FL"))));// Adds
                                                                   // wheels to
                                                                   // a list
        modules.add(new SwerveModule(WheelPosition.FrontRight, swerveCalc,
                Double.parseDouble(properties.getProperty("FR"))));
        modules.add(
                new SwerveModule(WheelPosition.BackLeft, swerveCalc, Double.parseDouble(properties.getProperty("BL"))));
        modules.add(new SwerveModule(WheelPosition.BackRight, swerveCalc,
                Double.parseDouble(properties.getProperty("BR"))));
    }

    public void unready() {
        for (SwerveModule module : modules) {
            module.delete();
        }
        modules.clear();
    }

    public static Map<WheelPosition, Tuple> getSwerveDict() {
        Map<WheelPosition, Tuple> swerveDict = new HashMap<>();
        swerveDict.put(WheelPosition.FrontLeft, new Tuple(0.33, 0.33));
        swerveDict.put(WheelPosition.FrontRight, new Tuple(0.33, -0.33));
        swerveDict.put(WheelPosition.BackLeft, new Tuple(-0.33, 0.33));
        swerveDict.put(WheelPosition.BackRight, new Tuple(-0.33, -0.33));

        return swerveDict;
    }

    public void setMode(DriveMode driveMode) {
        SmartDashboard.putString("mode", driveMode.toString());
        // Re-enable SwerveModules after mode changed from Disabled
        if (mode == DriveMode.DISABLED) {
            for (SwerveModule mod : modules) {
                mod.disable();
            }
        }
    }

    public void drive() {
        // climber.update();
        // shooter.update();
        // intake.update();
        // intake.update();
        // climber.update();
        // shooter.update();
        // Get degrees, convert to radians
        // TODO: This is gonna be here later because we will have a better gyro
        // and it will be possible then - Xander
        // double headingOffset = Math.toRadians(gyro.getAngle());
        double headingOffset = 0;
        /*
         * if(data.getYaw() >=0 && data.getYaw() <= 2*Math.PI) { headingOffset =
         * data.getYaw(); } else { headingOffset = 0;//Robot.corr
         * ectMod((Robot.data.getYaw() + 90 * (180 / Math.PI)), Math.PI * 2); }
         */

        double speed = joy.getSpeed();

        double direction = joy.getDirection() - headingOffset;
        double rotation = joy.getRotation();

        Tuple velocityVector = getVelocityVector(speed, direction);

        SmartDashboard.putNumber("Direction", direction);
        SmartDashboard.putNumber("Speed", speed);
        // tankDrive.update();
        swerveCalc.setAim(velocityVector, rotation);

        for (SwerveModule module : modules) {
            module.update();
        }
    }

    public void move(double speed, double direction, double rotation) {

        if (speed < 0) {
            speed = -speed;
            direction = -direction;
        }

        Tuple velocityVector = getVelocityVector(speed, direction);

        swerveCalc.setAim(velocityVector, rotation);

        for (SwerveModule module : modules) {
            module.update();
        }
    }

    public void stopMovement() {
        for (SwerveModule module : modules) {
            module.disable();
        }
    }

    public static Tuple getVelocityVector(double speed, double direction) {
        // Align our coordinate system with left-handed Cartesian coordinate
        // system
        direction -= 2 * Math.PI;

        Tuple vector = new Tuple((Math.sin(direction) * speed), (Math.cos(direction) * speed));

        System.out.println(vector.getX() + "  :  " + vector.getY());

        return vector;
    }

    public void zeroWheelPositions() {
        for (SwerveModule module : modules) {
            module.zeroPosition();
        }
    }

    public void printPotentiometers() {
        for (SwerveModule mod : modules) {
            mod.printEncoderValue();
        }
        System.out.println("");
    }

    public double getEncoderValue(WheelPosition wheelPosition) {
        System.out.println("Wheel Count: " + modules.size());
        for (int i = 0; i < modules.size(); i++) {
            System.out.println("Wheel Position: " + modules.get(i).getWheelPosition().toString());
            if (modules.get(i).getWheelPosition() == wheelPosition) {
                return modules.get(i).getEncoderValue();
            }
        }
        throw new IllegalArgumentException();
    }
}
