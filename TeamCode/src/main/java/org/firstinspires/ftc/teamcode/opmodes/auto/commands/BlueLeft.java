package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@Autonomous(name = "blue overall", group = ".Main Blue")
public class BlueLeft extends RobotOpMode{
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

        bot.claw.close();
        new AutoComGyroMecDrive(this, 40 ,0,0.4).Run();
        sleep(2000);
        bot.claw.vertical();
        bot.claw.open();
        sleep(2000);
        bot.claw.horizontal();
        sleep(1000);
        new AutoComGyroMecDrive(this, 0, 2, 0.2).Run();
        new AutoComGyroMecDrive(this,-110,0,0.4).Run();
        bot.claw.close();
        sleep(5000);
    }
}
