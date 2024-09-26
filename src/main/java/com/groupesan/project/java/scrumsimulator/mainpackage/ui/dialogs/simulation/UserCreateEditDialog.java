package com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.User;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.GridBagConstraintsBuilder;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.*;
import java.awt.*;
import javax.swing.*;

public class UserCreateEditDialog extends CreateEditDialog<Player> {
    private final transient DataModel<String> username;
    private final transient DataModel<ScrumRole> role;
    private final transient ScrumRole[] scrumRoles;

    // NOTE: This means user won't get serialized. We will need to revisit this later when we do
    // serialization.
    private final transient User user;

    public UserCreateEditDialog(
            CreateEditMode mode,
            String title,
            Player user,
            ScrumRole[] scrumRoles,
            CreateEditDialogHandler<Player> handler) {
        super(mode, title, user, handler);
        this.user = user;
        username = new DataModel<>(user == null ? "" : user.getName());
        role = new DataModel<>(user == null ? null : user.getRole());
        this.scrumRoles = scrumRoles;
        this.init();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected JPanel render() {
        JPanel container = new JPanel(new BorderLayout());

        JPanel inputs = new JPanel(new GridBagLayout());
        TextInput usernameInput =
                new TextInput("Name: ", new JTextField(username.getData()), username);
        JComboBox<ScrumRole> roleComboBox = new JComboBox<>(this.scrumRoles);

        if (user == null) {
            roleComboBox.setSelectedItem(null);
        } else {
            roleComboBox.setSelectedItem(user.getRole());
        }

        Dropdown<ScrumRole> roleDropdown = new Dropdown<>("Role: ", roleComboBox, role);

        inputs.add(
                usernameInput,
                new GridBagConstraintsBuilder()
                        .setGridX(0)
                        .setGridY(0)
                        .setWeightX(1)
                        .setFill(GridBagConstraints.HORIZONTAL)
                        .setInsets(new Insets(0, 0, 5, 0)));
        inputs.add(
                roleDropdown,
                new GridBagConstraintsBuilder()
                        .setGridX(0)
                        .setGridY(1)
                        .setWeightX(1)
                        .setFill(GridBagConstraints.HORIZONTAL)
                        .setInsets(new Insets(0, 0, 5, 0)));

        container.add(inputs, BorderLayout.NORTH);
        return container;
    }

    @Override
    protected void triggerSubmitHandler() {
        handler.onSubmit(new Player(username.getData(), role.getData()));
    }
}
