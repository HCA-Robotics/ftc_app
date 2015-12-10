package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tandi User on 11/13/2015.
 */
public class demo extends OpMode {

    DcMotor motorRight;
    DcMotor motorLeft;

    Timer timey = new Timer();

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
    }

/*    @Override public void start() {
        init();

        //waitForStart(); // Wait for the beginning of autonomous phase.


    }
*/
    boolean hasStarted = false;

    public void initSpeedStuff(){
        motorRight.setPower(-1.0);
        motorRight.setTargetPosition(5000);
        motorLeft.setPower(1.0);
        motorLeft.setTargetPosition(5000);

        timey.schedule(new TimerTask() {
            @Override
            public void run() {
                checkEncoderDistances();
            }
        }, 100);
    }

    private void checkEncoderDistances(){
        Log.d("Stuff", String.format("left: %d, right: %d", motorLeft.getCurrentPosition(), motorRight.getCurrentPosition()));
        if(Math.abs(motorRight.getCurrentPosition()) >= 5000 &&
           Math.abs(motorLeft.getCurrentPosition()) >= 5000) {
            motorRight.setPower(0.0);
            motorLeft.setPower(0.0);
            return;
        }

        timey.schedule(new TimerTask() {
            @Override
            public void run() {
                checkEncoderDistances();
            }
        }, 100);
    }


    @Override
    public void loop() {
        //OpModeIsActive;
        if(!hasStarted){
            timey.schedule(new TimerTask() {
                @Override
                public void run() {
                    initSpeedStuff();
                }
            }, 100);
            hasStarted = true;
        }
        {
            /*
            motorRight.setPower(-1.0);
            motorRight.setTargetPosition(3000);
            motorLeft.setPower(1.0);
            motorLeft.setTargetPosition(3000);

            /*try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }*/
         }
        }
    }
