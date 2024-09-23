package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */



public class PixelGate {

    private Servo pixelgate = null;

    // SERVO POSITIONS
    private final double CLOSE = 0.53; //NEEDS CHANGING
    private final double OPEN = 0.45; //NEEDS CHANGING
    // SERVOS
    private int numServos = 1;
    private int gate = 0;


    public Servo[] servos = new Servo[numServos];

    public PixelGate(Servo pixelgate){
        this.pixelgate = pixelgate;
    }

    public void close() {pixelgate.setPosition(CLOSE);}

    public void open() {pixelgate.setPosition(OPEN);}

    public void goToPosition(double position) {pixelgate.setPosition(position);
    }

}
