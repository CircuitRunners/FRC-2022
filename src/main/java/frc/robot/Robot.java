// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;


/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {

  private Joystick driver;

  private Mecanum drivebase;

  @Override
  public void robotInit() {

    drivebase = new Mecanum();
    driver = new Joystick(0);

  }

  @Override
  public void teleopPeriodic() {

    double y = -driver.getRawAxis(1); // Remember, this is reversed!
    double x = driver.getRawAxis(0) * 1.1; // Counteract imperfect strafing
    double rx = driver.getRawAxis(2);

    drivebase.drive(y,x,rx);
  }
}
