package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class EncoderTest extends OpMode {

    HDrive hDrive = null;

    @Override
    public void init() {
        hDrive = new HDrive(hardwareMap);

    }

    @Override
    public void loop() {

    }
}
