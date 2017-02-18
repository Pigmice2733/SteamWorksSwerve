package org.usfirst.frc.team2733.robot.systems;

import org.usfirst.frc.team2733.robot.JoystickInput;
import org.usfirst.frc.team2733.robot.JoystickInput.JoyStickButton;

import edu.wpi.first.wpilibj.Talon;

public class Intake {

    Talon motor;
    JoystickInput joy;
    public double speed = 1.0;
    
    public Intake(int motorPort, JoystickInput joy) {
        motor = new Talon(motorPort);
        this.joy = joy;
    }
    
    public void update() {
        if(joy.isButtonPressed(JoyStickButton.INTAKE)) {
            motor.set(speed);
        } else {
            motor.disable();
        }
    }
}
