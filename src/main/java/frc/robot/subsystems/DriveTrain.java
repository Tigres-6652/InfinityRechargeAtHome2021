// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  TalonSRX motorRight1, motorRight2, motorRight3,
               motorLeft1, motorLeft2, motorLeft3;


  public DriveTrain() {

    motorRight1 = new TalonSRX(RobotMap.MOTOR_PORT_RIGHT_FRONT);
    motorRight2 = new TalonSRX(RobotMap.MOTOR_PORT_RIGHT_MIDDLE);
    motorRight3 = new TalonSRX(RobotMap.MOTOR_PORT_RIGHT_REAR);

    motorLeft1 = new TalonSRX(RobotMap.MOTOR_PORT_LEFT_FRONT);
    motorLeft2 = new TalonSRX(RobotMap.MOTOR_PORT_LEFT_MIDDLE);
    motorLeft3 = new TalonSRX(RobotMap.MOTOR_PORT_LEFT_REAR);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void drive(double speedX, double speedY){

    double rightSpeed = -speedX - speedY;
    double leftSpeed = -speedX + speedY;

    motorLeft1.set(ControlMode.PercentOutput, leftSpeed);
    motorLeft2.set(ControlMode.PercentOutput, leftSpeed);
    motorLeft3.set(ControlMode.PercentOutput, leftSpeed);

    motorRight1.set(ControlMode.PercentOutput, rightSpeed);
    motorRight2.set(ControlMode.PercentOutput, rightSpeed);
    motorRight3.set(ControlMode.PercentOutput, rightSpeed);
  }


}
