// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;

public class Robot extends TimedRobot {
  private ADXRS450_Gyro gyro;
  private UsbCamera camera;

  private Joystick driver;
  //private Joystick operator;

  private WPI_TalonFX frontLeft;
  private WPI_TalonFX rearLeft;
  private WPI_TalonFX frontRight;
  private WPI_TalonFX rearRight;
  private MecanumDrive robotDrive;

  //private Intake intake;
  private Uptake uptake;

  private double prevX = 0;
  private double prevY = 0;
  private double prevZ = 0;

  private Timer timer = new Timer();

  /**
   * Power multiplier for the Y-axis, for vertical movement.
   */
  private double yPower = 1;
  /**
   * Power multiplier for the X-axis, for horizontal movement.
   */
  private double xPower = 1;
  /**
   * Power multiplier for the Z-axis, for rotation.
   */
  private double zPower = 0.5;

  /**
   * Magnitude for POV control.
   */
  private double precisePower = 0.2;

  @Override
  public void robotInit() {
    gyro = new ADXRS450_Gyro();
    camera = CameraServer.startAutomaticCapture();

    driver = new Joystick(Constants.kJoystickChannelDriver);
    //operator = new Joystick(Constants.kJoystickChannelOperator);

    frontLeft = new WPI_TalonFX(Constants.kFrontLeftChannel);
    rearLeft = new WPI_TalonFX(Constants.kRearLeftChannel);
    frontRight = new WPI_TalonFX(Constants.kFrontRightChannel);
    rearRight = new WPI_TalonFX(Constants.kRearRightChannel);

    // These ones need to be reversed for mecanum to work.
    frontLeft.setInverted(true);
    rearLeft.setInverted(true);

    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    rearLeft.setNeutralMode(NeutralMode.Coast);
    rearRight.setNeutralMode(NeutralMode.Coast);

    robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    /*
    intake = new Intake(
      Constants.intakeSpinMotorIndex,
      Constants.intakeLiftMotorIndex,
      Constants.intakeLimitSwitchBottomIndex,
      Constants.intakeLimitSwitchTopIndex);
    */
    uptake = new Uptake(
      Constants.uptakeTopMotorIndex,
      Constants.uptakeBottomMotorIndex);
  }

  private static double smooth(double val, double deadzone, double max) {
    if (Math.abs(val) < deadzone) {
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
    uptake.setOut();
    timer.reset();
    timer.start();
  }

@Override
  public void autonomousPeriodic() {
    double time = timer.get();
    if(time < 1.5){

    }
    else if(time > 1.5 && time < 4){
      uptake.stop();
      robotDrive.driveCartesian(-.35,0,0);
    }
    else if (time > 2.5 && time < 2.6){
      robotDrive.driveCartesian(.1, 0, 0);;
    }
   else  if (time > 2.6 && time < 3){
      robotDrive.stopMotor();
      //intake.liftDown();
    } 
    /*
    else if (time > 3 && time < 3.5){
      intake.liftStop()
    }
    else if(time > 3.5 && time < 4){
      intake.spinIn()
    }
    else if(time > 4 && time < 4.5){
      intake.spinStop()
      robotDrive.driveCartesian(.5,0,0)
    } else if(time > 4.5){
      robotDrive.stopMotor()
      uptake.setOut();
    }
    */
    
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.

    double x = driver.getRawAxis(Constants.FLIGHT_X_AXIS);
    double y = -driver.getRawAxis(Constants.FLIGHT_Y_AXIS);
    double z = driver.getRawAxis(Constants.FLIGHT_Z_AXIS);

    x = smooth(x, .05, .9);
    y = smooth(y, .05, .9);
    z = smooth(z, .05, .7);

    x = safety(x, prevX, .3);
    y = safety(y, prevY, .3);
    z = safety(z, prevZ, .3);

    if (driver.getPOV() == -1) {
      robotDrive.driveCartesian(y * yPower, x * xPower, z * zPower);
    } else {
      robotDrive.drivePolar(precisePower, driver.getPOV(), 0.0);
    }

    prevX = x;
    prevY = y;
    prevZ = z;

    uptake.set(
      driver.getRawButton(Constants.FLIGHT_TRIGGER_BUTTON),
      driver.getRawButton(Constants.FLIGHT_MIDDLE_BUTTON));

    /*
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
