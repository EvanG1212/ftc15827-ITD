package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubSystem {

    // MOTORS


    // SERVOS
    private Servo leftServo = null;
    private Servo rightServo = null;

    // SERVO POSITIONS
    private final double LEFT_OPEN = 0.30; //NEEDS CHANGING
    private final double LEFT_CLOSE = 0.80; //NEEDS CHANGING
    private final double RIGHT_OPEN = 0.50; //NEEDS CHANGING
    private final double RIGHT_CLOSE = 0; //NEEDS CHANGING

    public ClawSubSystem(Servo leftServo, Servo rightServo) {
        this.leftServo = leftServo;
        this.rightServo = rightServo;

    }

    public void leftOpen() {
        leftServo.setPosition(LEFT_OPEN);
    }

    public void leftClose() {
        leftServo.setPosition(LEFT_CLOSE);
    }
    public void rightOpen() {
        rightServo.setPosition(RIGHT_OPEN);
    }
    public void rightClose() {
        rightServo.setPosition(RIGHT_CLOSE);
    }
    public void leftGoToPosition(double position) {
        leftServo.setPosition(position);
    }
    public void rightGoToPosition(double position) {
        rightServo.setPosition(position);
    }
}
