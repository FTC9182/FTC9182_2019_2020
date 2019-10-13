package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonFoundationRed")
public class AutonFoundationRed extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();


        waitForStart();


        hDrive.AutoDrive(0.5, 3.3, "Backwards");

        grabber.Down();

        hDrive.AutoDrive(0.5, 3.5, "Forward");

        grabber.Up();

        hDrive.AutoDrive(0.5, 2, "Right");

        hDrive.AutoDrive(0.5,2,"Backwards");

        hDrive.AutoDrive(0.5,1.1, "TurnRight");

        hDrive.AutoDrive(0.5,0.5,"Backwards");

        hDrive.AutoDrive(0.5, 2, "Forward");

    }
}
