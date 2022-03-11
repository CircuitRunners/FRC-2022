// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  private ADXRS450_Gyro gyro;

  private Joystick driver;
  private Joystick operator;

  private WPI_TalonFX frontLeft;
  private WPI_TalonFX rearLeft;
  private WPI_TalonFX frontRight;
  private WPI_TalonFX rearRight;
  private MecanumDrive robotDrive;

  //private Intake intake;
  //private Uptake uptake;

  private double prevx = 0;
  private double prevy = 0;
  private double prevz = 0;

  @Override
  public void robotInit() {
    frontLeft = new WPI_TalonFX(Constants.kFrontLeftChannel);
    rearLeft = new WPI_TalonFX(Constants.kRearLeftChannel);
    frontRight = new WPI_TalonFX(Constants.kFrontRightChannel);
    rearRight = new WPI_TalonFX(Constants.kRearRightChannel);
    
    CameraServer.startAutomaticCapture();

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontLeft.setInverted(true);
    rearLeft.setInverted(true);

    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    rearLeft.setNeutralMode(NeutralMode.Coast);
    rearRight.setNeutralMode(NeutralMode.Coast);

    gyro = new ADXRS450_Gyro();

    robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    //intake = new Intake(Constants.intakeSpinMotorIndex, Constants.intakeLiftMotorIndex, Constants.intakeLimitSwitchBottomIndex,Constants.intakeLimitSwitchTopIndex,.5,.5);
    //uptake = new Uptake(Constants.uptakeTopMotorIndex, Constants.uptakeBottomMotorIndex);

    driver = new Joystick(Constants.kJoystickChannelDriver);
    operator = new Joystick(Constants.kJoystickChannelOperator);
  }

  private static double smooth(double val, double deadzone, double max) {
    if (Math.abs((val)) < deadzone) {
      return 0;
    } else if (val > max) {
      return max;
    } else {
      return Math.abs(val) * Math.abs(val) * (val / Math.abs(val));
    }
  }

  private static double safety(double cmdVal, double prevVal, double maxChange) {
    double diff = cmdVal - prevVal;
    if (Math.abs(diff) < maxChange) {
      return cmdVal;
    } else {
      if (diff > 0) {
        return prevVal + maxChange;
      } else {
        return prevVal - maxChange;
      }
    }
  }

  @Override
  public void autonomousInit() {
    // Comment out the uptake if another team has better auto.
    //uptake.runAutonomousStart();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    //uptake.runAutonomousEnd();

    frontLeft.set(.65);
    frontRight.set(.65);
    rearLeft.set(.65);
    rearRight.set(.65);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  
    frontLeft.stopMotor();
    frontRight.stopMotor();
    rearLeft.stopMotor();
    rearRight.stopMotor();
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.

    double x = -driver.getRawAxis(Constants.FLIGHT_X_AXIS);
    double y = driver.getRawAxis(Constants.FLIGHT_Y_AXIS);
    double z = driver.getRawAxis(Constants.FLIGHT_Z_AXIS);

    x = smooth(x, .05, .9);
    y = smooth(y, .05, .9);
    z = smooth(z, .05, .7);

    x = safety(x, prevx, .3);
    y = safety(y, prevy, .3);
    z = safety(z, prevz, .3);

    if (driver.getPOV() == -1) {
      robotDrive.driveCartesian(y, x, z);
    } else {
      robotDrive.drivePolar(.2, driver.getPOV(), 0.0);
    }

    prevx = x;
    prevy = y;
    prevz = z;

    /*
    uptake.runMotor(
      operator.getRawButton(Constants.XBOX_RBUMPER_BUTTON),
      operator.getRawButton(Constants.XBOX_RBUMPER_BUTTON));

    double liftAxis = Util.buttonAxis(
      operator.getRawButton(Constants.XBOX_B_BUTTON),
      operator.getRawButton(Constants.XBOX_Y_BUTTON));
    intake.liftAxis(liftAxis);

    double spinAxis = Util.buttonAxis(
      operator.getRawButton(Constants.XBOX_A_BUTTON),
      operator.getRawButton(Constants.XBOX_X_BUTTON));
    intake.spinAxis(spinAxis);
    */
  }
}
