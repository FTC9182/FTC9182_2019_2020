package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

@TeleOp(name = "VuforiaTest")
public class VuforiaTest extends OpMode {

    SkystoneID skystoneID = null;

    public void init(){

        skystoneID = new SkystoneID(hardwareMap);

    }

    public void loop() {

        /*if (((VuforiaTrackableDefaultListener)skystoneID.stoneTarget.getListener()).isVisible()) {

            telemetry.addData("SeenSkystone", "True");
            telemetry.update();

        } else {

            telemetry.addData("SeenSkystone", "False");
            telemetry.update();

        }*/

    }
}
