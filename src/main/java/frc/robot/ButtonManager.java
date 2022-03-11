package frc.robot;

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

    public ButtonManager() {}

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

    public boolean getPressed() {
        return state;
    }

    public boolean getReleased() {
        return !state;
    }

    public boolean getJustPressed() {
        return state && justChanged;
    }

    public boolean getJustReleased() {
        return !state && justChanged;
    }

    public boolean getJustChanged() {
        return justChanged;
    }

    public double getTimeHeld() {
        return timeHeld;
    }
}
