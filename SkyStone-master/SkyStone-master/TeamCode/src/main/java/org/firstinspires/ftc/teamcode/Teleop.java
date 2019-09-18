package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "Teleop")
public class Teleop extends OpMode {

    Grabber grabber1 = null;
    public ElapsedTime waitTime = new ElapsedTime();

    double basePower = 1;
    double fullExtend = 0;
    double currentPower = 0;

    boolean speedReady;

    @Override
    public void init() {

        grabber1.Up();
        currentPower = basePower;

        grabber1 = new Grabber(hardwareMap);

    }

    @Override
    public void loop() {

        speedReady = waitTime.milliseconds() > 500;

        if (gamepad1.x && speedReady){

            if (currentPower == basePower){ currentPower = fullExtend; grabber1.Down(); }
            else if (currentPower == fullExtend) { currentPower = basePower; grabber1.Up();}

            waitTime.reset();
        }

    }
}
