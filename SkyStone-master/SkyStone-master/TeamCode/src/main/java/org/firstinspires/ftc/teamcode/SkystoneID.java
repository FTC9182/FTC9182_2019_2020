package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public class SkystoneID {

    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;

    private static final String VUFORIA_KEY =
            "AcOQber/////AAAAGd+Wx7PVUULtlRxS6UeH3RgFL7O2kqLUIvryVwUgd7KQqprL1p5dzd2lpfSa0GIT1bxUPE33ZUWu8oe1S7pT7faMKK2buUugP8KJ3Vj2smsM7+K0LrTAWX/e5tW2zptEhgmH4XOGMD0rgiXHEopZWHVKfRzT2icGLg3ErUTYgHtNjLneooZhWiWDnXHEQFOc4JIoTz63aSIptNjN5q9fXbOwj1Wf4/nU+sxCU0EujqhoZWIztt2zI+mX1iOkGd/qyaSjaxdQ0q1E+YNx+v+gTZ5b0rmyr2ody3e4c4S6nTR9AhagdoDRL6VOm6v5CWWpNwM+ETWuYOBtGm5iTc/YxniKwXbClrFkXckzM+9A6lPt";

    private static final float mmPerInch        = 0;
    private static final float mmTargetHeight   = 0;
    private static final float stoneZ = 0;

    final float CAMERA_FORWARD_DISPLACEMENT  = 0;   // eg: Camera is 4 Inches in front of robot center
    final float CAMERA_VERTICAL_DISPLACEMENT = 0;   // eg: Camera is 8 Inches above ground
    final float CAMERA_LEFT_DISPLACEMENT     = 0;

    private VuforiaLocalizer vuforia;
    private boolean targetVisible = false;

    VuforiaTrackables targetsSkyStone;
    VuforiaTrackable stoneTarget;


    HDrive hDrive = null;

    boolean Left_Right;




    public SkystoneID (HardwareMap hardwareMap){

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = CAMERA_CHOICE;

        float phoneXRotate    = 0;
        float phoneYRotate    = 0;
        float phoneZRotate    = 0;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");

        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);

        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90 ;
        }

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }

        Left_Right = true;

    }

    public boolean SkystoneRedID(double Speed){

        if (((VuforiaTrackableDefaultListener)stoneTarget.getListener()).isVisible()) {

            targetVisible = true;

        }

        if (Left_Right = true){

            hDrive.ForwardRight.setPower(Speed);
            hDrive.ForwardLeft.setPower(-Speed);
            hDrive.BackwardsRight.setPower(-Speed);
            hDrive.BackwardsLeft.setPower(Speed);

        }

        if (Left_Right = false){

            hDrive.ForwardRight.setPower(-Speed);
            hDrive.ForwardLeft.setPower(Speed);
            hDrive.BackwardsRight.setPower(Speed);
            hDrive.BackwardsLeft.setPower(-Speed);

        }

        /*if (touch sensor = true){
            Left_Right = false;
        }*/

        return targetVisible = false;


    }
}
