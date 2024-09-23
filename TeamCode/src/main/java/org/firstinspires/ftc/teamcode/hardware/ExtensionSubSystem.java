package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */
// timmy

public class ExtensionSubSystem {

    // ACTUATORS
    private DcMotorEx extensionMotor = null;


    // SENSORS
    private ElapsedTime timer = new ElapsedTime();

    //LIMITS
    public double MAXIMUM_TICKS = 2300; //NEEDS CHANGING

    // CONSTRUCTOR
    public ExtensionSubSystem(DcMotorEx liftMotor, DcMotorEx liftMotor2) {
        this.extensionMotor = extensionMotor;

    }

    // SETTERS AND GETTERS
    public boolean isBusy() {
        return extensionMotor.isBusy();
    }

    public void setMotorMode(DcMotor.RunMode mode) {
        extensionMotor.setMode(mode);
    }

    public void setMotorBreak(DcMotor.ZeroPowerBehavior behavior) {
        extensionMotor.setZeroPowerBehavior(behavior);
    }

    public void setExtensionPower(double liftPower) {
        extensionMotor.setPower(liftPower);
    }

    public int getExtensionTicks() {
        return extensionMotor.getCurrentPosition();
    }

    public void resetTicks() {
        extensionMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }



    // LIFT FUNCTIONS

    public void stop() {
        extensionMotor.setPower(0.0);
    }

    public void up(double power) {
        extensionMotor.setPower(power);
    }

    public void down(double power) {
        extensionMotor.setPower(-power);
    }

    public void Extension(double power) {
        setExtensionPower(power * 0.85);
    }

    public void goToExtensionPosition(double position) {

    }

    public void upWithLimits(double power) {
        int current_ticks = getExtensionTicks();
        if (current_ticks <= MAXIMUM_TICKS && power > 0) {
            Extension(power);
        } else {
            stop();
        }
    }

    public void downWithLimits(double power) {
        int current_ticks = getExtensionTicks();
        if (current_ticks >= 0 && power > 0) {
            Extension(-power);
        } else {
            stop();
        }
    }

    public void liftWithLimits(double power) {
        int current_ticks = getExtensionTicks();
        if (current_ticks >= 0 && power < 0) {
            Extension(power);
        } else if (current_ticks <= MAXIMUM_TICKS && power > 0) {
            Extension(power);
        } else {
            stop();
        }
    }
}