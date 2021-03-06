/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * b
 * TeleOp Mode
 * <p/>
 * Enables control of the robot via the gamepad
 */
@TeleOp(name = "K9TeleOp_SingleDrive", group = "HCA Opmode")
public class K9TeleOp_SingleDrive extends OpMode {

	/*     * Note: the configuration of the servos is such that

	 * as the arm servo approaches 0, the arm position moves up (away from the floor).
	 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
	 */
    // TETRIX VALUES.


    // position of the arm servo.

    // amount to change the arm servo position.


    // position of the claw servo


    // amount to change the claw servo position by


    DcMotor Fmotorleft;
    DcMotor Fmotorright;
    DcMotor Bmotorleft;
    DcMotor Bmotorright;
    DcMotor lift;
    DcMotor treadleft;
    DcMotor treadright;
    DcMotor push;
    //Servo push;
    //DcMotor Treadleft;
    //DcMotor Treadright;
    /**
     * Constructor
     */
    public K9TeleOp_SingleDrive() {

    }

    /*
     * Code to run when the op mode is first enabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

		/*
		 * For the demo Tetrix K9 bot we assume the following,
		 *   There are two motors "motor_1" and "motor_2"
		 *   "motor_1" is on the right side of the bot.
		 *   "motor_2" is on the left side of the bot and reversed.
		 *
		 * We also assume that there are two servos "servo_1" and "servo_6"
		 *    "servo_1" controls the arm joint of the manipulator.
		 *    "servo_6" controls the claw joint of the manipulator.
		 */
        Fmotorleft = hardwareMap.dcMotor.get("motor_1");    //know naming conventions, nerd: variables are lowercase
        Fmotorright = hardwareMap.dcMotor.get("motor_2");   //constants or final variables are ALL_CAPS
        Bmotorleft = hardwareMap.dcMotor.get("motor_3");    //classes are capitalized, methods are also lowercase
        Bmotorright = hardwareMap.dcMotor.get("motor_4");
        lift = hardwareMap.dcMotor.get("motor_5");
        treadleft = hardwareMap.dcMotor.get("motor_6");
        treadright = hardwareMap.dcMotor.get("motor_7");
        push = hardwareMap.dcMotor.get("push");

        // assign the starting position of the wrist and claw
    }

    /*
     * This method will be called repeatedly in a loop
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop()
    {

		/*
		 * Gamepad 1
		 *
		 * Gamepad 1 controls the motors via the left stick, and it controls the
		 * wrist/claw via the a,b, x, y buttons
		 */

        // throttle: left_stick_y ranges from -1 to 1, where -1 is full up, and
        // 1 is full down
        // direction: left_stick_x ranges from -1 to 1, where -1 is full left
        // and 1 is full right
        //float throttle = -gamepad1.left_stick_y;
        //float throttle = gamepad1.right_stick_y;
        //float right = throttle;
        //float left = throttle;
        double powerlevel = 1.0;

        if (gamepad1.right_bumper){
            powerlevel = powerlevel*.5;
        }

        if (gamepad1.left_bumper){
            powerlevel = powerlevel*.5;
        }

        double C1LY = (gamepad1.left_stick_y);
        double C1LX = (gamepad1.left_stick_x);
        double C1RX = (gamepad1.right_stick_x);

        Fmotorright.setPower((-C1LY - C1LX - C1RX)*powerlevel); //sets each motor to appropriate power, inverting
        Fmotorleft.setPower((C1LY - C1LX - C1RX)*powerlevel);   //power where necessary (inversions may be bad, distribute)
        Bmotorleft.setPower((-C1LY - C1LX + C1RX)*powerlevel);
        Bmotorright.setPower((C1LY - C1LX + C1RX)*powerlevel);

        if (gamepad1.dpad_up /*&& !gamepad2.dpad_down*/)
        { //if the dpad up arrow is held, lift the arm
            lift.setPower(-1);

        }
        if (gamepad1.dpad_down /*&& !gamepad2.dpad_up*/) { //if the dpad down arrow is held, lower the arm
            lift.setPower(1);
        }//replaced "if(!(up ^  down)){"  with  "if(!(up || down)){"
        //replaced "if(!(up || down)){"  with  "else {"
        //replaced "else {"
        if(!(gamepad1.dpad_down || gamepad1.dpad_up)){
            lift.setPower(0);                               //else lock the motor; stop running
        }
        if (gamepad1.y /*&& !gamepad2.dpad_down*/) { //if the dpad up arrow is held, lift the arm
            push.setPower(-.8);

        }
        if (gamepad1.a /*&& !gamepad2.dpad_up*/) { //if the dpad down arrow is held, lower the arm
            push.setPower(.3);
        }//replaced "if(!(up ^  down)){"  with  "if(!(up || down)){"
        //replaced "if(!(up || down)){"  with  "else {"
        //replaced "else {"
        if(!(gamepad1.y || gamepad1.a)){
            push.setPower(0);                               //else lock the motor; stop running
        }

        /*if (gamepad2.x){
            push.setPosition(1);
        }

        if (gamepad2.b) {
            push.setPosition(.5);
        }*/
        if (gamepad1.dpad_left /*&& !gamepad2.dpad_down*/) { //if the dpad up arrow is held, lift the arm
            treadleft.setPower(-1);
            treadright.setPower(1);

        }

        if (gamepad1.dpad_right /*&& !gamepad2.dpad_up*/) { //if the dpad down arrow is held, lower the arm
            treadleft.setPower(1);
            treadright.setPower(-1);

        }
        if(!(gamepad1.dpad_right || gamepad1.dpad_left))
            treadleft.setPower(0);
            treadright.setPower(0);
        }

            // clip the right/left values so that the values never exceed +/- 1
        //left = Range.clip(left, -1, 1);

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        //right = (float)scaleInput(right);
        //left =  (float)scaleInput(left);

        //motorRight.setPower(right);
        //motorLeft.setPower(left);


        // update the position of the arm.
       /* if (gamepad2.x) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            leftclamp.setPosition(.5);
            rightclamp.setPosition(.5);
        }

        if (gamepad2.b) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo.
            rightclamp.setPosition(1.5);
            leftclamp.setPosition(-.3);
        }

        // update the position of the claw
        if (gamepad2.dpad_up)
        {
            leftarm.setPosition(.3);
            rightarm.setPosition(.7);
        }

        if (gamepad2.dpad_down) {
            leftarm.setPosition(2);
            rightarm.setPosition(-1);
        }

        if (gamepad2.dpad_left) {
            leftarm.setPosition(.6);
            rightarm.setPosition(.3);
        }*/
        //  telemetry.addData("light", ods_sensor.getVoltage());
    }
		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */


    //telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
    // telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));



	/*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
/*@Override
public void stop() {

}


	/*
	 * This method scales the joystick input so for low joystick values, the
	 * scaled value is less than linear.  This is to make it easier to drive
	 * the robot more precisely at slower speeds.
	 */
//	double scaleInput(double dVal)  {
//	double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
//			0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

// get the corresponding index for the scaleInput array.
//	int index = (int) (dVal * 16.0);

// index should be positive.
//	if (index < 0) {
//		index = -index;
//}

// index cannot exceed size of array minus 1.
//	if (index > 16) {
///		index = 16;
//	}

// get value from the array.
//	double dScale = 0.0;
//	if (dVal < 0) {
///		dScale = -scaleArray[index];
//	} else {
//		dScale = scaleArray[index];
//	}

//	// return scaled value.
//	return dScale;
//}}


