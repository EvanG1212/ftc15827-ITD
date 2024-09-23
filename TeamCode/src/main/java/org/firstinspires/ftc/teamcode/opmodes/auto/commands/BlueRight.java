package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@Autonomous(name = "Blue Right Prop park", group = ".Main Blue")
public class BlueRight extends RobotOpMode{
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

        new AutoComGyroMecDrive(this,  35.0,0.0,0.2).Run();
        sleep(1000);
       // bot.gate.open();
        sleep(1000);
        new AutoComGyroMecDrive(this, -35.0 ,0,0.3).Run();
       // bot.gate.close();
        new AutoComGyroMecDrive(this, 0.0 ,5,0.6).Run();
    }
}
