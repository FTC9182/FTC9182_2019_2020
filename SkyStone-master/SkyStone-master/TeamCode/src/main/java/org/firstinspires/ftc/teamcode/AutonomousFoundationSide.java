package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonomousFoundationSide extends LinearOpMode {

    Grabber grabber = null;

    double RightSide;
    double LeftSide;
    double MiddleDrive;

    @Override
    public void runOpMode() throws InterruptedException {

        grabber = new Grabber(hardwareMap);
        grabber.Up();


        waitForStart();


    }
}
