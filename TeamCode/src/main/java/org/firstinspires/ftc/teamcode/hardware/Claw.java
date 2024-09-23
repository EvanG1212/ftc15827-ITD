package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */



public class Claw {

    private Servo claw = null;
    private Servo wrist = null;

    // SERVO POSITIONS
    private final double CLOSE = 0.10; //NEEDS CHANGING
    private final double OPEN = 0.51; //NEEDS CHANGING
    private final double HORIZONTAL = 0.50; //NEEDS CHANGING
    private final double VERTICAL = 0.87; //NEEDS CHANGING
    // SERVOS
    private int numServos = 2;


    public Servo[] servos = new Servo[numServos];

    public Claw(Servo claw, Servo wrist){
        this.claw = claw;
        this.wrist = wrist;

    }


    public void close() {claw.setPosition(CLOSE);}

    public void open() {claw.setPosition(OPEN);}

    public void goToClawPosition(double position) {claw.setPosition(position);
    }

    public void horizontal() {wrist.setPosition(HORIZONTAL);}

    public void vertical() {wrist.setPosition(VERTICAL);}

    public void goToWristPosition(double position) {wrist.setPosition(position);
    }

}
