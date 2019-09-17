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
    double speedVari;

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

          speedVari = 1;

    }

    @Override
    public void loop() {

        //This changes the value of a variable that is used as a multiplier
        //to allow us to have variable speed.
        if (gamepad1.dpad_up){
            speedVari = 1;
        } else if (gamepad1.dpad_left) {
            speedVari = 0.5;
        } else if (gamepad1.dpad_right) {
            speedVari = 0.75;
        } else if (gamepad1.dpad_down) {
            speedVari = 0.25;
        }


        //This creates variables that change depending on the position of the joysticks,
        //which is used
        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        turnDegrees = gamepad1.right_stick_x;

        frontRight.setPower(speedVari*(-driveY-driveX-turnDegrees));
        frontLeft.setPower(speedVari*(-driveY+driveX+turnDegrees));
        backLeft.setPower(speedVari*(-driveY-driveX+turnDegrees));
        backRight.setPower(speedVari*(-driveY+driveX-turnDegrees));




    }
}
