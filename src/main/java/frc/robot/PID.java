// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;

/** Add your docs here. */
public class PID {

    //Variables necesarias para el kP
    double rightEncoder = Robot.getRobotContainer().getMagEncoders().getRightEncoderDistance();
    double leftEncoder = Robot.getRobotContainer().getMagEncoders().getLeftEncoderDistance();
    double sensorPosition = Robot.getRobotContainer().getMagEncoders().getMagEncodersDistance(rightEncoder, leftEncoder);

    //Variables necesarias para el kI
    double errorSum = 0;
    double lastTimeStamp = 0;
    
    double iLimit = 1;

    //Variables necesarias para el kD
    double lastError = 0;

    public double getDt(double lastTimeStamp){
        double dt = Timer.getFPGATimestamp() - lastTimeStamp;
        return dt;
    }
    
    public double getError(double setpoint){

        double error = setpoint - sensorPosition;

        return error;
    }

    public double getOutputSpeed(double error, double errorSum, double errorRate){
        double outputSpeed = Constants.kP*error + Constants.kI*errorSum + Constants.kD*errorRate;

        return outputSpeed;
    }


}
