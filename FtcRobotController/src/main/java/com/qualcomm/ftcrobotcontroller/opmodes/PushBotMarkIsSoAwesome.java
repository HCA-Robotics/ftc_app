package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/*
 * An example linear op mode where the pushbot
 * will drive in a square pattern using sleep()
 * and a for loop.
 */
public class PushBotMarkIsSoAwesome extends LinearOpMode {
    DcMotor testMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        testMotor = hardwareMap.dcMotor.get("motor_1");

        testMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);


        waitForStart();
        testMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitOneFullHardwareCycle();

        testMotor.setPower(1.0);
        testMotor.setTargetPosition(1440);

        while(testMotor.getCurrentPosition() < 1440){
            waitForNextHardwareCycle();
        }
        testMotor.setPower(0.0);
    }

}