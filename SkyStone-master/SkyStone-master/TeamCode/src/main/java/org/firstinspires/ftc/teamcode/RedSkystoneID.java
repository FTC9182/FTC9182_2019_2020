package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class RedSkystoneID extends LinearOpMode {

    SkystoneID skystoneID;

    @Override
    public void runOpMode() throws InterruptedException {

        skystoneID = new SkystoneID(hardwareMap);


        waitForStart();


    }
}
