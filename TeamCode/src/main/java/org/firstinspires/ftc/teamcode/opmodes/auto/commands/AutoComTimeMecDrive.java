package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Reicher Robotics on 3/25/2018.
 */

public class AutoComTimeMecDrive extends AutoCommandMain {
    private double speed;
    private double driveTime;
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

    public AutoComTimeMecDrive(LinearOpMode opMode, int driveTime, double speed){
        super(opMode);

        this.speed = speed;
        this.driveTime = driveTime;
    }

    @Override
    public void Start(){
        bot.mecanum.setMotorBreak(DcMotor.ZeroPowerBehavior.BRAKE);
        time.reset();
    }

    @Override
    public void Loop(){
        bot.mecanum.mecanumDrive(speed, driveSpeed, strafeSpeed, -correction);
    }

    @Override
    public void Stop(){
        bot.mecanum.stop();
    }

    @Override
    public boolean IsTaskRunning(){
        if (time.milliseconds() < driveTime) {
            return true;
        }
        return false;
    }
}