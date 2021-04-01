// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  /** Creates a new Intake. */
  TalonFX shooterMotor1, shooterMotor2;
  

  public Shooter() {

    /*shooterMotor1 = new TalonFX(RobotMap.SHOOTER_MOTOR_PORT_FRONT);
    shooterMotor2 = new TalonFX(RobotMap.SHOOTER_MOTOR_PORT_REAR);*/

  }

  public void forward() {

    shooterMotor1.set(ControlMode.PercentOutput, 0.8);
    shooterMotor2.set(ControlMode.PercentOutput, 0.8);
  }

  public void backward() {

    shooterMotor1.set(ControlMode.PercentOutput, -0.8);
    shooterMotor2.set(ControlMode.PercentOutput, -0.8);

  }

  public void stop() {

    shooterMotor1.set(ControlMode.PercentOutput, 0);
    shooterMotor2.set(ControlMode.PercentOutput, 0);
  }

  public void setRaw(double speed) {

    shooterMotor1.set(ControlMode.PercentOutput, speed);
    shooterMotor2.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
