package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

public class Climber {
    private Compressor pcmCompressor;

    private boolean enabled;
    private boolean pressureSwitch;

    private double current;

    // pneumatic hub
    private Compressor phCompressor;

    private Solenoid solenoid;

    public Climber() {
        this.pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
        this.phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);

        pcmCompressor.enableDigital();
        pcmCompressor.disable();

        this.enabled = pcmCompressor.enabled();
        this.pressureSwitch = pcmCompressor.getPressureSwitchValue();
        this.current = pcmCompressor.getCurrent();
        this.solenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.solenoidChannel);
    }

    public void set(boolean setting) {
        this.solenoid.set(setting);
    }

    public boolean isDisabled() {
        return this.solenoid.isDisabled();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public double getCurrent() {
        return this.current;
    }

    public boolean getPressureSwitch() {
        return this.pressureSwitch;
    }
}
