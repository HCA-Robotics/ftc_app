package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robo User on 4/28/2018.
 */
@Autonomous(name = "Drive", group = "HCA Opmode")
public class WheeOp extends BaseOpMode {

    private List<Task> tasks = new ArrayList<>();

    public void init() {
        tasks.add(new DriveForwardTask(10,.35));
    }

    public void loop(){
        for(Task t : tasks){
            if(!t.hasStarted()) {
                t.init(hardwareMap);
            }
            t.loop();

            if(t.isTaskDone()){
                tasks.remove(t);
            }
            telemetry.addData("Message", "Done");
        }
    }
    
}