package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Grabber
{
    private Servo blockServo = null;

    ElapsedTime MoveTime = new ElapsedTime();

    private double basePower = 0.05;
    private double fullExtend = .9;

    public Grabber(HardwareMap hardwareMap)
    {
        blockServo = hardwareMap.servo.get("pull_servo");

        blockServo.setPosition(0);

        MoveTime = new ElapsedTime();
    }

    public void Down()
    {
        MoveTime.reset();

        blockServo.setPosition(fullExtend);

        while (MoveTime.milliseconds() < 700);
    }

    public void Up()
    {
        MoveTime.reset();

        blockServo.setPosition(basePower);

        while (MoveTime.milliseconds() < 700);
    }
}
