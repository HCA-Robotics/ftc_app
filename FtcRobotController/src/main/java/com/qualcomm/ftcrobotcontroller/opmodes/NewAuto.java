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

    @Override
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

    @Override
    public void loop() {
        if(done){
            return;
        }
        forward();
        StopForward();
    }

    // The following code is supposed to reset the encoders and set the drive power to 0
    public void Reset() {
        set_drive_power(-0.0f, 0.0f);
        run_using_encoders();
        reset_drive_encoders();
        telemetry.addData("02", "reset");

    }

    private void reset_drive_encoders() {
        DcMotorController.RunMode tmp = motorLeft.getMode();
        motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setMode(tmp);

        tmp = motorRight.getMode();
        motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setMode(tmp);
    }

    private void set_drive_power(double lefty, double righty) {
        motorLeft.setPower(lefty);
        motorRight.setPower(righty);
    }
    int forwardCount = 0;
    // The following code is supposed to autonomously drive the robot forward until the encoders reach 2880
    public void forward() {

        System.out.println("Starting Forward");

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
            reset_drive_encoders();
            set_drive_power(0.0f, 0.0f);
            telemetry.addData("04", "stopped forward");
            done = true;
            forwardCount = 0;
        }
    }

    private boolean have_drive_encoders_reached(int i, int i1) {

        return motorLeft.getCurrentPosition() >= i && motorRight.getCurrentPosition() >= i1;
    }


    private void run_using_encoders() {
        //motorLeft.setMode( DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setMode( DcMotorController.RunMode.RUN_USING_ENCODERS);
        //motorRight.setMode( DcMotorController.RunMode.RESET_ENCODERS);
        motorRight.setMode( DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
}