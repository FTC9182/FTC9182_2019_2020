package org.firstinspires.ftc.teamcode;

//import android.hardware.camera2.CameraDevice;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous(name = "BlueSkystoneIDTest2")
public class BlueSkystoneIDTest2 extends LinearOpMode {

    //SkystoneID skystoneID;

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;

    private static final String VUFORIA_KEY =
            "AcOQber/////AAAAGd+Wx7PVUULtlRxS6UeH3RgFL7O2kqLUIvryVwUgd7KQqprL1p5dzd2lpfSa0GIT1bxUPE33ZUWu8oe1S7pT7faMKK2buUugP8KJ3Vj2smsM7+K0LrTAWX/e5tW2zptEhgmH4XOGMD0rgiXHEopZWHVKfRzT2icGLg3ErUTYgHtNjLneooZhWiWDnXHEQFOc4JIoTz63aSIptNjN5q9fXbOwj1Wf4/nU+sxCU0EujqhoZWIztt2zI+mX1iOkGd/qyaSjaxdQ0q1E+YNx+v+gTZ5b0rmyr2ody3e4c4S6nTR9AhagdoDRL6VOm6v5CWWpNwM+ETWuYOBtGm5iTc/YxniKwXbClrFkXckzM+9A6lPt";

    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    private static final float stoneZ = 2.00f * mmPerInch;

    //private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate    = 0;
    private float phoneYRotate    = 0;
    private float phoneZRotate    = 0;

    HDrive hDrive = null;
    BlockGrabber grabber = null;
    ElapsedTime moveTimer = null;
    ElapsedTime stopGoTimer = null;

    public void runOpMode() throws InterruptedException {

        //skystoneID = new SkystoneID(hardwareMap);

        hDrive = new HDrive(hardwareMap);
        grabber = new BlockGrabber(hardwareMap);

        moveTimer = new ElapsedTime();
        stopGoTimer = new ElapsedTime();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);

        //
        // Create a transformation matrix describing where the phone is on the robot.
        //
        // NOTE !!!!  It's very important that you turn OFF your phone's Auto-Screen-Rotation option.
        // Lock it into Portrait for these numbers to work.
        //
        // Info:  The coordinate frame for the robot looks the same as the field.
        // The robot's "forward" direction is facing out along X axis, with the LEFT side facing out along the Y axis.
        // Z is UP on the robot.  This equates to a bearing angle of Zero degrees.
        //
        // The phone starts out lying flat, with the screen facing Up and with the physical top of the phone
        // pointing to the LEFT side of the Robot.
        // The two examples below assume that the camera is facing forward out the front of the robot.

        // We need to rotate the camera around it's long axis to bring the correct camera forward.
        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90 ;
        }

        targetVisible = false;

        waitForStart();

        while (opModeIsActive() && hDrive.AutonSensor(15, 0.4, "GoToStone"));

        idle();

        hDrive.StopDriving("Left");

        targetsSkyStone.activate();

        moveTimer.reset();

        while (!isStopRequested() && targetVisible == false && moveTimer.milliseconds() < 8000) {

            stopGoTimer.reset();

            while(stopGoTimer.milliseconds() < 1000);

            stopGoTimer.reset();

            while(stopGoTimer.milliseconds() < 700 && targetVisible==false){
                // check all the trackable targets to see which one (if any) is visible.
                for (VuforiaTrackable trackable : allTrackables) {
                    if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                        telemetry.addData("Visible Target", trackable.getName());
                        targetVisible = true;
                        break;
                    }else {
                        telemetry.addData("Visible Target", "none");
                    }
                    telemetry.update();

                    CameraDevice.getInstance().setFlashTorchMode(true);

                }
            }

            stopGoTimer.reset();

            while(stopGoTimer.milliseconds() < 1000 && targetVisible == false){
                hDrive.ForwardRight.setPower(0.3);
                hDrive.ForwardLeft.setPower(0.3);
                hDrive.BackwardsRight.setPower(0.3);
                hDrive.BackwardsLeft.setPower(0.3);
            }

            hDrive.StopDriving("InstantStop");
        }

        // Disable Tracking when we are done;
        targetsSkyStone.deactivate();

        CameraDevice.getInstance().setFlashTorchMode(false);

        idle();

        hDrive.StopDriving("InstantStop");

        moveTimer.reset();

        while(moveTimer.milliseconds() < 150){
            hDrive.ForwardRight.setPower(0.3);
            hDrive.ForwardLeft.setPower(0.3);
            hDrive.BackwardsRight.setPower(0.3);
            hDrive.BackwardsLeft.setPower(0.3);
        }

        idle();

        hDrive.StopDriving("InstantStop");

        while(opModeIsActive() && hDrive.AutoDrive(0.4, 1.7, "Left"));

        idle();

        hDrive.StopDriving("Left");

        grabber.Down();

        idle();

        while(opModeIsActive() && hDrive.AutoDrive(0.4, 2, "Right"));

        idle();

        hDrive.StopDriving("Right");

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.6, "BlueParkSkystone"));

        idle();

        hDrive.StopDriving("Backwards");

        moveTimer.reset();

        while(opModeIsActive() && moveTimer.milliseconds() < 400){
            hDrive.ForwardRight.setPower(-0.4);
            hDrive.ForwardLeft.setPower(-0.4);
            hDrive.BackwardsRight.setPower(-0.4);
            hDrive.BackwardsLeft.setPower(-0.4);
        }

        idle();

        hDrive.StopDriving("Backwards");

        while(opModeIsActive() && hDrive.AutoDrive(0.6, 2, "Right"));

        idle();

        hDrive.StopDriving("Right");

        grabber.Up();

        idle();

        while(opModeIsActive() && hDrive.AutoDrive(0.6, 2, "Left"));

        idle();

        hDrive.StopDriving("Left");

        moveTimer.reset();

        while(opModeIsActive() && hDrive.AutonSensor(0, 0.4, "BlueParkQuarry2"));

        idle();

        hDrive.StopDriving("Forward");

        while (opModeIsActive());

    }
}
