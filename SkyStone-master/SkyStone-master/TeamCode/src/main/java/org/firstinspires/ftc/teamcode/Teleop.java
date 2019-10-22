package org.firstinspires.ftc.teamcode;

import android.renderscript.Sampler;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "Teleop")
public class Teleop extends OpMode {

    HDrive hDrive = null;
    Grabber grabber = null;
    WheelGrabber wheelGrabber = null;
    Arm armExtend = null;
    ArmRotation armRotate = null;
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

    //Arm
    double gunnerY;
    double gunnerY2;

    boolean armReady = true;
    boolean armRotateReady = true;
    boolean Locked  = false;
    boolean Up = true;

    public ElapsedTime triggerTime = new ElapsedTime();
    public ElapsedTime armTime = new ElapsedTime();
    public ElapsedTime armRotateTime = new ElapsedTime();

    public void init() {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        wheelGrabber = new WheelGrabber(hardwareMap);
        armExtend = new Arm(hardwareMap);
        armRotate = new ArmRotation(hardwareMap);
        grabber.Up();
        currentPower = basePower;
    }

    public void loop() {
        //Gamepad1
        speedReady = waitTime.milliseconds() > 500;
        triggerReady = triggerTime.milliseconds() > 500;
        armReady = armTime.milliseconds() > 300;
        armRotateReady = armRotateTime.milliseconds() > 400;

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
        gunnerY = gamepad2.left_stick_y;
        gunnerY2 = gamepad2.right_stick_y;
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
                wheelGrabber.Open();
                triggerTime.reset();
            } else if (gamepad2.right_bumper) {
                wheelGrabber.IncrementClose();
                triggerTime.reset();
            } else if (gamepad2.left_bumper) {
                wheelGrabber.IncrementOpen();
                triggerTime.reset();
            }
        }

        armExtend.Move(gunnerY);

        if (gunnerY2 >= 0.5) { Locked = false; Up = false; telemetry.addData("Down", Locked);}
        else if (gunnerY2 <= -0.5) { Locked = false; Up = true; telemetry.addData("Up", Locked);}
        else if (gunnerY2 >= -0.3 && gunnerY2 <= 0.3) { Locked = true; telemetry.addData("Locked", Locked);}

        if (!Locked && Up){ armRotate.UpMove(gunnerY2); }
        else if (!Locked && !Up) { armRotate.DownMove(gunnerY2); }
        else if (Locked) { armRotate.GravityCounter(); }

        /*if(armReady) {
            if (gunnerY >= .5) {
                arm.IncrementUp();
                armTime.reset();
            }
            if (gunnerY <= -.5) {
                arm.IncrementDown();
                armTime.reset();
            }
        }*/


        /*if (gamepad2.b && armRotateReady && !Locked)
        {
            armRotateTime.reset();
            armRotate.Brake();
            Locked = true;
        } else if (!Locked){
            armRotate.Move(gunnerY2);
        }else if (gamepad2.b && armRotateReady && Locked){
            Locked = false;
        }*/


        /*if (armRotateReady){
            if (gunnerY2 >= .5){
                armRotate.Up();
                armRotateTime.reset();
            }
            if (gunnerY2 <= -.5){
                armRotate.Down();
                armRotateTime.reset();
            }
        }*/
        telemetry.update();
    }
}


