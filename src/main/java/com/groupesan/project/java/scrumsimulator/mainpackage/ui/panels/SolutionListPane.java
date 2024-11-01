package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SolutionWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class SolutionListPane extends JFrame implements BaseComponent {
    private Player player;

    public SolutionListPane(Player player) {
        this.player = player;
        this.init();
    }
    private List<SolutionWidget> widgets = new ArrayList<>();
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

        JScrollPane scrollPane = new JScrollPane(subPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        refreshSolutionList();

        myJpanel.add(
                scrollPane,
                new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH)
        );

        JButton newSolutionButton = new JButton("Add solution");
        newSolutionButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SolutionForm form = new SolutionForm();
                        form.setVisible(true);
                        form.addWindowListener(
                                new WindowAdapter() {
                                    @Override
                                    public void windowClosed(WindowEvent e) {
                                        refreshSolutionList();
                                    }
                                }
                        );
                    }
                }
        );

        myJpanel.add(
                newSolutionButton,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL)
        );

        JButton probabilities_Button = new JButton("Fine-tune Probabilities");
        if (player.getRole().getName().equals(Roles.PRODUCT_OWNER.getDisplayName())) {
            probabilities_Button.setEnabled(false);
        }
        probabilities_Button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SolutionsProbabilityPane form = new SolutionsProbabilityPane();
                        form.setVisible(true);
                    }
                }
        );

        myJpanel.add(
                probabilities_Button,
                new CustomConstraints(0, 2, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(myJpanel);
    }

    private void refreshSolutionList(){
        subPanel.removeAll();
        widgets.clear();

        List<Solution> solutions = SolutionStore.getInstance().getSolutions();
        for(int i=0; i<solutions.size(); i++){
            SolutionWidget widget = new SolutionWidget(solutions.get(i));
            widgets.add(widget);
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i+1,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        subPanel.revalidate();
        subPanel.repaint();
    }
}
