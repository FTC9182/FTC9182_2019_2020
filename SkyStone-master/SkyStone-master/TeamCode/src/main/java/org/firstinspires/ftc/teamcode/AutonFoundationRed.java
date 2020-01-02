package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "AutonFoundationRed")
public class AutonFoundationRed extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;
    LinearOpMode opMode;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();

        waitForStart();

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.3, "GoToFoundation"));

        idle();

        hDrive.StopDriving("InstantStop");

        //AutoDrive is encoder based movement

        while(opModeIsActive() && hDrive.AutoDrive(0.5, 1.3, "Left"));

        idle();

        hDrive.StopDriving("Left");

        grabber.Down();

        idle();

        while(opModeIsActive() && hDrive.AutonSensor(11, 0.5, "PullToWall/GoToStone"));

        idle();

        hDrive.StopDriving("Forward");

        grabber.Up();

        idle();

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "RedParkFoundation"));

        idle();

        hDrive.StopDriving("Right");

            while(opModeIsActive()){

            }

    }
}
