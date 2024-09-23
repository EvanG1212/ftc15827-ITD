package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Reicher Robotics on 3/25/2018.
 */

public class AutoComGyroMecDrive extends AutoCommandMain {
    private double speed;
    private double driveDistance;
    private double driveSpeed = 0.0;
    private double strafeDistance;
    private double strafeSpeed = 0.0;

    private double error;
    private double correction;
    private double targetHeading;
    private double prevTime;
    private double integral;
    private double derivative;
    private double prevError;
    private double currentTime;
    private double timeLapsed;

    ElapsedTime time = new ElapsedTime();

    public AutoComGyroMecDrive(LinearOpMode opMode, double driveDistance, double strafeDistance, double speed){
        super(opMode);

        this.speed = speed;
        this.driveDistance = driveDistance;
        this.strafeDistance = strafeDistance;
    }

    @Override
    public void Start(){
        bot.mecanum.setDriveTargets(driveDistance, strafeDistance);
        driveSpeed = driveDistance / (Math.abs(driveDistance) + Math.abs(strafeDistance));
        strafeSpeed = strafeDistance / (Math.abs(driveDistance) + Math.abs(strafeDistance));
        bot.mecanum.setMotorBreak(DcMotor.ZeroPowerBehavior.BRAKE);
        bot.mecanum.setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.mecanum.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
        targetHeading = bot.gyroIMU.getHeading();
        prevError = 0.0;
        prevTime = 0.0;
        integral = 0.0;
        time.reset();
    }

    @Override
    public void Loop(){
        currentTime = time.milliseconds();
        timeLapsed = currentTime - prevTime;
        error = bot.gyroIMU.getError(targetHeading);
        integral += error * timeLapsed;
        derivative = (error - prevError) / timeLapsed;
        correction = bot.mecanum.P * error + bot.mecanum.I * integral + bot.mecanum.D * derivative;
        prevTime = currentTime;
        prevError = error;
        bot.mecanum.mecanumDrive(speed, driveSpeed, strafeSpeed, -correction);
        opMode.telemetry.addData("Motor 0", bot.mecanum.getMotorTicks(0));
        opMode.telemetry.addData("Motor 1", bot.mecanum.getMotorTicks(1));
        opMode.telemetry.addData("Motor 2", bot.mecanum.getMotorTicks(2));
        opMode.telemetry.addData("Motor 3", bot.mecanum.getMotorTicks(3));
        opMode.telemetry.update();
    }

    @Override
    public void Stop(){
        bot.mecanum.stop();
        bot.mecanum.setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public boolean IsTaskRunning(){
        return bot.mecanum.is4MotorsBusy();
    }
}