package org.usfirst.frc.team2733.robot.testing;

import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team2733.robot.RobotMap;

public class AdditionTest {

	@Test
	public void exampleTest() {
		Assert.assertEquals(2 + 2, 4);
	}

	@Test
	public void robotMapTest() {
		Assert.assertEquals(RobotMap.test(), 3);
	}

}
