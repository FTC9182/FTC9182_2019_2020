package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "AutonQuarryRed")
public class AutonQuarryRed extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();

        waitForStart();

        hDrive.AutonSensor(5, 0.5, "GoToFoundation/Stone");

        hDrive.AutonSensor(0, 0.4, "RedParkQuarry");

        /*hDrive.AutoDrive(0.5, 1, "Forward");

        hDrive.AutoDrive(0.5, 4, "Right");*/


    }
}

