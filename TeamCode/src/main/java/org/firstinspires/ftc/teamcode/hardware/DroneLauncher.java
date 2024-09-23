package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */



public class DroneLauncher {

    private Servo dronelauncher = null;

    // SERVO POSITIONS
    private final double CLOSE = 0.15; //NEEDS CHANGING
    private final double OPEN = 0.30; //NEEDS CHANGING



    // SERVOS
    private int numServos = 1;
    private int gate = 0;


    public Servo[] servos = new Servo[numServos];

    public DroneLauncher(Servo dronelauncher){
        this.dronelauncher = dronelauncher;
    }

    public void close() {dronelauncher.setPosition(CLOSE);}

    public void open() {dronelauncher.setPosition(OPEN);}

    public void goToPosition(double position) {dronelauncher.setPosition(position);
    }

}
