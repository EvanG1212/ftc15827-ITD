package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */

@Disabled

public class   ServoOuttake {

    private Servo outtakeservo = null;

    // SERVO POSITIONS
    private final double CLOSE = 0.65; //NEEDS CHANGING
    private final double OPEN = 0.5; //NEEDS CHANGING



    // SERVOS
    private int numServos = 1;
    private int outtake = 0;


    public Servo[] servos = new Servo[numServos];

    public ServoOuttake(Servo outtakeservo){
        this.outtakeservo = outtakeservo;
    }

    public void close() {
        outtakeservo.setPosition(CLOSE);
    }

    public void open() {
        outtakeservo.setPosition(OPEN);
    }



    public void position(double position) {
        outtakeservo.setPosition(position);
    }

}
