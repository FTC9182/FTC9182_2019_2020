package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "AutonFoundation")
public class AutonomousFoundationSide extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;

    /*double RightSide;
    double LeftSide;
    double MiddleDrive;*/

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();


        waitForStart();



        hDrive.AutoDrive(1, 1, 0, 1, true, 0);


    }
}