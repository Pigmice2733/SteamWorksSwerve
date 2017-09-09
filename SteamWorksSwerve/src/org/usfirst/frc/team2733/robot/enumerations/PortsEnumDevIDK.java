package org.usfirst.frc.team2733.robot.enumerations;

public enum PortsEnumDevIDK {

    // Joystick ports
    JOYSTICK_ONE(0), JOYSTICK_TWO(1),

    // Motors
    SHOOTER(4), BALL_RELEASE(4), CLIMBER(5), INTAKE(6),

    FRONT_LEFT_DRIVE_MOTOR(2), FRONT_RIGHT_DRIVE_MOTOR(0), BACK_LEFT_DRIVE_MOTOR(6), BACK_RIGHT_DRIVE_MOTOR(5),

    FRONT_LEFT_ROTATION_MOTOR(3), FRONT_RIGHT_ROTATION_MOTOR(1), BACK_LEFT_ROTATION_MOTOR(7), BACK_RIGHT_ROTATION_MOTOR(
            4),

    // Analog encoders
    FRONT_RIGHT_ROTATION_ANALOG_ENCODER(0), BACK_RIGHT_ROTATION_ANALOG_ENCODER(1), FRONT_LEFT_ROTATION_ANALOG_ENCODER(
            2), BACK_LEFT_ROTATION_ANALOG_ENCODER(3);

    private int port;

    PortsEnumDevIDK(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

}
