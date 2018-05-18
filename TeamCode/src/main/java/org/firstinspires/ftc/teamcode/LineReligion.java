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

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * b
 * TeleOp Mode
 * <p/>
 * Enables control of the robot via the gamepad
 */
@TeleOp(name = "Line Religion", group = "HCA Opmode")
public class LineReligion extends BaseOpMode {

    private RollingAverage rollingAverage;
    private RollingAverage rollingAverageDistantce;

    class RollingAverage{
        private final int windowSize;
        private List<Double> values = new ArrayList<>();

        RollingAverage(int windowSize){
            this.windowSize = windowSize;
        }

        public Double appendItem(Double v){
            this.values.add(v);

            if(this.values.size() > windowSize){
                this.values.remove(0);
            }

            return getAverage();
        }

        public Double getAverage() {
            if(values.size() == 0) {
                return 0D;
            }

            Double sum = 0D;
            for(Double i: values){
                sum += i;
            }

            return sum / this.values.size();
        }
    }

    double leftPower, rightPower, correction;
    final double PERFECT_COLOR_VALUE = .2;
    OpticalDistanceSensor lightSensor;
    OpticalDistanceSensor distanceSensor;

    @Override
    public void init() {
        super.init();

        distanceSensor = hardwareMap.opticalDistanceSensor.get("ods");
        distanceSensor.resetDeviceConfigurationForOpMode();

        this.rollingAverage = new RollingAverage(200);
        this.rollingAverageDistantce = new RollingAverage(200);
    }

    /**
     * Constructor
     */
    public LineReligion() {


    }
    // assign the starting position of the wrist and claw


    /*
     * This method will be called repeatedly in a loop
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */
    @Override
    public void loop() {
        Double distance = distanceSensor.getLightDetected();
        telemetry.addData("Distance (Avg)", rollingAverageDistantce.appendItem(distance));
        telemetry.addData("Distance", distance);
        Double rawLight = distanceSensor.getRawLightDetected();

        telemetry.addData("Raw (Avg)", rollingAverage.appendItem(rawLight));
        telemetry.addData("Raw", rawLight);

        telemetry.addData("Useless Info", distanceSensor.getManufacturer());
        telemetry.addData("Raw Max", distanceSensor.getRawLightDetectedMax());
        telemetry.addData("Status", distanceSensor.status());
        telemetry.update();
        /*telemetry.addData("Color Value", lightSenor.getlightDetected());

        while (true) {

            correction = (PERFECT_COLOR_VALUE - lightSenor.getLightDetected())
        }*/
    }

    @Override
    public void stop() {

    }


}