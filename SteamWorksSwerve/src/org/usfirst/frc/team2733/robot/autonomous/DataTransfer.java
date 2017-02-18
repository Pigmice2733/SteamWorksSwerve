package org.usfirst.frc.team2733.robot.autonomous;

import org.usfirst.frc.team2733.robot.Robot;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.swerve.Vector_Point_Abomination;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DataTransfer {
 
	boolean isAligned;
	double alignmentFactor;
	
	NetworkTable networkTable;
	
	public DataTransfer(Robot robot) {
		networkTable = NetworkTable.getTable("datatable");
		
		while (!networkTable.isConnected() && robot.isAutonomous() && robot.isEnabled()) {
		    Timer.delay(0.2);
            System.out.println("Uhh.. The network table still isn't connected. You should start to panic.");
        }
	}
	
	public Vector_Point_Abomination getVelocity() {
	    double speed = networkTable.getNumber("speed", 0);
        double direction = networkTable.getNumber("direction", 0);
        
        return DriveTrain.getVelocityVector(speed, direction);
	}
	
	public double getRotation() {
	    return networkTable.getNumber("rotation", 0);
    }
	
	public void autonoousOverMessage() {
		networkTable.putBoolean("autoOver", true);
	}
}
