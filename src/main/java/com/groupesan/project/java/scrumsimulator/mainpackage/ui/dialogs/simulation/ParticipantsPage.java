package com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.*;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.GridBagConstraintsBuilder;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.CreateEditMode;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.DataList;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.ResuableHeader;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.Wizard;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class ParticipantsPage extends Wizard.WizardPage {

    private final DataModel<List<Player>> users;
    private final DataModel<List<ScrumRole>> roles;

    public ParticipantsPage(DataModel<List<Player>> users, DataModel<List<ScrumRole>> roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    protected String getId() {
        return "Participants";
    }

    @Override
    protected JPanel render() {
        JPanel container = new JPanel(new BorderLayout());

        ResuableHeader resuableHeader =
                new ResuableHeader("Participants", "Add participants to your simulation.");
        DataList<Player> list = new DataList<>(this.users);
        for (int i = 0; i < roles.getData().size(); i++) {
            list.addItem(new Player("Player " + i, roles.getData().get(i)));
        }
        JScrollPane rolesPane = new JScrollPane(list);

        JPanel controls = new JPanel();
        controls.setAlignmentX(LEFT_ALIGNMENT);
        BoxLayout controlsLayout = new BoxLayout(controls, BoxLayout.X_AXIS);
        controls.setLayout(controlsLayout);

        JButton create = new JButton("Create");
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");

        create.addActionListener(
                l ->
                        new UserCreateEditDialog(
                                        CreateEditMode.CREATE,
                                        "User",
                                        null,
                                        roles.getData().toArray(new ScrumRole[0]),
                                        list::addItem)
                                .setVisible(true));

        edit.addActionListener(
                l -> {
                    int selectedIndex = list.getSelectedIndex();
                    Player selectedUser = list.getModel().getElementAt(selectedIndex);
                    new UserCreateEditDialog(
                                    CreateEditMode.EDIT,
                                    "User",
                                    selectedUser,
                                    roles.getData().toArray(new ScrumRole[0]),
                                    data -> {
                                        System.out.println(data);
                                        list.editItem(selectedIndex, data);
                                    })
                            .setVisible(true);
                });

        delete.addActionListener(l -> list.removeSelectedItem());

        controls.add(create);
        controls.add(Box.createHorizontalStrut(5));
        controls.add(edit);
        controls.add(Box.createHorizontalStrut(5));
        controls.add(delete);

        JPanel inputs = new JPanel(new GridBagLayout());
        inputs.add(
                resuableHeader,
                new GridBagConstraintsBuilder()
                        .setGridX(0)
                        .setGridY(0)
                        .setWeightX(1)
                        .setFill(GridBagConstraints.HORIZONTAL)
                        .setInsets(new Insets(0, 0, 5, 0)));
        inputs.add(
                controls,
                new GridBagConstraintsBuilder()
                        .setGridX(0)
                        .setGridY(1)
                        .setFill(GridBagConstraints.HORIZONTAL)
                        .setInsets(new Insets(0, 0, 5, 0)));
        inputs.add(
                rolesPane,
                new GridBagConstraintsBuilder()
                        .setGridX(0)
                        .setGridY(2)
                        .setFill(GridBagConstraints.BOTH)
                        .setWeightY(1.0)
                        .setWeightX(1.0));

        container.add(inputs, BorderLayout.CENTER);

        return container;
    }
}
