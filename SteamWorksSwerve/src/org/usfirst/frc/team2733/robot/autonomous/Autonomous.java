package org.usfirst.frc.team2733.robot.autonomous;

import org.usfirst.frc.team2733.robot.PID;
import org.usfirst.frc.team2733.robot.Robot;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {

    private final DriveTrain driveTrain;
    private final Robot robot;
    private final DataTransfer dataTransfer;
    
    private PID lateralMovementPID, forwardMovementPID;
    
    private final InitialLocation initLoc;
    
    public Autonomous (Robot robot, DriveTrain driveTrain) {
        this.robot = robot;
        this.driveTrain = driveTrain;
        
        this.lateralMovementPID = new PID(0.01, 0);
        this.forwardMovementPID = new PID(0.01, 0);
        
        this.dataTransfer = new DataTransfer(robot);
        
        this.initLoc = InitialLocation.fromValue(DriverStation.getInstance().getLocation());
    }
    
    public void startAutonomous() {
        driveToTower();
        
        double distance, direction;
        
        //// Movement update with info from RPi
        while (robot.isAutonomous() && robot.isEnabled()) {
            
            direction = dataTransfer.getDirection();
            distance = dataTransfer.getDistance();
            
            if(Math.abs(direction) > 1) {
                
                double lateralMovement = lateralMovementPID.getVal(direction, 0);
                
                driveTrain.moveSideways(lateralMovement);
                
            } else if (distance > 25) {
                
                double forwardMovement = forwardMovementPID.getVal(distance, 25);
                
                driveTrain.moveForwards(forwardMovement);
                
            } else {
                
                // Place gear
                
                driveTrain.stopMovement();
                
            }
            
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