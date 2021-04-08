// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DefaultDrive;
import frc.robot.subsystems.DriveTrain;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private static RobotContainer m_robotContainer;
  DriveTrain driveTrain;
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    driveTrain = new DriveTrain();

    //Inicializando Magencoders
    
    getRobotContainer().getDriveTrain().motorLeft1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    getRobotContainer().getDriveTrain().motorRight1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);

    getRobotContainer().getDriveTrain().motorLeft1.setSensorPhase(true);
    getRobotContainer().getDriveTrain().motorRight1.setSensorPhase(false);

    //Resetear encoders a cero
    getRobotContainer().getDriveTrain().motorLeft1.setSelectedSensorPosition(0,0,10);
    getRobotContainer().getDriveTrain().motorRight1.setSelectedSensorPosition(0,0,10);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //Reinicia ambos encoders cada vez que arranca el periodo autonomo
    getRobotContainer().getMagEncoders().rightEncoder.reset();
    getRobotContainer().getMagEncoders().leftEncoder.reset();
    //Condiciones iniciales
    getRobotContainer().getPID().errorSum = 0;
    getRobotContainer().getPID().lastTimeStamp = Timer.getFPGATimestamp();

    /*m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }*/
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic(){
    double rightDistance = getRobotContainer().getMagEncoders().getRightEncoderDistance();
    double leftDistance = getRobotContainer().getMagEncoders().getLeftEncoderDistance();
    double setpoint = getRobotContainer().getMagEncoders().getMagEncodersDistance(rightDistance, leftDistance);
  
    double speed = 3;
    double stopSpeed = 0;
  
    //Robot avanza 6.56168 pies = 2 metros
    if(setpoint < 6.56168){
      driveTrain.driveAutonomus(speed, -speed);
    }
    else{
      driveTrain.driveAutonomus(stopSpeed, stopSpeed);
    }

   /* PRUEBA DEL PID, FALTA LA IMPLEMENTACION DE LA CONSTANTE KD, CUANDO SE QUIERA PROBRAR, COMENTAR LO DEMAS DEL PERIODO AUTONOMO
   DEL PERIODO AUTONOMO  MENOS la declaracion de stopSpeed
   

    double setpoint1 = 13.1234; //4 metros
    double error = getRobotContainer().getPID().getError(setpoint1);
    double iLimit = getRobotContainer().getPID().iLimit;
    double errorSum = getRobotContainer().getPID().errorSum;
    double dt = getRobotContainer().getPID().dt;
    if(Math.abs(error)<iLimit){
      errorSum = errorSum + error*dt;
    }

    double outputSpeed = getRobotContainer().getPID().getOutputSpeed(error, errorSum);
    if(error != 0){
      driveTrain.driveAutonomus(outputSpeed, -outputSpeed);
    }
    else{
      driveTrain.driveAutonomus(stopSpeed, stopSpeed);
    }
    */

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    getRobotContainer().getDriveTrain().setDefaultCommand(new DefaultDrive(getRobotContainer().getDriveTrain()));
    //Otra manera de hacerlo
    //CommandScheduler.getInstance().setDefaultCommand(subsystem, defaultCommand);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}


  public static RobotContainer getRobotContainer(){

    return m_robotContainer;
  }







}
