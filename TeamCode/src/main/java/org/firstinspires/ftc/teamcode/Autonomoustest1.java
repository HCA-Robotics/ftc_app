package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tandi User on 4/24/2017.
 */
@Autonomous(name = "Autonomoustest1", group = "HCA Opmode")
public class Autonomoustest1 extends BaseOpMode {


    List<Task> tasks = new ArrayList<Task>();

    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        tasks.add(new DriveForwardTask(10, 0.5));

        for (Task t : tasks) {
            t.init(hardwareMap);
        }
    }

    boolean done = false;
    boolean hasstarted = false;

    @Override
    public void loop() {
        for (Task t : tasks){
            t.loop();
            if(t.isTaskDone()){
                tasks.remove(t);
            }

        }
    }

    @Override
    public void stop() {

        telemetry.addData("Message", "Done");

    }

}



