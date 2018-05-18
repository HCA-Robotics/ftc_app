package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Robo User on 2/24/2018.
 */

@Autonomous(name="Jewel Auto Red")
public class JewelAutoRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ColorSensor revColor = hardwareMap.colorSensor.get("RevColor");
        DcMotor Fmotorleft = hardwareMap.dcMotor.get("motor_1");
        DcMotor Fmotorright = hardwareMap.dcMotor.get("motor_2");
        DcMotor Bmotorleft = hardwareMap.dcMotor.get("motor_3");
        DcMotor Bmotorright = hardwareMap.dcMotor.get("motor_4");
        DcMotor treadleft = hardwareMap.dcMotor.get("motor_6");
        DcMotor treadright = hardwareMap.dcMotor.get("motor_7");
        Servo ColorArm = hardwareMap.servo.get("servo_1");

        waitForStart();

        // back up and drop color arm
        Fmotorleft.setPower(1);
        Fmotorright.setPower(-1);
        Bmotorleft.setPower(-1);
        Bmotorright.setPower(1);
        sleep(500);
        Fmotorleft.setPower(0);
        Fmotorright.setPower(0);
        Bmotorleft.setPower(0);
        Bmotorright.setPower(0);

        ColorArm.setPosition(1);

        if (revColor.red() > revColor.blue()) {
            // turn right
            Fmotorleft.setPower(1);
            Fmotorright.setPower(1);
            Bmotorleft.setPower(-1);
            Bmotorright.setPower(-1);
            sleep(100);
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

            Fmotorleft.setPower(-1);
            Fmotorright.setPower(-1);
            Bmotorleft.setPower(1);
            Bmotorright.setPower(1);
            sleep(100);
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

        } else {
            // turn left and turn back
            Fmotorleft.setPower(-1);
            Fmotorright.setPower(-1);
            Bmotorleft.setPower(1);
            Bmotorright.setPower(1);
            sleep(100);
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

            Fmotorleft.setPower(1);
            Fmotorright.setPower(1);
            Bmotorleft.setPower(-1);
            Bmotorright.setPower(-1);
            sleep(100);
            Fmotorleft.setPower(0);
            Fmotorright.setPower(0);
            Bmotorleft.setPower(0);
            Bmotorright.setPower(0);

        }


        // Turn left

        Fmotorleft.setPower(-1);
        Fmotorright.setPower(-1);
        Bmotorleft.setPower(1);
        Bmotorright.setPower(1);
        sleep(500);
        Fmotorleft.setPower(0);
        Fmotorright.setPower(0);
        Bmotorleft.setPower(0);
        Bmotorright.setPower(0);

        //go forward
        Fmotorleft.setPower(-1);
        Fmotorright.setPower(1);
        Bmotorleft.setPower(1);
        Bmotorright.setPower(-1);
        sleep(900);
        Fmotorleft.setPower(0);
        Fmotorright.setPower(0);
        Bmotorleft.setPower(0);
        Bmotorright.setPower(0);

        //release glyph
        treadleft.setPower(.5);
        treadright.setPower(-.5);
        sleep(1000);
        treadleft.setPower(0);
        treadright.setPower(0);

    }
}
