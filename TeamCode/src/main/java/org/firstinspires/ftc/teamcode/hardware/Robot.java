package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.C;

/**
 * Created by Reicher Robotics on 2/22/2018.
 */

public class Robot {
    public HardwareMap hardwareMap;
    private ElapsedTime timer = new ElapsedTime();

   public MecanumSubSystem mecanum = null;
    public GyroIMU gyroIMU = null;

    public Claw claw = null;

    public HangSubSystem hang = null;

    public ArmSubSystem arm = null;

    public PlacerSubSystem placer = null;


    public LiftSubSystem lift = null;
    public IntakeSubSystem intake = null;



    boolean mecanumDrive = true;

    public Robot(){
    }

    public void init(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        // Mecanum Drive Sub System
        DcMotorEx motorDriveLeftFront = hardwareMap.get(DcMotorEx.class, "lf");
        motorDriveLeftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        DcMotorEx motorDriveLeftRear = hardwareMap.get(DcMotorEx.class, "lr");
        motorDriveLeftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        DcMotorEx motorDriveRightFront = hardwareMap.get(DcMotorEx.class, "rf");
        motorDriveRightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        DcMotorEx motorDriveRightRear = hardwareMap.get(DcMotorEx.class, "rr");
        motorDriveRightRear.setDirection(DcMotorSimple.Direction.FORWARD);
        mecanum = new MecanumSubSystem(motorDriveLeftFront, motorDriveLeftRear, motorDriveRightFront, motorDriveRightRear);
        mecanum.setPID(0.1, 0.0, 0.0);
        mecanum.setDriveProperties(384.5, 96.0 / 25.4, 1.0);

        // Gyro/IMU Sub System
        IMU imu = hardwareMap.get(IMU.class, "imu");
        gyroIMU = new GyroIMU(imu);


       //claw thingy
        Servo servoClaw = hardwareMap.servo.get("claw");
        Servo servoWrist = hardwareMap.servo.get("wrist");
        claw = new Claw(servoClaw, servoWrist);




        // Hang
       //DcMotorEx winchLeftMotor = hardwareMap.get(DcMotorEx.class, "winchLeft");
       // DcMotorEx winchRightMotor = hardwareMap.get(DcMotorEx.class, "winchRight");
       // Servo leftHangServo = hardwareMap.servo.get("lhang");
       // Servo rightHangServo = hardwareMap.servo.get("rhang");
      //  hang = new HangSubSystem(winchLeftMotor, false, winchRightMotor, false, leftHangServo, rightHangServo);


       // Servo placerServo = hardwareMap.servo.get("placer");
       // placer = new PlacerSubSystem(placerServo);

        // Outtake Sub System
        //Servo outtakeServo = hardwareMap.servo.get("outtake");
       // outtake = new ServoOuttake(outtakeServo);

        // Drone Servo Sub System
       // Servo droneServo = hardwareMap.servo.get("droneser");
       // droneser = new ServoDrone(droneServo);


       //  Lift Sub System
        DcMotorEx motorLift = hardwareMap.get(DcMotorEx.class, "lift");
        motorLift.setDirection(DcMotorSimple.Direction.FORWARD);
        DcMotorEx motorLift2 = hardwareMap.get(DcMotorEx.class, "lift2");
        motorLift2.setDirection(DcMotorSimple.Direction.REVERSE);
        lift = new LiftSubSystem(motorLift, motorLift2);


       // Drone Sub System
       // DcMotorEx motorDrone = hardwareMap.get(DcMotorEx.class, "drone");
      //  motorDrone.setDirection(DcMotorSimple.Direction.FORWARD);
        //drone = new DroneSubSystem(motorDrone);

      //  Intake Sub System
        DcMotorEx motorIntake = hardwareMap.get(DcMotorEx.class, "intake");
        motorIntake.setDirection(DcMotorSimple.Direction.FORWARD);
        intake = new IntakeSubSystem(motorIntake);


        //Arm Sub System
       // DcMotorEx ArmMotor = hardwareMap.get(DcMotorEx.class, "arm");
       // arm = new ArmSubSystem(ArmMotor, false);

        //Claw sub system
      // Servo leftServo = hardwareMap.servo.get("lclaw");
      // Servo rightServo = hardwareMap.servo.get("rclaw");
      // claw = new ClawSubSystem(leftServo, rightServo);
    }

    public void initAutoServos() {


        claw.open();
        claw.close();
    }

    public void sleep(int time) {
        timer.reset();
        while(timer.milliseconds() < time) {
        }
    }
}