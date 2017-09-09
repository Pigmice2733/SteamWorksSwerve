package org.usfirst.frc.team2733.robot.comm;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class JoystickInput {

    // private double lastDirection;
    private Joystick driverJoyOne;
    private XboxController driverJoyTwo;

    public JoystickInput(int port0, int port1) {
        driverJoyOne = new Joystick(port0);
        driverJoyTwo = new XboxController(port1);
        // lastDirection = 0;
    }

    public double getSpeed() {
        double speed = driverJoyOne.getMagnitude();

        speed = (Math.abs(speed) < 0.1) ? 0 : speed;

        speed *= speed;

        speed = (Math.abs(speed) > 1.0) ? (Math.signum(speed) * 1.0) : speed;

        speed *= 0.6;

        return speed;
    }

    public double getSpeedMulti() {
        double speedMulti = driverJoyOne.getRawAxis(3);

        speedMulti *= -0.5;
        speedMulti += 1.0;

        if (speedMulti > 0.1) {
            return speedMulti;
        } else {
            return 0.1;
        }

    }

    public double getDirection() {
        double radians = driverJoyOne.getDirectionRadians();

        System.out.println("Direction: " + radians);

        /*
         * radians = (radians < 0) ? ((2 * Math.PI) + radians) : radians;
         * 
         * radians = (driverJoyOne.getMagnitude() < .1) ? lastDirection :
         * radians;
         * 
         * lastDirection = radians;
         */

        return (2 * Math.PI) - radians;
    }

    public double getRotation() {
        double rotationSpeed = driverJoyOne.getTwist();// * getSpeedMulti();
        // double rotationSpeed = joy2.getRawAxis(0);
        rotationSpeed = (Math.abs(rotationSpeed) < 0.5) ? 0
                : (rotationSpeed < 0) ? rotationSpeed + 0.5 : rotationSpeed - 0.5;
        // rotationSpeed *= 0.8;
        return rotationSpeed;
    }

    public boolean isButtonPressed(JoyStickButton button) {
        return driverJoyOne.getRawButton(button.getRawButtonNumber());
    }

    public boolean getShooter1() {
        return driverJoyTwo.getBumper(Hand.kLeft);
    }

    public double getClimber() {
        if (driverJoyTwo.getTriggerAxis(Hand.kLeft) >= 0.1) {
            return driverJoyTwo.getTriggerAxis(Hand.kLeft);
        } else if (driverJoyTwo.getTriggerAxis(Hand.kRight) >= 0.1) {
            return -driverJoyTwo.getTriggerAxis(Hand.kRight);
        } else {
            return 0;
        }
    }

    public boolean getIntake() {
        return driverJoyTwo.getXButton();
    }

    public boolean getBallRelease() {
        return driverJoyTwo.getBumper(Hand.kRight);
    }

    public enum JoyStickButton {
        CLIMBER(2), SHOOTER(1), INTAKE(3);

        private int rawButtonNumber;

        private JoyStickButton(int rawButtonNumber) {
            this.rawButtonNumber = rawButtonNumber;
        }

        public int getRawButtonNumber() {
            return rawButtonNumber;
        }
    }

    public double getTankLeft() {
        if (Math.abs(driverJoyTwo.getRawAxis(1)) < 0.15) {
            return 0;
        }
        return -driverJoyTwo.getRawAxis(1);
    }

    public double getTankRight() {
        if (Math.abs(driverJoyTwo.getRawAxis(5)) < 0.15) {
            return 0;
        }
        return -driverJoyTwo.getRawAxis(5);
    }

    public double getTankVertical() {
        if (Math.abs(driverJoyTwo.getRawAxis(1)) < 0.1) {
            return 0;
        }
        return -driverJoyTwo.getRawAxis(1);
    }

    // Positive is right
    // Negative is left
    public double getTankHorizontal() {
        if (Math.abs(driverJoyTwo.getRawAxis(0)) < 0.1) {
            return 0;
        }
        return driverJoyTwo.getRawAxis(0);
    }
}