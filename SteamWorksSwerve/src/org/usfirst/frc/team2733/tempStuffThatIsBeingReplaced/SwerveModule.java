package org.usfirst.frc.team2733.tempStuffThatIsBeingReplaced;

import org.usfirst.frc.team2733.robot.AbsoluteEncoder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {

	WheelName name;
	AbsoluteEncoder steerEncoder;
	SpeedController steerMotor;
	SpeedController driveMotor;
	
	//holds the actual values of the wheels
	private double dir;
	private double speed;
	
	//holds the values that the wheels are trying to approach
	private double aimDir;
	private double aimSpeed;
	
	//holds the constants that the speed and direction are updated every second (PID)
	//private double KSpeed = .1;
	//private double KDir = .01;
	
	public SwerveModule(WheelName name, AbsoluteEncoder encoder, SpeedController drive, SpeedController steer) {
		this.name = name;
		this.steerEncoder = encoder;
		this.steerMotor = steer;
		this.driveMotor = drive;
		
		//zeros the values
		dir = 0;
		speed = 0;
		
		aimDir = 0;
		aimSpeed = 0;
	}
	
	public void enable() {
		
	}
	
	public void disable() {
		driveMotor.disable();
	}
	
	public void update() {
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
	
	public void set(double velX, double velY, double rot, float xCoord, float yCoord) {
		double Wxi = velX + (rot*yCoord);
		double Wyi = velY - (rot*xCoord);
		
		aimSpeed = Math.sqrt(Math.pow(Wxi, 2) + Math.pow(Wyi, 2));
		aimDir = Math.atan2(Wxi, Wyi);
		
		update();
	}
	
	public void aimDir(double newDir) {
		aimDir = newDir;
	}
	 
	public void aimSpeed(double newSpeed) {
		aimSpeed = newSpeed;
		//SmartDashboard.putNumber(name + " drive", speed);
        //driveMotor.pidWrite(speed);
	}
	
	public double getDir() {
		return dir;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	protected double returnPIDInput() {
        return steerEncoder.getAbsRotation();
	}

	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber(name + " steer", output);
        
		steerMotor.pidWrite(output);
	}
	
	public enum WheelName {
		FL,
		FR,
		BL,
		BR
		
	}
}
