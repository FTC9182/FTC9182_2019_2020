package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

public class Sensors {

    OpticalDistanceSensor BackSensor = null;

    public void AutonBackSensor(double TargetDistance){
        while(BackSensor.getLightDetected() < TargetDistance){

        }
    }
}
