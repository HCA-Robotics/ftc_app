package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This Interface models an asynchronous task.
 */
public interface Task {
    /**
     * init starts the task
     * @param hardwareMap map to robot components
     */
    void init(HardwareMap hardwareMap);

    /**
     * loop does the task
     */
    void loop();

    /**
     *Answers the question, is Task Done
     *
     * @return true if task is done, false otherwise
     */
    boolean isTaskDone();

    /**
     * Answers the question, has the task started
     *
     * @return true if has started, false otherwise
     */
    boolean hasStarted();
}
