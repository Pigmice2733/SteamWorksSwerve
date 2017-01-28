package org.usfirst.frc.team2733.robot;

public class Wheel {

	//holds the actual values of the wheels
	private double dir;
	private double speed;
	
	//holds the values that the wheels are trying to approach
	private double aimDir;
	private double aimSpeed;
	
	//holds the constants that the speed and direction are updated every second (PID)
	private double KSpeed = .1;
	private double KDir = .1;
	
	public Wheel(){
		//zeros the values
		dir = 0;
		speed = 0;
		
		aimDir = 0;
		aimSpeed = 0;
	}
	
	public void update(){
		//approach speed
		speed += ((aimSpeed - speed) * KSpeed);
		
		//approach dir
		if(Math.abs(aimDir - dir) > Math.abs((Math.PI - aimDir) + dir)){
			dir -= ((Math.PI - aimDir) + dir) * KDir;
		}else{
			dir += (aimDir - dir) * KDir;
		}
		
	}
	
	public void aimDir(double newDir){
		aimDir = newDir;
	}
	
	public void aimSpeed(double newSpeed){
		aimSpeed = newSpeed;
	}
	
	public double getDir(){
		if(speed > 0){
			return dir;
		}else{
			return dir + Math.PI;
		}
	}
	
	public double getSpeed(){
		return speed;
	}
	
}