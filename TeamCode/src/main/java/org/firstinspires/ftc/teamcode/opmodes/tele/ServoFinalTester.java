/*
 * Copyright (c) 2020 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.Controller;
import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@TeleOp(name = "Servo Final Tester", group = ".Main")
public class ServoFinalTester extends RobotOpMode
{
    private Controller controller1;
    private boolean CLAW;
    private double position;
    private int servo;

    private ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        controller1 = new Controller(gamepad1);


        position = 0.5;
        servo = 0;
        CLAW = false;


        telemetry.addLine("Status Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addLine("Status Initialized");
            controller1.update();



            /**********************************************************************************************
             * Controller 2 Controls
             **********************************************************************************************/


            /**********************************************************************************************
             * Telemetry
             **********************************************************************************************/

            telemetry.addData("servo position: ", position);
            telemetry.update();
        }
    }
}
