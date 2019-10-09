package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private DcMotor armExtend;
    private int currentPosition;

    public Arm(HardwareMap hardwareMap){
        armExtend = hardwareMap.dcMotor.get("arm_extend");
        armExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        currentPosition = armExtend.getCurrentPosition();
    }

    public void Move(double gunnerY){
        armExtend.setPower(.6*gunnerY);
    }

    public void IncrementUp(){
        armExtend.setTargetPosition(currentPosition+10);
        armExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armExtend.setPower(.2);
        currentPosition = armExtend.getCurrentPosition();
    }

    public void IncrementDown(){
        armExtend.setTargetPosition(currentPosition-10);
        armExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armExtend.setPower(.2);
        currentPosition = armExtend.getCurrentPosition();
    }
}
