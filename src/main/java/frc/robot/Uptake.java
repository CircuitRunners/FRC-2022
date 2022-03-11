package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Uptake 
{
    /**
     * Top uptake motor controller, 775pro.
     */
    private VictorSP topMotor;
    /**
     * Bottom uptake motor controller, 775pro.
     */
    //private VictorSP bottomMotor;

    /**
     * Motor power when turning outward.
     */
    private double outPower = 0.6;

    /**
     * Motor power when turning inward.
     */
    private double inPower = 0.7;

    /**
     * Instantiates uptake motor controllers.
     *
     * @param topIndex The PWM index of the top motor controller.
     * @param bottomIndex The PWM index of the bottom motor controller.
     */
    public Uptake(int topIndex, int bottomIndex) {
        topMotor = new VictorSP(topIndex);
        //bottomMotor = new VictorSP(bottomIndex);
    }

    public Uptake(int topIndex, int bottomIndex, double outPower, double inPower) {
        this(topIndex, bottomIndex);
        this.outPower = outPower;
        this.inPower = inPower;
    }

    /**
     * Sets the motors to run during autonomous
     */
    public void runAutonomousStart() {
        topMotor.set(outPower);
        //bottomMotor.set(outPower);
    }

    /**
     * Sets the motors to stop during autonomous.
     */
    public void runAutonomousEnd() {
        topMotor.stopMotor();
        //bottomMotor.stopMotor();
    }
    
    /**
     * Runs the motors according to an axis.
     * 
     * If the axis is positive, {@link #outPower} is used.
     * Otherwise, {@link #inPower} is used.
     * 
     * @param axis The axis.
     */
    public void set(double axis) {
        // Positive: Outward; Negative: Inward
        double power = (axis > 0) ? outPower : inPower;

        topMotor.set(axis * power);
        //bottomMotor.set(axis * power);
    }
    
    /**
     * Runs the motors depending on what buttons are pressed.
     * 
     * @param positive the button that causes the motors to run forwards.
     * @param negative the button that causes the motors to run backwards.
     */
    public void set(boolean positive, boolean negative)
    {
        double axis = Util.buttonAxis(positive, negative);
        set(axis);
    }
}

