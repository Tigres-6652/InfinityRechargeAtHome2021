// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;

/** Add your docs here. */
public class PID {
    double rightEncoder = Robot.getRobotContainer().getMagEncoders().getRightEncoderDistance();
    double leftEncoder = Robot.getRobotContainer().getMagEncoders().getLeftEncoderDistance();

    double encoders = Robot.getRobotContainer().getMagEncoders().getMagEncodersDistance(rightEncoder, leftEncoder);
  
    double setpoint = 5;

    double error = setpoint - encoders;

    double kP = Constants.kP;
    double kI = Constants.kI;
    double kD = Constants.kD;

    //public double errorSum = 0;
    public double lastTimeStamp = 0;

    double dt = Timer.getFPGATimestamp() - lastTimeStamp;

    public double errorSum = error * dt;


    public double getMotorSpeedOutput(){

        double motorSpeedOutput = kP * error + kI * errorSum;

        return motorSpeedOutput;
    }



}
