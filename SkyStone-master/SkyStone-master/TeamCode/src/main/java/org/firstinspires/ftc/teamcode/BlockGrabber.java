package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BlockGrabber
{
    private Servo pullerServo = null;

    ElapsedTime MoveTime = new ElapsedTime();

    private double basePower = 1;
    private double fullExtend = 0.02;

    public BlockGrabber(HardwareMap hardwareMap)
    {
        pullerServo = hardwareMap.servo.get("block_servo");

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
