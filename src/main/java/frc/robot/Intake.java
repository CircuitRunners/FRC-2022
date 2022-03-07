package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Intake {
    /**
     * the motor that turns the wheels to grab the Game Elements
     */
    private VictorSP spinner;
    /**
     * motor that spins to lift the intake up or down
     */
    private VictorSP lifter;
    /**
     * switch that checks if the lift is down
     */
    private DigitalInput liftDownSwitch;
    /**
     * switch that checks if the lift is up
     */
    private DigitalInput liftUpSwitch;

    /**
     * spin motor power
     */
    public double spinSpeed;
    /**
     * lift motor power
     */
    public double liftSpeed;

    public Intake(VictorSP spinner, VictorSP lifter, DigitalInput liftDownSwitch, DigitalInput liftUpSwitch, double spinSpeed, double liftSpeed) {
        this.spinner = spinner;
        this.lifter = lifter;
        this.liftDownSwitch = liftDownSwitch;
        this.liftUpSwitch = liftUpSwitch;
        this.spinSpeed = spinSpeed;
        this.liftSpeed = liftSpeed;
    }

    /**
     * set the spinner to some arbitrary speed
     * 
     * @param speed SPeed
     */
    public void setSpin(double speed) {
        spinner.set(spinSpeed);
    }

    /**
     * spin inward to absorb Game Elements
     * 
     * @see #spinSpeed
     */
    public void spinIn() {
        spinner.set(spinSpeed);
    }

    /**
     * spin outward, opposite of {@link #spinIn}
     * 
     * @see #spinSpeed
     */
    public void spinOut() {
        spinner.set(-spinSpeed);
    }

    /**
     * stop spinning entirely
     */
    public void spinStop() {
        spinner.stopMotor();
    }

    /**
     * raise the intake
     */
    public void liftUp() {
        if (!liftUpSwitch.get()) {
            lifter.set(liftSpeed);
        } else {
            liftStop();
        }
    }

    /**
     * drop the intake
     */
    public void liftDown() {
        if (!liftDownSwitch.get()) {
            lifter.set(-liftSpeed);
        } else {
            liftStop();
        }
    }
    
    /**
     * Stop the lifter
     */
    public void liftStop() {
        lifter.stopMotor();
    }
}