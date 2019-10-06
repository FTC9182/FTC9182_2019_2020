package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WheelGrabber {

    public Servo[] grabbers = new Servo[2];

    double open = 0;
    double close = 1;
    double currentDegree = 0;
    double increment = .2;

    boolean buttonReady = true;

    public ElapsedTime waitTime = new ElapsedTime();

    public WheelGrabber (HardwareMap hardwareMap){
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
            if (currentDegree <= open) { currentDegree = open; }
            servo.setPosition(currentDegree);
        }
    }

    public void IncrementClose(){
        for (Servo servo : grabbers){
            currentDegree += increment;
            if (currentDegree <= close){ currentDegree = close; }
            servo.setPosition(currentDegree);
        }
    }


}
