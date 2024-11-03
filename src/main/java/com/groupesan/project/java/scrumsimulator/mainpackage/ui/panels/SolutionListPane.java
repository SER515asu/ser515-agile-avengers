package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
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
    private String simulationId;
    private List<SolutionWidget> widgets = new ArrayList<>();
    private JPanel subPanel = new JPanel();

    public SolutionListPane(Player player, String simulationId) {
        this.player = player;
        this.simulationId = simulationId;
        this.init();
    }

    public void init() {
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

        mainPanel.add(
                scrollPane,
                new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.BOTH)
        );

        JButton newSolutionButton = new JButton("Add Solution");
        newSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolutionForm form = new SolutionForm(simulationId);
                form.setVisible(true);
                form.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        refreshSolutionList();
                    }
                });
            }
        });

        mainPanel.add(
                newSolutionButton,
                new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL)
        );

        add(mainPanel);
    }

    private void refreshSolutionList() {
        subPanel.removeAll();
        widgets.clear();

        List<Solution> solutions = SolutionStore.getInstance(simulationId).getSolutions();
        for (int i = 0; i < solutions.size(); i++) {
            SolutionWidget widget = new SolutionWidget(solutions.get(i), simulationId);
            widgets.add(widget);
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i + 1,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        subPanel.revalidate();
        subPanel.repaint();
    }

    // Optional: method to access the list of widgets if needed for other UI updates or testing
    public List<SolutionWidget> getWidgets() {
        return widgets;
    }
}
