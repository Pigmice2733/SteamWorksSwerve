package org.usfirst.frc.team2733.robot.autonomous;

import org.usfirst.frc.team2733.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DataTransfer {
 
	NetworkTable networkTable;
	
	public DataTransfer(Robot robot) {
		networkTable = NetworkTable.getTable("datatable");
		
		while (!networkTable.isConnected() && robot.isTest() && robot.isEnabled()) {
		    Timer.delay(0.2);
            System.out.println("Uhh.. The network table still isn't connected. You should start to panic.");
        }
	}
	
	// Distance from ultrasonic sensor to peg
	public double getDistance() {
	    return networkTable.getNumber("distance", 0);
    }
	
	// Distance from side to side for robot to align to peg
	public double getDirection() {
	    return networkTable.getNumber("direction", 0);
	}
	
	public void autonoousOverMessage() {
		networkTable.putBoolean("autoOver", true);
	}
}
