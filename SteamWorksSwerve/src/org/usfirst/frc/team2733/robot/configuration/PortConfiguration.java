package org.usfirst.frc.team2733.robot.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class PortConfiguration {

    private final String configFilePath;

    private final Map<WheelPosition, SpeedController> driveMotorControllers;
    private final Map<WheelPosition, SpeedController> rotationMotorControllers;
    private final Map<WheelPosition, Integer> encoderPorts;

    private int shooterPort, agitatorPort, intakePort, climberPort;

    private int joystickOne, joystickTwo;

    public enum Chassis {
        DEVBOT, COMPBOT;
    }

    private final Chassis chassis;

    /**
     * Construct a port config structure and fill it in
     * 
     * @param shouldReadFromFile
     *            Whether the port values should be read from a file or not
     * @param configFile
     *            The file to read values from, can be left null if using values
     *            from code
     */
    public PortConfiguration(boolean shouldReadFromFile, String configFilePath, Chassis chassis) {
        this.configFilePath = configFilePath;
        this.chassis = chassis;
        driveMotorControllers = new HashMap<>();
        rotationMotorControllers = new HashMap<>();
        encoderPorts = new HashMap<>();

        reloadConfig(shouldReadFromFile);
    }

    /**
     * Reload the config data
     * 
     * @param shouldReadFromFile
     *            Whether the port values should be read from a file or not
     * @param configFile
     *            The name of the file to read from if reading from a file
     */
    public void reloadConfig(boolean shouldReadFromFile) {
        if (shouldReadFromFile) {
            boolean success = tryReadFromFile();
            if (!success) {
                System.out.println("Couldn't read port config file, reading ports from code instead\n");
                initializePorts();
            }

        } else {
            initializePorts();
        }
    }

    /**
     * Gets an unmodifiable reference to the drive motor ports map
     * 
     * @return Unmodifiable reference to the drive motor ports map
     */
    public Map<WheelPosition, SpeedController> getDriveMotorPorts() {
        return Collections.unmodifiableMap(driveMotorControllers);
    }

    /**
     * Gets an unmodifiable reference to the rotation motor ports map
     * 
     * @return Unmodifiable reference to the rotation motor ports map
     */
    public Map<WheelPosition, SpeedController> getRotationMotorPorts() {
        return Collections.unmodifiableMap(rotationMotorControllers);
    }

    /**
     * Gets an unmodifiable reference to the encoder ports map
     * 
     * @return Unmodifiable reference to the encoder map
     */
    public Map<WheelPosition, Integer> getEncoderPorts() {
        return Collections.unmodifiableMap(encoderPorts);
    }

    /**
     * Port for the shooter flywheel
     * 
     * @return Shooter port number
     */
    public int getShooterPort() {
        return shooterPort;

    }

    /**
     * Port for the agitator motor
     * 
     * @return Agitator port number
     */
    public int getAgitatorPort() {
        return agitatorPort;
    }

    /**
     * Port for the climber motor
     * 
     * @return Climber port number
     */
    public int getClimberPort() {
        return climberPort;
    }

    /**
     * Port for the intake motor
     * 
     * @return Intake port number
     */
    public int getIntakePort() {
        return intakePort;
    }

    /**
     * Port for the first joystick
     * 
     * @return
     */
    public int getJoytsickOne() {
        return joystickOne;
    }

    /**
     * Port for the second joystick
     * 
     * @return
     */
    public int getJoystickTwo() {
        return joystickTwo;
    }

    /**
     * Attempts to read config ports from file
     * 
     * @return <i>true</i> if succeeded, <i>false</i> if failed
     */
    private boolean tryReadFromFile() {
        FileInputStream fileStream = null;
        Properties portConfig = new Properties();

        try {
            fileStream = new FileInputStream(configFilePath);
            portConfig.load(fileStream);
            fileStream.close();
        } catch (IOException | NullPointerException ex) {
            if (configFilePath == null) {
                System.out.println("Failed to load port configuration, file path is null\n");
            } else {
                System.out.println("Failed to load port configuration\n");
            }

            try {
                fileStream.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();

            return false;
        }

        // Load port values from portConfig into the maps
        loadFromProperties(portConfig);

        return true;
    }

    /**
     * Initializes ports with hard-coded values
     */
    private void initializePorts() {

        if (this.chassis == Chassis.DEVBOT) {
            driveMotorControllers.put(WheelPosition.FrontLeft, new Talon(4));
            driveMotorControllers.put(WheelPosition.FrontRight, new Talon(0));
            driveMotorControllers.put(WheelPosition.BackLeft, new Talon(6));
            driveMotorControllers.put(WheelPosition.BackRight, new Talon(2));

            rotationMotorControllers.put(WheelPosition.FrontLeft, new Spark(5));
            rotationMotorControllers.put(WheelPosition.FrontRight, new Spark(1));
            rotationMotorControllers.put(WheelPosition.BackLeft, new Spark(7));
            rotationMotorControllers.put(WheelPosition.BackRight, new Spark(3));

        } else if (this.chassis == Chassis.COMPBOT) {
            driveMotorControllers.put(WheelPosition.FrontLeft, new CANTalon(18));
            driveMotorControllers.put(WheelPosition.FrontRight, new CANTalon(16));
            driveMotorControllers.put(WheelPosition.BackLeft, new CANTalon(12));
            driveMotorControllers.put(WheelPosition.BackRight, new CANTalon(11));

            rotationMotorControllers.put(WheelPosition.FrontLeft, new CANTalon(5));
            rotationMotorControllers.put(WheelPosition.FrontRight, new CANTalon(15));
            rotationMotorControllers.put(WheelPosition.BackLeft, new CANTalon(14));
            rotationMotorControllers.put(WheelPosition.BackRight, new CANTalon(13));
        }

        encoderPorts.put(WheelPosition.FrontLeft, 2);
        encoderPorts.put(WheelPosition.FrontRight, 0);
        encoderPorts.put(WheelPosition.BackLeft, 3);
        encoderPorts.put(WheelPosition.BackRight, 1);

        shooterPort = 0;
        agitatorPort = 1;
        intakePort = 3;
        climberPort = 2;

        joystickOne = 0;
        joystickTwo = 1;

    }

    /**
     * Load port values from Properties into the maps
     * 
     * @param portsConfig
     *            The Properties to load from
     */
    private void loadFromProperties(Properties portConfig) {

        encoderPorts.put(WheelPosition.FrontLeft, Integer.parseInt(portConfig.getProperty("encoderFL")));
        encoderPorts.put(WheelPosition.FrontRight, Integer.parseInt(portConfig.getProperty("encoderFR")));
        encoderPorts.put(WheelPosition.BackLeft, Integer.parseInt(portConfig.getProperty("encoderBL")));
        encoderPorts.put(WheelPosition.BackRight, Integer.parseInt(portConfig.getProperty("encoderBR")));

        shooterPort = Integer.parseInt(portConfig.getProperty("shooter"));
        agitatorPort = Integer.parseInt(portConfig.getProperty("agitator"));
        intakePort = Integer.parseInt(portConfig.getProperty("intake"));
        climberPort = Integer.parseInt(portConfig.getProperty("climber"));

        joystickOne = Integer.parseInt(portConfig.getProperty("joyOne"));
        joystickTwo = Integer.parseInt(portConfig.getProperty("joyTwo"));
    }

}
