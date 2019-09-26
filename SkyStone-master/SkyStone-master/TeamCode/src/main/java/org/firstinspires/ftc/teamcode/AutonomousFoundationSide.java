package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonomousFoundationSide extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;

    double RightSide;
    double LeftSide;
    double MiddleDrive;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();


        waitForStart();

        hDrive.AutoDrive(4, 4, 0);


    }
}
