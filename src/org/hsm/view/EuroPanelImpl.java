package org.hsm.view;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *A specific JPanel for euros.
 *
 */
public class EuroPanelImpl extends JPanel implements EuroPanel {

        private static final long serialVersionUID = 3071776183164860039L;
        private static final int MAX_COST = 1000000;
        private static final int MAX_CENT = 99;
        private static final int CENT_FACTOR = 100;
        private final JSpinner euros;
        private final JSpinner cents;

        /**
         *Create the euro panel.
         */
        public EuroPanelImpl() {
            this.euros = new JSpinner(new SpinnerNumberModel(0, 0, MAX_COST, 1));
            this.cents = new JSpinner(new SpinnerNumberModel(0, 0, MAX_CENT, 1));
            this.setLayout(new FlowLayout());
            final JLabel comma = new JLabel(", ");
            this.add(this.euros);
            this.add(comma);
            this.add(this.cents);
        }

        @Override
        public int getValue() {
            final int euro = ((SpinnerNumberModel) this.euros.getModel()).getNumber().intValue() * CENT_FACTOR;
            final int cent = ((SpinnerNumberModel) this.cents.getModel()).getNumber().intValue();
            return (euro + cent);
        }

}
