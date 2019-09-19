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
    DriveTrain move = null;
    public ElapsedTime waitTime = new ElapsedTime();

    double basePower = 1;
    double fullExtend = 0;
    double currentPower = 0;

    double driveX;
    double driveY;
    double turnDegrees;
    double speedVari;

    boolean speedReady;

    @Override
    public void init() {

        grabber1 = new Grabber(hardwareMap);
        grabber1.Up();
        currentPower = basePower;
        move = new DriveTrain(hardwareMap);

        speedVari = 1;

    }

    @Override
    public void loop() {

        speedReady = waitTime.milliseconds() > 500;

        if (gamepad1.x && speedReady){

            if (currentPower == basePower){ currentPower = fullExtend; grabber1.Down(); }
            else if (currentPower == fullExtend) { currentPower = basePower; grabber1.Up();}

            waitTime.reset();


        }

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        turnDegrees = gamepad1.right_stick_x;

        if (gamepad1.dpad_up){
            speedVari = 1;
        } else if (gamepad1.dpad_left) {
            speedVari = 0.5;
        } else if (gamepad1.dpad_right) {
            speedVari = 0.75;
        } else if (gamepad1.dpad_down) {
            speedVari = 0.25;
        }

        move.Move(speedVari, driveX, driveY, turnDegrees);

    }
}
