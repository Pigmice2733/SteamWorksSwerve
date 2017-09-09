package org.usfirst.frc.team2733.robot;

import java.util.List;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class TestMode {

    private List<Testable> testables;

    public TestMode(List<Testable> systemsToTest) {
        testables = systemsToTest;
    }

    public void runTests() {
        for (Testable systemToTest : testables) {
            systemToTest.test();
        }
    }

    public void portsTest() {
        // portTest(PortsEnum.FRONT_LEFT_ROTATION_MOTOR.getPort());
        // portTest(PortsEnum.FRONT_LEFT_DRIVE_MOTOR.getPort());
    }

    public void portTest(int port) {
        CANTalon canTalon = new CANTalon(port);
        canTalon.set(1.0);
        Timer.delay(2.0);
        canTalon.set(0.0);
        canTalon.delete();
    }

    public void portTestTalon(int port) {
        Talon talon = new Talon(port);
        talon.set(0.35);
        Timer.delay(2.0);
        talon.set(0.0);
        talon.free();
    }

    public void portTestSpark(int port) {
        Spark spark = new Spark(port);
        spark.set(0.35);
        Timer.delay(2.0);
        spark.set(0.0);
        spark.free();
    }

}