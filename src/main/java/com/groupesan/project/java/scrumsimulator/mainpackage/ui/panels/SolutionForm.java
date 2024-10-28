package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionForm extends JFrame implements BaseComponent {

    private boolean editMode;

    public SolutionForm(){
        this.editMode = false;
        this.init();
    }

    // Constructor for edit mode
    public SolutionForm(Solution solution){
        this.editMode = true;
        this.mySolution = solution;
        this.init();
    }

    private Solution mySolution;

    JTextField titleField = new JTextField();
    JTextArea descArea = new JTextArea();
    // Create JComboBox for blocker selection
    JComboBox<Blocker> blockersBox = new JComboBox<>();

    public void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Add Solution");
        if(editMode){
            setTitle("Edit Solution");
        }
        setSize(400, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        JLabel titleLabel = new JLabel("Title:");
        myJpanel.add(
                titleLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                titleField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel blockerSelectionLabel = new JLabel("Blocker:");
        myJpanel.add(
                blockerSelectionLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                blockersBox,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton submitButton = new JButton("Submit");
        myJpanel.add(
                submitButton,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, GridBagConstraints.NONE));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );
        myJpanel.add(
                cancelButton,
                new CustomConstraints(
                        0, 3, GridBagConstraints.EAST, GridBagConstraints.NONE));


        add(myJpanel);
    }
}
