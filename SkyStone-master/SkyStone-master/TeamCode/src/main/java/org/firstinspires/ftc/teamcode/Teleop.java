package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "Teleop")
public class Teleop extends OpMode {

    Grabber grabber1 = new Grabber(hardwareMap);
    ElapsedTime waitTime = new ElapsedTime();

    boolean speedReady;

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        speedReady = waitTime.milliseconds() > 500;

    }
}
