package org.usfirst.frc.team2733.robot.testing;

import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team2733.robot.systems.swervedrive.SwerveCalc;
import org.usfirst.frc.team2733.robot.systems.swervedrive.SwerveDriveTrain;
import org.usfirst.frc.team2733.robot.systems.swervedrive.Tuple;

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
        SwerveCalc swerveCalc = new SwerveCalc(SwerveDriveTrain.getSwerveDict());
        
        // Target Speed doesn't matter for the test so it is always 1 (can't be 0)
        Tuple<Double> angelSpeed = swerveCalc.calcOptimalHeading(startAngle, targetAngle, startAngle);
        if (!almostEqual(angelSpeed.getY(), startAngle, uncertainty) && angelSpeed.getX() == expectedSpeed) {
            Assert.fail();
        }
    }
    
    public static boolean almostEqual(double a, double b, double uncertainty){
        return Math.abs(a - b) < uncertainty;
    }
}
