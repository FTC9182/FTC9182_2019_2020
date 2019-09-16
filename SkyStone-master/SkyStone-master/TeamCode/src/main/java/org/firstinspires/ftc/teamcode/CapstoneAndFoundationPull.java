package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "TeleopServoTest")
public class CapstoneAndFoundationPull extends OpMode
{
    Servo pullerServo = null;

    double basePower = 0;
    double fullExtend = 1;
    double currentPower = 0;

    @Override
    public void init()
    {
        currentPower = basePower;
        pullerServo = hardwareMap.servo.get("Puller_Servo");
    }

    @Override
    public void loop()
    {
        if (gamepad1.y)//back
        {
            if (currentPower == basePower){ currentPower = fullExtend; pullerServo.setPosition(currentPower); }
            else if (currentPower == fullExtend) { currentPower = basePower; pullerServo.setPosition((currentPower));}
        }
    }
}
