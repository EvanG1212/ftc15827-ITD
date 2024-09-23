package org.firstinspires.ftc.teamcode.opmodes.auto;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComGyroMecDrive;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComTimeMecDrive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "blue left backboard")
public class BlueLeftPark extends RobotOpMode{
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
        if(isStopRequested()) return;

      // new AutoComGyroMecDrive(this,  0.0 ,30,.3).Run();
      new AutoComGyroMecDrive(this,  10,0,0.3).Run();
      // bot.gate.open();
       sleep(1500);
        //   new AutoComGyroMecDrive(this, 0 ,15,.50).Run();
        new AutoComTimeMecDrive(this, 5000,2).Run();
      //  bot.gate.close();
        sleep(1500);
    }
}
