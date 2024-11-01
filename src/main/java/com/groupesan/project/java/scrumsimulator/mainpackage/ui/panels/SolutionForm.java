package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SolutionFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SolutionStore;
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

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String title = titleField.getText();
                        String desc = descArea.getText();
                        if(title.isEmpty() || desc.isEmpty()){
                            Object[] message = {
                                    " Title and Description cannot be empty ",
                            };

                            // Show a dialog with the JTextField containing the Simulation ID
                            JOptionPane.showMessageDialog(
                                    SolutionForm.this,
                                    message,
                                    "Error",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            mySolution = SolutionFactory.getSolutionFactory().createNewSolution(title, desc);
                            SolutionStore.getInstance().addSolution(mySolution);
                            Object[] message = {
                                    " Solution added successfully",
                            };

                            // Show a dialog with the JTextField containing the Simulation ID
                            JOptionPane.showMessageDialog(
                                    SolutionForm.this,
                                    message,
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                    }
                }
        );
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
