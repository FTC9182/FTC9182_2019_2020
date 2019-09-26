package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name = "Teleop")
public class Teleop extends OpMode {

    HDrive hDrive = null;
    Grabber grabber = null;
    public ElapsedTime waitTime = new ElapsedTime();

    //Drive var
    double driveX;
    double driveY;
    double turnDegrees;

    //Grabber var
    double currentPower;
    double basePower = 1;
    double fullExtend = 0;

    boolean speedReady;

    public void init(){

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();
        currentPower = basePower;
    }

    public void loop(){

        speedReady = waitTime.milliseconds() > 500;

        if (gamepad1.x && speedReady){

            if (currentPower == basePower){ currentPower = fullExtend; grabber.Down(); }
            else if (currentPower == fullExtend) { currentPower = basePower; grabber.Up();}

            waitTime.reset();

        driveX = gamepad1.left_stick_x;
        driveY = gamepad1.left_stick_y;
        turnDegrees = gamepad1.right_stick_x;

        hDrive.drive(driveX, driveY, turnDegrees);


    }
}
