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
    private VictorSP indexer;
    /**
     * Motor that sucks balls into the uptakle.
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
    public double spinSpeed = 0.5;
    /**
     * Lifter motor power.
     */
    public double liftSpeed = 0.5;

    public Intake(int spinnerIndex, int lifterIndex, int liftDownSwitchIndex, int liftUpSwitchIndex) {
        spinner = new VictorSP(spinnerIndex);
        lifter = new VictorSP(lifterIndex);
        liftDownSwitch = new DigitalInput(liftDownSwitchIndex);
        liftUpSwitch = new DigitalInput(liftUpSwitchIndex);
    }

    public Intake(int spinnerIndex, int lifterIndex, int liftDownSwitchIndex, int liftUpSwitchIndex, double spinSpeed, double liftSpeed) {
        this(spinnerIndex, lifterIndex, liftDownSwitchIndex, liftUpSwitchIndex);
        this.spinSpeed = spinSpeed;
        this.liftSpeed = liftSpeed;
    }

    /**
     * Spin according to an axis.
     * 
     * @param axis The axis.
     */
    public void spinAxis(double axis) {
        if (axis != 0) {
            spinner.set(axis * spinSpeed);
        } else {
            spinStop();
        }
    }

    /**
     * Spin inward to grab things.
     * 
     * @see #spinSpeed
     */
    public void spinIn() {
        spinner.set(spinSpeed);
        indexer.set(spinSpeed);
    }

    /**
     * Spin outward (opposite of {@link #spinIn}).
     * 
     * @see #spinSpeed
     */
    public void spinOut() {
        spinner.set(-spinSpeed);
        indexer.set(-spinSpeed);
    }

    /**
     * Stop spinning entirely.
     */
    public void spinStop() {
        spinner.stopMotor();
        indexer.stopMotor();
    }

    /**
     * Turn the intake according to an axis.
     * 
     * @param axis The axis.
     */
    public void liftAxis(double axis) {
        boolean liftSwitch;
        if (axis > 0) {
            liftSwitch = liftUpSwitch.get();
        } else if (axis < 0) {
            liftSwitch = liftDownSwitch.get();
        } else {
            liftStop();
            return;
        }

        if (!liftSwitch) {
            lifter.set(axis * liftSpeed);
        } else {
            liftStop();
        }
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
