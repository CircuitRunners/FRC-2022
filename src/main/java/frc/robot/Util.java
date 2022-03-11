package frc.robot;

public class Util {
    /**
     * Simulate an axis input using buttons.
     * 
     * <p>
     *   For example, if button A is positive and button B is negative:
     *   <ul>
     *     <li>Neither A nor B pressed: {@code 0}</li>
     *     <li>Only A pressed: {@code 1}</li>
     *     <li>Only B pressed: {@code -1}</li>
     *     <li>Both A and B pressed: {@code 0}</li>
     *   </ul>
     * </p>
     * 
     * <hr>
     * 
     * @param pos The positive input.
     * @param neg The negative input.
     * @return The axis, within range of -1 to 1.
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
