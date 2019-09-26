package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HDrive {

    DcMotor ForwardRight = null;
    DcMotor ForwardLeft = null;
    DcMotor BackwardsRight = null;
    DcMotor BackwardsLeft = null;
    DcMotor Middle = null;

    public HDrive(HardwareMap hardwareMap){
        ForwardRight = hardwareMap.dcMotor.get("front_right");
        ForwardLeft = hardwareMap.dcMotor.get("front_left");
        BackwardsRight = hardwareMap.dcMotor.get("back_right");
        BackwardsLeft = hardwareMap.dcMotor.get("back_left");
        Middle = hardwareMap.dcMotor.get("middle");

        ForwardRight.setDirection(DcMotorSimple.Direction.FORWARD);
        ForwardLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        BackwardsRight.setDirection(DcMotorSimple.Direction.FORWARD);
        BackwardsLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        Middle.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void drive(double driveX, double driveY, double turnDegrees){

        ForwardRight.setPower(driveY - turnDegrees);
        ForwardLeft.setPower(driveY + turnDegrees);
        BackwardsRight.setPower(driveY - turnDegrees);
        BackwardsLeft.setPower(driveY + turnDegrees);
        Middle.setPower(driveX);
    }

    public void AutoDrive(double RightDrive, double LeftDrive, double MiddleDrive){
        ForwardRight.setPower(RightDrive);
        ForwardLeft.setPower(RightDrive);
        BackwardsRight.setPower(LeftDrive);
        BackwardsLeft.setPower(LeftDrive);
        Middle.setPower(MiddleDrive);
        
    }


}
