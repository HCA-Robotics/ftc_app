package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by Tandi User on 4/24/2017.
 */
@Autonomous(name = "wallstopper", group = "HCA Opmode")
public class wallstopper extends BaseOpMode {

    //  private ElapsedTime runtime = new ElapsedTime();
    DcMotor motorLeft = null;
    DcMotor motorRight = null;


    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorRight = hardwareMap.dcMotor.get("motor_2");

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        state = INITIAL;

    }

    int state = 0;

    final static int INITIAL = 0;
    final static int WAIT_FOR_POSITION = 1;
    final static int DONE = 2;

    @Override
    public void loop() {
        telemetry.addData("Status", "loop");
        int targetPosition = 1 * 1120;
        telemetry.addData("Status", "MULA");
        switch (state) {
            case INITIAL:
                telemetry.addData("Status", "hi there!");
                motorLeft.setTargetPosition(targetPosition);
                motorRight.setTargetPosition(targetPosition);
                telemetry.addData("Status", "atjsethogetor");
                motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                state = WAIT_FOR_POSITION;
                break;
            case WAIT_FOR_POSITION:
                int position = Math.min(
                        Math.abs(motorLeft.getCurrentPosition()),
                        Math.abs(motorRight.getCurrentPosition()));
                telemetry.addData("Status", "WAIT!!!!!");

                if (position >= targetPosition) {
                    motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    state = DONE;
                }
                break;
            case DONE:
                telemetry.addData("Message", "Done");
                break;
        }
    }

}

