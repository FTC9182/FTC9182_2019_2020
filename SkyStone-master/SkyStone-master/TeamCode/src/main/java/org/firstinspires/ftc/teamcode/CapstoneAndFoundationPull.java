package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "TeleopServoTest")
public class CapstoneAndFoundationPull extends OpMode
{
    Servo pullerServo = null;

    ElapsedTime waitTime = new ElapsedTime();

    double basePower = 1;
    double fullExtend = 0;
    double currentPower = 0;

    boolean speedReady;

    @Override
    public void init()
    {
        currentPower = basePower;
        pullerServo = hardwareMap.servo.get("Puller_Servo");
        pullerServo.setPosition(0);
    }

    @Override
    public void loop()
    {
        speedReady = waitTime.milliseconds() > 500;

        if (gamepad1.y && speedReady)
        {
            if (currentPower == basePower){ currentPower = fullExtend; pullerServo.setPosition(currentPower); }
            else if (currentPower == fullExtend) { currentPower = basePower; pullerServo.setPosition((currentPower));}

            waitTime.reset();
        }
    }
}
//Nicholas 9/15/19
