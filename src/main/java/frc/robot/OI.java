// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.StartShooting;
import frc.robot.subsystems.Intake;


/** Add your docs here. */
public class OI {
    Intake intake;
    Joystick pilot;
    JoystickButton aButton, bButton;


    public OI(){
        intake = new Intake();
        pilot = new Joystick(0);
        aButton = new JoystickButton(pilot, 0); // JoystickButton(joytick objeto, port del boton)
        bButton = new JoystickButton(pilot, 1);

    }

    public void configureButtonBindings(){
        //botones y comandos
        //Mientras aButton esté presionado se ejecuta IntakeCommand
        aButton.whileHeld(new IntakeCommand(Robot.getRobotContainer().getIntake()));
        bButton.whileHeld(new StartShooting(Robot.getRobotContainer().getShooter()));

    }

    public double getAxisX(){

        return pilot.getX();
    }

    public double getAxisY(){

        return pilot.getY();
    }



}
