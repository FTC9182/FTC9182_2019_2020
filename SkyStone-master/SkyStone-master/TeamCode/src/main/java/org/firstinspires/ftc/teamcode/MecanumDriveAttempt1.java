package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "Teleop1")
public class MecanumDriveAttempt1 extends OpMode {

    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;

    double driveX;
    double driveY;

    @Override
    public void init() {
          frontRight = hardwareMap.dcMotor.get("front_right");
          frontLeft = hardwareMap.dcMotor.get("front_left");
          backLeft = hardwareMap.dcMotor.get("back_left");
          backRight = hardwareMap.dcMotor.get("back_left");

          frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
          frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
          backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
          backRight.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    @Override
    public void loop() {

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;

        frontRight.setPower(driveY+driveX);
        frontLeft.setPower(driveY-driveX);
        backLeft.setPower(driveY+driveX);
        backRight.setPower(driveY-driveY);



    }
}
