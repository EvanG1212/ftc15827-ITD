package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComGyroMecDrive;

@Autonomous(name = "Blue FAR/RIGHT Park", group = ".Main Blue")
public class BlueFarNav extends RobotOpMode{
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


    }
}
