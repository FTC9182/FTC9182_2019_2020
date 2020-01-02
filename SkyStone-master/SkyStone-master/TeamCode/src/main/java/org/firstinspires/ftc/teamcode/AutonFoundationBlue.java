package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "AutonFoundationBlue")
public class AutonFoundationBlue extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;
    LinearOpMode opMode;

    ElapsedTime totalTime = null;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();


        waitForStart();

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.3, "GoToFoundation"));

        idle();

        hDrive.StopDriving("InstantStop");

        while(opModeIsActive() && hDrive.AutoDrive(0.5, 1.3, "Right"));

        idle();

        hDrive.StopDriving("Right");

        grabber.Down();

        idle();

        while(opModeIsActive() && hDrive.AutonSensor(11, 0.5, "PullToWall/GoToStone"));

        idle();

        hDrive.StopDriving("Forward");

        grabber.Up();

        idle();

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "BlueParkFoundation"));

        idle();

        hDrive.StopDriving("Left");

            while (opModeIsActive()){

            }

    }
}