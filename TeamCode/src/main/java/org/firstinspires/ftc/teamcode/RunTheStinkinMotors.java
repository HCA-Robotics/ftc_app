package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * Created by Tandi User on 4/24/2017.
 */
@Autonomous(name = "RunTheStinkinMotors", group = "HCA Opmode")
public class RunTheStinkinMotors extends BaseOpMode {

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


    }

    //int motorrighttargetpostion = 1 * 1220;
    //int motorlefttargetpostion = 1 * 1220;

    @Override
    public void loop() {
        //telemetry.addData("Status", motorlefttargetpostion);
       // telemetry.addData("Status", motorrighttargetpostion);
        motorLeft.setPower(1);
        motorRight.setPower(1);
       // telemetry.addData("Status", "hi there!");
       // motorLeft.setTargetPosition(1220);
       // motorRight.setTargetPosition(1220);
       // telemetry.addData("Status", "atjsethogetor");
       // motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       // motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        //int position = Math.min(
          //      Math.abs(motorLeft.getCurrentPosition()),
          //      Math.abs(motorRight.getCurrentPosition()));
      //  telemetry.addData("Status", "WAIT!!!!!");

       // if (position >= motorlefttargetpostion) {
         //   motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //    motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        }

      //  telemetry.addData("Message", "Done");

    }




