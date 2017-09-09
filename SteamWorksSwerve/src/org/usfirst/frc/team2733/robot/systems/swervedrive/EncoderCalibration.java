package org.usfirst.frc.team2733.robot.systems.swervedrive;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.usfirst.frc.team2733.robot.enumerations.WheelPosition;

public class EncoderCalibration {

    private final String calibrationFilePath;

    // Encoder offsets
    private final Map<WheelPosition, Double> encoderOffsets;

    /**
     * Construct an encoder calibration structure
     * @param shouldReadFromFile Whether the encoder offsets should be read from a file or zeroed
     * @param calibrationFilePath The file to read values from, can be left null if values are to be zeroed
     */
    public EncoderCalibration(boolean shouldReadFromFile, String calibrationFilePath) {
        this.calibrationFilePath = calibrationFilePath;

        encoderOffsets = new HashMap<>();

        reloadOffsets(shouldReadFromFile);
    }

    /**
     * Get the encoder offset for a specific swerve module
     * @param pos WheelPosition of the encoder's swerve module
     * @return Encoder offset of that encoder
     */
    public double getEncoderOffset(WheelPosition pos) {
        return encoderOffsets.get(pos).doubleValue();
    }

    /**
     * Set the offset for an encoder
     * @param pos WheelPosition of the encoder's swerve module
     * @param encoderOffset The new encoder offset to set for future use
     */
    public void setEncoderOffset(WheelPosition pos, double encoderOffset) {
        encoderOffsets.put(pos, Double.valueOf(encoderOffset));
    }

    /**
     * Reload all the offsets
     * @param shouldReadFromFile Whether the encoder offsets should be read from a file or zeroed
     */
    public void reloadOffsets(boolean shouldReadFromFile) {
        if (shouldReadFromFile) {
            boolean success = tryReadFromFile();
            if (!success) {
                System.out.println("Couldn't read encoder caibration file, zeroing values instead\n");
                zeroValues();
            }
        } else {
            zeroValues();
            System.out.println("Zeroing Encoder Values");
        }
    }

    /**
     * Saves all the current encoder offsets to the file
     */
    public void saveCalibrationFile() {
        FileOutputStream fileStream = null;
        Properties encoderOffsets = new Properties();

        for (final WheelPosition pos : WheelPosition.values()) {
            encoderOffsets.setProperty(pos.getName(), Double.toString((this.encoderOffsets.get(pos))));
        }

        try {
            fileStream = new FileOutputStream(calibrationFilePath);
            encoderOffsets.store(fileStream, null);
            fileStream.close();
        } catch (IOException | NullPointerException ex) {
            if(calibrationFilePath == null) {
                System.out.println("Failed to save calibration data, calibration file path is null\n");
            } else {
                System.out.println("Failed to save encoder calibration data\n");
            }
            
            try {
                fileStream.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
        }

        System.out.println("Successfully saved encoder calibration data\n");
    }

    /**
     * Format and print current encoder offsets
     */
    public void printEncoderOffsets() {
        String encoderOffsetOutput = "Encoder Offsets:\n";

        for (final WheelPosition pos : WheelPosition.values()) {
            encoderOffsetOutput += pos.getName() + ": " + encoderOffsets.get(pos) + "\n";
        }

        System.out.println(encoderOffsetOutput);
    }

    /**
     * Attempts to read encoder offsets from file
     * 
     * @return <i>true</i> if succeeded, <i>false</i> if failed
     */
    private boolean tryReadFromFile() {
        FileInputStream fileStream = null;
        Properties encoderOffsets = new Properties();

        try {
            fileStream = new FileInputStream(calibrationFilePath);
            encoderOffsets.load(fileStream);
            fileStream.close();
        } catch (IOException | NullPointerException ex) {
            if(calibrationFilePath == null) {
                System.out.println("Failed to load calibration data, calibration file path is null\n");
            } else {
                System.out.println("Failed to load encoder calibration data\n");
            }
            
            try {
                fileStream.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            ex.printStackTrace();

            return false;
        }

        for (final WheelPosition pos : WheelPosition.values()) {
            this.encoderOffsets.put(pos, Double.valueOf(encoderOffsets.getProperty(pos.getName())));
        }
        System.out.println("Loaded Encoders From File");
        return true;
    }

    /**
     * Zero all encoder offsets
     */
    public void zeroValues() {
        for (final WheelPosition pos : WheelPosition.values()) {
            encoderOffsets.put(pos, 0.0);
        }
    }
}
