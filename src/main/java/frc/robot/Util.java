package frc.robot;

public class Util {
    /**
     * Simulate an axis input using buttons.
     * If both are enabled, 0 is returned.
     * 
     * @param pos The positive input.
     * @param neg The negative input.
     * @return The calculated axis from -1 to 1.
     */
    public static double buttonAxis(boolean pos, boolean neg) {
        double axis = 0;

        if (pos) {
            axis += 1;
        }

        if (neg) {
            axis -= 1;
        }

        return axis;
    }    
}
