package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ArmRotation {

    DcMotor armRotation = null;
    public double upPower = .5; //.6
    private double downPower = .12;//.15
    public double boostPower = .625;//.8
    private double currentUpPower;
    private int currentPosition;
    private int rotationIndex = 15;
    public double gravityCounter = .17;//.165
    public double boostGravityPower = .2;//.2
    public double currentGravityCounter;
    public double currentDownPower;

    final double TICKS = 100;

    public ArmRotation(HardwareMap hardwareMap){
        armRotation = hardwareMap.dcMotor.get("arm_rotate");
        armRotation.setPower(0);
        //armRotation.setTargetPosition(60);
        //armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        currentGravityCounter = gravityCounter;
        currentUpPower = upPower;
        currentDownPower = downPower;
    }
/*
    public void Up(){
        armRotation.setTargetPosition(currentPosition + rotationIndex);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setPower(currentUpPower);
        currentPosition = armRotation.getCurrentPosition();
    }
/*
    public void Down(){
        armRotation.setTargetPosition(currentPosition - rotationIndex);
        armRotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotation.setPower(upPower);
        currentPosition = armRotation.getCurrentPosition();
    }
*/
    public void UpMove(double gunnerY2){
        armRotation.setPower(-gunnerY2 * currentUpPower);
    }

    public void DownMove(double gunnerY2) { armRotation.setPower(-gunnerY2 * currentDownPower); }

    public void GravityCounter() {armRotation.setPower(currentGravityCounter);}

    public void Brake(){
        armRotation.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Boost(double power, double gravityPower){ currentUpPower = power; currentGravityCounter = gravityPower; }

    public void StopGravity(double gravitycounter, double recovery) {
        currentGravityCounter = gravitycounter;
        if (recovery == 1){
            currentDownPower = upPower;
        }
        else if (recovery == 0){
            currentDownPower = downPower;
        }
    }
/*
    public boolean EncoderMove(double TargetDistance, double speed, String Direction){

        if (Direction == "Up"){

            armRotation.setPower(speed);

            return armRotation.getCurrentPosition() < (TargetDistance * TICKS);
        }

        if (Direction == "Down"){

            armRotation.setPower(-speed);

            return armRotation.getCurrentPosition() > (-TargetDistance * TICKS);
        }

        return false;

    }
*/
}
