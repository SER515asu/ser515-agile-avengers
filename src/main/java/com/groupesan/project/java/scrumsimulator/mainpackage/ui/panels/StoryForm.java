package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StoryForm extends JFrame implements BaseComponent {

    Double[] pointsList = {1.0, 2.0, 3.0, 5.0, 8.0, 11.0, 19.0, 30.0, 49.0};
    boolean isNewStory;
    private JTextField nameField;
    private JTextArea descArea;
    private JComboBox<Double> pointsCombo;
    private JComboBox<Double> bvCombo;
    private boolean isValidForm;

    public StoryForm(UserStory userStory) {
        this.userStory = userStory;
        this.isNewStory = false;
        this.init();
    }
    public StoryForm() {
        this.isNewStory = true;
        this.isValidForm = false;
        this.init();
    }

    private UserStory userStory;

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(this.isNewStory? "New User Story":("Edit User Story " + userStory.getId().toString()));
        setSize(400, 300);
        nameField = new JTextField();
        descArea = new JTextArea();
        pointsCombo = new JComboBox<>(pointsList);
        bvCombo = new JComboBox<>(pointsList);
        if(!this.isNewStory){
            pointsCombo.setSelectedItem(userStory.getPointValue());
            bvCombo.setSelectedItem(userStory.getBusinessValue());
            nameField = new JTextField(userStory.getName());
            descArea = new JTextArea(userStory.getDescription());
        } else {
            nameField = new JTextField();
            descArea = new JTextArea();
        }
        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();

        setLayout(myBorderLayout);

        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
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

        JLabel pointsLabel = new JLabel("Points:");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                pointsCombo,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel businessValueLabel = new JLabel("Business Value:");
        myJpanel.add(
                businessValueLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                bvCombo,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Submit");

        if(this.isNewStory){
            submitButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();
                            String description = descArea.getText();
                            isValidForm = (!name.isBlank() && !description.isBlank());
                            dispose();
                        }
                    });
        } else {
            submitButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            userStory.setName(nameField.getText());
                            userStory.setDescription(descArea.getText());
                            userStory.setPointValue((Double)pointsCombo.getSelectedItem());
                            userStory.setBusinessValue((Double)bvCombo.getSelectedItem());
                            dispose();
                        }
                    });
        }

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 4, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 4, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public UserStory getUserStoryObject() {
        if(!isValidForm){
            return null;
        }
        String name = nameField.getText();
        String description = descArea.getText();
        Double points = (Double) pointsCombo.getSelectedItem();
        Double businessValue = (Double) bvCombo.getSelectedItem();

        UserStoryFactory userStoryFactory = UserStoryFactory.getInstance();

        UserStory userStory = userStoryFactory.createNewUserStory(name, description, points, businessValue);

        userStory.doRegister();

        return userStory;
    }
}
