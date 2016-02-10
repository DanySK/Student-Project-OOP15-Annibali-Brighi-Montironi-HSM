package org.hsm.view.gui;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.dialog.GreenhouseCreateDialog;
import org.hsm.view.dialog.PlantAddDialog;
import org.hsm.view.dialog.PlantCreateDialog;
import org.hsm.view.utility.Utilities;

/**
 *The MenuBar component for the main frame.
 *
 */
public class MenuBar implements GUIComponent {

    private final JMenuBar bar;
    private final JMenu edit;
    private final JMenu chart;
    private final JMenuItem saveGreenhouse;
    private final JMenuItem removeGreenhouse;
    private final JMenuItem newDatabase;
    private final JMenuItem importDatabase;
    private final JMenuItem exportDatabase;

    /**
     * Create the MenuBar.
     * @param frame the MainFrame for the MenuBar
     */
    public MenuBar(final JFrame frame) {
        //Initialization
        this.bar = new JMenuBar();
        //Menu creation
        final JMenu file = new JMenu("File");
        this.edit = new JMenu("Edit");
        this.chart = new JMenu("Charts");
        final JMenu information = new JMenu("Information");
        final JMenu help = new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);
        edit.setMnemonic(KeyEvent.VK_E);
        chart.setMnemonic(KeyEvent.VK_C);
        information.setMnemonic(KeyEvent.VK_I);
        help.setMnemonic(KeyEvent.VK_H);
        //Menù File Item
        final JMenuItem newGreenhouse = new JMenuItem("New Greenhouse");
        newGreenhouse.addActionListener(e -> new GreenhouseCreateDialog(frame).start());
        file.add(newGreenhouse);
        final JMenuItem loadGreenhouse = new JMenuItem("Open Greenhouse");
        loadGreenhouse.addActionListener(e -> ControllerImpl.getController().loadGreenhouse());
        file.add(loadGreenhouse);
        this.saveGreenhouse = new JMenuItem("Save Greenhouse");
        saveGreenhouse.addActionListener(e -> ControllerImpl.getController().saveGreenhouse());
        file.add(saveGreenhouse);
        this.removeGreenhouse = new JMenuItem("Remove Greenhouse");
        removeGreenhouse.addActionListener(e -> ControllerImpl.getController().deleteGreenhouse());
        file.add(removeGreenhouse);
        file.addSeparator();
        this.newDatabase = new JMenuItem("New Database");
        this.newDatabase.addActionListener(e -> ControllerImpl.getController().newDatabase());
        file.add(newDatabase);
        this.importDatabase = new JMenuItem("Import Database");
        importDatabase.addActionListener(e -> ControllerImpl.getController().loadDatabase());
        file.add(importDatabase);
        this.exportDatabase = new JMenuItem("Export Database");
        exportDatabase.addActionListener(e -> ControllerImpl.getController().saveDatabase());
        file.add(exportDatabase);
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> ControllerImpl.getController().exit());
        file.addSeparator();
        file.add(exit);
        //Menu Edit Item
        final JMenuItem addPlant = new JMenuItem("Add Plant");
        addPlant.addActionListener(e -> {
            if (ControllerImpl.getController().isDbEmpty()) {
                Utilities.errorMessage(frame, "The Database is empty or not loaded");
            } else {
                new PlantAddDialog(frame).start();
            }
        });
        edit.add(addPlant);
        final JMenuItem removePlant = new JMenuItem("Remove plant from Greenhouse");
        removePlant.addActionListener(e -> ControllerImpl.getController().delPlant());
        edit.add(removePlant);
        edit.addSeparator();
        final JMenuItem addModel = new JMenuItem("Create Plant");
        addModel.addActionListener(e -> new PlantCreateDialog(frame).start());
        edit.add(addModel);
        final JMenuItem removeDBPlant = new JMenuItem("Remove plant from Database");
        removeDBPlant.addActionListener(e -> ControllerImpl.getController().deleteDbPlant());
        edit.add(removeDBPlant);
        //Menu Chart Item
        final JMenuItem brightnessBarChart = new JMenuItem("Show Brightness Bar Chart");
        chart.add(brightnessBarChart);
        brightnessBarChart.addActionListener(e -> ControllerImpl.getController().showBrightnessBarChart());
        final JMenuItem phBarChart = new JMenuItem("Show Basicity Bar Chart");
        chart.add(phBarChart);
        phBarChart.addActionListener(e -> ControllerImpl.getController().showPhBarChart());
        final JMenuItem temperatureBarChart = new JMenuItem("Show Temperature Bar Chart");
        chart.add(temperatureBarChart);
        temperatureBarChart.addActionListener(e -> ControllerImpl.getController().showTemperatureBarChart());
        final JMenuItem conductivityBarChart = new JMenuItem("Show Conductivity Bar Chart");
        chart.add(conductivityBarChart);
        conductivityBarChart.addActionListener(e -> ControllerImpl.getController().showConductivityBarChart());
        chart.addSeparator();
        final JMenuItem brightnessLineChart = new JMenuItem("Show Brightness Line Chart");
        chart.add(brightnessLineChart);
        final JMenuItem phLineChart = new JMenuItem("Show Basicity Line Chart");
        chart.add(phLineChart);
        final JMenuItem temperatureLineChart = new JMenuItem("Show Temperature Line Chart");
        chart.add(temperatureLineChart);
        final JMenuItem conductivityLineChart = new JMenuItem("Show Conductivity Line Chart");
        chart.add(conductivityLineChart);
        //Menu Help Item
        final JMenuItem about = new JMenuItem("About Hydroponic System Manager");
        help.add(about);
        //Add menus in MenuBar
        bar.add(file);
        bar.add(edit);
        bar.add(chart);
        bar.add(information);
        bar.add(help);
    }

    /**
     * Set the state of the Edit commands in the Menù.
     * @param state the state of the commands
     */
    public void setEditCommands(final boolean state) {
        this.edit.setEnabled(state);
        this.exportDatabase.setEnabled(state);
        this.importDatabase.setEnabled(state);
        this.saveGreenhouse.setEnabled(state);
        this.newDatabase.setEnabled(state);
        this.removeGreenhouse.setEnabled(state);
        this.chart.setEnabled(state);
    }

    @Override
    public JComponent getComponent() {
        return this.bar;
    }

}
