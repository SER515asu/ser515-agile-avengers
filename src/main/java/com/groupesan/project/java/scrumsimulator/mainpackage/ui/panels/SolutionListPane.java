package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SolutionListPane extends JFrame implements BaseComponent {
    private Player player;

    public SolutionListPane(Player player) {
        this.player = player;
        this.init();
    }
//    private List<UserStoryWidget> widgets = new ArrayList<>();
    private JPanel subPanel = new JPanel();

    public void init(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("List of Solutions");
        setSize(500, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        subPanel.setLayout(new GridBagLayout());

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH)
        );

        JButton newSolutionButton = new JButton("Add solution");
        newSolutionButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SolutionForm form = new SolutionForm();
                        form.setVisible(true);
                    }
                }
        );

        myJpanel.add(
                newSolutionButton,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL)
        );

        add(myJpanel);
    }
}
