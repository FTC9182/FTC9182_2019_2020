package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "AutonFoundationBlue2")
public class AutonFoundationBlue2 extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;
    LinearOpMode opMode;

    ElapsedTime totalTime = null;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();

        //totalTime = new ElapsedTime();

        //totalTime.reset();


        waitForStart();

        //totalTime.reset();

        //while (totalTime.milliseconds() < 27000){



        //hDrive.AutonSensor(4, 0.5, "GoToFoundation");


        //while(opModeIsActive() && hDrive.AutonSensor(95, 0.5, "GoBackwards"));

        /*while(opModeIsActive() && hDrive.AutoDrive(0.5, 3, "Backwards"));

        idle();

        hDrive.StopDriving("Backwards");*/

        while(opModeIsActive() && hDrive.AutoDrive(0.5, 2, "Right"));

        idle();

        hDrive.StopDriving("Right");

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.3, "GoToFoundation"));

        idle();

        hDrive.StopDriving("InstantStop");

        grabber.Down();

        idle();

        while(opModeIsActive() && hDrive.AutonSensor(11, 0.5, "PullToWall"));

        idle();

        hDrive.StopDriving("Forward");

        //hDrive.AutoDrive(0.5, 3.5, "Forward");

        grabber.Up();

        idle();

        while(opModeIsActive() && hDrive.AutoDrive(0.5, 2, "Left"));

        idle();

        hDrive.StopDriving("Left");

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "BlueParkFoundation"));

        idle();

        hDrive.StopDriving("Left");

        //hDrive.AutonSensor(0, 0.5, "BlueParkFoundation");

        //maybe decrease strafe speed to get a grip on the tiles?

        //hDrive.AutoDrive(0.5, 5, "Left");
/*
        hDrive.AutoDrive(0.5,2,"Backwards");

        hDrive.AutoDrive(0.5,1.1, "TurnLeft");

        hDrive.AutoDrive(0.5,0.5,"Backwards");

        hDrive.AutoDrive(0.5, 2.7, "Forward");*/
        //}

            while (opModeIsActive()){

            }

    }
}