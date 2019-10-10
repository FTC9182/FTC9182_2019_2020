package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmRotation {

    DcMotor armRotation = null;
    private double power = .2;
    private int currentPosition;
    private int rotationIndex = 5;
    private ElapsedTime armTimer = new ElapsedTime();

    public ArmRotation(HardwareMap hardwareMap){
        armRotation = hardwareMap.dcMotor.get("arm_rotate");
        armRotation.setPower(power);
        armRotation.setTargetPosition(260);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
}
