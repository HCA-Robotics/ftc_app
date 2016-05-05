package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Tandi User on 3/14/2016.
 */
public class NewAuto  extends OpMode {
    DcMotor motorRight;
    DcMotor motorLeft;
    Servo claw;
    Servo arm;
    private boolean done = false;
    //private HardwareMap hardwareMap;

    @Override //<--- ask joe
    public void init() {
        System.out.println("Initing");
        motorRight = hardwareMap.dcMotor.get("motor_1");
        motorLeft = hardwareMap.dcMotor.get("motor_2");

        System.out.println("Done initing");
        telemetry.addData("01", "initialized");
    }

    @Override
    public void init_loop() {

        Reset();
    }
    int state = 0;
    @Override
    public void loop() {
        if(state == 2){     //this is supposed to make sure that a function finishes and returns it
            return;
        }
        else if(state == 0) {
            forward();
            StopForward();
        }
        else if(state == 1) {
            Turn();
            DoneTurning();
        }
    }

    // The following code is supposed to reset the encoders and set the drive power to 0
    public void Reset() {
        set_drive_power(-0.0f, 0.0f);
        //run_using_encoders();
        reset_drive_encoders();
        telemetry.addData("02", "reset");

    }

    private void reset_drive_encoders() {
        DcMotorController.RunMode tmp = motorLeft.getMode();
        motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //motorLeft.setMode(tmp);
//these codes set the mode of reset_encoders to the motors
        tmp = motorRight.getMode();
        motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        //motorRight.setMode(tmp);

    }

    private void set_drive_power(double lefty, double righty) {
        motorLeft.setPower(lefty);
        motorRight.setPower(righty);
    }
    int forwardCount = 0; //this counts the encoder value and returns it
    // The following code is supposed to autonomously drive the robot forward until the encoders reach 2880
    public void forward() {

        System.out.println("Starting Forward");

        motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        set_drive_power(1.0f, 1.0f);
        //telemetry.addData("03", "forward: " + (forwardCount++));
        String state = String.format("(%d, %d, %d)", forwardCount++, motorLeft.getCurrentPosition(), motorRight.getCurrentPosition());

        telemetry.addData("03", "forward: " + state);
        System.out.println("Ending Forward");
    }
    //The following code will stop the robot from moving forward
    public void StopForward() {
        if (have_drive_encoders_reached(2880, 2880)) {
            //if (have_drive_encoders_reached(1440, 1440)) {
            //if (have_drive_encoders_reached(144, 144)) {
            set_drive_power(0.0f, 0.0f);
            telemetry.addData("04", "stopped forward");
            forwardCount = 0;
            state = 1;
            reset_drive_encoders();
            Reset();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //the following code is supposed to turn the robot to the desired position
    public void Turn() {
        motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        motorRight.setDirection(DcMotor.Direction.FORWARD);
        set_drive_power(-1.0f, 1.0f);
        String state = String.format("(%d, %d, %d)", forwardCount++, motorLeft.getCurrentPosition(), motorRight.getCurrentPosition());
        telemetry.addData("05", "turning: " + state);
    }
    //the following code is supposed to stop the robot from turning
    public void DoneTurning() {
        if (have_drive_encoders_reached(2200, 2200)) {
            reset_drive_encoders();
            set_drive_power(0.0f, 0.0f);
            state = 2;
            telemetry.addData("06", "turndeded");
        }
    }

    private boolean have_drive_encoders_reached(int i, int i1) {

        return Math.abs(motorLeft.getCurrentPosition()) >= i && Math.abs(motorRight.getCurrentPosition()) >= i1;
    }

    //sets the mode to run using encoders
    private void run_using_encoders() {
        //motorLeft.setMode( DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setMode( DcMotorController.RunMode.RUN_USING_ENCODERS);
        //motorRight.setMode( DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setMode( DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
}