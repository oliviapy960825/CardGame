package edu.udel.dianali.gameframework;

import android.os.Handler;
import android.view.View;

/**
 * Created by jatlas on 4/4/17.
 */

public class ModelUpdateController implements Runnable {
    private Handler handler;
    private long lastUpdate;
    private long updateTimeMillis;

    private Model model;
    private View view;
    private float modelTimePerSecond;

    private boolean active;

    public ModelUpdateController(Model model, View view, long updateTimeMillis, float modelTimePerSecond) {
        this.model = model;
        this.view = view;
        this.modelTimePerSecond = modelTimePerSecond;

        this.handler = new Handler();
        this.updateTimeMillis = updateTimeMillis;

    }

    public float getModelTimePerSecond() {
        return modelTimePerSecond;
    }

    public void setModelTimePerSecond(float modelTimePerSecond) {
        this.modelTimePerSecond = modelTimePerSecond;
    }

    @Override
    public void run() {
        if (active) { // possible to stop the updates between postDelayed and run
            long elapsed = System.currentTimeMillis() - lastUpdate;
            model.update(modelTimePerSecond * elapsed / 1000f);
            view.invalidate();
            next();
        }
    }

    private void next() {
        lastUpdate = System.currentTimeMillis();

        handler.postDelayed(this, updateTimeMillis);
    }

    public void start() {
        active = true;
        next();
    }

    public void stop() {
        active = false;
    }
}
