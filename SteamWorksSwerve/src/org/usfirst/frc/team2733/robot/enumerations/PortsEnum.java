package org.usfirst.frc.team2733.robot.enumerations;

public enum PortsEnum {
	
	//conrollers
	JOYSTICK_ONE (0),
	JOYSTICK_TWO (1),
	
	//sensors
	GYRO (8),
	
	//motors
	SHOOTER (4),
	CLIMBER (5),
	INTAKE (6), 
	
	FRONT_RIGHT_DRIVE_MOTOR (0),
	BACK_RIGHT_DRIVE_MOTOR (0),
	FRONT_LEFT_DRIVE_MOTOR (0),
	BACK_LEFT_DRIVE_MOTOR (0),
	
	FRONT_RIGHT_ROTATION_MOTOR (0),
	BACK_RIGHT_ROTATION_MOTOR (1),
	FRONT_LEFT_ROTATION_MOTOR (2),
	BACK_LEFT_ROTATION_MOTOR (3),

	//encoders
	FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_ONE (0),
	BACK_RIGHT_ROTATION_DIGITAL_ENCODER_ONE (2),
	FRONT_LEFT_ROTATION_DIGITAL_ENCODER_ONE (4),
	BACK_LEFT_ROTATION_DIGITAL_ENCODER_ONE (6),
	
	FRONT_RIGHT_ROTATION_DIGITAL_ENCODER_TWO (1),
	BACK_RIGHT_ROTATION_DIGITAL_ENCODER_TWO (3),
	FRONT_LEFT_ROTATION_DIGITAL_ENCODER_TWO (5),
	BACK_LEFT_ROTATION_DIGITAL_ENCODER_TWO (7),

	FRONT_RIGHT_ROTATION_ANALOG_ENCODER (0),
	BACK_RIGHT_ROTATION_ANALOG_ENCODER (1),
	FRONT_LEFT_ROTATION_ANALOG_ENCODER (2),
	BACK_LEFT_ROTATION_ANALOG_ENCODER (3);
	
	private int port;
	
	PortsEnum(int port){
		this.port = port;
	}
	
	public int getPort(){
		return port;
	}
	
}
