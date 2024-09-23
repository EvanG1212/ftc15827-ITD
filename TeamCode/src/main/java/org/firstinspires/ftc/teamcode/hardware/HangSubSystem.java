package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class HangSubSystem {

    // MOTORS
    private DcMotorEx winchLeftMotor = null;
    private DcMotorEx winchRightMotor = null;

    // SERVOS
    private Servo leftServo = null;
    private Servo rightServo = null;

    // SERVO POSITIONS
    private final double LEFT_UP = 0.72; //NEEDS CHANGING
    private final double LEFT_DOWN = 0.36; //NEEDS CHANGING
    private final double RIGHT_UP = 0.30; //NEEDS CHANGING
    private final double RIGHT_DOWN = 0.67; //NEEDS CHANGING

    public HangSubSystem(DcMotorEx winchLeftMotor, boolean isLeftReversed, DcMotorEx winchRightMotor, boolean isRightReversed, Servo leftServo, Servo rightServo) {
        this.winchLeftMotor = winchLeftMotor;
        if (isLeftReversed) {
            this.winchLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        this.winchRightMotor = winchRightMotor;
        if (isRightReversed) {
            this.winchRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        this.leftServo = leftServo;
        this.rightServo = rightServo;
    }

    public void winchStop() {
        winchLeftMotor.setPower(0.0);
        winchRightMotor.setPower(0.0);
    }
    public void winchUp() {
        winchLeftMotor.setPower(1.0);
        winchRightMotor.setPower(1.0);
    }

    public void winchDown() {
        winchLeftMotor.setPower(-1.0);
        winchRightMotor.setPower(-1.0);
    }

    public void leftUp() {
        leftServo.setPosition(LEFT_UP);
    }

    public void leftDown() {
        leftServo.setPosition(LEFT_DOWN);
    }
    public void rightUp() {
        rightServo.setPosition(RIGHT_UP);
    }
    public void rightDown() {
        rightServo.setPosition(RIGHT_DOWN);
    }
    public void leftGoToPosition(double position) {
        leftServo.setPosition(position);
    }
    public void rightGoToPosition(double position) {
        rightServo.setPosition(position);
    }
}
