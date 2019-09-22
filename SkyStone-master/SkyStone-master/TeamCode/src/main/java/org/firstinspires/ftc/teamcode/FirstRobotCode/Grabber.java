package org.firstinspires.ftc.teamcode.FirstRobotCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Grabber
{
    private Servo pullerServo = null;

    private double basePower = 1;
    private double fullExtend = 0.15;

    public Grabber(HardwareMap hardwareMap)
    {
        pullerServo = hardwareMap.servo.get("pull_servo");

        pullerServo.setPosition(0);
    }

    public void Down()
    {
        pullerServo.setPosition(fullExtend);
    }

    public void Up()
    {
        pullerServo.setPosition(basePower);
    }
}