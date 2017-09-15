package org.usfirst.frc.team2733.robot.systems.swervedrive;

import org.usfirst.frc.team2733.robot.enumerations.PIEnum;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;
import org.usfirst.frc.team2733.robot.utilities.Tuple;

import edu.wpi.first.wpilibj.SpeedController;

public class SwerveModule {

    // Wheel angle always needs to be between 0pi and 2pi
    private double speed, angle = 0;

    private final WheelPosition wheelPos;

    private final SwerveCalc swerveCalc;

    private DriveMotor driveMotor = null;
    private final RotationMotor rotationMotor;

    private static final double MAX_ACCELERATION = 10.208 - 3;

    double lastSpeed = 0;

    // In order to allow update to be called at any interval
    long lastUpdate = 0;

    /**
     * Holds the motor objects and calculates the optimal movements
     * 
     * @param driveMotorPort
     *            Port for the drive motor
     * @param rotationMotorPort
     *            Port for the rotation motor
     * @param encoderPort
     *            Port for the encoder (analog potentiometer)
     * @param wheelPos
     *            WheelPosition for this swerve module
     * @param swerveCalc
     *            Handle to the swerveCalc for this drive train
     * @param analogOffset
     *            Offset for the encoder
     */
    public SwerveModule(SpeedController driveSpeedController, SpeedController rotationSpeedController, int encoderPort,
            WheelPosition wheelPos, SwerveCalc swerveCalc, double analogOffset) {
        PIEnum P_Coef;
        PIEnum I_Coef;

        switch (wheelPos) {
        case FrontRight:
            P_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_P;
            I_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_I;
            break;
        case FrontLeft:
            P_Coef = PIEnum.MODULE_ROTATION_FRONT_LEFT_P;
            I_Coef = PIEnum.MODULE_ROTATION_FRONT_LEFT_I;
            break;
        case BackRight:
            P_Coef = PIEnum.MODULE_ROTATION_BACK_RIGHT_P;
            I_Coef = PIEnum.MODULE_ROTATION_BACK_RIGHT_I;
            break;
        case BackLeft:
            P_Coef = PIEnum.MODULE_ROTATION_BACK_LEFT_P;
            I_Coef = PIEnum.MODULE_ROTATION_BACK_LEFT_I;
            break;
        default:
            P_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_P;
            I_Coef = PIEnum.MODULE_ROTATION_FRONT_RIGHT_I;
            break;
        }

        driveMotor = new DriveMotor(driveSpeedController, PIEnum.MODULE_DRIVE_P.getCoefficient(),
                PIEnum.MODULE_DRIVE_I.getCoefficient());
        rotationMotor = new RotationMotor(rotationSpeedController, P_Coef.getCoefficient(), I_Coef.getCoefficient(),
                encoderPort, analogOffset);

        this.swerveCalc = swerveCalc;
        this.wheelPos = wheelPos;
    }

    // Updates the components
    public void update() {
        double targSpeed = swerveCalc.getVelAim(wheelPos);

        // Delta Time in seconds
        double deltaTime = (System.nanoTime() - lastUpdate) / Math.pow(10, 9);
        double acceleration = (targSpeed - lastSpeed) / (deltaTime);
        if (acceleration >= MAX_ACCELERATION) {
            acceleration = MAX_ACCELERATION;
        } else if (acceleration <= -MAX_ACCELERATION) {
            acceleration = -MAX_ACCELERATION;
        }
        targSpeed = acceleration * deltaTime + lastSpeed;

        lastSpeed = targSpeed;
        lastUpdate = System.nanoTime();

        double targAngle = swerveCalc.getRotAim(wheelPos);
        
        Tuple<Double> angleSpeed = swerveCalc.calcOptimalHeading(targSpeed, targAngle, angle);
        speed = angleSpeed.getX();
        angle = angleSpeed.getY();

        driveMotor.update(speed);
        rotationMotor.update(angle);
    }

    /**
     * Stop all motor motion
     */
    public void stopMotors() {
        driveMotor.stop();
        rotationMotor.stop();
    }

    /**
     * Free all resources
     */
    public void free() {
        driveMotor.free();
        rotationMotor.free();
    }

    // For Testing Purpose
    // START
    public double getSpeed() {
        return speed;
    }

    public double getAngle() {
        return angle;
    }
    // END

    /**
     * Turn wheel to zero and stop drive motor
     */
    public void zeroPosition() {
        driveMotor.update(0);
        rotationMotor.update(0);
    }

    /**
     * Get the WheelPosition of this module
     * 
     * @return The wheel position of this swerve module
     */
    public WheelPosition getWheelPosition() {
        return wheelPos;
    }

    /**
     * Get encoder value of rotation motor
     * 
     * @return
     */
    public double getEncoderValue() {
        return rotationMotor.getEncoderValue();
    }

}
