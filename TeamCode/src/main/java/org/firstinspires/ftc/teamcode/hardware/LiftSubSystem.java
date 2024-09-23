package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */
// timmy

public class LiftSubSystem {

    // ACTUATORS
    private DcMotorEx liftMotor = null;
    private DcMotorEx liftMotor2 = null;

    // SENSORS
    private ElapsedTime timer = new ElapsedTime();

    //LIMITS
    public double MAXIMUM_TICKS = 2300; //NEEDS CHANGING

    // CONSTRUCTOR
    public LiftSubSystem(DcMotorEx liftMotor, DcMotorEx liftMotor2) {
        this.liftMotor = liftMotor;
        this.liftMotor2 = liftMotor2;
    }

    // SETTERS AND GETTERS
    public boolean isBusy() {
        return liftMotor.isBusy();
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        liftMotor.setMode(mode);
        liftMotor2.setMode(mode);
    }

    public void setMotorBreak(DcMotor.ZeroPowerBehavior behavior) {
        liftMotor.setZeroPowerBehavior(behavior);
        liftMotor2.setZeroPowerBehavior(behavior);
    }

    public void setLiftPower(double liftPower) {
        liftMotor.setPower(liftPower);
        liftMotor2.setPower(liftPower);
    }

    public int getLiftTicks() {
        return liftMotor.getCurrentPosition();
    }

    public void resetTicks() {
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }



    // LIFT FUNCTIONS

    public void stop() {
        liftMotor.setPower(0.0);
        liftMotor2.setPower(0.0);
    }

    public void up(double power) {
        liftMotor.setPower(power);
        liftMotor2.setPower(power);
    }

    public void down(double power) {
        liftMotor.setPower(-power);
        liftMotor2.setPower(-power);
    }

    public void lift(double power) {
        setLiftPower(power * 0.85);
    }

    public void goToLiftPosition(double position) {

    }

    public void upWithLimits(double power) {
        int current_ticks = getLiftTicks();
        if (current_ticks <= MAXIMUM_TICKS && power > 0) {
            lift(power);
        } else {
            stop();
        }
    }

    public void downWithLimits(double power) {
        int current_ticks = getLiftTicks();
        if (current_ticks >= 0 && power > 0) {
            lift(-power);
        } else {
            stop();
        }
    }

    public void liftWithLimits(double power) {
        int current_ticks = getLiftTicks();
        if (current_ticks >= 0 && power < 0) {
            lift(power);
        } else if (current_ticks <= MAXIMUM_TICKS && power > 0) {
            lift(power);
        } else {
            stop();
        }
    }
}