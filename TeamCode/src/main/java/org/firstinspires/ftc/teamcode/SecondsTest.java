package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by Robo User on 4/28/2018.
 */

public class SecondsTest implements Task {

    private long seconds;
    private double power;
    private long initTime;
    private boolean started = false;
    private boolean haveStarted = false;
    private boolean isDone = false;

    public void SecondTest(long sec,double pow){
        seconds = sec;
        power = pow;
    }

    DcMotor Fmotorright;
    DcMotor Fmotorleft;
    DcMotor Bmotorleft;
    DcMotor Bmotorright;

    @Override
    public void init(HardwareMap hardwareMap) {
        Bmotorright = hardwareMap.dcMotor.get("motor_1");
        Bmotorleft = hardwareMap.dcMotor.get("motor_2");
        Fmotorright = hardwareMap.dcMotor.get("motor_3");
        Fmotorleft = hardwareMap.dcMotor.get("motor_4");

        Fmotorright.setPower(power);       //encoded
        Fmotorleft.setPower(-1 * power);     //encoded
        Bmotorleft.setPower(power);        //not encoded
        Bmotorright.setPower(-1 * power);    //not encoded

        started = true;
    }

    @Override
    public void loop() {
        if(started){
            initTime = System.currentTimeMillis();
            started = false;
        }

        if(seconds <= System.currentTimeMillis()-initTime) {
            Fmotorleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Fmotorright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bmotorleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bmotorright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            Fmotorright.setPower(0);
            Fmotorleft.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

            isDone = true;
        }
    }

    @Override
    public boolean isTaskDone() {
        return isDone;
    }

    @Override
    public boolean hasStarted() {
        return haveStarted;
    }
}
