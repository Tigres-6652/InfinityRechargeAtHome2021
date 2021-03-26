// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.resources;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

/** Add your docs here. */
public class NavX {

    AHRS ahrs;

    public NavX(){

        // try catch sirve para evitar error en caso de que navX no este conectado
        try{
        ahrs = new AHRS(SPI.Port.kMXP);
        } catch(RuntimeException e){
            e.printStackTrace();
        }

    }

    //Retorna valor del giroscopio de -180° a 180° a 0° a 360°
    public double getGyro(){

        //se agrega el +180 para convertirlo de 0° a 360°
        return ahrs.getYaw() + 180;

    }

}
