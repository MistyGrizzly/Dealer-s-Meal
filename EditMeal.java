// Author : John Whitmore
// Date Created : 02 / 18 / 2025
// Date Modified : 02 / 18 / 2025
// File Name : EditMeal.java
// Purpose : provides window for both adding a new meal, and editing a pre-existing meal

import javax.swing.*;
import javax.swing.text.StyledDocument;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;

public class EditMeal extends JFrame {

    private JPanel panel;
    private JPanel scrollPanel;
    private JLabel nameL, flavorL, textureL, typeL, prepL, ingrediantsL, recipeL;
    private JTextField nameTF, ingrediantsTF, prepTF;
    private JScrollPane recipeScroll;
    private JTextPane recipeP;
    private JComboBox<String> flavorM, textureM, typeM;
    private JCheckBox spicyC;
    private JButton addIngB, removeIngB, editMealB, backB;
    private String oldName;
    private final int HEIGHT = 500, WIDTH = 550;

    public EditMeal(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Create A New Meal");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        panelCreation();
        add(panel);
        scrollCreation();
        add(scrollPanel);
        add(Box.createHorizontalGlue());
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }

    public EditMeal(Meal edited){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit Meal");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        panelCreation(edited);
        add(panel);
        scrollCreation(edited);
        add(scrollPanel);
        add(Box.createHorizontalGlue());
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }

    private void panelCreation(){
        panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        ButtonListener button = new ButtonListener();

        backB = new JButton("Back");
        backB.addActionListener(button);
        backB.setActionCommand("back");
        panel.add(backB, BorderLayout.NORTH);

        nameL = new JLabel("Name");
        nameTF = new JTextField(20);

        flavorL = new JLabel("Flavor");
        flavorM = new JComboBox<>(DealersMeal.getFlavors());
        flavorM.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textureL = new JLabel("Texture");
        textureM = new JComboBox<>(DealersMeal.getTextures());
        textureM.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        typeL = new JLabel("Meal Type");
        typeM = new JComboBox<>(DealersMeal.getTypes());
        typeM.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        prepL = new JLabel("Prep Time");
        prepTF = new JTextField(5);

        spicyC = new JCheckBox("Spicy?");
        spicyC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ingrediantsL = new JLabel("Ingrediants");
        ingrediantsTF = new JTextField(10);

        addIngB = new JButton("Add");
        addIngB.addActionListener(button);
        addIngB.setActionCommand("add");

        removeIngB = new JButton("Remove");
        removeIngB.addActionListener(button);
        removeIngB.setActionCommand("remove");

        editMealB = new JButton("Create");
        editMealB.addActionListener(button);
        editMealB.setActionCommand("create");

        panel.setLayout(layout);
        setGroupLayout(layout);
    }

    private void panelCreation(Meal meal){
        panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        ButtonListener button = new ButtonListener();

        backB = new JButton("Back");
        backB.addActionListener(button);
        backB.setActionCommand("back");
        panel.add(backB, BorderLayout.NORTH);

        nameL = new JLabel("Name");
        nameTF = new JTextField(meal.getName());
        oldName = meal.getName();

        flavorL = new JLabel("Flavor");
        flavorM = new JComboBox<>(DealersMeal.getFlavors());
        flavorM.setSelectedItem(meal.getFlavor());
        flavorM.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textureL = new JLabel("Texture");
        textureM = new JComboBox<>(DealersMeal.getTextures());
        textureM.setSelectedItem(meal.getTexture());
        textureM.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        typeL = new JLabel("Meal Type");
        typeM = new JComboBox<>(DealersMeal.getTypes());
        typeM.setSelectedItem(meal.getMealType());
        typeM.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        prepL = new JLabel("Prep Time");
        prepTF = new JTextField(meal.getPrepTime());
        prepTF.setText(String.valueOf(meal.getPrepTime()));

        spicyC = new JCheckBox("Spicy?");
        spicyC.setSelected(meal.getSpicy());
        spicyC.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ingrediantsL = new JLabel("Ingrediants");
        ingrediantsTF = new JTextField(10);

        addIngB = new JButton("Add");
        addIngB.addActionListener(button);
        addIngB.setActionCommand("add");

        removeIngB = new JButton("Remove");
        removeIngB.addActionListener(button);
        removeIngB.setActionCommand("remove");

        editMealB = new JButton("Edit Meal");
        editMealB.addActionListener(button);
        editMealB.setActionCommand("edit");

        panel.setLayout(layout);
        setGroupLayout(layout);        
    }

