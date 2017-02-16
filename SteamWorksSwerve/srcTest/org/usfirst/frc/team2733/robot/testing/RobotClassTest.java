package org.usfirst.frc.team2733.robot.testing;

import org.junit.Assert;
import org.junit.Test;

public class RobotClassTest {
    
    @Test
    public void robotClassTest() {
        try {
            Class.forName("org.usfirst.frc.team2733.robot.Robot");
        } catch (Exception e) {
            Assert.fail();
        }
    }
    
}
