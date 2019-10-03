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

    final double DRIVETICKS = 600;

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

    public void drive(double driveX, double driveY, double turnDegrees, double speedVari){

        ForwardRight.setPower(speedVari * (driveY - turnDegrees));
        ForwardLeft.setPower(speedVari * (driveY + turnDegrees));
        BackwardsRight.setPower(speedVari * (driveY - turnDegrees));
        BackwardsLeft.setPower(speedVari * (driveY + turnDegrees));
        Middle.setPower(speedVari * driveX);
    }

    public void StopDriving(){

        ForwardRight.setPower(0);
        ForwardLeft.setPower(0);
        BackwardsRight.setPower(0);
        BackwardsLeft.setPower(0);
        Middle.setPower(0);



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

    }

    public void AutoDrive(double Speed, double TargetDistance, String Direction){
//        ForwardRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        ForwardRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        ForwardLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        ForwardLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        BackwardsRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        BackwardsRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        BackwardsLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        BackwardsLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Middle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        Middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        double FBDistance;

        FBDistance = (ForwardRight.getCurrentPosition() + ForwardLeft.getCurrentPosition() + BackwardsLeft.getCurrentPosition() + BackwardsRight.getCurrentPosition())/4;

        double SDistance;

        SDistance = Middle.getCurrentPosition();

        double DriveDistance;

        DriveDistance = TargetDistance * DRIVETICKS;

        while ((Direction == "Forward") && (FBDistance > -DriveDistance)){

            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(-Speed);
            FBDistance = (ForwardRight.getCurrentPosition() + ForwardLeft.getCurrentPosition() + BackwardsLeft.getCurrentPosition() + BackwardsRight.getCurrentPosition())/4;

        }

        StopDriving();

        while ((Direction == "Backward") && (FBDistance < DriveDistance)){

            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(Speed);
            FBDistance = (ForwardRight.getCurrentPosition() + ForwardLeft.getCurrentPosition() + BackwardsLeft.getCurrentPosition() + BackwardsRight.getCurrentPosition())/4;

        }

        StopDriving();

        while ((Direction == "Sideways") &&(SDistance < DriveDistance)){
            Middle.setPower(Speed);
            SDistance = Middle.getCurrentPosition();
        }

        StopDriving();



    }


}
