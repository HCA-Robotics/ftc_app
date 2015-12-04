package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Tandi User on 11/13/2015.
 */
public class demo  extends OpMode {

    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
    }

    /*@Override public void start() {
        init();

        //waitForStart(); // Wait for the beginning of autonomous phase.


    }*/

    @Override
    public void loop() {
        //OpModeIsActive();
        {
            motorRight.setPower(-100);
            motorLeft.setPower(100);

            try {
                Thread.sleep(3000);

                //setPowerFloat();
                Thread.sleep(27000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }
    }

}
