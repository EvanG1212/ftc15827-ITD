package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComGyroMecDrive;

@Autonomous(name = "Red NEAR/RIGHT Park", group = ".Main Red")
public class RedNearNav extends RobotOpMode{
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

        new AutoComGyroMecDrive(this,  0.0,20.0,0.3).Run();
        new AutoComGyroMecDrive(this, 0.0 ,0.,0.3).Run();
      //  bot.claw.rightClose();
        sleep(1000);
        new AutoComGyroMecDrive(this, -2.0 ,0,0.3).Run();
      // bot.claw.rightOpen();
        new AutoComGyroMecDrive(this,  0.0,0.0,0.3).Run();
    }
}
