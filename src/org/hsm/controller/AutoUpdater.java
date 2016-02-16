package org.hsm.controller;

/**
 *
 * Automatically update the plants values every n seconds.
 *
 */
public class AutoUpdater extends Thread {

    private static final int MS_TO_S = 1000;

    private final Controller controller = ControllerImpl.getController();
    private final Simulator simulator = new SimulatorImpl();

    private int time = 1;
    private volatile boolean stopped;

    /**
     * Create a thread called Auto_Updater_Thread.
     */
    public AutoUpdater() {
        super("Auto_Updater_Thread");
    }

    /**
     * Set refresh rate.
     *
     * @param time
     *            the refresh rate in seconds
     */
    public void setTime(final int time) {
        this.time = time;
    }

    @Override
    public void run() {
        this.stopped = false;
        while (!this.stopped) {
            this.controller.getView().cleanGreenhouse();

            this.controller.getGreenhouse().getPlants().forEach((a, b) -> {
                final double ph = simulator.getSimulatedPh(b);
                final double bright = simulator.getSimulatedBrightness(b);
                final double cond = simulator.getSimulatedConductibility(b);
                final double temp = simulator.getSimulatedTemperature(b);
                this.controller.getView().insertNewPlant(a, b.getModel().getName(), b.getCost() / 100, ph, bright, cond,
                        temp);
                b.addPhValue(ph);
                b.addBrightValue(bright);
                b.addConductValue(cond);
                b.addTempValue(temp);
            });

            try {
                Thread.sleep(this.time * MS_TO_S);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        this.stopped = true;
    }

}
