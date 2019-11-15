package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonRedQuarrySkystoneID extends LinearOpMode {

    HDrive hDrive = null;
    LinearOpMode opMode;
    ArmRotation armRotation = null;
    Arm armExtend = null;

    @Override
    public void runOpMode() throws InterruptedException {

            hDrive = new HDrive(hardwareMap);
            armRotation = new ArmRotation(hardwareMap);
            armExtend = new Arm(hardwareMap);


            waitForStart();

            while(opModeIsActive() && hDrive.AutonSensor(5, 0.5, "PullToWall/GoToStone"));

            idle();

            hDrive.StopDriving("Forward");

            while(opModeIsActive() && hDrive.AutonSensor(0, 0.3, "SkystoneRedID"));

            idle();

            hDrive.StopDriving("Left");

            while(opModeIsActive() && armRotation.EncoderMove(3, 0.4, "Up"));

            idle();



    }
}
