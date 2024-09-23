package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class CommandTurn extends CommandMain {
    private double speed;
    private double distance;

    public CommandTurn(LinearOpMode opMode, double turnSpeed, double turnDistance){
        super(opMode);

        this.speed = turnSpeed;
        this.distance = turnDistance;
    }

    @Override
    public void Start(){
        bot.mecanum.setTurnTargets(distance);
        bot.mecanum.setMotorBreak(DcMotor.ZeroPowerBehavior.BRAKE);
        bot.mecanum.setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.mecanum.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void Loop(){
        bot.mecanum.mecanumDrive(speed, 0.0, 0.0, 1.0);

        opMode.telemetry.addData("Turn Target", bot.mecanum.driveDistanceToTicks(distance));
        opMode.telemetry.addData("LF Ticks", bot.mecanum.getMotorTicks(0));
        opMode.telemetry.addData("LR Ticks", bot.mecanum.getMotorTicks(1));
        opMode.telemetry.addData("RF Ticks", bot.mecanum.getMotorTicks(2));
        opMode.telemetry.addData("RR Ticks", bot.mecanum.getMotorTicks(3));
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
