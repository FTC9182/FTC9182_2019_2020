package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Teleop")
public class Teleop extends OpMode {

    HDrive hDrive = null;

    public void init(){

        hDrive = new HDrive(hardwareMap);

    }

    public void loop(){
        

    }
}
