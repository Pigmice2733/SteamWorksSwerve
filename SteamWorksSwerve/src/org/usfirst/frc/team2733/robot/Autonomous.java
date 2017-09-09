package org.usfirst.frc.team2733.robot;

import org.usfirst.frc.team2733.robot.comm.DataTransfer;
import org.usfirst.frc.team2733.robot.systems.AbstractDriveTrain;
import org.usfirst.frc.team2733.robot.utilities.PID;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {

    private final AbstractDriveTrain driveTrain;
    private final Robot robot;
    private final DataTransfer dataTransfer;
    
    private final PID rotationalPID, lateralMovementPID, forwardMovementPID;
    
    private final InitialLocation initLoc;
    
    public Autonomous (Robot robot, AbstractDriveTrain driveTrain) {
        this.robot = robot;
        this.driveTrain = driveTrain;
        
        this.lateralMovementPID = new PID(0.25, 0);
        this.forwardMovementPID = new PID(0.05, 0);
        this.rotationalPID = new PID(0.05, 0);
        
        this.dataTransfer = new DataTransfer(robot);
        
        this.initLoc = InitialLocation.fromValue(DriverStation.getInstance().getLocation());
    }
    
    public void startAutonomous() {
        double distance, direction;
        
        // Movement update with info from RPi
        while (robot.isAutonomous() && robot.isEnabled()) {
            
            direction = dataTransfer.getDirection();
            distance = dataTransfer.getDistance();
            
            // Straighten robot
            if(Math.abs(dataTransfer.getYaw()) > 5) {
                
                double rotationalVelocity = rotationalPID.getVal(dataTransfer.getYaw(), 0.0);
                driveTrain.swerveDrive(0, 0, rotationalVelocity, 0.0);
                
            // Align robot laterally
            } else if(Math.abs(direction) > 1) {
                
                double lateralMovement = lateralMovementPID.getVal(direction, 0);
                
                driveTrain.swerveDrive(lateralMovement, 0.5 * Math.PI, 0.0, dataTransfer.getYaw());
            
            // Move robot forward to gear lift
            } else if (distance > 25) {
                
                double forwardMovement = forwardMovementPID.getVal(distance, 25);
                
                driveTrain.swerveDrive(forwardMovement, 0, 0, 0);
                
            // Stop robot
            } else {
                driveTrain.stopMovement();
            }
            
            Timer.delay(0.075);
        }
    }
    
    @SuppressWarnings("unused") //can someone confirm if this is used
	private void driveToTower() {
        switch (initLoc) {
        case LEFT:
            
            
            
            break;
        case CENTER:
            
            // Distance is distance from robot's starting position to base of tower (in meters)
            double distance = 5;
            double startTime = Timer.getFPGATimestamp();
            
            while (distance > 0) {
                driveTrain.swerveDrive(0.1, 0, 0, 0);
                distance -= dataTransfer.velocityY() * (Timer.getFPGATimestamp() - startTime);
            }
            
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