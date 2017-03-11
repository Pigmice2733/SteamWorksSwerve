package org.usfirst.frc.team2733.robot;

import java.util.ArrayList;

import org.usfirst.frc.team2733.robot.enumerations.PortsEnum;

import com.ctre.CANTalon;

public class TankDrive {
    
    JoystickInput joy;
    
    ArrayList<CANTalon> talons = new ArrayList<>();
    CANTalon fr;
    CANTalon fl;
    CANTalon br;
    CANTalon bl;
    
    public TankDrive(JoystickInput joy) {
        this.joy = joy;
        
        bl = new CANTalon(PortsEnum.FRONT_RIGHT_DRIVE_MOTOR.getPort());
        
        fr = new CANTalon(PortsEnum.BACK_LEFT_DRIVE_MOTOR.getPort());
        fl = new CANTalon(PortsEnum.BACK_RIGHT_DRIVE_MOTOR.getPort());
        
        CANTalon rotation1 = new CANTalon(PortsEnum.FRONT_LEFT_ROTATION_MOTOR.getPort());
        CANTalon rotation2 = new CANTalon(PortsEnum.FRONT_RIGHT_ROTATION_MOTOR.getPort());
        CANTalon rotation3 = new CANTalon(PortsEnum.BACK_LEFT_ROTATION_MOTOR.getPort());
        CANTalon rotation4 = new CANTalon(PortsEnum.BACK_RIGHT_ROTATION_MOTOR.getPort());
        
        rotation1.enableBrakeMode(true);
        rotation2.enableBrakeMode(true);
        rotation3.enableBrakeMode(true);
        rotation4.enableBrakeMode(true);
        
    }
    
    public void update() {
        double leftSide = joy.getTankLeft();
        double rightSide = -joy.getTankRight();
        
        //fl.set(leftSide);
        bl.set(leftSide);
        fr.set(rightSide);
        fl.set(leftSide);
        
        //bl.set(rightSide);
        //br.set(rightSide);
        /*double speed = joy.getTankVertical();
        
        double tankSpeed = joy.getTankHorizontal();
        
        double leftSide = speed + tankSpeed;
        double rightSide = speed - tankSpeed;
        
        fl.set(leftSide);
        fr.set(leftSide);
        bl.set(rightSide);
        br.set(rightSide);*/
    }
}
