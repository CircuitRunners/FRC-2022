package frc.robot;

/**
 * Keeps track of button holding and timing.
 */
public class ButtonManager {
    /**
     * Whether the button is pressed or not.
     */
    protected boolean state = false;
    /**
     * If the state was changed in the last frame.
     */
    protected boolean justChanged = false;
    /**
     * The duration the last state has been held for.
     */
    protected double timeHeld = 0;

    /**
     * Create a new button manager with a specific starting state.
     * 
     * @param state The initial state.
     */
    public ButtonManager(boolean state) {
        this.state = state;
    }

    /**
     * Create a button manager with all default properties.
     */
    public ButtonManager() {}

    /**
     * Update the button's state.
     * 
     * @param newState The new button state.
     * @param deltaTime The time elapsed since the last frame.
     */
    public void update(boolean newState, double deltaTime) {
        if (newState == state) {
            timeHeld += deltaTime;
            if (justChanged) {
                justChanged = false;
            }
        } else {
            justChanged = true;
            timeHeld = 0;
        }
    }

    /**
     * Update the button's state without updating the time held.
     * 
     * @param newState The new button state.
     */
    public void update(boolean newState) {
        update(newState, 0);
    }

    /**
     * @return If the button is currently pressed.
     */
    public boolean getPressed() {
        return state;
    }

    /**
     * @return If the button is currently released.
     */
    public boolean getReleased() {
        return !state;
    }

    /**
     * @return If the button was just pressed within the last frame.
     */
    public boolean getJustPressed() {
        return state && justChanged;
    }

    /**
     * @return If the button was just released within the last frame.
     */
    public boolean getJustReleased() {
        return !state && justChanged;
    }

    /**
     * @return If the button's state was just changed within the last frame.
     */
    public boolean getJustChanged() {
        return justChanged;
    }

    /**
     * @return If the button's state stayed the same in the last frame.
     */
    public boolean getJustHeld() {
        return !justChanged;
    }

    /**
     * @return The time the current button state has been held for.
     */
    public double getTimeHeld() {
        return timeHeld;
    }
}