    private void scrollCreation(){
        scrollPanel = new JPanel();
        GroupLayout layout = new GroupLayout(scrollPanel);

        recipeL = new JLabel("Recipe");

        recipeP = new JTextPane();
        StyledDocument doc = recipeP.getStyledDocument();
        recipeP.setStyledDocument(doc);
        recipeP.setEditable(true);

        recipeScroll = new JScrollPane(recipeP);
        recipeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recipeScroll.setMinimumSize(new Dimension(200, 300));
        panel.add(recipeScroll, BorderLayout.EAST);

        scrollPanel.setLayout(layout);
        setScrollLayout(layout);
    }

    private void scrollCreation(Meal edited){
        scrollPanel = new JPanel();
        GroupLayout layout = new GroupLayout(scrollPanel);

        recipeL = new JLabel("Recipe");

        recipeP = new JTextPane();
        recipeP.setText(edited.toString());
        StyledDocument doc = recipeP.getStyledDocument();
        recipeP.setStyledDocument(doc);
        recipeP.setEditable(true);

        recipeScroll = new JScrollPane(recipeP);
        recipeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recipeScroll.setMinimumSize(new Dimension(200, 300));
        panel.add(recipeScroll, BorderLayout.EAST);

        scrollPanel.setLayout(layout);
        setScrollLayout(layout);
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            switch(cmd){
                case "add":
                    break;
                case "remove":
                    break;
                case "edit":
                    Meal edited = new Meal();
                    edited.setName(nameTF.getText());
                    edited.setFlavor(flavorM.getSelectedItem().toString());
                    edited.setTexture(textureM.getSelectedItem().toString());
                    edited.setType(typeM.getSelectedItem().toString());
                    edited.setPrepTime(tryParse(prepTF.getText()));
                    edited.setSpicy(spicyC.isSelected());
                    edited.setRecipe(recipeP.getText());
                    DealersMeal.addMeal(edited, oldName);

                    new SearchWindow().setVisible(true);
                    dispose();
                    break;
                case "create":
                    Meal created = new Meal();
                    created.setName(nameTF.getText());
                    created.setFlavor(flavorM.getSelectedItem().toString());
                    created.setTexture(textureM.getSelectedItem().toString());
                    created.setType(typeM.getSelectedItem().toString());
                    created.setPrepTime(tryParse(prepTF.getText()));
                    created.setSpicy(spicyC.isSelected());
                    created.setRecipe(recipeP.getText());
                    DealersMeal.addMeal(created, created.getName());

                    new SearchWindow().setVisible(true);
                    dispose();
                    break;
                case "back":
                    new SearchWindow().setVisible(true);
                    dispose();
                    break;
            }
        }
    }

    private void setGroupLayout(GroupLayout layout){
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        try {
            layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backB))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameL)
                        .addComponent(nameTF))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(flavorL)
                        .addComponent(flavorM))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textureL)
                        .addComponent(textureM))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(typeL)
                        .addComponent(typeM))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(prepL)
                        .addComponent(prepTF))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(spicyC))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(ingrediantsL)
                        .addComponent(ingrediantsTF))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addIngB)
                        .addComponent(removeIngB))
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(editMealB))
            );
            layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backB)
                        .addComponent(nameL)
                        .addComponent(flavorL)
                        .addComponent(textureL)
                        .addComponent(typeL)
                        .addComponent(prepL)
                        .addComponent(ingrediantsL)
                        .addComponent(addIngB)
                        .addComponent(editMealB))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameTF)
                        .addComponent(flavorM)
                        .addComponent(textureM)
                        .addComponent(typeM)
                        .addComponent(prepTF)
                        .addComponent(spicyC)
                        .addComponent(ingrediantsTF)
                        .addComponent(removeIngB))
            );
        } catch (IllegalArgumentException iae){
            System.err.println("Group is null");
        } catch (IllegalStateException ise){
            System.err.println("Illegal State");
        }
    }

    private void setScrollLayout(GroupLayout layout){
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        try {
            layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(recipeL)
                    .addComponent(recipeScroll)
            );
            layout.setHorizontalGroup(
                layout.createParallelGroup()
                    .addComponent(recipeL)
                    .addComponent(recipeScroll)  
            );
        } catch (IllegalArgumentException iae){
            System.err.println(iae);
        } catch (IllegalStateException ise){
            System.err.println(ise);
        }
    }

    private Integer tryParse(String text){
        try{
            return Integer.parseInt(text);
        } catch (NumberFormatException nfe){
            prepTF.setText("Must be Valid Number");
            return null;
        }
    }
}
