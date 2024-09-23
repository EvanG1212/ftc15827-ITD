package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Reicher Robotics on 2/22/2018.
 */

public class IntakeSubSystem {

    // STATES
    public enum IntakeState {
        IN,
        OUT,
        OFF,
        MANUAL
    }

    // PROPERTIES
    private double  IN_POWER = 1.0; //NEEDS CHANGING MAYBE
    private double  OUT_POWER = -0.5; //NEEDS CHANGING MAYBE
    private IntakeState intakeState = IntakeState.OFF;

    // ACTUATORS
    private DcMotorEx motorIntake = null;

    // CONSTRUCTOR
    public IntakeSubSystem(DcMotorEx motorIntake) {
        this.motorIntake = motorIntake;
    }

    // SETTERS AND GETTERS
    public boolean isMotorBusy() {
        return motorIntake.isBusy();
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        motorIntake.setMode(mode);
    }

    public void setMotorBreak(DcMotor.ZeroPowerBehavior mode) {
        motorIntake.setZeroPowerBehavior(mode);
    }

    public void setPowers(double inPower, double outPower) {
        IN_POWER = inPower;
        OUT_POWER = -outPower;
    }

    public void setPowers(double power) {
        IN_POWER = power;
        OUT_POWER = -power;
    }

    public IntakeState getState() {
        return intakeState;
    }

    public void setState(IntakeState intakeState) {
        this.intakeState = intakeState;
    }

    // INTAKE FUNCTIONS
    public void stop() {
        motorIntake.setPower(0.0);
    }

    public void in() {
        motorIntake.setPower(IN_POWER);
    }

    public void in(double power) {
        motorIntake.setPower(power);
    }

    public void out() {
        motorIntake.setPower(OUT_POWER);
    }

    public void out(double power) {
        motorIntake.setPower(-power);
    }

    public void update() {
        switch(intakeState) {
            case IN:
                in();
                break;
            case OUT:
                out();
                break;
            case MANUAL:
                break;
            case OFF:
            default:
                stop();
                break;
        }
    }
}
