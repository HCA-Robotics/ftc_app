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

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class K9TeleOpAuto extends OpMode {

    /*
     * Note: the configuration of the servos is such that
     * as the arm servo approaches 0, the arm position moves up (away from the floor).
     * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
     */
    // TETRIX VALUES.
    final static double ARM_MIN_RANGE  = 0.20;
    final static double ARM_MAX_RANGE  = 0.90;
    final static double CLAW_MIN_RANGE  = 0.20;
    final static double CLAW_MAX_RANGE  = 0.7;

    // position of the arm servo.
    double armPosition;

    // amount to change the arm servo position.
    double armDelta = 0.1;

    // position of the claw servo
    double clawPosition;

    // amount to change the claw servo position by
    double clawDelta = 0.1;

    DcMotor motorRight;
    DcMotor motorLeft;
    Servo claw;
    Servo arm;

    /**
     * Constructor
     */
    public K9TeleOpAuto() {

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
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        arm = hardwareMap.servo.get("servo_1");
        claw = hardwareMap.servo.get("servo_6");

        // assign the starting position of the wrist and claw
        armPosition = 0.2;
        clawPosition = 0.2;

        motorLeft.setMode
                ( DcMotorController.RunMode.RUN_USING_ENCODERS
                );

        motorRight.setMode
                (DcMotorController.RunMode.RUN_USING_ENCODERS
                );

        motorLeft.setMode
                ( DcMotorController.RunMode.RESET_ENCODERS
                );

        motorRight.setMode
                (DcMotorController.RunMode.RESET_ENCODERS
                );


    }
    //--------------------------------------------------------------------------
    //

	/*
	 * This method will be called repeatedly in a loop
	 * 
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */

    @Override public void start ()

    {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        // super.start ();

        //
        // Reset the motor encoders on the drive wheels.
        //


    } // start
    //--------------------------------------------------------------------------
    //
    // a_left_encoder_count
    //
    /**
     * Access the left encoder's count.

     int a_left_encoder_count ()
     {
     int l_return = 0;

     if (motorLeft != null)
     {
     l_return = motorLeft.getCurrentPosition ();
     }

     return l_return;

     } // a_left_encoder_count
     */
    //--------------------------------------------------------------------------
    //
    // a_right_encoder_count
    //
    /**
     * Access the right encoder's count.

     int a_right_encoder_count ()

     {
     int l_return = 0;

     if (motorRight != null)
     {
     l_return = motorRight.getCurrentPosition ();
     }

     return l_return;

     } // a_right_encoder_count
     */
    //--------------------------------------------------------------------------
    //
    // has_left_drive_encoder_reached
    //
    /**
     * Indicate whether the left drive motor's encoder has reached a value.
     */
    boolean has_left_drive_encoder_reached (double p_count)

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        if (motorLeft != null)
        {
            //
            // Has the encoder reached the specified values?
            //
            // TODO Implement stall code using these variables.
            //
            if (Math.abs (motorLeft.getCurrentPosition ()) > p_count)
            {
                //
                // Set the status to a positive indication.
                //
                l_return = true;
            }
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_left_drive_encoder_reached

    //--------------------------------------------------------------------------
    //
    // has_right_drive_encoder_reached
    //
    /**
     * Indicate whether the right drive motor's encoder has reached a value.
     */
    boolean has_right_drive_encoder_reached (double p_count)

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        if (motorRight != null)
        {
            //
            // Have the encoders reached the specified values?
            //
            // TODO Implement stall code using these variables.
            //
            if (Math.abs (motorRight.getCurrentPosition ()) > p_count)
            {
                //
                // Set the status to a positive indication.
                //
                l_return = true;
            }
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_right_drive_encoder_reached

    //--------------------------------------------------------------------------
    //
    // have_drive_encoders_reached
    //
    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean have_drive_encoders_reached(double p_left_count, double p_right_count)
    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Have the encoders reached the specified values?
        //
        if (has_left_drive_encoder_reached (p_left_count) &&
                has_right_drive_encoder_reached (p_right_count))
        {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // have_encoders_reached

    public static final int s_INITIAL = 0;
    public static final int s_MOVING_FORWARD = 1;

    //--------------------------------------------------------------------------
    //
    // loop
    //
    /**
     * Implement a state machine that controls the robot during auto-operation.
     * The state machine uses a class member and encoder input to transition
     * between states.
     *
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override public void loop ()

    {
        //----------------------------------------------------------------------
        //
        // State: Initialize (i.e. state_0).
        //
        switch (v_state)
        {
            //
            // Synchronize the state machine and hardware.
            //
            case s_INITIAL:
                //
                // Reset the encoders to ensure they are at a known good value.
                //
                motorLeft.setMode
                        ( DcMotorController.RunMode.RESET_ENCODERS
                        );

                motorRight.setMode
                        (DcMotorController.RunMode.RESET_ENCODERS
                        );
                //
                // Transition to the next state when this method is called again.
                //
                v_state = s_MOVING_FORWARD;

                break;
            //
            // Drive forward until the encoders exceed the specified values.
            //
            case s_MOVING_FORWARD:
                //
                // Tell the system that motor encoders will be used.  This call MUST
                // be in this state and NOT the previous or the encoders will not
                // work.  It doesn't need to be in subsequent states.
                //


                //
                // Start the drive wheel motors at full power.
                //
                // write the values to the motors
                motorRight.setTargetPosition(2880);
                motorLeft.setTargetPosition(2880);
                motorRight.setPower(1.00);
                motorLeft.setPower(1.00);
                v_state = -s_MOVING_FORWARD;
                break;

            case -s_MOVING_FORWARD:

                /*
                try {

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                */

                if (have_drive_encoders_reached (2880, 2880)) {

                    //
                    // Stop the motors.
                    //
                    motorRight.setPower(0.0);
                    motorLeft.setPower(0.0);
                    //
                    // Reset the encoders to ensure they are at a known good value.
                    //
                    motorLeft.setMode
                            (DcMotorController.RunMode.RESET_ENCODERS
                            );

                    motorRight.setMode
                            (DcMotorController.RunMode.RESET_ENCODERS
                            );
                    //
                    // Transition to the next state when this method is called
                    // again.
                    //
                    v_state = 2;

                }


                break;
            //
            // Wait...
            //
            case 2:

                //
                // Pivot turn right
                //
                motorRight.setTargetPosition(0);
                motorLeft.setTargetPosition(750);
                motorRight.setPower(0.0);
                motorLeft.setPower(0.20);


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (have_drive_encoders_reached (750, 0)) {
                    //
                    // Reset the encoders to ensure they are at a known good value.
                    //
                    motorLeft.setMode
                            (DcMotorController.RunMode.RESET_ENCODERS
                            );

                    motorRight.setMode
                            (DcMotorController.RunMode.RESET_ENCODERS
                            );


                    //
                    // Stop the motors.
                    //
                    motorRight.setPower(0.0);
                    motorLeft.setPower(0.0);

                    v_state++;

                }
                //

                break;
            //
            // Turn left until the encoders exceed the specified values.
            //
            case 3:

                motorRight.setTargetPosition(2000);
                motorLeft.setTargetPosition(2000);
                motorRight.setPower(1.00);
                motorLeft.setPower(1.00);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if (have_drive_encoders_reached (2000, 2000)) {
                    //
                    // Reset the encoders to ensure they are at a known good value.
                    //
                    motorLeft.setMode
                            (DcMotorController.RunMode.RESET_ENCODERS
                            );

                    motorRight.setMode
                            (DcMotorController.RunMode.RESET_ENCODERS
                            );


                    //
                    // Stop the motors.
                    //
                    motorRight.setPower(0.0);
                    motorLeft.setPower(0.0);
                    // Transition to the next state when this method is called
                    // again.

                    v_state++;

                }
                //

                break;
            //
            // Wait...
            //

            default:
                //
                // The autonomous actions have been accomplished (i.e. the state has
                // transitioned into its final state.
                //
                break;
        }

        //
        // Send telemetry data to the driver station.
        //

    } // loop

    //--------------------------------------------------------------------------
    //
    // v_state
    //
    /**
     * This class member remembers which state is currently active.  When the
     * start method is called, the state will be initialized (0).  When the loop
     * starts, the state will change from initialize to state_1.  When state_1
     * actions are complete, the state will change to state_2.  This implements
     * a state machine for the loop method.
     */
    private int v_state = 0;

} // PushBotAuto
