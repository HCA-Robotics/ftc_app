package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;

/**
 * Created by Tandi User on 4/24/2017.
 */
@Autonomous(name = "Autonomous Encoder Drive", group = "HCA Opmode")
@Disabled
public class Autonomous_Phase_2018_02_09 extends BaseOpMode {

    /**  private ElapsedTime runtime = new ElapsedTime();
     */
    DcMotor Fmotorleft;
    DcMotor Fmotorright;
    DcMotor Bmotorleft;
    DcMotor Bmotorright;
    DcMotor treadleft;
    DcMotor treadright;



    @Override
    public void init() {
       // boolean hasstarted=false;
        Fmotorleft = hardwareMap.dcMotor.get("motor_1");
        Fmotorright = hardwareMap.dcMotor.get("motor_2");
        Bmotorleft = hardwareMap.dcMotor.get("motor_3");
        Bmotorright = hardwareMap.dcMotor.get("motor_4");
        //motor 5 is the arm, not used here
        treadleft = hardwareMap.dcMotor.get("motor_6");
        treadright = hardwareMap.dcMotor.get("motor_7");

        Fmotorleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Fmotorright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Fmotorleft.setDirection(DcMotor.Direction.REVERSE);
        Bmotorleft.setDirection(DcMotor.Direction.REVERSE);

    }

    boolean done = false;
    boolean hasstarted = false;

    @Override
    public void loop() {

     // if (done) return;
        if (!hasstarted) {
            hasstarted = true;

            Fmotorleft.setTargetPosition(1220);
            Fmotorright.setTargetPosition(1220);

            Fmotorleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Fmotorright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Fmotorleft.setPower(.5);
            Fmotorright.setPower(.5);
            Bmotorleft.setPower(.5);
            Bmotorright.setPower(.5);
        }

        int position = Math.min(
                Math.abs(Fmotorright.getCurrentPosition()),
                Math.abs(Fmotorleft.getCurrentPosition()));

        if (position >= 1220) {
            done = true;
            Fmotorleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Fmotorright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Fmotorleft.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
            Fmotorright.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);


        }
    }

    @Override
    public void stop() {

        telemetry.addData("Message", "Done");
        Fmotorleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Fmotorright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

}



