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
    //ElapsedTime totalTime = null;

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

        /*while(opModeIsActive() && hDrive.AutoDrive(0.5, 3.2, "Backwards"));

        idle();

        hDrive.StopDriving("Backwards");*/

        while(opModeIsActive() && hDrive.AutonSensor(77, 0.5, "GoBackwards"));

        idle();

        hDrive.StopDriving("Backwards");

        while(opModeIsActive() && hDrive.AutoDrive(0.5, 1, "Left"));

        idle();

        hDrive.StopDriving("Left");

        grabber.Down();

        idle();

        while(opModeIsActive() && hDrive.AutonSensor(6, 0.5, "PullToWall/GoToStone"));

        idle();

        hDrive.StopDriving("Forward");

        //hDrive.AutoDrive(0.5, 3.5, "Forward");

        grabber.Up();

        idle();

        //hDrive.AutonSensor(0, 0.5, "RedParkFoundation");

        //maybe decrease strafe speed to get a grip on the tiles?


        //while(opModeIsActive() && hDrive.AutoDrive(0.5, 3, "Right"));

        //hDrive.AutoDrive(0.5, 5, "Right");

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "RedParkFoundation"));

        idle();

        hDrive.StopDriving("Right");

        /*hDrive.AutoDrive(0.5,2,"Backwards");

        hDrive.AutoDrive(0.5,1.1, "TurnRight");

        hDrive.AutoDrive(0.5,0.5,"Backwards");

        hDrive.AutoDrive(0.5, 2.7, "Forward");*/
        //}

            while(opModeIsActive()){

            }

    }
}
