package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Reicher Robotics on 3/25/2018.
 */

public class AutoComEncoderTurn extends AutoCommandMain {
    private double speed;
    private double turnDistance;

    public AutoComEncoderTurn(LinearOpMode opMode, double turnDistance, double speed){
        super(opMode);

        this.speed = speed;
        this.turnDistance = turnDistance;
    }

    @Override
    public void Start(){
        bot.mecanum.setTurnTargets(turnDistance);
        bot.mecanum.setMotorBreak(DcMotor.ZeroPowerBehavior.BRAKE);
        bot.mecanum.setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.mecanum.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void Loop(){
        bot.mecanum.mecanumDrive(speed, 0.0, 0.0, 1.0);
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