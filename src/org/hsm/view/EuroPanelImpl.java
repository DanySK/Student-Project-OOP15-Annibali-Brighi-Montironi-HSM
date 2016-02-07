package org.hsm.view;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *A specific JPanel for euros.
 *
 */
public class EuroPanelImpl implements EuroPanel {

        private static final int MAX_COST = 1000000;
        private static final int MAX_CENT = 99;
        private static final int CENT_FACTOR = 100;
        private final JPanel panel;
        private final JSpinner euros;
        private final JSpinner cents;

        /**
         *Create the euro panel.
         */
        public EuroPanelImpl() {
            this.panel = new JPanel();
            this.euros = new JSpinner(new SpinnerNumberModel(0, 0, MAX_COST, 1));
            this.cents = new JSpinner(new SpinnerNumberModel(0, 0, MAX_CENT, 1));
            this.panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            final JLabel comma = new JLabel(", ");
            this.panel.add(this.euros);
            this.panel.add(comma);
            this.panel.add(this.cents);
        }

        @Override
        public int getValue() {
            final int euro = ((SpinnerNumberModel) this.euros.getModel()).getNumber().intValue() * CENT_FACTOR;
            final int cent = ((SpinnerNumberModel) this.cents.getModel()).getNumber().intValue();
            return (euro + cent);
        }

        @Override
        public JComponent getComponent() {
            return this.panel;
        }

}
