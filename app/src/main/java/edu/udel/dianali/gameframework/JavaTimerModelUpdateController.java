package edu.udel.dianali.gameframework;

import java.util.Timer;
import java.util.TimerTask;

public class JavaTimerModelUpdateController {
    class UpdateTask extends TimerTask {
        public void run() {
            if (active) { // possible to stop the updates between postDelayed and run
                long elapsed = System.currentTimeMillis() - lastUpdate;
                model.update(modelTimePerSecond * elapsed / 1000f);
                next();
            }
        }
    }

    private Timer timer;
    long lastUpdate;
    long updateTimeMillis;

    Model model;
    private float modelTimePerSecond;

    boolean active;

    public JavaTimerModelUpdateController(Model model, long updateTimeMillis, float modelTimePerSecond) {
        this.model = model;
        this.modelTimePerSecond = modelTimePerSecond;

        this.timer = new Timer();
        this.updateTimeMillis = updateTimeMillis;
    }

    public float getModelTimePerSecond() {
        return modelTimePerSecond;
    }

    public void setModelTimePerSecond(float modelTimePerSecond) {
        this.modelTimePerSecond = modelTimePerSecond;
    }

    private void next() {
        lastUpdate = System.currentTimeMillis();
        timer.schedule(new UpdateTask(), updateTimeMillis);
    }

    public void start() {
        active = true;
        next();
    }

    public void stop() {
        active = false;
        timer.cancel();
    }

}
