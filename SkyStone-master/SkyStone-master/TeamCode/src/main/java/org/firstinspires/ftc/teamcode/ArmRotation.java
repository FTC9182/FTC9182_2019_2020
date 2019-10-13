package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmRotation {

    DcMotor armRotation = null;
    private double power = .5;
    private int currentPosition;
    private int rotationIndex = 15;

    public ArmRotation(HardwareMap hardwareMap){
        armRotation = hardwareMap.dcMotor.get("arm_rotate");
        armRotation.setPower(power);
        armRotation.setTargetPosition(60);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Up(){
        armRotation.setTargetPosition(currentPosition + rotationIndex);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setPower(power);
        currentPosition = armRotation.getCurrentPosition();
    }

    public void Down(){
        armRotation.setTargetPosition(currentPosition - rotationIndex);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setPower(power);
        currentPosition = armRotation.getCurrentPosition();
    }

    public void Move(double gunnerY){
        armRotation.setPower(-.5*gunnerY);
    }

    public void Brake(){
        armRotation.setPower(0);
    }

}
