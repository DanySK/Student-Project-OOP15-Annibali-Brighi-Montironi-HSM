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

    private final int time;
    private volatile boolean stopped;

    /**
     *
     * @param time
     *            refresh rate
     */
    public AutoUpdater(final int time) {
        super("Auto_Updater_Thread");
        this.time = time;
    }

    @Override
    public void run() {
        this.stopped = false;
        while (!this.stopped) {
            this.controller.getView().cleanGreenhouse();

            this.controller.getGreenhouse().getPlants().forEach((a, b) -> {
                this.controller.getView().insertPlant(a, b.getModel().getName(), 1,
                        this.simulator.getSimulatedPh(b.getModel().getBotanicalName()),
                        this.simulator.getSimulatedBrightness(b.getModel().getBotanicalName()),
                        this.simulator.getSimulatedConductibility(b.getModel().getBotanicalName()),
                        this.simulator.getSimulatedTemperature(b.getModel().getBotanicalName()));
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
        super.interrupt();
        this.stopped = true;
    }

}
