package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This Class Drives Forward set distance.
 */
public class DriveForwardTask implements Task {

    private int inches;
    private double power;
    private boolean haveStarted = false;
    private boolean isDone = false;
    private int currentPosition;


    DcMotor Fmotorright;
    DcMotor Fmotorleft;
    DcMotor Bmotorleft;
    DcMotor Bmotorright;
    private double targetDist;

    public DriveForwardTask(int inches, double power) {
        this.inches = inches;
        this.power = power;
    }

    @Override
    public void init(HardwareMap hardwareMap) {
        Bmotorright = hardwareMap.dcMotor.get("motor_1");
        Bmotorleft = hardwareMap.dcMotor.get("motor_2");
        Fmotorright = hardwareMap.dcMotor.get("motor_3");
        Fmotorleft = hardwareMap.dcMotor.get("motor_4");

        int initPosition = Fmotorright.getCurrentPosition();
        double radius = 1.5;
        targetDist = 1220;// / (2 * 3.1416 * radius)) * inches + initPosition;

        Fmotorleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Fmotorright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Fmotorleft.setDirection(DcMotor.Direction.REVERSE);
        Bmotorleft.setDirection(DcMotor.Direction.REVERSE);
        Bmotorright.setDirection(DcMotor.Direction.FORWARD);
        Fmotorright.setDirection(DcMotor.Direction.FORWARD);
        haveStarted = true;
    }

    @Override
    public void loop() {
        currentPosition = Fmotorright.getCurrentPosition();
        if (targetDist <= currentPosition) {
            Fmotorleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Fmotorright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bmotorleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            Bmotorright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            Fmotorright.setPower(0);
            Fmotorleft.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

            isDone = true;

        } else {
            Fmotorright.setPower(power);       //encoded
            Fmotorleft.setPower(power);     //encoded
            Bmotorleft.setPower(power);        //not encoded
            Bmotorright.setPower(power);    //not encoded
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
