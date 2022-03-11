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
     * @param positive The positive input.
     * @param negative The negative input.
     * @return The axis, within a range of -1 to 1.
     */
    public static double buttonAxis(boolean positive, boolean negative) {
        double axis = 0;
        
        if (positive) {
            axis += 1;
        }

        if (negative) {
            axis -= 1;
        }

        return axis;
    }    
}
