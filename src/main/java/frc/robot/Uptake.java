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

    private double speed = 0.5;

    /**
     * Uptake Constructor, instantiates uptake motor controllers
     *
     * @param topIndex The PWM index of the top motor controller
     * @param bottomIndex The PWM index of the bottom motor controller
     */
    public Uptake(int topIndex, int bottomIndex) {
        uptakeMotor = new VictorSP(topIndex);
        buptakeMotor = new VictorSP(bottomIndex);
    }

    public void runAutonomousStart() {
        uptakeMotor.set(speed);
        buptakeMotor.set(speed);
    }

    public void runAutonomousEnd() {
        uptakeMotor.stopMotor();
        buptakeMotor.stopMotor();
    }
    
    public void runMotor(Boolean aButton, Boolean bButton)
    {
        double axis = 0;
        if (aButton) {
            axis += 1;
        }
        if (bButton) {
            axis -= 1;
        }

        uptakeMotor.set(axis * speed);
        buptakeMotor.set(axis * speed);
    }
}

