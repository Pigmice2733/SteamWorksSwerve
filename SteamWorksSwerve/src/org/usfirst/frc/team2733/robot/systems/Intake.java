package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;

import edu.wpi.first.wpilibj.Talon;

public class Intake {

    Talon motor;
    JoystickInput joy;
    public double speed = 0.6;
    
    public Intake(JoystickInput joy) {
        this.joy = joy;
        motor = new Talon(PortsEnum.INTAKE.getPort());
    }
    
    public void update() {
        if (joy.getIntake()) {
            motor.set(-speed);
        } else {
            motor.set(0);
        }
    }
}
