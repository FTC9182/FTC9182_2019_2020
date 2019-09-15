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
    double turnDegrees;

    @Override
    public void init() {
          frontRight = hardwareMap.dcMotor.get("front_right");
          frontLeft = hardwareMap.dcMotor.get("front_left");
          backLeft = hardwareMap.dcMotor.get("back_left");
          backRight = hardwareMap.dcMotor.get("back_right");

          frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
          frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
          backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
          backRight.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    @Override
    public void loop() {

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        turnDegrees = gamepad1.right_stick_x;

        frontRight.setPower(-driveY-driveX-turnDegrees);
        frontLeft.setPower(-driveY+driveX+turnDegrees);
        backLeft.setPower(-driveY-driveX+turnDegrees);
        backRight.setPower(-driveY+driveX-turnDegrees);




    }
}
