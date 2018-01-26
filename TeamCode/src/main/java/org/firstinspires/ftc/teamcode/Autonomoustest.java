package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Consumer;

import java.util.ArrayList;

/**
 * Created by Tandi User on 4/24/2017.
 */
@Autonomous(name = "autonomous", group = "HCA Opmode")
public class Autonomoustest extends BaseOpMode {

    /**
     * private ElapsedTime runtime = new ElapsedTime();
     */
    DcMotor Fmotorleft;
    DcMotor Fmotorright;
    DcMotor Bmotorleft;
    DcMotor Bmotorright;
    DcMotor treadleft;
    DcMotor treadright;

    @Override
    public void init() {

        Fmotorleft = hardwareMap.dcMotor.get("motor_1");
        Fmotorright = hardwareMap.dcMotor.get("motor_2");
        Bmotorleft = hardwareMap.dcMotor.get("motor_3");
        Bmotorright = hardwareMap.dcMotor.get("motor_4");
        treadleft = hardwareMap.dcMotor.get("motor_6");
        treadright = hardwareMap.dcMotor.get("motor_7");


        telemetry.addData("time", targetTime);

    }

    double targetTime;

    int state = 0;

    void state0() {
        targetTime = System.currentTimeMillis() + 900;
        state = 1;
    }

    void state1() {
        if (System.currentTimeMillis() < targetTime) {
            Fmotorleft.setPower(-1);
            Fmotorright.setPower(1);
            Bmotorleft.setPower(1);
            Bmotorright.setPower(-1);
        } else {
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

            targetTime = System.currentTimeMillis() + 1000;
            state = 2;
        }
    }

    @Override
    public void loop() {
        if (state == 0) state0();
        if (state == 1) state1();
        if (state == 2) state2();
        //if (state == 3) state3();
        if (state == 4) state4();
    }

private void state2() {
        //After the robot has initially run for 3 seconds
        if (System.currentTimeMillis() < targetTime) {
            treadleft.setPower(.5);
            treadright.setPower(-.5);

        }
        else{
            treadleft.setPower(0);
            treadright.setPower(0);

            //targetTime = System.currentTimeMillis() + 1000;
            state = 4;
    }
}
  /* void state3() {
        if (System.currentTimeMillis() < targetTime) {
            Fmotorleft.setPower(1);
            Fmotorright.setPower(-1);
            Bmotorleft.setPower(1 );
            Bmotorright.setPower(-1);
        }
        else {
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

        }}
*/
    void state4() {
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);
            treadleft.setPower(0);
            treadright.setPower(0);
     }

        /*if (System.currentTimeMillis() >= 2000) {
        *   done = true;
        *   motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        *   motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        *   motorLeft.setPower(0);
        *   motorRight.setPower(0);
        *   motorRight.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        *   motorLeft.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        */



       // telemetry.addData("Message", "Done");


    }





