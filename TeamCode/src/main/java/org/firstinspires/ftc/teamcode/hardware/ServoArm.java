package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */

@Disabled

public class ServoArm {

    private Servo arm = null;

    // SERVO POSITIONS
    private final double LEFT = 0.15;
    private final double RIGHT = 0.825;
    private final double OUT = 0.48;


    // SERVOS
    private int numServos = 1;
    private int CLAW1 = 0;

    public Servo[] servos = new Servo[numServos];

    public ServoArm(Servo arm){
        this.arm = arm;
    }

    public void left() {
        arm.setPosition(LEFT);
    }

    public void right() {
        arm.setPosition(RIGHT);
    }

    public void out() {
        arm.setPosition(OUT);
    }

    public void goToPosition(double position) {
        arm.setPosition(position);
    }
}
