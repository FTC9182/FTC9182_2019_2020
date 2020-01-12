package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

@TeleOp(name = "VuforiaTestLinearOpMode")
@Disabled
public class VuforiaTestLinearOpMode extends LinearOpMode {

    SkystoneID skystoneID;

    @Override
    public void runOpMode() throws InterruptedException {

        skystoneID = new SkystoneID(hardwareMap);

        waitForStart();

        //skystoneID.targetsSkyStone.activate();

        /*while (opModeIsActive()){
            if (((VuforiaTrackableDefaultListener)skystoneID.stoneTarget.getListener()).isVisible()) {

                telemetry.addData("SeenSkystone", "True");
                telemetry.update();

            } else {

                telemetry.addData("SeenSkystone", "False");
                telemetry.update();

            }
        }*/


    }
}
