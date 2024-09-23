package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

/**
 * Created by Reicher Robotics on 2/22/2018.
 */

public class  DroneSubSystem {

    // STATES
    public enum DroneState {
        ON,
        OFF,
        MANUAL
    }

    // PROPERTIES
    private double  ON_POWER = 0.69;
    private DroneState droneState = DroneState.OFF;

    // ACTUATORS
    private DcMotorEx motorDrone = null;

    // CONSTRUCTOR
    public DroneSubSystem(DcMotorEx motorDrone) {
        this.motorDrone = motorDrone;
    }

    // SETTERS AND GETTERS
    public boolean isMotorBusy() {
        return motorDrone.isBusy();
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        motorDrone.setMode(mode);
    }

    public void setMotorBreak(DcMotor.ZeroPowerBehavior mode) {
        motorDrone.setZeroPowerBehavior(mode);
    }


    public void setPowers(double power) {
        ON_POWER = power;

    }

    public DroneState getState() {
        return droneState;
    }

    public void setState(DroneState droneState) {this.droneState = droneState;}

    // INTAKE FUNCTIONS
    public void stop() {
        motorDrone.setPower(0.0);
    }

    public void on() {
        motorDrone.setPower(ON_POWER);
    }

    public void on(double power) {
        motorDrone.setPower(power);
    }


    public void update() {
        switch(droneState) {
            case ON:
                on();
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
