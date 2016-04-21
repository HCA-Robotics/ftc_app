package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by Tandi User on 12/13/2015.
 */

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import static com.qualcomm.robotcore.hardware.DcMotorController.RunMode;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tandi User on 11/13/2015.
 */
public class demo2 extends OpMode {

    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
    }

    private boolean stopit = false;
    @Override
    public void loop() {

            if(stopit)
                return;
            stopit = true;

            motorRight.setPower(-1.0);
            motorLeft.setPower(1.0);

            try {
                Thread.sleep(3000);

                motorRight.setPowerFloat();
                motorLeft.setPowerFloat();
                //Thread.sleep(270000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
