package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

@TeleOp (name = "TeleopServoTest")
public class CapstoneAndFoundationPull
{
    Servo pullerServo = null;

    ElapsedTime waitTime = new ElapsedTime();

    double basePower = 1;
    double fullExtend = 0;
    double currentPower = 0;

    boolean speedReady;


    public void start()
    {
        currentPower = basePower;
        //pullerServo = hardwareMap.servo.get("Puller_Servo");

        pullerServo.setPosition(0);
    }

    public void grabber()
    {
        //speedReady = waitTime.milliseconds() > 500;

       // if (gamepad1.y && speedReady)
        //{
            if (currentPower == basePower){ currentPower = fullExtend; pullerServo.setPosition(currentPower); }
            else if (currentPower == fullExtend) { currentPower = basePower; pullerServo.setPosition((currentPower));}

            waitTime.reset();
        //}
    }
}
//Nicholas 9/15/19
