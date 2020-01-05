package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HDrive {

    DcMotor ForwardRight = null;
    DcMotor ForwardLeft = null;
    DcMotor BackwardsRight = null;
    DcMotor BackwardsLeft = null;
    DcMotor Middle = null;

    ElapsedTime SlowTime = null;

    ModernRoboticsI2cRangeSensor FrontDistanceSensor;
    ColorSensor BottomSensorColor;
    //DistanceSensor BackDistanceSensor;
    ColorSensor FrontColorSensor;
    DigitalChannel FoundationTouch;

    final double DRIVETICKS = 800;

    final int SCALE_FACTOR = 255;

    boolean SkystoneSeen;
    boolean SeenSkystone;

    public HDrive(HardwareMap hardwareMap) {
        ForwardRight = hardwareMap.dcMotor.get("front_right");
        ForwardLeft = hardwareMap.dcMotor.get("front_left");
        BackwardsRight = hardwareMap.dcMotor.get("back_right");
        BackwardsLeft = hardwareMap.dcMotor.get("back_left");
        Middle = hardwareMap.dcMotor.get("middle");

        ForwardRight.setDirection(DcMotorSimple.Direction.REVERSE);
        ForwardLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        BackwardsRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BackwardsLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        Middle.setDirection(DcMotorSimple.Direction.REVERSE);

        SlowTime = new ElapsedTime();

        FrontDistanceSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range");

        BottomSensorColor = hardwareMap.get(ColorSensor.class, "bottom_sensor");

//        BackDistanceSensor = hardwareMap.get(DistanceSensor.class, "back_distance_sensor");

        FrontColorSensor = hardwareMap.get(ColorSensor.class, "front_color_sensor");

        FoundationTouch = hardwareMap.digitalChannel.get("foundation_touch");


        FoundationTouch.setMode(DigitalChannel.Mode.INPUT);

    }

    public void drive(double driveX, double driveY, double turnDegrees, double speedVari) {

        ForwardRight.setPower(-speedVari*(driveY-driveX-turnDegrees));
        ForwardLeft.setPower(-speedVari*(driveY+driveX+turnDegrees));
        BackwardsLeft.setPower(-speedVari*(driveY-driveX+turnDegrees));
        BackwardsRight.setPower(-speedVari*(driveY+driveX-turnDegrees));

        /*ForwardRight.setPower(speedVari * (driveY - turnDegrees));
        ForwardLeft.setPower(speedVari * (driveY + turnDegrees));
        BackwardsRight.setPower(speedVari * (driveY - turnDegrees));
        BackwardsLeft.setPower(speedVari * (driveY + turnDegrees));
        Middle.setPower(speedVari * -driveX);*/
    }

    public void StopDriving(String Direction) {

        SlowTime.reset();

        if (ForwardRight.getPower() > 0.2 && Direction == "Forward") {
            while (SlowTime.milliseconds() < 500) {
                ForwardRight.setPower(0.2);
                ForwardLeft.setPower(0.2);
                BackwardsRight.setPower(0.2);
                BackwardsLeft.setPower(0.2);
            }
        }

        if (ForwardRight.getPower() < -0.2 && Direction == "Backwards") {
            while (SlowTime.milliseconds() < 500) {
                ForwardRight.setPower(-0.2);
                ForwardLeft.setPower(-0.2);
                BackwardsRight.setPower(-0.2);
                BackwardsLeft.setPower(-0.2);
            }
        }

        if (/*Middle.getPower() > 0.2*/ ForwardRight.getPower() > 0.2 && Direction == "Left") {
            while (SlowTime.milliseconds() < 500) {
                //Middle.setPower(0.2);
                ForwardRight.setPower(0.2);
                ForwardLeft.setPower(-0.2);
                BackwardsRight.setPower(-0.2);
                BackwardsLeft.setPower(0.2);
            }
        }

        if (/*Middle.getPower() < -0.2*/ ForwardRight.getPower() < -0.2 && Direction == "Right") {
            while (SlowTime.milliseconds() < 500) {
                //Middle.setPower(-0.2);
                ForwardRight.setPower(-0.2);
                ForwardLeft.setPower(0.2);
                BackwardsRight.setPower(0.2);
                BackwardsLeft.setPower(-0.2);
            }
        }

        if ((ForwardRight.getPower() > 0.2) && (Direction == "TurnLeft")) {
            while (SlowTime.milliseconds() < 500) {
                ForwardRight.setPower(-0.2);
                ForwardLeft.setPower(0.2);
                BackwardsRight.setPower(-0.2);
                BackwardsLeft.setPower(0.2);
            }
        }

        if ((ForwardRight.getPower() < -0.2) && (Direction == "TurnRight")) {
            while (SlowTime.milliseconds() < 500) {
                ForwardRight.setPower(0.2);
                ForwardLeft.setPower(-0.2);
                BackwardsRight.setPower(0.2);
                BackwardsLeft.setPower(-0.2);
            }
        }

        ForwardRight.setPower(0);
        ForwardLeft.setPower(0);
        BackwardsRight.setPower(0);
        BackwardsLeft.setPower(0);
        Middle.setPower(0);

        ForwardRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ForwardRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ForwardLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ForwardLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackwardsRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackwardsRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackwardsLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackwardsLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Middle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public boolean AutoDrive(double Speed, double TargetDistance, String Direction) {
//        ForwardRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        ForwardRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        ForwardLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        ForwardLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        BackwardsRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        BackwardsRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        BackwardsLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        BackwardsLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        Middle.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        Middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        double FBDistance;

        FBDistance = (-ForwardRight.getCurrentPosition() + ForwardLeft.getCurrentPosition() + BackwardsRight.getCurrentPosition() - BackwardsLeft.getCurrentPosition()) / 4;

        double SDistance;

        SDistance = (ForwardRight.getCurrentPosition() - ForwardLeft.getCurrentPosition() - BackwardsRight.getCurrentPosition() + BackwardsLeft.getCurrentPosition());

        //SDistance = Middle.getCurrentPosition();

        double TurnDistance;

        TurnDistance = (ForwardRight.getCurrentPosition() + BackwardsRight.getCurrentPosition()) / 2;

        double DriveDistance;

        DriveDistance = TargetDistance * DRIVETICKS;

        if ((Direction == "Forward")) {

            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(Speed);
            FBDistance = (-ForwardRight.getCurrentPosition() + ForwardLeft.getCurrentPosition() + BackwardsRight.getCurrentPosition() - BackwardsLeft.getCurrentPosition()) / 4;

            return FBDistance > -DriveDistance;

        }

        //StopDriving("Forward");

        if (Direction == "Backwards") {

            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(-Speed);
            FBDistance = (-ForwardRight.getCurrentPosition() + ForwardLeft.getCurrentPosition() + BackwardsRight.getCurrentPosition() - BackwardsLeft.getCurrentPosition()) / 4;

            return FBDistance < DriveDistance;

        }

        //StopDriving("Backwards");

        if (Direction == "Left") {

            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(Speed);

            SDistance = (ForwardRight.getCurrentPosition() - ForwardLeft.getCurrentPosition() - BackwardsRight.getCurrentPosition() + BackwardsLeft.getCurrentPosition()) / 4;

            return SDistance < DriveDistance;

            /*Middle.setPower(Speed);
            SDistance = Middle.getCurrentPosition();

            return SDistance < DriveDistance;*/
        }

        //StopDriving("Left");

        if (Direction == "Right") {

            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(-Speed);

            SDistance = (ForwardRight.getCurrentPosition() - ForwardLeft.getCurrentPosition() - BackwardsRight.getCurrentPosition() + BackwardsLeft.getCurrentPosition()) / 4;

            return SDistance > -DriveDistance;
            /*Middle.setPower(-Speed);
            SDistance = Middle.getCurrentPosition();

            return SDistance > -DriveDistance;*/
        }

        //StopDriving("Right");

        if (Direction == "TurnLeft") {
            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(Speed);

            TurnDistance = (ForwardRight.getCurrentPosition() + BackwardsRight.getCurrentPosition()) / 2;

            return TurnDistance < DriveDistance;
        }

        //StopDriving("TurnLeft");

        if (Direction == "TurnRight") {
            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(-Speed);

            TurnDistance = (ForwardRight.getCurrentPosition() + BackwardsRight.getCurrentPosition()) / 2;

            return TurnDistance > -DriveDistance;
        }

        //StopDriving("TurnRight");

        return false;


    }

    public boolean AutonSensor(int TargetDistance, double Speed, String Direction) {
        if (Direction == "PullToWall/GoToStone") {

            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(Speed);

            return FrontDistanceSensor.rawUltrasonic() > TargetDistance;

        }

        //StopDriving("Forward");

        if (Direction == "BlueParkFoundation") {

            //Middle.setPower(-Speed);
            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(Speed);

            return (BottomSensorColor.blue() - BottomSensorColor.red()) < 25;

        }

        //StopDriving("Left");

        if (Direction == "RedParkFoundation") {

            //Middle.setPower(Speed);

            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(-Speed);

            return (BottomSensorColor.red() - BottomSensorColor.blue()) < 35;

        }

        //StopDriving("Right");

        if (Direction == "RedParkQuarry") {

            //Middle.setPower(Speed);
            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(-Speed);

            return (BottomSensorColor.red() - BottomSensorColor.blue()) < 35;

        }

        //StopDriving("Right");

        if (Direction == "BlueParkQuarry") {

            //Middle.setPower(-Speed);
            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(Speed);

            return (BottomSensorColor.blue() - BottomSensorColor.red()) < 25;

        }

        //StopDriving("Left");

        if (Direction == "GoBackwards") {

            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(-Speed);

            return (FrontDistanceSensor.rawUltrasonic() < TargetDistance);// || FrontDistanceSensor.rawUltrasonic() > 200
        }

        //StopDriving("Backwards");

        if (Direction == "GoToFoundation"){

            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(-Speed);

            return FoundationTouch.getState() == true;

        }

        //StopDriving("Backwards");

        if (Direction == "SkystoneRedIDPart1") {

            if ((FrontColorSensor.red()) < 50) {
                SkystoneSeen = true;
            }

            //Middle.setPower(-Speed);
            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(Speed);

            return SkystoneSeen == false;

        }

        if (Direction == "SkystoneRedIDPart2"){
            if(FrontColorSensor.red() > 50){
                SeenSkystone = true;
            }

            //Middle.setPower(-Speed);
            ForwardRight.setPower(Speed);
            ForwardLeft.setPower(-Speed);
            BackwardsRight.setPower(-Speed);
            BackwardsLeft.setPower(Speed);

            return SeenSkystone == false;
        }

        if (Direction == "SkystoneBlueID") {

            if ((FrontColorSensor.red()) < 50) {
                SkystoneSeen = true;
            }

            //Middle.setPower(Speed);
            ForwardRight.setPower(-Speed);
            ForwardLeft.setPower(Speed);
            BackwardsRight.setPower(Speed);
            BackwardsLeft.setPower(-Speed);

            return SkystoneSeen == false;

        }

        return false;


    }

}
