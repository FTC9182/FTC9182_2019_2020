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

        //hDrive.AutonSensor(4, 0.5, "GoToFoundation/Stone");

        hDrive.AutoDrive(0.5, 2.4, "Backwards");

        hDrive.AutoDrive(0.5, 1, "Right");

        grabber.Down();

        hDrive.AutonSensor(7, 0.5, "PullToWall");

        //hDrive.AutoDrive(0.5, 3.5, "Forward");

        grabber.Up();

        hDrive.AutonSensor(0, 0.5, "BlueParkFoundation");

        /*hDrive.AutoDrive(0.5, 3, "Left");

        hDrive.AutoDrive(0.5,2,"Backwards");

        hDrive.AutoDrive(0.5,1.1, "TurnLeft");

        hDrive.AutoDrive(0.5,0.5,"Backwards");

        hDrive.AutoDrive(0.5, 2.7, "Forward");*/

    }
}