package controller;

import java.util.Timer;
import java.util.TimerTask;

public class GhostTimer {

    private Timer timer;
    private GhostController ghostController;

    public GhostTimer(GhostController ghostController) {
        this.timer = new Timer();
        this.ghostController = ghostController;
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            ghostController.getRandomDirection();
        }
    };

    public void start(){

    }
}
