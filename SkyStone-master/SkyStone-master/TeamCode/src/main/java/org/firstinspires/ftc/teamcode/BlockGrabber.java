package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BlockGrabber {

    private Servo[] grabbers;

    double open = 0;
    double close = .5;
    double currentDegree = 0;
    double increment = .05;

    public BlockGrabber (HardwareMap hardwareMap){
        grabbers[0] = hardwareMap.servo.get("grabber1");
        grabbers[1] = hardwareMap.servo.get("grabber2");

        grabbers[1].setDirection(Servo.Direction.REVERSE);

        Open();
    }

    public void Open(){
        for (Servo servo : grabbers){
            servo.setPosition(open);
            currentDegree = open;
        }
    }

    public void Close(){
        for (Servo servo : grabbers){
            servo.setPosition(close);
            currentDegree = close;
        }
    }

    public void IncrementOpen(){
        for (Servo servo : grabbers){
            currentDegree -= increment;
            servo.setPosition(currentDegree);
        }
    }

    public void IncrementClose(){
        for (Servo servo : grabbers){
            currentDegree += increment;
            servo.setPosition(currentDegree);
        }
    }
}
