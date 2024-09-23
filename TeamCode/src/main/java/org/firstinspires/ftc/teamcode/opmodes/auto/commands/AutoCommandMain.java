package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

/**
 * Created by Reicher Robotics on 3/19/2018.
 */

public abstract class AutoCommandMain {
    public Robot bot;
    public LinearOpMode opMode;
    public boolean overrideLoop = false;

    public AutoCommandMain(LinearOpMode opMode){
        this.opMode = opMode;
        this.bot = new Robot();
        bot.init(opMode.hardwareMap);
    }

    public void setOverrideLoop(boolean va){
        overrideLoop = overrideLoop;
    }

    public void Run(){
        Start();
        if(!overrideLoop){
            while(canRunLoop()){
                Loop();
            }
            Stop();
        }

    }

    public abstract void Start();
    public abstract void Loop();
    public abstract void Stop();
    public abstract boolean IsTaskRunning();

    public boolean canRunLoop(){
        return !opMode.isStopRequested() && opMode.opModeIsActive() && IsTaskRunning();
    }
}