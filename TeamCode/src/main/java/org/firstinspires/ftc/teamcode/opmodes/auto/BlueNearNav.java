package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComGyroMecDrive;

@Autonomous(name = "Blue NEAR/LEFT Park", group = ".Main Blue")
public class BlueNearNav extends RobotOpMode{
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

        new AutoComGyroMecDrive(this,  0.0,6.0,0.3).Run();
        new AutoComGyroMecDrive(this, 46.0 ,0.,0.3).Run();
      //  bot.claw.rightClose();
        sleep(1000);
        new AutoComGyroMecDrive(this, -8.0 ,0,0.3).Run();
     //   bot.claw.rightClose();
        new AutoComGyroMecDrive(this,  0.0,-6.0,0.3).Run();
    }
}
