package org.usfirst.frc.team2733.robot.autonomous;

import org.usfirst.frc.team2733.robot.Robot;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.swerve.Vector_Point_Abomination;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {

    private final DriveTrain driveTrain;
    private final Robot robot;
    private final DataTransfer dataTransfer;
    
    private final InitialLocation initLoc;
    
    public Autonomous (Robot robot, DriveTrain driveTrain) {
        this.robot = robot;
        this.driveTrain = driveTrain;
        
        this.dataTransfer = new DataTransfer(robot);
        
        this.initLoc = InitialLocation.fromValue(DriverStation.getInstance().getLocation());
    }
    
    public void startAutonomous() {
        driveToTower();
        
        //// Movement update with info from RPi
        while (robot.isAutonomous() && robot.isEnabled()) {
            Vector_Point_Abomination velocityVector = dataTransfer.getVelocity();
            
            double rotation = dataTransfer.getRotation();
            
            driveTrain.swerveCalc.setAim(velocityVector, rotation);
            
            // Ball Shooter - If necessary
            // double ballShooterSpeed = networkTable.getNumber("shooterSpeed", 0);
            
            
            Timer.delay(0.1);
        }
    }
    
    private void driveToTower() {
        switch (initLoc) {
        case LEFT:
            
            break;
        case CENTER:
            
            break;
        case RIGHT:
            
            break;
        default:
            System.out.println("Uhh.. The python script isn't telling us where we are!! PANIC!! I guess we will wait for input from the script.");
        }
    }
    
    private enum InitialLocation {
        LEFT (1),
        CENTER (2),
        RIGHT (3);
        
        private final int value;
        
        InitialLocation(int value) {
            this.value = value;
        }
        
        public static InitialLocation fromValue(int value) {
            for (InitialLocation initLoc : InitialLocation.values()) {
              if (initLoc.value == value) {
                return initLoc;
              }
            }
            throw new IllegalArgumentException("No InitialLocation with value " + value + ".");
        }
    }
}