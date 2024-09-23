package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@Autonomous (name = "Red Left Navigate", group = "Auto")
public class RedLeftNav extends RobotOpMode {
    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        bot.init(hardwareMap);

        telemetry.addLine("Status Initialized");
        telemetry.update();

        waitForStart();

        new CommandDrive(this, 0.5, 28.0).Run();
        new CommandTurn(this, 0.5, 22.0).Run();
    }
}
