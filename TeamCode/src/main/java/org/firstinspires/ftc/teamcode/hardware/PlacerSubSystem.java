package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */

@Disabled

public class PlacerSubSystem {

    private Servo servo = null;

    // SERVO POSITIONS
    private final double IN = 0.5;
    private final double OUT = 0.77;


    // SERVOS
    private int numServos = 1;

    public Servo[] servos = new Servo[numServos];

    public PlacerSubSystem(Servo servo){
        this.servo = servo;
    }

    public void in() {
        servo.setPosition(IN);
    }

    public void out() {
        servo.setPosition(OUT);
    }

    public void goToPosition(double position) {
        servo.setPosition(position);
    }
}
