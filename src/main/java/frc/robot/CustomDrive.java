package frc.robot;

import static java.util.Objects.requireNonNull;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

public class CustomDrive extends RobotDriveBase implements Sendable, AutoCloseable {
  private static int instances = 0;

  public final WPI_TalonFX frontLeft;
  public final WPI_TalonFX rearLeft;
  public final WPI_TalonFX frontRight;
  public final WPI_TalonFX rearRight;

  public CustomDrive(
      WPI_TalonFX frontLeft,
      WPI_TalonFX rearLeft,
      WPI_TalonFX frontRight,
      WPI_TalonFX rearRight) {
    requireNonNull(frontLeft, "Front-left motor cannot be null");
    requireNonNull(rearLeft, "Rear-left motor cannot be null");
    requireNonNull(frontRight, "Front-right motor cannot be null");
    requireNonNull(rearRight, "Rear-right motor cannot be null");

    this.frontLeft = frontLeft;
    this.rearLeft = rearLeft;
    this.frontRight = frontRight;
    this.rearRight = rearRight;

    SendableRegistry.addChild(this, frontLeft);
    SendableRegistry.addChild(this, rearLeft);
    SendableRegistry.addChild(this, frontRight);
    SendableRegistry.addChild(this, rearRight);
    instances++;
    SendableRegistry.addLW(this, "CustomDrive", instances);
  }

  @Override
  public void stopMotor() {
    frontLeft.stopMotor();
    rearLeft.stopMotor();
    frontRight.stopMotor();
    rearRight.stopMotor();
  }

  @Override
  public String getDescription() {
    return "CustomDrive";
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("CustomDrive");
    builder.setActuator(true);
    builder.setSafeState(this::stopMotor);
    builder.addDoubleProperty(
        "Front Left Motor Speed", frontLeft::get, frontLeft::set);
    builder.addDoubleProperty(
        "Front Right Motor Speed", frontRight::get, frontRight::set);
    builder.addDoubleProperty(
        "Rear Left Motor Speed", rearLeft::get, rearLeft::set);
    builder.addDoubleProperty(
        "Rear Right Motor Speed", rearRight::get, rearRight::set);
  }

  @Override
  public void close() {
    SendableRegistry.remove(this);
  }
}
