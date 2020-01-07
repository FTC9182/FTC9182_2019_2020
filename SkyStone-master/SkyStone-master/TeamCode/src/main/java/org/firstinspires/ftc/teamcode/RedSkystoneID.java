package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public class RedSkystoneID extends LinearOpMode {

    //SkystoneID skystoneID;

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false;

    private static final String VUFORIA_KEY =
            "AcOQber/////AAAAGd+Wx7PVUULtlRxS6UeH3RgFL7O2kqLUIvryVwUgd7KQqprL1p5dzd2lpfSa0GIT1bxUPE33ZUWu8oe1S7pT7faMKK2buUugP8KJ3Vj2smsM7+K0LrTAWX/e5tW2zptEhgmH4XOGMD0rgiXHEopZWHVKfRzT2icGLg3ErUTYgHtNjLneooZhWiWDnXHEQFOc4JIoTz63aSIptNjN5q9fXbOwj1Wf4/nU+sxCU0EujqhoZWIztt2zI+mX1iOkGd/qyaSjaxdQ0q1E+YNx+v+gTZ5b0rmyr2ody3e4c4S6nTR9AhagdoDRL6VOm6v5CWWpNwM+ETWuYOBtGm5iTc/YxniKwXbClrFkXckzM+9A6lPt";

    private static final float mmPerInch = 25.4f;
    private static final float mmTargetHeight = (6) * mmPerInch;

    private static final float stoneZ = 2.00f * mmPerInch;

    private VuforiaLocalizer vuforia = null;
    boolean targetVisible = false;
    float phoneXRotate = 0;
    float phoneYRotate = 0;
    float phoneZRotate = 0;

    HDrive hDrive = null;
    BlockGrabber grabber = null;

    public void runOpMode() throws InterruptedException {

        //skystoneID = new SkystoneID(hardwareMap);

        hDrive = new HDrive(hardwareMap);
        grabber = new BlockGrabber(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");

        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90;
        }

        targetVisible = false;

        waitForStart();

        //We need another distance sensor on the side of the robot so that it can pull up to the stone since
        //the current one is on the front.

        while (opModeIsActive() && hDrive.AutonSensor(20, 0.4, "need new sensor"));

        idle();

        hDrive.StopDriving("Left");

        targetsSkyStone.activate();

        while (opModeIsActive() && targetVisible == false){

            //while (!isStopRequested()) {

                if (((VuforiaTrackableDefaultListener) stoneTarget.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", stoneTarget.getName());
                    targetVisible = true;
                } else {
                    telemetry.addData("Visible Target", "none");
                }

            //}

            hDrive.ForwardRight.setPower(-0.3);
            hDrive.ForwardLeft.setPower(-0.3);
            hDrive.BackwardsRight.setPower(-0.3);
            hDrive.BackwardsLeft.setPower(-0.3);

        }

        targetsSkyStone.deactivate();

        idle();

        hDrive.StopDriving("Backwards");

        while(opModeIsActive() && hDrive.AutoDrive(0.4, 1.7, "Left"));

        idle();

        hDrive.StopDriving("Left");

        grabber.Down();

        idle();

        while(opModeIsActive() && hDrive.AutoDrive(0.4, 2, "Right"));

        idle();

        hDrive.StopDriving("Right");

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.4, "RedParkSkystone"));

        idle();

        hDrive.StopDriving("Forward");

        while(opModeIsActive() && hDrive.AutoDrive(0.4, 2, "Forward"));

        idle();

        hDrive.StopDriving("Forward");

        grabber.Up();

        idle();

        while(opModeIsActive() && hDrive.AutoDrive(0.4, 2, "Backwards"));

        idle();

        hDrive.StopDriving("Backwards");





    }
}
