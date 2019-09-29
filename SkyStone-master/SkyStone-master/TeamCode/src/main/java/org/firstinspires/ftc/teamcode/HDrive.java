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

    final double DRIVETICKS = 2240;

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

    public void StopDriving(){
        ForwardRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ForwardRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ForwardLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ForwardLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackwardsRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackwardsRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackwardsLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackwardsLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Middle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ForwardRight.setPower(0);
        ForwardLeft.setPower(0);
        BackwardsRight.setPower(0);
        BackwardsLeft.setPower(0);
        Middle.setPower(0);

    }

    public void AutoDrive(double RightPower, double LeftPower, double MiddlePower, final double FBTargetDistance, boolean FB, final double STargetDistance){
        //ForwardRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //ForwardRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //ForwardLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //ForwardLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //BackwardsRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //BackwardsRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //BackwardsLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //BackwardsLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Middle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        double FBDistance;

        FBDistance = (ForwardRight.getCurrentPosition() * ForwardLeft.getCurrentPosition() * BackwardsLeft.getCurrentPosition() * BackwardsRight.getCurrentPosition())/4;

        double SDistance;

        SDistance = Middle.getCurrentPosition();

        double FBDriveDistance;

        FBDriveDistance = FBTargetDistance * DRIVETICKS;

        double SDriveDistance;

        SDriveDistance = STargetDistance * DRIVETICKS;

        if (FB == true && (FBDistance < FBDriveDistance)){

            ForwardRight.setPower(RightPower);
            ForwardLeft.setPower(RightPower);
            BackwardsRight.setPower(LeftPower);
            BackwardsLeft.setPower(LeftPower);
        } else if (FB == false &&(SDistance < SDriveDistance)){
            Middle.setPower(MiddlePower);
        } else {
            StopDriving();
        }



    }


}
