package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Uptake 
{
    VictorSP uptakeMotor = new VictorSP(5);
    VictorSP buptakeMotor = new VictorSP(6);

    public void runAutonomousStart(){
        uptakeMotor.set(.5);
        buptakeMotor.set(.5);
    }

    public void runAutonomousEnd(){
        uptakeMotor.set(.5);
        buptakeMotor.set(.5);
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

