package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmRotation {

    DcMotor armRotation = null;
    public double upPower = .6;
    private double downPower = .15;
    public double boostPower = .8;
    private double currentUpPower = .6;
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
        armRotation.setPower(currentUpPower);
        currentPosition = armRotation.getCurrentPosition();
    }

    public void Down(){
        armRotation.setTargetPosition(currentPosition - rotationIndex);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setPower(upPower);
        currentPosition = armRotation.getCurrentPosition();
    }

    public void UpMove(double gunnerY2){
        armRotation.setPower(-gunnerY2 * currentUpPower);
    }

    public void DownMove(double gunnerY2) { armRotation.setPower(-gunnerY2 * downPower); }

    public void GravityCounter() {armRotation.setPower(.16);}

    public void Brake(){
        armRotation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Boost(double power){ currentUpPower = power; }

}
