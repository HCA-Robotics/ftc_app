package com.qualcomm.ftcrobotcontroller.opmodes;

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
public class demo extends OpMode {

    DcMotor motorRight;
    DcMotor motorLeft;

    Timer timey = new Timer();

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        resetEncoders();
    }

/*    @Override public void start() {
        init();

        //waitForStart(); // Wait for the beginning of autonomous phase.


    }
*/
    boolean hasStarted = false;

    public void initSpeedStuff(){
        motorRight.setPower(-1.0);
        motorRight.setTargetPosition(7000);
        motorLeft.setPower(1.0);
        motorLeft.setTargetPosition(7000);
        //timey.cancel();
        timey.schedule(new TimerTask() {
            @Override
            public void run() {
                checkEncoderDistances();
            }
        }, 100);
    }

    private void checkEncoderDistances(){
        Log.d("Stuff", String.format("left: %d, right: %d", motorLeft.getCurrentPosition(), motorRight.getCurrentPosition()));
        if(Math.abs(motorRight.getCurrentPosition()) >= 7000 &&
           Math.abs(motorLeft.getCurrentPosition()) >= 7000) {
            motorRight.setPower(0.0);
            motorLeft.setPower(0.0);
            //turnRobot();
            return;
        }
        ////timey.cancel();
        timey.schedule(new TimerTask() {
            @Override
            public void run() {
                checkEncoderDistances();
            }
        }, 100);
    }

    private void resetEncoders(){
        RunMode lefty = motorLeft.getMode();
        motorLeft.setMode(RunMode.RESET_ENCODERS);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorLeft.setMode(lefty);

        RunMode righty = motorRight.getMode();
        motorRight.setMode(RunMode.RESET_ENCODERS);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorRight.setMode(righty);
    }

    private void turnRobot() {
        resetEncoders();
        motorRight.setPower(0.0);
        motorRight.setTargetPosition(0);
        motorLeft.setPower(1.0);
        motorLeft.setTargetPosition(50);
        //timey.cancel();
        /*timey.schedule(new TimerTask() {
            @Override
            public void run() {
                watchTurn();
            }
        }, 100);*/
        //watchTurn();
    }


    private void watchTurn(){
        Log.d("Watching Turn", String.format("left: %d, right: %d", motorLeft.getCurrentPosition(), motorRight.getCurrentPosition()));
        if(Math.abs(motorRight.getCurrentPosition()) >= 0 &&
                Math.abs(motorLeft.getCurrentPosition()) >= 50) {
            motorRight.setPower(0.0);
            motorLeft.setPower(0.0);
            // set motor speed stuff
            resetEncoders();
            //timey.cancel();
            timey.schedule(new TimerTask() {
                @Override
                public void run() {
                    motorRight.setPower(1.0);
                    motorRight.setTargetPosition(4500);
                    motorLeft.setPower(1.0);
                    motorLeft.setTargetPosition(4500);

                    checkEncoderDistances2();
                }
            }, 100);

            return;
        }
        //timey.cancel();
        timey.schedule(new TimerTask() {
            @Override
            public void run() {
                watchTurn();
            }
        }, 100);
    }


    private void checkEncoderDistances2(){
        Log.d("Stuff 2", String.format("left: %d, right: %d", motorLeft.getCurrentPosition(), motorRight.getCurrentPosition()));
        if(Math.abs(motorRight.getCurrentPosition()) >= 4500 &&
                Math.abs(motorLeft.getCurrentPosition()) >= 4500) {
            motorRight.setPower(0.0);
            motorLeft.setPower(0.0);
            return;
        }
        //timey.cancel();
        timey.schedule(new TimerTask() {
            @Override
            public void run() {
                checkEncoderDistances2();
            }
        }, 100);
    }


    @Override
    public void loop() {
        //OpModeIsActive;
        if(!hasStarted){
            ////timey.cancel();
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

    @Override
    public void stop() {
        timey.cancel();
    }
}
