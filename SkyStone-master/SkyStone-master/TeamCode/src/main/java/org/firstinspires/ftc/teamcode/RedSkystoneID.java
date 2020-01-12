package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous(name = "RedSkystoneID")
@Disabled
public class RedSkystoneID extends LinearOpMode {

    //SkystoneID skystoneID;

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = true;

    private static final String VUFORIA_KEY =
            "AcOQber/////AAAAGd+Wx7PVUULtlRxS6UeH3RgFL7O2kqLUIvryVwUgd7KQqprL1p5dzd2lpfSa0GIT1bxUPE33ZUWu8oe1S7pT7faMKK2buUugP8KJ3Vj2smsM7+K0LrTAWX/e5tW2zptEhgmH4XOGMD0rgiXHEopZWHVKfRzT2icGLg3ErUTYgHtNjLneooZhWiWDnXHEQFOc4JIoTz63aSIptNjN5q9fXbOwj1Wf4/nU+sxCU0EujqhoZWIztt2zI+mX1iOkGd/qyaSjaxdQ0q1E+YNx+v+gTZ5b0rmyr2ody3e4c4S6nTR9AhagdoDRL6VOm6v5CWWpNwM+ETWuYOBtGm5iTc/YxniKwXbClrFkXckzM+9A6lPt";

    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    private static final float stoneZ = 2.00f * mmPerInch;

    //private static final float bridgeZ = 6.42f * mmPerInch;
    //private static final float bridgeY = 23 * mmPerInch;
    //private static final float bridgeX = 5.18f * mmPerInch;
    //private static final float bridgeRotY = 59;                                 // Units are degrees
    //private static final float bridgeRotZ = 180;

    //private static final float halfField = 72 * mmPerInch;
    //private static final float quadField  = 36 * mmPerInch;

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
        //VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
        //blueRearBridge.setName("Blue Rear Bridge");
        //VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
        //redRearBridge.setName("Red Rear Bridge");
        //VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
        //redFrontBridge.setName("Red Front Bridge");
        //VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
        //blueFrontBridge.setName("Blue Front Bridge");
        //VuforiaTrackable red1 = targetsSkyStone.get(5);
        //red1.setName("Red Perimeter 1");
        //VuforiaTrackable red2 = targetsSkyStone.get(6);
        //red2.setName("Red Perimeter 2");
        //VuforiaTrackable front1 = targetsSkyStone.get(7);
        //front1.setName("Front Perimeter 1");
        //VuforiaTrackable front2 = targetsSkyStone.get(8);
        //front2.setName("Front Perimeter 2");
        //VuforiaTrackable blue1 = targetsSkyStone.get(9);
        //blue1.setName("Blue Perimeter 1");
        //VuforiaTrackable blue2 = targetsSkyStone.get(10);
        //blue2.setName("Blue Perimeter 2");
        //VuforiaTrackable rear1 = targetsSkyStone.get(11);
        //rear1.setName("Rear Perimeter 1");
        //VuforiaTrackable rear2 = targetsSkyStone.get(12);
        //rear2.setName("Rear Perimeter 2");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);


        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

        //Set the position of the bridge support targets with relation to origin (center of field)
        /*blueFrontBridge.setLocation(OpenGLMatrix
                .translation(-bridgeX, bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, bridgeRotZ)));

        blueRearBridge.setLocation(OpenGLMatrix
                .translation(-bridgeX, bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, bridgeRotZ)));

        redFrontBridge.setLocation(OpenGLMatrix
                .translation(-bridgeX, -bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, 0)));

        redRearBridge.setLocation(OpenGLMatrix
                .translation(bridgeX, -bridgeY, bridgeZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, 0)));

        //Set the position of the perimeter targets with relation to origin (center of field)
        red1.setLocation(OpenGLMatrix
                .translation(quadField, -halfField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));

        red2.setLocation(OpenGLMatrix
                .translation(-quadField, -halfField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));

        front1.setLocation(OpenGLMatrix
                .translation(-halfField, -quadField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90)));

        front2.setLocation(OpenGLMatrix
                .translation(-halfField, quadField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 90)));

        blue1.setLocation(OpenGLMatrix
                .translation(-quadField, halfField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));

        blue2.setLocation(OpenGLMatrix
                .translation(quadField, halfField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));

        rear1.setLocation(OpenGLMatrix
                .translation(halfField, quadField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , -90)));

        rear2.setLocation(OpenGLMatrix
                .translation(halfField, -quadField, mmTargetHeight)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));
*/
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

        final float CAMERA_FORWARD_DISPLACEMENT  = 4.0f * mmPerInch;
        final float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;
        final float CAMERA_LEFT_DISPLACEMENT     = 0;

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }

        targetVisible = false;

        waitForStart();

        while (opModeIsActive() && hDrive.AutonSensor(20, 0.4, "GoToStone"));

        idle();

        hDrive.StopDriving("Left");

        targetsSkyStone.activate();

        moveTimer.reset();

        while (!isStopRequested() && targetVisible == false && moveTimer.milliseconds() < 8000) {

            stopGoTimer.reset();

            while(stopGoTimer.milliseconds() < 700){
                // check all the trackable targets to see which one (if any) is visible.
                targetVisible = false;
                for (VuforiaTrackable trackable : allTrackables) {
                    if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                        telemetry.addData("Visible Target", trackable.getName());
                        targetVisible = true;

                        // getUpdatedRobotLocation() will return null if no new information is available since
                        // the last time that call was made, or if the trackable is not currently visible.
                        /*OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                        if (robotLocationTransform != null) {
                            lastLocation = robotLocationTransform;
                        }
                        break;*/
                    }
                }

                // Provide feedback as to where the robot is located (if we know).
                if (targetVisible) {
                    // express position (translation) of robot in inches.
                    /*VectorF translation = lastLocation.getTranslation();
                    telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                            translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);

                    // express the rotation of the robot in degrees.
                    Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                    telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);*/
                }
                else {
                    telemetry.addData("Visible Target", "none");
                }
                telemetry.update();
            }

            stopGoTimer.reset();

            while(stopGoTimer.milliseconds() < 1000){
                hDrive.ForwardRight.setPower(-0.3);
                hDrive.ForwardLeft.setPower(-0.3);
                hDrive.BackwardsRight.setPower(-0.3);
                hDrive.BackwardsLeft.setPower(-0.3);
            }

            hDrive.StopDriving("InstantStop");
        }

        // Disable Tracking when we are done;
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
