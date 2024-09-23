package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@Autonomous(name = "Red right backboard")
public class RedRightPark extends RobotOpMode{
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException{

        bot.init(hardwareMap);
        bot.initAutoServos();

        telemetry.addLine("Status Initialized :) ");

        telemetry.update();


        while (!isStarted())
        waitForStart();
        runtime.reset();

        if(isStopRequested()) return;

    //    new AutoComGyroMecDrive(this,  5, 0,.6).Run();
    //    new AutoComGyroMecDrive(this,  0,0,0.6).Run();
   //    bot.gate.open();
    //  sleep(1500);
     //   new AutoComGyroMecDrive(this,  -1,0,.3).Run();
     //   new AutoComGyroMecDrive(this, -0.2,-27,.5).Run()
        new AutoComTimeMecDrive(this, 5000,2).Run();;
      //  bot.gate.close();
        sleep(1500);
    }
}
