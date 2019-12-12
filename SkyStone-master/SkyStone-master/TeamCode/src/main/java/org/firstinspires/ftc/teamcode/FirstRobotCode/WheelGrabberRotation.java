package org.firstinspires.ftc.teamcode.FirstRobotCode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WheelGrabberRotation
{
    private Servo pullerServo = null;

    ElapsedTime MoveTime = new ElapsedTime();

    private double basePower = 0;
    private double fullExtend = 0.5;

    public WheelGrabberRotation(HardwareMap hardwareMap)
    {
        pullerServo = hardwareMap.servo.get("rotate_servo");

        pullerServo.setPosition(basePower);

        MoveTime = new ElapsedTime();
    }

    public void Down()
    {
        MoveTime.reset();

        pullerServo.setPosition(fullExtend);

        while (MoveTime.milliseconds() < 700);
    }

    public void Up()
    {
        MoveTime.reset();

        pullerServo.setPosition(basePower);

        while (MoveTime.milliseconds() < 700);
    }
}
