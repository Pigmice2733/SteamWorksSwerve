package org.usfirst.frc.team2733.robot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class AutoCalibration {
	
	public static final String calibrationFileName = "/home/lvuser/test.properties";
	
	public static void logConfigurationFile() {
		try {
			System.out.println(getConfigurationFileAsString());
		} catch (IOException e) {
			System.out.println("No configuration file found.");
		}
	}
	
	private static String getConfigurationFileAsString() throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(calibrationFileName);
		@SuppressWarnings("resource")
		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		s.close();
		return result;
	}

	public static void saveConfigurationFile(Properties properties) throws IOException {
		properties.store(new FileOutputStream(calibrationFileName), null);
	}
	
	public static Properties getConfigurationFile() throws IOException {
		Properties properties = new Properties();
		
		InputStream inputStream = new FileInputStream(calibrationFileName);
		if (inputStream != null) {
			properties.load(inputStream);
		}
		
		return properties;
	}

	public static Properties getDefaultConfigurationFile() {
		Properties properties = new Properties();
		properties.setProperty("FL", "0");
		properties.setProperty("FR", "0");
		properties.setProperty("BL", "0");
		properties.setProperty("BR", "0");
		return properties;
	}
}
