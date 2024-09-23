package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@Autonomous(name = "red overall   ", group = ".Main Red")
public class RedRight extends RobotOpMode{
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


        sleep(3000);
        new AutoComGyroMecDrive(this, 65 ,0,0.4).Run();
        bot.claw.close();
        sleep(1000);
        bot.claw.open();
        new AutoComGyroMecDrive(this,-110,0,0.4).Run();
        new AutoComGyroMecDrive(this,0,55,0.4).Run();
        new AutoComGyroMecDrive(this,-5,0,0.6).Run();
        new AutoComGyroMecDrive(this,5,0,0.4).Run();
        bot.claw.close();
        new AutoComGyroMecDrive(this,5,0,0.4).Run();
        bot.claw.open();
        new AutoComGyroMecDrive(this,5,0,0.4).Run();





    }
}
