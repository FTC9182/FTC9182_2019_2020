package org.firstinspires.ftc.teamcode.FirstRobotCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "Teleop1")
public class MecanumDriveAttempt1 extends OpMode {

    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;

    Servo pullServo;

    ElapsedTime waitTime = new ElapsedTime();

    double driveX;
    double driveY;
    double turnDegrees;
    double speedVari;

    boolean speedReady;

    @Override
    public void init() {
          frontRight = hardwareMap.dcMotor.get("front_right");
          frontLeft = hardwareMap.dcMotor.get("front_left");
          backLeft = hardwareMap.dcMotor.get("back_left");
          backRight = hardwareMap.dcMotor.get("back_right");
          pullServo = hardwareMap.servo.get("pull_servo");

          frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
          frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
          backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
          backRight.setDirection(DcMotorSimple.Direction.FORWARD);

          speedVari = 1;

    }

    @Override
    public void loop() {
        if (gamepad1.y && speedReady)
        {

        }

        //if (gamepad1.y)  {
            //pullServo.setPosition(pullServo.getPosition()+(1/30));
        //} else if (gamepad1.b)  {
            //pullServo.setPosition(pullServo.getPosition()-(1/30));
        //}

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
        //which is used to determine the direction the wheels spin. The variable turnDegrees
        //simply adds or subtracts from the speed to add turning.
        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        turnDegrees = gamepad1.right_stick_x;

        //This compiles all above code to create our drive code.
        //The code is different depending on which wheel it is for.
        frontRight.setPower(speedVari*(-driveY-driveX-turnDegrees));
        frontLeft.setPower(speedVari*(-driveY+driveX+turnDegrees));
        backLeft.setPower(speedVari*(-driveY-driveX+turnDegrees));
        backRight.setPower(speedVari*(-driveY+driveX-turnDegrees));




    }
}
//Greg 9/11/19
//Greg 9/15/19
