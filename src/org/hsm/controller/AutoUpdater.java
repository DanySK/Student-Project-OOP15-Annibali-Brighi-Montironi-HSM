package org.hsm.controller;

/**
 *
 * Automaticclay update plnats values every n seconds.
 *
 */
public class AutoUpdater extends Thread {

    private static final int MS_TO_S = 1000;

    private final int time;

    /**
     *
     * @param time
     *            refresh rate
     */
    public AutoUpdater(final int time) {
        this.time = time;
    }

    @Override
    public void run() {
        super.run();

        // TODO

        try {
            Thread.sleep(this.time * MS_TO_S);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
