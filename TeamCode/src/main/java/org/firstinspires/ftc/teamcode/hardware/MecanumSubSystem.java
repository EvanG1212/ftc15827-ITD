package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Reicher Robotics on 2/22/2018.
 */

public class MecanumSubSystem {

    // PROPERTIES
    public double P, I, D;
    public double TICKS_PER_REV, WHEEL_DIAMETER, GEAR_RATIO,TICKS_PER_INCH;

    // MOTORS
    private DcMotorEx leftFront, leftRear, rightFront, rightRear;
    private DcMotorEx[] motors = new DcMotorEx[4];

    public MecanumSubSystem(DcMotorEx driveLeftFront, DcMotorEx driveLeftRear, DcMotorEx driveRightFront, DcMotorEx driveRightRear) {
        leftFront = driveLeftFront;
        leftRear = driveLeftRear;
        rightFront = driveRightFront;
        rightRear = driveRightRear;

        motors[0] = leftFront;
        motors[1] = leftRear;
        motors[2] = rightFront;
        motors[3] = rightRear;
    }

    public void setPID(double pCoeff, double iCoeff, double dCoeff) {
        P = pCoeff;
        I = iCoeff;
        D = dCoeff;
    }

    public void setDriveProperties(double motorTicks, double wheelDiameter, double gearRatio) {
        TICKS_PER_REV = motorTicks;
        WHEEL_DIAMETER = wheelDiameter;
        GEAR_RATIO = gearRatio;
        TICKS_PER_INCH = TICKS_PER_REV / WHEEL_DIAMETER / 3.14159 * GEAR_RATIO;
    }

    public boolean isAnyMotorBusy() {
        boolean isBusy = false;
        for (DcMotorEx _motor : motors) {
            if (_motor.isBusy()) {
                isBusy = true;
            }
        }
        return isBusy;
    }

    public boolean is4MotorsBusy() {
        boolean isBusy = false;
        if (motors[0].isBusy() && motors[1].isBusy() && motors[2].isBusy() && motors[3].isBusy()) {
            isBusy = true;
        }
        return isBusy;
    }

    public void setMotorModes(DcMotor.RunMode mode) {
        for (DcMotorEx _motor : motors) {
            _motor.setMode(mode);
        }
    }

    public void setMotorBreak(DcMotor.ZeroPowerBehavior mode) {
        for (DcMotorEx _motor : motors) {
            _motor.setZeroPowerBehavior(mode);
        }
    }

    public void setDrivePowers(double leftFrontPower, double leftRearPower, double rightFrontPower, double rightRearPower) {
        leftFront.setPower(leftFrontPower);
        leftRear.setPower(leftRearPower);
        rightFront.setPower(rightFrontPower);
        rightRear.setPower(rightRearPower);
    }

    public int getMotorTicks(int driveMotor) {
        return motors[driveMotor].getCurrentPosition();
    }

    public void setDriveTargets(double driveDistance) {
        int driveTarget = driveDistanceToTicks(driveDistance);
        leftFront.setTargetPosition(driveTarget);
        leftRear.setTargetPosition(driveTarget);
        rightFront.setTargetPosition(driveTarget);
        rightRear.setTargetPosition(driveTarget);
    }

    public void setDriveTargets(double driveDistance, double strafeDistance) {
        int driveTarget = driveDistanceToTicks(driveDistance);
        int strafeTarget = strafeDistanceToTicks(strafeDistance);
        leftFront.setTargetPosition(driveTarget + strafeTarget);
        leftRear.setTargetPosition(driveTarget - strafeTarget);
        rightFront.setTargetPosition(driveTarget - strafeTarget);
        rightRear.setTargetPosition(driveTarget + strafeTarget);
    }

    public void setStrafeTargets(double strafeDistance) {
        int strafeTarget = strafeDistanceToTicks(strafeDistance);
        leftFront.setTargetPosition(strafeTarget);
        leftRear.setTargetPosition(-strafeTarget);
        rightFront.setTargetPosition(-strafeTarget);
        rightRear.setTargetPosition(strafeTarget);
    }

    public void setTurnTargets(double turnDistance) {
        int turnTarget = driveDistanceToTicks(turnDistance);
        leftFront.setTargetPosition(turnTarget);
        leftRear.setTargetPosition(turnTarget);
        rightFront.setTargetPosition(-turnTarget);
        rightRear.setTargetPosition(-turnTarget);
    }

    public void mecanumDrive(double maxDrivePower, double drive, double strafe, double turn) {
        double leftFrontPower = drive + strafe + turn;
        double leftRearPower = drive - strafe + turn;
        double rightFrontPower = drive - strafe - turn;
        double rightRearPower = drive + strafe - turn;

        double totalPower = Math.abs(drive) + Math.abs(strafe) + Math.abs(turn);

        if (totalPower > 1.0) {
            leftFrontPower /= totalPower;
            leftRearPower /= totalPower;
            rightFrontPower /= totalPower;
            rightRearPower /= totalPower;
        }

        leftFrontPower *= maxDrivePower;
        leftRearPower *= maxDrivePower;
        rightFrontPower *= maxDrivePower;
        rightRearPower *= maxDrivePower;

        setDrivePowers(leftFrontPower, leftRearPower, rightFrontPower, rightRearPower);
    }

    public void stop(){
        mecanumDrive(0.0, 0.0, 0.0, 0.0);
    }

    public int driveDistanceToTicks(double inches) { return (int) (inches * TICKS_PER_INCH) ; }
    public int strafeDistanceToTicks(double inches) { return (int) (inches * TICKS_PER_INCH / Math.sqrt(2.0)) ; }

}
