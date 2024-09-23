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

@TeleOp(name = "Drive", group = ".Main")
public class DriveTeleOp extends RobotOpMode
{
    private Controller controller1;
    private boolean SLOW;
    private double maxDrivePower;

    private ElapsedTime time = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        controller1 = new Controller(gamepad1);


        SLOW = false;

        telemetry.addLine("Status Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive())
        {
            telemetry.addLine("Status Initialized");
            controller1.update();

            if (controller1.leftJoystickButton == Controller.ButtonState.PRESSED || controller1.rightJoystickButton == Controller.ButtonState.PRESSED) {
                SLOW = !SLOW;
            }
            if (SLOW) {
                maxDrivePower = 0.4;
            } else {
                maxDrivePower = 1.0;
            }

            //bot.mecanum.mecanumDrive(maxDrivePower, controller1.leftJoystickYValue, controller1.leftJoystickXValue, controller1.rightJoystickXValue);


            /**********************************************************************************************
             * Controller 2 Controls
             **********************************************************************************************/

            bot.mecanum.setDrivePowers(controller1.leftJoystickXValue, controller1.leftJoystickYValue, controller1.rightJoystickXValue, controller1.rightJoystickYValue);

        }
    }
}
