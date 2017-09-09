package org.usfirst.frc.team2733.robot.comm;

//import edu.wpi.first.wpilibj.DriverStation; //unused
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardIO {
//    private DriverStation driverStation;  //unused
    
    public DashboardIO () {
//        driverStation = DriverStation.getInstance();  //unused
        
        SmartDashboard.putBoolean("portConfigMode", true);
    }
    
    /**
     * Test whether the port config should be read from a file, or from the hard-coded values
     * @return <i>true</i> if port config should be read from file, <i>false</i> otherwise
     * */
    public boolean shouldReadPortConfigFromFile() {
        return SmartDashboard.getBoolean("portConfigMode", false);
    }
}
