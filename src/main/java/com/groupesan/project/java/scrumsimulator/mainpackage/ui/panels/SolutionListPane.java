package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Roles;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SolutionWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

public class SolutionListPane extends JFrame {
    private Player player;
    private JPanel subPanel = new JPanel();
    private JPanel headerPanel = new JPanel();
    private List<SolutionWidget> widgets = new ArrayList<>();
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

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create and add headers only once
        headerPanel.setLayout(new GridBagLayout());
        addHeaders();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Set up subPanel for solution list items
        subPanel.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(subPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        refreshSolutionList();


        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Adding buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton newSolutionButton = new JButton("Add solution");
        if (player.getRole().getName().equals(Roles.SCRUM_ADMINISTRATOR.getDisplayName())) {
            newSolutionButton.setEnabled(false);
        }

        newSolutionButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void windowClosed(WindowEvent e) {
                        refreshSolutionList();
                    }
                });


        buttonPanel.add(newSolutionButton, new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        JButton probabilitiesButton = new JButton("Fine-tune Probabilities");
        probabilitiesButton.setEnabled(false);
        if (player.getRole().getName().equals(Roles.SCRUM_ADMINISTRATOR.getDisplayName())) {
            probabilitiesButton.setEnabled(true);
        }
        probabilitiesButton.addActionListener(e -> {
            SolutionsProbabilityPane form = new SolutionsProbabilityPane(simulationId);
            form.setVisible(true);
        });
        buttonPanel.add(probabilitiesButton, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void addHeaders() {
        JLabel idHeader = new JLabel("ID");
        JLabel titleHeader = new JLabel("Title");
        JLabel descHeader = new JLabel("Description");


        idHeader.setHorizontalAlignment(SwingConstants.CENTER);
        titleHeader.setHorizontalAlignment(SwingConstants.CENTER);
        descHeader.setHorizontalAlignment(SwingConstants.CENTER);


        headerPanel.add(idHeader, new CustomConstraints(0, 0, GridBagConstraints.CENTER, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(titleHeader, new CustomConstraints(1, 0, GridBagConstraints.CENTER, 0.3, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(descHeader, new CustomConstraints(2, 0, GridBagConstraints.CENTER, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
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
                            GridBagConstraints.HORIZONTAL)
            );
        }

        subPanel.revalidate();
        subPanel.repaint();
    }

    // Optional: method to access the list of widgets if needed for other UI updates or testing
    public List<SolutionWidget> getWidgets() {
        return widgets;
    }
}
