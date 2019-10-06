package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private DcMotor armExtend;

    public Arm(HardwareMap hardwareMap){
        armExtend = hardwareMap.dcMotor.get("arm_extend");
    }

    public void Extend(){
        armExtend.setPower(.5);
    }

    public void Retract(){
        armExtend.setPower(.5);
    }


}
