package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "AutonFoundationBlue")
public class AutonFoundationBlue extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();


        waitForStart();


        hDrive.AutoDrive(0.5, 3, "Backwards");

        grabber.Down();

        hDrive.AutoDrive(0.5, 3.3, "Forward");

        grabber.Up();

        hDrive.AutoDrive(0.5, 4.7, "Left");

        hDrive.AutoDrive(0.5,2,"Backwards");

        hDrive.AutoDrive(0.5,1.1, "TurnLeft");

        hDrive.AutoDrive(0.5,0.5,"Backwards");

        hDrive.AutoDrive(0.5, 2, "Forward");

    }
}