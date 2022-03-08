package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Uptake 
{
    /**
     * Top uptake motor controller, 775pro
     */
    private VictorSP uptakeMotor;
    /**
     * Bottom uptake motor controller, 775pro
     */
    private VictorSP buptakeMotor;

    /**
     * Uptake Constructor, instantiates uptake motor controllers
     *
     * @param topIndex The PWM index of the top motor controller
     * @param bottomIndex The PWM index of the bottom motor controller
     */
    public Uptake(int topIndex, int bottomIndex){
        uptakeMotor = new VictorSP(topIndex);
        buptakeMotor = new VictorSP(bottomIndex);
    }

    /**
     * Sets the motors to run during autonomous
     */
    public void runAutonomousStart(){
        uptakeMotor.set(.5);
        buptakeMotor.set(.5);
    }

    /**
     * Sets the motors to stop during autonomous
     */
    public void runAutonomousEnd(){
        uptakeMotor.stopMotor();
        buptakeMotor.stopMotor();
    }
    
    /**
     * Runs the motor depending on which button is pressed
     * @param aButton the button that causes the motors to run forwards
     * @param bButton the button that causes the motors to run backwards
     */
    public void runMotor(Boolean aButton, Boolean bButton)
    {
        if (aButton)
        {
            uptakeMotor.set(.5);
            buptakeMotor.set(.5);
        }

        else if (bButton)
        {
            uptakeMotor.set(-.5);
            buptakeMotor.set(-.5);
        }
        else
        {
            uptakeMotor.set(0);
            buptakeMotor.set(0);
        }
    }
}

