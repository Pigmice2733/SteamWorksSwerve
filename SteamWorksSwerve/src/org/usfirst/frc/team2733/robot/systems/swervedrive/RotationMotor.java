package org.usfirst.frc.team2733.robot.systems.swervedrive;

import org.usfirst.frc.team2733.robot.utilities.Modulus;
import org.usfirst.frc.team2733.robot.utilities.PID;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Rotation motor controller
 */
public class RotationMotor {

    // Rotation motor
    private final SpeedController rotationMotor;

    // Encoder
    private AnalogPotentiometer analogPoten;

    double motorSpeed;

    // PID controller
    private final PID PIController;

    /**
     * Rotation motor constructor
     * 
     * @param motorPort
     *            Rotation motor port
     * @param P
     *            P coefficient for PID controller
     * @param I
     *            I coefficient for PID controller
     * @param encoderPort
     *            Encoder port
     * @param encoderOffset
     *            Offset for encoder
     */
    public RotationMotor(SpeedController speedController, double P, double I, int encoderPort, double encoderOffset) {
        rotationMotor = speedController;

        analogPoten = new AnalogPotentiometer(encoderPort, 1, encoderOffset);

        PIController = new PID(P, I);
        PIController.setInputBounds(2 * Math.PI, 0);
        PIController.setContinuous(true);

        PIController.setOutputBounds(1.0, -1.0);

        motorSpeed = 0;
    }

    /**
     * Set target angle and apply PID update
     * 
     * @param setAngle
     *            The angle (in radians) to turn wheel to
     */
    public void update(double setAngle) {

        double currentAngle = Modulus.modulus(analogPoten.get(), 1) * 2 * Math.PI;

        motorSpeed = PIController.getVal(currentAngle, setAngle);

        rotationMotor.set(motorSpeed);
    }

    /**
     * Stop rotation motor
     */
    public void stop() {
        rotationMotor.disable();
    }

    /**
     * Free all resources
     */
    public void free() {
        analogPoten.free();
    }

    /**
     * Get encoder value (range 0-1);
     * 
     * @return
     */
    public double getEncoderValue() {
        return Modulus.modulus(analogPoten.get(), 1);
    }

}