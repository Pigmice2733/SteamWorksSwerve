package org.usfirst.frc.team2733.robot.driveTrain;

import org.usfirst.frc.team2733.robot.AbsoluteEncoder;
import org.usfirst.frc.team2733.robot.AbsoluteEncoder.RotationType;

public class Coordinator {
	
	AbsoluteEncoder	swerveEncoder;
	double k = 1;
	double offset = 0;
	
	double targAng;
	double targSpeed;
	
	/**
	 * Initializes the coordinators for the swerve modules.
	 * @param encoderPort The port for the encoder associated with a particular rotation motor
	 */
	
	Coordinator(RotationMotor rotationMotor, DriveMotor swerveMotor, int encoderPort){
		swerveEncoder = new AbsoluteEncoder(encoderPort, this.k, this.offset);
	}
	
	/**
	 * Sets the targAng and targSpeed variables to the most efficient values
	 * @param Angle The angle that is targeted. 
	 * @param Speed The speed that is targeted.
	 */
	public void setTarget(double angle, double speed){ /*UNFINISHED*/
		if(swerveEncoder.getRotationType() == RotationType.Radians){
			if(angle == swerveEncoder.getAbsRotation()){
				targAng = angle;
			}else if((swerveEncoder.getAbsRotation() <= (angle + (Math.PI / 2)) && (swerveEncoder.getAbsRotation() >= (angle + (Math.PI / 2))))){
				
			}
		}
		Math.
	}
	
}
