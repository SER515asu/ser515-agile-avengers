package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class CreateEditDialog<T> extends JDialog implements BaseComponent {
    protected final CreateEditMode mode;
    private final String title;
    protected final CreateEditDialogHandler<T> handler;
    protected final T data;

    public CreateEditDialog(
            CreateEditMode mode, String title, T data, CreateEditDialogHandler<T> handler) {
        this.mode = mode;
        this.title = title;
        this.handler = handler;
        this.data = data;
    }

    @Override
    public void init() {
        setModal(true);
        setTitle(this.mode.toString() + " " + this.title);

        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel footer = renderFooter();

        container.add(this.render(), BorderLayout.CENTER);
        container.add(footer, BorderLayout.SOUTH);
        add(container);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel renderFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        JPanel buttonsPanel = new JPanel();

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(l -> dispose());

        JButton confirm =
                new JButton(
                        this.mode.equals(CreateEditMode.CREATE) ? this.mode.toString() : "Save");
        confirm.addActionListener(
                l -> {
                    this.triggerSubmitHandler();
                    dispose();
                });

        buttonsPanel.add(cancel);
        buttonsPanel.add(confirm);
        footer.add(buttonsPanel, BorderLayout.EAST);

        return footer;
    }

    protected abstract JPanel render();

    protected abstract void triggerSubmitHandler();
}
