package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.opencv.RedPropDetectionPipeline;
import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComGyroMecDrive;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoComGyroPIDTurn;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "Red Spike Near/Right", group =".Main Red")
public class RedSpikeRight extends RobotOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private RedPropDetectionPipeline pipeline = new RedPropDetectionPipeline();
    OpenCvWebcam webcam;

    @Override
    public void runOpMode() throws InterruptedException{

        bot.init(hardwareMap);
        bot.initAutoServos();

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new RedPropDetectionPipeline();
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
            telemetry.addData("left", pipeline.getLeftColor());
            telemetry.addData("middle", pipeline.getMidColor());
            telemetry.addData("Block Position", pipeline.getPosition());
            telemetry.addData("Frame Count", webcam.getFrameCount());
            telemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
            telemetry.addData("Total frame time ms", webcam.getTotalFrameTimeMs());
            telemetry.addData("Pipeline time ms", webcam.getPipelineTimeMs());
            telemetry.addData("Overhead time ms", webcam.getOverheadTimeMs());
            telemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
            telemetry.update();

            sleep(100);
        }

        RedPropDetectionPipeline.PropPosition propPosition =  pipeline.getPosition();
        waitForStart();
        runtime.reset();

        if (isStopRequested()) return;



        switch (propPosition) {
            case Left:
                new AutoComGyroMecDrive(this, 0.0,0.0,.5).Run();
                new AutoComGyroPIDTurn(this, 0.0, 0.0,0.5);
               // bot.gate.open();
                break;
            case Middle:
                break;
            case Right:
                break;
        }


    }

}
