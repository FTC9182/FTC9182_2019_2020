package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private DcMotor armExtend;

    public Arm(HardwareMap hardwareMap){
        armExtend = hardwareMap.dcMotor.get("arm_extend");
    }

    public void Move(double gunnerY){
        armExtend.setPower(.6*gunnerY);
    }


}
