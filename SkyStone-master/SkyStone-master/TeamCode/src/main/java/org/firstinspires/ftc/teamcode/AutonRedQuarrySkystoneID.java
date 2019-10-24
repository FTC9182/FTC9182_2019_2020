package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonRedQuarrySkystoneID extends LinearOpMode {

    HDrive hDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);


        waitForStart();



    }
}
