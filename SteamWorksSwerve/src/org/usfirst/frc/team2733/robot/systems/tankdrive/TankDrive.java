package org.usfirst.frc.team2733.robot.systems.tankdrive;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team2733.robot.systems.AbstractDriveTrain;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class TankDrive extends AbstractDriveTrain {

    List<Integer> leftPorts;
    List<Integer> rightPorts;
    
    List<CANTalon> leftSlaves = new ArrayList<>();
    List<CANTalon> rightSlaves = new ArrayList<>();
    
    CANTalon leftMaster;
    CANTalon rightMaster;
    
    // State variable tracking whether or not resources have been initialized
    private boolean isInitialized;
    
    public TankDrive(List<Integer> leftPorts, List<Integer> rightPorts) {
        this.leftPorts = leftPorts;
        this.rightPorts = rightPorts;
    }
       
    
    @Override
    public void initialize() {
        if(isInitialized) {
            System.out.println("Tank initialized() is called when already initialized\n");
            return;
        }
        
        leftMaster = new CANTalon(leftPorts.get(0));
        rightMaster = new CANTalon(rightPorts.get(0));
        
        createSlaves(leftPorts, leftMaster, leftSlaves);
        createSlaves(rightPorts, rightMaster, rightSlaves);
        
        isInitialized = true;
    }
    
    @Override
    public void releaseReources() {
        if(!isInitialized) {
            System.out.println("Tank releaseResources() is called when not initialized\n");
            return;
        }
        
        freeSlaves(leftSlaves);
        
        freeSlaves(rightSlaves);
        
        isInitialized = false;
    }
    
    @Override
    public boolean isInitialized() {
        return isInitialized;
    }
    
    @Override
    public void ready() {
        if(!isInitialized) {
            System.out.println("Tank ready() is called before initialize()\n");
            return;
        }
    }
    
    @Override
    public void swerveDrive(double speed, double direction, double rotation, double gyroOffset) {
        if(!isInitialized) {
            System.out.println("Tank swerveDrive() is called before initialize()\n");
            return;
        }
        // TODO: implemented swerve drive for a tank drive train
    }
    
    @Override
    public void tankDrive(double leftSpeed, double rightSpeed, double gyroOffset) {
        if(!isInitialized) {
            System.out.println("Tank tankDrive() is called before initialize()\n");
            return;
        }
        
        leftMaster.set(leftSpeed);
        rightMaster.set(rightSpeed);
    }
    
    @Override
    public void stopMovement() {
        if(!isInitialized) {
            System.out.println("Tank stopMovement() is called before initialize()\n");
            return;
        }
        
        leftMaster.disable();
        rightMaster.disable();
    }
    
    /**
     * Creates a slave of master for each port except port number 0, adds them to slaves
     * @param ports List of ports for the slaves
     * @param master CANTalon to use as the master
     * @param slaves List to hold the slaves
     */
    private void createSlaves(List<Integer> ports, CANTalon master, List<CANTalon> slaves) {
        for(int i = 1; i < ports.size(); i++) {
            CANTalon slave = new CANTalon(ports.get(i));
            slave.changeControlMode(TalonControlMode.Follower);
            slave.set(master.getDeviceID());
            slaves.add(slave);
        }
    }
    /**
     * Frees all slaves
     * @param slaves The list of slaves to free
     */
    private void freeSlaves(List<CANTalon> slaves) {
        for(CANTalon slave : slaves) {
            slave.delete();
        }
    }

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}
}
