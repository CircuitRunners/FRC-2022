package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Intake {
    /**
     * Motor that spins the compliant wheels.
     */
    private VictorSP spinner;
    /**
     * Motor that turns the intake up or down.
     */
    private VictorSP lifter;
    /**
     * Switch that checks if the lift is down.
     */
    private DigitalInput liftDownSwitch;
    /**
     * Switch that checks if the lift is up.
     */
    private DigitalInput liftUpSwitch;

    /**
     * Spinner motor power.
     */
    public double spinSpeed;
    /**
     * Lifter motor power.
     */
    public double liftSpeed;

    public Intake(int spinnerIndex, int lifterIndex, int liftDownSwitchIndex, int liftUpSwitchIndex, double spinSpeed, double liftSpeed) {
        spinner = new VictorSP(spinnerIndex);
        lifter = new VictorSP(lifterIndex);
        liftDownSwitch = new DigitalInput(liftDownSwitchIndex);
        liftUpSwitch = new DigitalInput(liftUpSwitchIndex);
        this.spinSpeed = spinSpeed;
        this.liftSpeed = liftSpeed;
    }

    /**
     * Set the spinner to some arbitrary speed.
     * 
     * @param speed Speed
     */
    public void setSpin(double speed) {
        spinner.set(speed);
    }

    /**
     * Spin inward to grab things.
     * 
     * @see #spinSpeed
     */
    public void spinIn() {
        spinner.set(spinSpeed);
    }

    /**
     * Spin outward (opposite of {@link #spinIn})
     * 
     * @see #spinSpeed
     */
    public void spinOut() {
        spinner.set(-spinSpeed);
    }

    /**
     * Stop spinning entirely.
     */
    public void spinStop() {
        spinner.stopMotor();
    }

    /**
     * Raise the intake.
     */
    public void liftUp() {
        if (!liftUpSwitch.get()) {
            lifter.set(liftSpeed);
        } else {
            liftStop();
        }
    }

    /**
     * Lower the intake.
     */
    public void liftDown() {
        if (!liftDownSwitch.get()) {
            lifter.set(-liftSpeed);
        } else {
            liftStop();
        }
    }
    
    /**
     * Stop moving the intake.
     */
    public void liftStop() {
        lifter.stopMotor();
    }
}
