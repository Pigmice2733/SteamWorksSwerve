package org.usfirst.frc.team2733.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DataTransfer {
 
	boolean isAligned;
	double alignmentFactor;
	
	NetworkTable table;
	
	public DataTransfer() {
		table = NetworkTable.getTable("datatable");
	}
	
	public void receive() {
		
	}
	
	public void reply() {
		
	}
}
