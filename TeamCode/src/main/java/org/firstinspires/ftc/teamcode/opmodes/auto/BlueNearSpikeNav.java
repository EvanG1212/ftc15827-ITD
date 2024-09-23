package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.opencv.BluePropDetectionPipeline;
import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComEncoderTurn;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComGyroMecDrive;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "Blue NEAR/LEFT Spike Nav", group =".Main Blue")
public class BlueNearSpikeNav extends RobotOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private BluePropDetectionPipeline pipeline = new BluePropDetectionPipeline();
    OpenCvWebcam webcam;

    @Override
    public void runOpMode() throws InterruptedException{

        bot.init(hardwareMap);
        bot.initAutoServos();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new BluePropDetectionPipeline();
        webcam.setPipeline(pipeline);


        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(1280,720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
        telemetry.addLine("Status Initialized");

        telemetry.addLine("");

        while (!isStarted())
        {
            telemetry.addData("Left", pipeline.getLeftColor());
            telemetry.addData("Center", pipeline.getMidColor());
            telemetry.addData("Prop Position", pipeline.getPosition());
            telemetry.addData("Frame Count", webcam.getFrameCount());
            telemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
            telemetry.addData("Total frame time ms", webcam.getTotalFrameTimeMs());
            telemetry.addData("Pipeline time ms", webcam.getPipelineTimeMs());
            telemetry.addData("Overhead time ms", webcam.getOverheadTimeMs());
            telemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
            telemetry.update();

            sleep(100);
        }

        BluePropDetectionPipeline.PropPosition propPosition =  pipeline.getPosition();
        waitForStart();
        runtime.reset();

        if (isStopRequested()) return;

        switch (propPosition) {
            case Left:
                new AutoComGyroMecDrive(this, 20.0,0.0,0.3).Run();
                new AutoComEncoderTurn(this, -10.5,0.3).Run();
                new AutoComGyroMecDrive(this, 10.0,0.0,0.3).Run();
              //  bot.claw.rightClose();
                sleep(1000);
                new AutoComGyroMecDrive(this, -10.0,0.0,0.3).Run();
                //new AutoComEncoderTurn(this, -10.5,0.3).Run();
                //new AutoComGyroMecDrive(this, 0.0, -60.0, 0.3).Run();
                //new AutoComGyroMecDrive(this, 46.0, 0.0, 0.3).Run();
                //new AutoComGyroMecDrive(this, 0.0, -20.0, 0.3).Run();
                break;
            case Middle:
                new AutoComGyroMecDrive(this, 31.0,0.0,0.3).Run();
              //  bot.claw.rightClose();
                sleep(1000);
                new AutoComGyroMecDrive(this, -10.0,0.0,0.3).Run();
                //new AutoComEncoderTurn(this, -21,0.3).Run();
                //new AutoComGyroMecDrive(this, 0.0, -60.0, 0.3).Run();
                //new AutoComGyroMecDrive(this, 46.0, 0.0, 0.3).Run();
                //new AutoComGyroMecDrive(this, 0.0, -20.0, 0.3).Run();
                break;
            case Right:
                new AutoComGyroMecDrive(this, 20.0,0.0,0.3).Run();
                new AutoComEncoderTurn(this, 10.5,0.3).Run();
                new AutoComGyroMecDrive(this, 10.0,0.0,0.3).Run();
              //  bot.claw.rightClose();
                sleep(1000);
                new AutoComGyroMecDrive(this, -10.0,0.0,0.3).Run();
                //new AutoComEncoderTurn(this, -31.5,0.3).Run();
                //new AutoComGyroMecDrive(this, 0.0, -60.0, 0.3).Run();
                //new AutoComGyroMecDrive(this, 46.0, 0.0, 0.3).Run();
                //new AutoComGyroMecDrive(this, 0.0, -20.0, 0.3).Run();
                break;
        }
    }
}
