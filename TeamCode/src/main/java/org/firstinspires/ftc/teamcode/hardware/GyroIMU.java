package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

/**
 * Created by Reicher Robotics on 3/4/2018.
 */

public class GyroIMU {
    private IMU imu;

    public GyroIMU(IMU imu) {

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.UP;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        this.imu = imu;
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();
    }

    public void resetYaw() {
        imu.resetYaw();
    }

    public double getHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return (orientation.getYaw(AngleUnit.DEGREES) + 360) % 360;
    }

    public double getError(double targetAngle){
        double angleError = 0.0;
        angleError = (targetAngle - getHeading());
        angleError -= (360 * Math.floor(0.5 + (angleError / 360.0)));
        return angleError;
    }

    public double getError(double targetAngle, double currentAngle){
        double angleError = 0.0;
        angleError = (targetAngle - currentAngle);
        angleError -= (360 * Math.floor(0.5 + (angleError / 360.0)));
        return angleError;
    }
}