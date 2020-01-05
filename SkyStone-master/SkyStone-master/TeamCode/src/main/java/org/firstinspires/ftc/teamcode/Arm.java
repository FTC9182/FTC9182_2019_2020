package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Arm {

    public DcMotor armExtend;
    private int currentPosition;
    private double power = .3;
    private double armPower = .35;
    private ElapsedTime armTimer = new ElapsedTime();

    public Arm(HardwareMap hardwareMap){
        armExtend = hardwareMap.dcMotor.get("arm_extend");
        armExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        currentPosition = armExtend.getCurrentPosition();
        armExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
/*
    public void Move(double gunnerY){
        if (armExtend.getCurrentPosition() < 500 && armExtend.getCurrentPosition() > -20){
            armExtend.setPower(-.3 * gunnerY);
        }
    }
*/
    public void OriginalMove(double gunnerY)
    {
        armExtend.setPower(power * -gunnerY);
    }
/*
    public void Brake(){
        armExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void IncrementUp(){
        armTimer.reset();
        armExtend.setTargetPosition(currentPosition+10);
        armExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(armExtend.isBusy() && armTimer.seconds() < 1){ armExtend.setPower(armPower); }
        currentPosition = armExtend.getCurrentPosition();
    }

    public void IncrementDown(){
        armTimer.reset();
        armExtend.setTargetPosition(currentPosition-10);
        armExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(armExtend.isBusy() && armTimer.seconds() < 1){ armExtend.setPower(armPower); }
        currentPosition = armExtend.getCurrentPosition();
    }
*/
}

