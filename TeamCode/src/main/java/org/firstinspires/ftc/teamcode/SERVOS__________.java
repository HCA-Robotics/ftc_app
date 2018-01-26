package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by Tandi User on 4/24/2017.
 */
@Autonomous(name = "Autonomoustest", group = "HCA Opmode")
public class SERVOS__________ extends BaseOpMode {

    /**  private ElapsedTime runtime = new ElapsedTime();
    */
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    Servo servo_1 = null;
    Servo servo_2 = null;



    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorRight = hardwareMap.dcMotor.get("motor_2");
        servo_1  = hardwareMap.servo.get("servo_1");
        servo_2  = hardwareMap.servo.get("servo_2");

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        servo_1.resetDeviceConfigurationForOpMode();
        servo_2.resetDeviceConfigurationForOpMode();

    }

    boolean done = false;
    boolean hasstarted = false;

    @Override
    public void loop() {

        if (done) return;
        if (!hasstarted) {
            hasstarted = true;
            telemetry.addData("Status", "hi there!");
            motorLeft.setTargetPosition(1220);
            motorRight.setTargetPosition(1220);
            telemetry.addData("Status", "atjsethogetor");
            motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorLeft.setPower(1);
            motorRight.setPower(1);
            servo_1.setPosition(1);
            servo_2.setPosition(1);
        }

        int position = Math.min(
                Math.abs(motorLeft.getCurrentPosition()),
                Math.abs(motorRight.getCurrentPosition()));
        telemetry.addData("Status", "WAIT!!!!!");

        if (position >= 1220) {
            done = true;
            motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorLeft.setPower(0);
            motorRight.setPower(0);
            motorRight.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
            motorLeft.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);


        }
    }

    @Override
       public void stop() {

            telemetry.addData("Message", "Done");
        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        }

    }



