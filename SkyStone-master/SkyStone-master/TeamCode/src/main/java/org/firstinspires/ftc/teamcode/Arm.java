package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.EventListenerProxy;

public class Arm {

    private DcMotor armExtend;
    private int currentPosition;
    private double armPower = .45;
    private ElapsedTime armTimer = new ElapsedTime();

    public Arm(HardwareMap hardwareMap){
        armExtend = hardwareMap.dcMotor.get("arm_extend");
        armExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        currentPosition = armExtend.getCurrentPosition();
        armExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Move(double gunnerY){
        armExtend.setPower(-.5 * gunnerY);
    }

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
}
