package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmRotation {

    DcMotor armRotation = null;
    private double upPower = .6;
    private double downPower = .2;
    private int currentPosition;
    private int rotationIndex = 15;

    public ArmRotation(HardwareMap hardwareMap){
        armRotation = hardwareMap.dcMotor.get("arm_rotate");
        armRotation.setPower(0);
        //armRotation.setTargetPosition(60);
        //armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Up(){
        armRotation.setTargetPosition(currentPosition + rotationIndex);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setPower(upPower);
        currentPosition = armRotation.getCurrentPosition();
    }

    public void Down(){
        armRotation.setTargetPosition(currentPosition - rotationIndex);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setPower(upPower);
        currentPosition = armRotation.getCurrentPosition();
    }

    public void UpMove(double gunnerY2){
        armRotation.setPower(-gunnerY2 * upPower);
    }

    public void DownMove(double gunnerY2) { armRotation.setPower(-gunnerY2 * downPower); }

    public void GravityCounter() {armRotation.setPower(.15);}

    public void Brake(){
        armRotation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

}
