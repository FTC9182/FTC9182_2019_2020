package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "AutonQuarryBlue")
public class AutonQuarryBlue extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {

        while(opModeIsActive()){

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();

        waitForStart();

        hDrive.AutonSensor(5, 0.5, "GoToFoundation/Stone");

        idle();

        hDrive.AutonSensor(0, 0.4, "BlueParkQuarry");

        idle();

        /*hDrive.AutoDrive(0.5, 1, "Forward");

        hDrive.AutoDrive(0.5, 4, "Left");*/

        while(opModeIsActive()){

        }

        }


    }
}
