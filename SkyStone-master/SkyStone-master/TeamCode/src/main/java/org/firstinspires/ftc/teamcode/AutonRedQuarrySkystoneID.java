package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AutonRedQuarrySkystoneID extends LinearOpMode {

    HDrive hDrive = null;
    ArmRotation armRotation = null;
    Arm armExtend = null;

    @Override
    public void runOpMode() throws InterruptedException {

        while(opModeIsActive()){

            hDrive = new HDrive(hardwareMap);
            armRotation = new ArmRotation(hardwareMap);
            armExtend = new Arm(hardwareMap);


            waitForStart();

            hDrive.AutonSensor(5, 0.5, "PullToWall/GoToStone");

            hDrive.AutonSensor(0, 0.3, "SkystoneRedID");

            armRotation.EncoderMove(3, 0.4, "Up");



        }


    }
}
