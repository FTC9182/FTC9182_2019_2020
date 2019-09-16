package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class FoundationPull_Autonomous extends LinearOpMode
{
    Servo pullerServo = null;

    DcMotor frontRight = null;
    DcMotor frontLeft = null;
    DcMotor backRight = null;
    DcMotor backLeft = null;


    @Override
    public void runOpMode() throws InterruptedException
    {
        pullerServo = hardwareMap.servo.get("Puller_Servo");

        frontRight = hardwareMap.dcMotor.get("front_right");
        frontLeft = hardwareMap.dcMotor.get("front_left");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");

        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        /* ! Autonomous commands ! */

        //Go backwards
        //Move servo + .5
        //Move to the left
        //Move servo + .5
        //Move forward
        //Move servo - 1

    }
}
