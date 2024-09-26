package com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.GridBagConstraintsBuilder;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.CreateEditDialog;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.CreateEditDialogHandler;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.CreateEditMode;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.TextInput;
import java.awt.*;
import javax.swing.*;

public class RoleCreateEditDialog extends CreateEditDialog<String> {
    private final DataModel<String> text;

    public RoleCreateEditDialog(
            CreateEditMode mode,
            String title,
            String roleName,
            CreateEditDialogHandler<String> handler) {
        super(mode, title, roleName, handler);
        this.text = new DataModel<>(roleName);
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
        TextInput roleInput = new TextInput("Role Name: ", new JTextField(text.getData()), text);
        inputs.add(
                roleInput,
                new GridBagConstraintsBuilder()
                        .setGridX(0)
                        .setGridY(0)
                        .setWeightX(1)
                        .setFill(GridBagConstraints.HORIZONTAL)
                        .setInsets(new Insets(0, 0, 5, 0)));

        container.add(inputs, BorderLayout.NORTH);
        return container;
    }

    @Override
    protected void triggerSubmitHandler() {
        handler.onSubmit(text.getData());
    }
}
