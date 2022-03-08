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

    public void runAutonomousStart(){
        uptakeMotor.set(.5);
        buptakeMotor.set(.5);
    }

    public void runAutonomousEnd(){
        uptakeMotor.stopMotor();
        buptakeMotor.stopMotor();
    }
    
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

