package org.firstinspires.ftc.teamcode;

/*
Modern Robotics Color Sensor Example with color number
Created 9/29/2016 by Colton Mehlhoff of Modern Robotics using FTC SDK 2.2
Reuse permitted with credit where credit is due

Configuration:
I2CDevice "cc" (MRI Color Sensor with default I2C address 0x3c (0x1e 7-bit)

ModernRoboticsI2cColorSensor class is not being used because it can not access color number.
ColorSensor class is not being used because it can not access color number.

To change color sensor I2C Addresses, go to http://modernroboticsedu.com/mod/lesson/view.php?id=96
Support is available by emailing support@modernroboticsinc.com.
*/

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "RevColorTest", group = "MRI")
// @Autonomous(...) is the other common choice
@Disabled
public class RevColorTest extends OpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    byte[] colorCcache;

    ColorSensor colorC;
    I2cDeviceSynch colorCreader;
    DcMotor Fmotorleft;
    DcMotor Fmotorright;
    DcMotor Bmotorleft;
    DcMotor Bmotorright;
    DcMotor treadleft;
    DcMotor treadright;
    Servo Sensorarm;

//    TouchSensor touch;         //Instance of TouchSensor - for changing color sensor mode

  //  boolean touchState = false;  //Tracks the last known state of the touch sensor
   boolean LEDState = true;     //Tracks the mode of the color sensor; Active = true, Passive = false

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
colorC = hardwareMap.colorSensor.get("RevColor");
        Fmotorleft = hardwareMap.dcMotor.get("motor_1");
        Fmotorright = hardwareMap.dcMotor.get("motor_2");
        Bmotorleft = hardwareMap.dcMotor.get("motor_3");
        Bmotorright = hardwareMap.dcMotor.get("motor_4");
        treadleft = hardwareMap.dcMotor.get("motor_6");
        treadright = hardwareMap.dcMotor.get("motor_7");
        Sensorarm = hardwareMap.servo.get("servo_1");

        double targettime;
        //the below lines set up the configuration file
        /*colorC = hardwareMap.i2cDevice.get("RevColor");
        colorCreader = new I2cDeviceSynchImpl(colorC, I2cAddr.create8bit(0x3c), false);
        colorCreader.engage();
*/

     //   touch = hardwareMap.touchSensor.get("t");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*

     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();

        //targettime = System.currentTimeMillis()+1000;

        colorC.enableLed(LEDState);
        /*if(LEDState) {
            colorCreader.write8(3, 0);    //Set the mode of the color sensor using LEDState
        }
        else{
            colorCreader.write8(3, 1);    //Set the mode of the color sensor using LEDState
        }*/
        //Active - For measuring reflected light. Cancels out ambient light
        //Passive - For measuring ambient light, eg. the FTC Color Beacon
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        //The below two if() statements ensure that the mode of the color sensor is changed only once each time the touch sensor is pressed.
        //The mode of the color sensor is saved to the sensor's long term memory. Just like flash drives, the long term memory has a life time in the 10s or 100s of thousands of cycles.
        //This seems like a lot but if your program wrote to the long term memory every time though the main loop, it would shorten the life of your sensor.

       // if (!touchState && touch.isPressed()) {  //If the touch sensor is just now being pressed (was not pressed last time through the loop but now is)
         //   touchState = true;                   //Change touch state to true because the touch sensor is now pressed
           // LEDState = !LEDState;                //Change the LEDState to the opposite of what it was
        colorC.enableLed(LEDState);

        if(LEDState){
                colorCreader.write8(3, 0);    //Set the mode of the color sensor using LEDState
            }
            else{
                colorCreader.write8(3, 1);    //Set the mode of the color sensor using LEDState
            }
        colorCcache = colorCreader.read(0x04, 1);

        //display values
        telemetry.addData("2 #C", colorCcache[0] & 0xFF);
        telemetry.addData("2 #C", colorC.argb());
        }
        //if (!touch.isPressed()) {                //If the touch sensor is now pressed
          //  touchState = false;                  //Set the touchState to false to indicate that the touch sensor was released




    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}