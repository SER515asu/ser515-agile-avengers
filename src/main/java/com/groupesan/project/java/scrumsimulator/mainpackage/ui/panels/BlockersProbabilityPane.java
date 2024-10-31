package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BlockersProbabilityPane extends JFrame implements BaseComponent {
    private List<Blocker> availableBlockers = new ArrayList<>();
    private List<Blocker> selectedBlockers = new ArrayList<>();
    private JComboBox<String> blockerJComboBox = new JComboBox<>();
    JPanel subPanel = new JPanel();

    public BlockersProbabilityPane(){
        this.availableBlockers = BlockerStore.getInstance().getAllBlockers();
        this.init();
    }

    public void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Fine-tune Blockers Probabilities");
        setSize(400, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        refreshBlockers();

        JLabel blockerSelectionLabel = new JLabel("Select Blocker:");
        myJpanel.add(
                blockerSelectionLabel,
                new CustomConstraints(0, 0, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(
                blockerJComboBox,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectCurrentBlocker();
                    }
                }
        );

        myJpanel.add(
                selectButton,
                new CustomConstraints(0, 2, GridBagConstraints.LINE_END, 0.2, 0.0, GridBagConstraints.NONE));

        JLabel selectedBlockersLabel = new JLabel("Selected Blockers:");
        myJpanel.add(
                selectedBlockersLabel,
                new CustomConstraints(0, 3, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Sub panel to show the list of selected blockers
        subPanel.setLayout(new GridBagLayout());

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(0, 4, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.BOTH));

        add(myJpanel);
    }

    private void refreshBlockers(){
        blockerJComboBox.removeAllItems();
        for(Blocker blocker: availableBlockers){
            blockerJComboBox.addItem(blocker.getName());
        }
    }

    private void selectCurrentBlocker(){
        int selectedBlockerIndex = blockerJComboBox.getSelectedIndex();
        Blocker selectedBlocker = availableBlockers.get(selectedBlockerIndex);
        selectedBlockers.add(selectedBlocker);
        subPanel.add(
                new JLabel(selectedBlocker.getName()),
                new CustomConstraints(0, selectedBlockers.size()-1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.NONE));
        availableBlockers.remove(selectedBlockerIndex);
        refreshBlockers();
    }
}
