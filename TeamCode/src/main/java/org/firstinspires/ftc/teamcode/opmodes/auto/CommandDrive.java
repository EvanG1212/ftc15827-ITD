package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class CommandDrive extends CommandMain {
    private double speed;
    private double distance;
    private double driveSpeed = 0.0;

    public CommandDrive(LinearOpMode opMode, double driveSpeed, double driveDistance){
        super(opMode);

        this.speed = driveSpeed;
        this.distance = driveDistance;
    }

    @Override
    public void Start(){
        bot.mecanum.setDriveTargets(distance);
        bot.mecanum.setMotorBreak(DcMotor.ZeroPowerBehavior.BRAKE);
        bot.mecanum.setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.mecanum.setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void Loop(){
        bot.mecanum.mecanumDrive(speed, 1.0, 0.0, 0.0);

        opMode.telemetry.addData("Drive Target", bot.mecanum.driveDistanceToTicks(distance));
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
