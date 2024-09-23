package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by Reicher Robotics on 3/25/2018.
 */

public class AutoComGyroPIDTurn extends AutoCommandMain {
    private double maxSpeed = 0.0;
    private double minSpeed = 0.1;
    private double targetHeading = 0.0;
    private double tolerance = 4.0;
    private double maxTime;
    private boolean tooLong = false;
    ElapsedTime time = new ElapsedTime();

    private double error;
    private double correction;
    private double prevTime;
    private double integral;
    private double derivative;
    private double prevError;
    private double currentTime;
    private double timeLapsed;

    public AutoComGyroPIDTurn(LinearOpMode opMode, double speed, double angle) {
        super(opMode);

        this.maxSpeed = speed;
        this.targetHeading = angle;
        maxTime = 100.0;
    }

    public AutoComGyroPIDTurn(LinearOpMode opMode, double speed, double angle, double time) {
        super(opMode);

        this.maxSpeed = speed;
        this.targetHeading = angle;
        this.maxTime = time;
    }

    @Override
    public void Start() {
        prevError = 0.0;
        prevTime = 0.0;
        integral = 0.0;
        time.reset();
    }

    @Override
    public void Loop() {
        if (time.time() > maxTime) {
            tooLong = true;
        }
    }

    @Override
    public void Stop() {

    }

    @Override
    public boolean IsTaskRunning() {
        return !onHeading((int) bot.gyroIMU.getHeading()) && !opMode.isStopRequested() && !tooLong;
    }

    private boolean onHeading(int currentHeading) {
        boolean onTarget = false;

        currentTime = time.milliseconds();
        timeLapsed = currentTime - prevTime;
        error = bot.gyroIMU.getError(targetHeading, currentHeading);
        integral += error * timeLapsed;
        derivative = (error - prevError) / timeLapsed;
        correction = bot.mecanum.P * error + bot.mecanum.I * integral + bot.mecanum.D * derivative;
        prevTime = currentTime;
        prevError = error;

        if (Math.abs(targetHeading - currentHeading) <= tolerance) {
            correction = 0.0;
            onTarget = true;
        }

        bot.mecanum.mecanumDrive(maxSpeed, 0.0, 0.0, -correction);
        return onTarget;
    }
}