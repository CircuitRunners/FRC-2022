package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Climber {
    
    private Compressor compressor;
    private Solenoid climbSolenoid;

    public Climber(){
        compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
        climbSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
    }

    public void extend(){
        climbSolenoid.set(true);
    }

    public void retract(){
        climbSolenoid.set(false);
    }

    public void setClimbSolenoid(boolean on){
        climbSolenoid.set(on);
    }

    public void setCompressor(){
        compressor.enableDigital();
    }
}
