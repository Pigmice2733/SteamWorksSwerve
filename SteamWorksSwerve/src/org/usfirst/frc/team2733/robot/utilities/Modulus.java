package org.usfirst.frc.team2733.robot.utilities;

public class Modulus {

    /**
     * An implementation of the modulus operator more suited to our needs than
     * the native Java implementation
     * 
     * @param number
     *            The number to take the modulus of
     * @param modulus
     *            The modulo value
     * @return The modulo of number and modulus, always positive
     */
    public static double modulus(double number, double modulus) {
        return number < 0 ? number % modulus + modulus : number % modulus;
    }
}
