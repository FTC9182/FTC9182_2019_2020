package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "Teleop")
public class Teleop extends OpMode {

    HDrive hDrive = null;
    Grabber grabber = null;
    WheelGrabber wheelGrabber = null;
    public ElapsedTime waitTime = new ElapsedTime();

    //Drive var
    double driveX;
    double driveY;
    double turnDegrees;
    double speedVari = 1;

    //Grabber var
    double currentPower;
    double basePower = 1;
    double fullExtend = 0;

    boolean speedReady;

    //Block grabber
    boolean triggerReady = true;

    public ElapsedTime triggerTime = new ElapsedTime();

    public void init() {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        wheelGrabber = new WheelGrabber(hardwareMap);
        grabber.Up();
        currentPower = basePower;
    }

    public void loop() {
        //Gamepad1
        speedReady = waitTime.milliseconds() > 500;
        triggerReady = triggerTime.milliseconds() > 500;

        if (gamepad1.x && speedReady) {

            if (currentPower == basePower) {
                currentPower = fullExtend;
                grabber.Down();
                waitTime.reset();
            } else if (currentPower == fullExtend) {
                currentPower = basePower;
                grabber.Up();
                waitTime.reset();
            }
        }

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        turnDegrees = gamepad1.right_stick_x;

        hDrive.drive(driveX, driveY, turnDegrees, speedVari);

        if (gamepad1.dpad_up) {
            speedVari = 1;
        } else if (gamepad1.dpad_left) {
            speedVari = 0.5;
        } else if (gamepad1.dpad_right) {
            speedVari = 0.75;
        } else if (gamepad1.dpad_down) {
            speedVari = 0.25;
        }

        //Gamepad2
        if (triggerReady) {
            if (gamepad2.right_trigger >= .75f) {
                wheelGrabber.Close();
                triggerTime.reset();
            } else if (gamepad2.left_trigger >= .75f) {
                wheelGrabber.IncrementOpen();
                triggerTime.reset();
            } else if (gamepad2.right_bumper) {
                wheelGrabber.IncrementClose();
                triggerTime.reset();
                telemetry.addData("Bumper Press: ", "Right Bumper");
            } else if (gamepad2.left_bumper) {
                wheelGrabber.Open();
                triggerTime.reset();
                telemetry.addData("Bumper Press: ", "Left Bumper");
            }

                telemetry.addData("Servo Current Degree: ", wheelGrabber.currentDegree);
        }

        telemetry.update();
        }
    }


