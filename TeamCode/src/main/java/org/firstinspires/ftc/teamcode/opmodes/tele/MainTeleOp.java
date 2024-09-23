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

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.DroneLauncher;
import org.firstinspires.ftc.teamcode.hardware.DroneSubSystem;
import org.firstinspires.ftc.teamcode.hardware.IntakeSubSystem;
import org.firstinspires.ftc.teamcode.lib.Controller;
import org.firstinspires.ftc.teamcode.opmodes.RobotOpMode;

@TeleOp(name = "Main", group = ".Main")
public class MainTeleOp extends RobotOpMode {
    private Controller controller1, controller2;
    private boolean SLOW, INTAKEIN, INTAKEOUT, DRONE, OUTTAKE, LIFT, GATE, LAUNCHER, HOOKS_UP, WINCH, PLACER_IN, ARM, LEFT_CLAW, RIGHT_CLAW, OPEN_CLAW, WRIST_VERTICAL, WRIST_HORIZONTAL;
    private double maxDrivePower, maxLiftPower, maxIntakePower;
    private double position;
    private int ticks;


    @Override
    public void runOpMode() throws InterruptedException {
        bot.init(hardwareMap);
        //bot.initTeleOpServos();
        controller1 = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);

        IntakeSubSystem.IntakeState currentIntakeState = IntakeSubSystem.IntakeState.OFF;
        SLOW = false;
        INTAKEIN = false;
        INTAKEOUT = false;
        DRONE = false;
        OUTTAKE = false;
        LIFT = true;
        GATE = false;
        LAUNCHER = false;
        HOOKS_UP = false;
        WINCH = false;
        PLACER_IN = true;
        ARM = true;
        LEFT_CLAW = false;
        RIGHT_CLAW =false;
        OPEN_CLAW =false;

        maxLiftPower = 0.7;
        maxIntakePower = 0.75;
        position = 0.48;

        ticks = 0;

        telemetry.addLine("Status Initialized");
        telemetry.addLine("good luck!");


        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            controller1.update();
            controller2.update();

            // DRIVE CONTROLS

            bot.mecanum.mecanumDrive(maxDrivePower, controller1.leftJoystickYValue, controller1.leftJoystickXValue, controller1.rightJoystickXValue);

            if (controller1.leftJoystickButton == Controller.ButtonState.ON_PRESS || controller1.rightJoystickButton == Controller.ButtonState.ON_PRESS) {
                SLOW = !SLOW;
            }
            if (SLOW) {
                maxDrivePower = 0.4;
            } else {
                maxDrivePower = 1;
            }
















            // stuff with hang sub system










            // arm
           // if (controller1.dPadUp == Controller.ButtonState.PRESSED) {
           //     bot.arm.armup();
           // } else  if (controller1.dPadDown == Controller.ButtonState.PRESSED) {
           //     bot.arm.armdown();
           // }
           //     else { bot.arm.armstop();
           // }
                //claw
            // xontroller 2
            if (controller2.rightBumper == Controller.ButtonState.ON_PRESS) {
                OPEN_CLAW = !OPEN_CLAW;
            }
            if (OPEN_CLAW) {
                bot.claw.open();
            } else {
                bot.claw.close();
            }


            if (controller2.leftBumper == Controller.ButtonState.ON_PRESS) {
                WRIST_VERTICAL = !WRIST_VERTICAL;
            }
            if (WRIST_VERTICAL) {
                bot.claw.vertical();
            } else {
                bot.claw.horizontal();
            }

            //lift
//p2
            bot.lift.up(maxLiftPower * controller2.leftJoystickYValue);
                //intake
            bot.intake.out(maxIntakePower * controller2.rightJoystickYValue);




            /*

            if (controller1.xButton == Controller.ButtonState.ON_PRESS) {
                position = 0.15;
            }

            if (controller1.bButton == Controller.ButtonState.ON_PRESS) {
                position = 0.825;
            }

             */


            telemetry.addData("LF Ticks", bot.mecanum.getMotorTicks(0));
            telemetry.addData("LR Ticks", bot.mecanum.getMotorTicks(1));
            telemetry.addData("RF Ticks", bot.mecanum.getMotorTicks(2));
            telemetry.addData("RR Ticks", bot.mecanum.getMotorTicks(3));
            telemetry.addData("lift1", bot.lift.getLiftTicks());
            telemetry.addData("intake",bot.intake,get);
            telemetry.update();
//hi
            telemetry.update();
        }
    }
}

