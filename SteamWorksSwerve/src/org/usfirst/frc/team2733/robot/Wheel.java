package org.usfirst.frc.team2733.robot;

public class Wheel {

	//holds the actual values of the wheels
	private double dir;
	private double speed;
	
	//holds the values that the wheels are trying to approach
	private double aimDir;
	private double aimSpeed;
	
	//holds the constants that the speed and direction are updated every second (PID)
	//private double KSpeed = .1;
	//private double KDir = .01;
	
	public Wheel(){
		//zeros the values
		dir = 0;
		speed = 0;
		
		aimDir = 0;
		aimSpeed = 0;
	}
	
	public void update(){
		//approach speed
		//speed += ((aimSpeed - speed) * KSpeed);
		speed = aimSpeed;
		
		//aimDir = aimDir % (Math.PI * 2);
		//approach dir
		
		//if(Math.abs(aimDir - dir) > Math.abs((Math.PI - aimDir) + dir)){
		//	dir -= ((Math.PI - aimDir) + dir) * KDir;
		//}else{
		//	dir += (aimDir - dir) * KDir;
		//}
		dir = aimDir;
		
	}
	
	public void set(double velX, double velY, double rot, float xCoord, float yCoord){
		double Wxi = velX + (rot*yCoord);
		double Wyi = velY - (rot*xCoord);
		
		aimSpeed = Math.sqrt(Math.pow(Wxi, 2) + Math.pow(Wyi, 2));
		aimDir = Math.atan2(Wxi, Wyi);
		
		update();
	}
	
	
	public void aimDir(double newDir){
		aimDir = newDir;
	}
	
	public void aimSpeed(double newSpeed){
		aimSpeed = newSpeed;
	}
	
	public double getDir(){
		return dir;
	}
	
	public double getSpeed(){
		return speed;
	}
	
}
