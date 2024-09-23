package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@Autonomous(name = "Red Left Prop park", group = ".Main Blue")
public class RedLeft extends RobotOpMode{
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException{

        bot.init(hardwareMap);
        bot.initAutoServos();

        telemetry.addLine("Status Initialized :) ");
        while (!isStarted())
            telemetry.update();

        waitForStart();
        runtime.reset();


        new AutoComGyroMecDrive(this, 0.0 ,15,0.6).Run();
    }
}
