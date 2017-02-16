package org.usfirst.frc.team2733.robot.testing;

import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team2733.robot.driveTrain.DriveTrain;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc;
import org.usfirst.frc.team2733.robot.swerve.SwerveCalc.AngleSpeedObject;

public class OptimalAngleTest {
    
    private static final double uncertainty = 0.001;
    
    @Test
    public void optimalAngleTest() {
        testAngle(0, 0.8 * Math.PI, 1, -0.2 * Math.PI, -1);
        testAngle(0, 0.3 * Math.PI, 1, 0.3 * Math.PI, 1);
        testAngle(0, -0.3 * Math.PI, 1, -0.3 * Math.PI, 1);
        testAngle(0, 1.3 * Math.PI, 1, 0.3 * Math.PI, -1);
    }
    
    public void testAngle(double startAngle, double targetAngle, double targetSpeed, double expectedAngle, double expectedSpeed) {
        SwerveCalc swerveCalc = new SwerveCalc(DriveTrain.getSwerveDict());

        // Target Speed doesn't matter for the test so it is always 1 (can't be 0)
        AngleSpeedObject angelSpeedObject = swerveCalc.calcOptimalHeading(startAngle, targetAngle, startAngle);
        if (!almostEqual(angelSpeedObject.getAngle(), startAngle, uncertainty) && angelSpeedObject.getSpeed() == expectedSpeed) {
            Assert.fail();
        }
    }
    
    public static boolean almostEqual(double a, double b, double uncertainty){
        return Math.abs(a - b) < uncertainty;
    }
}
