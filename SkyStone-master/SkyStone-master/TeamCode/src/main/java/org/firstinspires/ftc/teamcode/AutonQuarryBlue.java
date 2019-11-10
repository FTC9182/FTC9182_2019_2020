package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "AutonQuarryBlue")
public class AutonQuarryBlue extends LinearOpMode {

    Grabber grabber = null;
    HDrive hDrive = null;
    LinearOpMode opMode;

    @Override
    public void runOpMode() throws InterruptedException {

        hDrive = new HDrive(hardwareMap);
        grabber = new Grabber(hardwareMap);
        grabber.Up();

        waitForStart();

<<<<<<< Updated upstream
        hDrive.AutonSensor(5, 0.5, "GoToFoundation/Stone");
=======
        while(opModeIsActive()){

        /*hDrive.AutonSensor(5, 0.5, "GoToFoundation/Stone");
>>>>>>> Stashed changes

        idle();

        hDrive.AutonSensor(0, 0.4, "BlueParkQuarry");

        idle();
*/
        /*hDrive.AutoDrive(0.5, 1, "Forward");

        hDrive.AutoDrive(0.5, 4, "Left");*/

        hDrive.AutoDrive(.5, 3, "Left");

        idle();

        while(opModeIsActive()){

        }

    }

}
