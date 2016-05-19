package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/*
 * An example linear op mode where the pushbot
 * will drive in a square pattern using sleep()
 * and a for loop.
 */
public class PushBotMarkIsSoCool extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("motor_1");
        rightMotor = hardwareMap.dcMotor.get("motor_2");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        rightMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        waitForStart();
        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();
//the following code runs the robot forward at 1.0 power for 1 sec
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);

        sleep(1000);
//the following code turns the robot at .7 power for 1.6 seconds
        leftMotor.setPower(0.7);
        rightMotor.setPower(-0.7);

        sleep(1600);
//the following code runs the robot forward at 1.0 power for 1 sec
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);

        sleep(1000);


        leftMotor.setPowerFloat();
        rightMotor.setPowerFloat();

    }
}
