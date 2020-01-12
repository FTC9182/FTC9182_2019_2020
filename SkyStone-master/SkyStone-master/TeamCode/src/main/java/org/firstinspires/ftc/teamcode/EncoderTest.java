package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "EncoderTest")
@Disabled
public class EncoderTest extends OpMode {

    HDrive hDrive = null;
    LinearOpMode opMode;

    @Override
    public void init() {
        hDrive = new HDrive(hardwareMap);

    }

    @Override
    public void loop() {

        if (gamepad1.a){
            hDrive.ForwardRight.setPower(1);
        } else if (gamepad1.b){
            hDrive.ForwardRight.setPower(-1);
        } else {
            hDrive.ForwardRight.setPower(0);
        }

        telemetry.addData("ForwardRight",hDrive.ForwardRight.getCurrentPosition());

    }
}
