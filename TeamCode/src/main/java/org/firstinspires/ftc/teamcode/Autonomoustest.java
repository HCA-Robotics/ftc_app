package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;

/**
 * Created by Tandi User on 4/24/2017.
 */
@Autonomous(name = "autonomous", group = "HCA Opmode")
public class Autonomoustest extends BaseOpMode {

    public void driveInches(int inches, double power) {
        telemetry.addData("Position1", Fmotorright.getCurrentPosition());
        //goes a target number of inches
        double motorPower = (power / 100);
        double radius = 1.5;
        double targetInches = inches;
        double targetDist = (1220 / (2 * 3.1416 * radius)) * targetInches;

        int initPosition = Math.abs(Fmotorright.getCurrentPosition());
        int currentPosition = Math.abs(Fmotorright.getCurrentPosition() + Fmotorleft.getCurrentPosition()) / 2;
        //add current position to target distance

        Fmotorright.setPower(motorPower);       //encoded
        Fmotorleft.setPower(-1 * motorPower);     //encoded
        Bmotorleft.setPower(motorPower);        //not encoded
        Bmotorright.setPower(-1 * motorPower);    //not encoded
        telemetry.addData("Position", currentPosition);
        //Math.abs(initPosition-currentPosition) <= Math.abs(1)
        while ((initPosition + 1220) >= currentPosition) {
            telemetry.addData("Checkup", "Brake1");
            currentPosition = Math.abs(Fmotorright.getCurrentPosition() + Fmotorleft.getCurrentPosition()) / 2;
            telemetry.addData("Position1", currentPosition);
        }
        Fmotorleft.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        Fmotorright.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        Bmotorleft.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
        Bmotorright.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);

        Fmotorright.setPower(0);
        Fmotorleft.setPower(0);
        Bmotorleft.setPower(0);
        Bmotorright.setPower(0);

    }

    /**
     * private ElapsedTime runtime = new ElapsedTime();
     */
    DcMotor Fmotorright;
    DcMotor Fmotorleft;
    DcMotor Bmotorleft;
    DcMotor Bmotorright;
    DcMotor treadleft;
    DcMotor treadright;

    @Override
    public void init() {
        telemetry.addData("Checkup", "Initial");
        Bmotorright = hardwareMap.dcMotor.get("motor_1");
        Bmotorleft = hardwareMap.dcMotor.get("motor_2");
        Fmotorright = hardwareMap.dcMotor.get("motor_3");
        Fmotorleft = hardwareMap.dcMotor.get("motor_4");
        treadleft = hardwareMap.dcMotor.get("motor_6");
        treadright = hardwareMap.dcMotor.get("motor_7");

        //Fmotorright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Fmotorleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    boolean done = false;
    boolean hasstarted = false;
    public static final int INITIAL = 0;
    public static final int CHECKUOP = 1;
    int state = INITIAL;
    @Override
    public void loop() {

        switch(state){
            case INITIAL:

                break;

        }
        telemetry.addData("Checkup", "loop");
        if (done)
            return;

        if (!hasstarted) {
            telemetry.addData("flag", "loop");
            hasstarted = true;
            driveInches(24, 10);
            done = true;
            telemetry.addData("method executed", "done");
        }


    }
}






/*private void state2() {
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
}*/
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








