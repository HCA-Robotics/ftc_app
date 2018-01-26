package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class BaseOpMode  extends OpMode{
    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

    }

    protected DcMotor getLeftMotor(){
        return motorLeft;
    }

    protected DcMotor getRightMotor(){
        return motorRight;
    }

    protected void sendTextMessage(String msg) {
        telemetry.addData("TEXT", msg);
        telemetry.update();
    }
}
