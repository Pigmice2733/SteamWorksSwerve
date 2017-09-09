package org.usfirst.frc.team2733.robot.enumerations;

public enum WheelPosition {
    FrontLeft("FL"), FrontRight("FR"), BackLeft("BL"), BackRight("BR");

    private String name;

    WheelPosition(String wheelName) {
        this.name = wheelName;
    }

    public String getName() {
        return name;
    }
}
