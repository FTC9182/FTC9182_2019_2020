package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonRedQuarrySkystoneID")
public class AutonRedQuarrySkystoneID extends LinearOpMode {

    HDrive hDrive = null;
    ArmRotation armRotation = null;
    Arm armExtend = null;
    BlockGrabber grabber = null;

    @Override
    public void runOpMode() throws InterruptedException {

            hDrive = new HDrive(hardwareMap);
            armRotation = new ArmRotation(hardwareMap);
            armExtend = new Arm(hardwareMap);
            grabber = new BlockGrabber(hardwareMap);


            waitForStart();

            while(opModeIsActive() && hDrive.AutonSensor(12, 0.4, "PullToWall/GoToStone"));

            idle();

            hDrive.StopDriving("InstantStop");

            while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "SkystoneRedIDPart1"));

            idle();

            hDrive.StopDriving("InstantStop");

            while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "SkystoneRedIDPart2"));

            idle();

            hDrive.StopDriving("InstantStop");

            while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "SkystoneRedIDPart1"));

            idle();

            hDrive.StopDriving("InstantStop");

            while(opModeIsActive() && hDrive.AutonSensor(0, 0.5, "SkystoneRedIDPart2"));

            idle();

            hDrive.StopDriving("InstantStop");


            grabber.Down();

            idle();

            while(opModeIsActive() && hDrive.AutoDrive(0.4, 1.5, "Backwards"));

            idle();

            hDrive.StopDriving("Backwards");

            while(opModeIsActive() && hDrive.AutonSensor(0, 0.3, "RedParkQuarry"));

            idle();

            hDrive.StopDriving("Right");

            while(opModeIsActive() && hDrive.AutoDrive(0.4, 1, "Right"));

            idle();

            hDrive.StopDriving("Right");

            grabber.Up();

            while(opModeIsActive() && hDrive.AutoDrive(0.4, 1, "Left"));

            idle();

            hDrive.StopDriving("Left");

            while(opModeIsActive());



    }
}
