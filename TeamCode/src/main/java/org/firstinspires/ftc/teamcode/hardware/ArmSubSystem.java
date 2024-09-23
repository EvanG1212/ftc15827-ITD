package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmSubSystem {

    // MOTORS
    private DcMotorEx ArmMotor = null;

    // SERVO POSITIONS
    private final double LEFT_UP = 0.72; //NEEDS CHANGING
    private final double LEFT_DOWN = 0.36; //NEEDS CHANGING

    public ArmSubSystem(DcMotorEx motor, boolean isReversed) {
        this.ArmMotor = motor;
        this.ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE



        );
        if (isReversed) {
            this.ArmMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }

    }

    public void armstop() {ArmMotor.setPower(0.0);
    }
    public void armup() {ArmMotor.setPower(0.5);
    }
// needs tuning :)
    public void armdown() {ArmMotor.setPower(-0.5);

    }
}
