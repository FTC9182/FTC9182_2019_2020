package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Teleop")
public class Teleop extends OpMode {

    HDrive hDrive = null;
    Grabber grabber = null;

    double driveX;
    double driveY;
    double turnDegrees;

    public void init(){

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);

    }

    public void loop(){

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        turnDegrees = gamepad1.right_stick_x;

        hDrive.drive(driveX, driveY, turnDegrees);


    }
}
