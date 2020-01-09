package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "AutonQuarryBlue")
public class AutonQuarryBlue extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;
    LinearOpMode opMode;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();

        waitForStart();



        /*while(opModeIsActive() && hDrive.AutonSensor(77, 0.5, "GoBackwards"));

        idle();

        hDrive.StopDriving("Backwards");*/

        while(opModeIsActive() && hDrive.AutonSensor(19, 0.5, "PullToWall"));

        idle();

        hDrive.StopDriving("Forward");

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.4, "BlueParkQuarry"));

        idle();

        hDrive.StopDriving("Left");

            while (opModeIsActive()) {

            }

    }
}
