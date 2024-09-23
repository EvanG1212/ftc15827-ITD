package org.firstinspires.ftc.teamcode.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class RedPropDetectionPipeline extends OpenCvPipeline {

    public enum PropPosition
    {
        Left,
        Middle,
        Right,
    }
    //defines color we are checking for
    //change the first value to 0 and last to 255 for blue side pipeline
    static final Scalar Red = new Scalar(255, 0, 0);

    //Defines location and size of the regions
    static final Point Center_Middle_Point = new Point(730,260);
    static final Point Left_Middle_Point = new Point(230,300);

    static final int Region_Side = 140;
    static final int Threshold = 25;

    Point center_region_pointA = new Point(
            Center_Middle_Point.x  - Region_Side/2,
            Center_Middle_Point.y  + Region_Side/2);
    Point center_region_pointB = new Point(
            Center_Middle_Point.x  + Region_Side/2,
            Center_Middle_Point.y  -  Region_Side/2);

    Point left_region_pointA = new Point(
            Left_Middle_Point.x - Region_Side/2,
            Left_Middle_Point.y + Region_Side/2);
    Point left_region_pointB = new Point(
            Left_Middle_Point.x + Region_Side/2,
            Left_Middle_Point.y - Region_Side/2);

    Mat center_region_Cb;
    Mat left_region_Cb;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    int center_avg;
    int left_avg;

    private volatile RedPropDetectionPipeline.PropPosition position = RedPropDetectionPipeline.PropPosition.Right;

    //This function takes RGB values from camera and converts to YCrCb
    void inputToCb(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_BGR2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }

    @Override
    public void init(Mat firstFrame)
    {
        inputToCb(firstFrame);

        center_region_Cb = Cb.submat(new Rect(center_region_pointA, center_region_pointB));
        left_region_Cb = Cb.submat(new Rect(left_region_pointA, left_region_pointB));
    }

    @Override
    public Mat processFrame(Mat input) {
        inputToCb(input);

        center_avg = (int) Core.mean(center_region_Cb).val[0];
        left_avg = (int) Core.mean(left_region_Cb).val[0];

        Imgproc.rectangle(
                input,
                center_region_pointA,
                center_region_pointB,
                Red,
                2);

        position = PropPosition.Left;

        Imgproc.rectangle(
                input,
                left_region_pointA,
                left_region_pointB,
                Red,
                2);

        if (left_avg - center_avg > Threshold) {
            position = PropPosition.Left;
        } else if (center_avg - left_avg > Threshold) {
            position = PropPosition.Middle;
        } else {
            position = PropPosition.Right;
        }

        return input;
    }

    public int getLeftColor() {return left_avg;}
    public int getMidColor() {return center_avg;}

    public PropPosition getPosition() {return position;}
}