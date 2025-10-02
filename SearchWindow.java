// Author : John Whitmore
// Date Created : 02 / 12 / 2025
// Date Modified : 03 / 11 / 2025
// File Name : SearchWindow.java
// Purpose : provides main program window with options for meal search, pantry, and add meal

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;



public class SearchWindow extends JFrame{
    // fields
    private String title = "Dealer's Meal";
    private JPanel panel;
    private JLabel nameLabel, flavorLabel, textureLabel, typeLabel, prepLabel;
    private JTextField nameSearch, prepTime;
    private JComboBox<String> flavorMenu, textureMenu, typeMenu;
    private JCheckBox spicyCheck, useOwned;
    private JButton searchButton, pantryButton, addMealButton;
    private final int HEIGHT = 500, WIDTH = 550;

    public SearchWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelCreation();
        setTitle(title);
        add(panel);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }

    private void panelCreation(){
        panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        Font font1 = new Font("SansSerif", Font.BOLD, 16);
        ButtonListener button = new ButtonListener();

        nameLabel = new JLabel("Name");
        nameLabel.setFont(font1);

        nameSearch = new JTextField(20);
        nameSearch.setFont(font1);


        searchButton = new JButton("Search");
        searchButton.setFont(font1);
        searchButton.addActionListener(button);
        searchButton.setActionCommand("search");

        flavorLabel = new JLabel("Flavor");
        flavorLabel.setFont(font1);

        flavorMenu = new JComboBox<>(DealersMeal.getFlavors());
        flavorMenu.setFont(font1);
        flavorMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        textureLabel = new JLabel("Texture");
        textureLabel.setFont(font1);

        textureMenu = new JComboBox<>(DealersMeal.getTextures());
        textureMenu.setFont(font1);
        textureMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        typeLabel = new JLabel("Meal Type");
        typeLabel.setFont(font1);

        typeMenu = new JComboBox<>(DealersMeal.getTypes());
        typeMenu.setFont(font1);
        typeMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        prepLabel = new JLabel("Prep Time");
        prepLabel.setFont(font1);

        prepTime = new JTextField(5);
        prepTime.setFont(font1);

        spicyCheck = new JCheckBox("Spicy?");
        spicyCheck.setFont(font1);
        spicyCheck.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addMealButton = new JButton("New Meal");
        addMealButton.setFont(font1);
        addMealButton.addActionListener(button);
        addMealButton.setActionCommand("addMeal");

        pantryButton = new JButton("Pantry");
        pantryButton.setFont(font1);
        pantryButton.addActionListener(button);
        pantryButton.setActionCommand("pantry");

        useOwned = new JCheckBox("Use Owned Ingrediants");
        useOwned.setFont(font1);
        
        panel.setLayout(layout);
        setGroupLayout(layout);
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            switch(cmd){
                case "addMeal":
                    new EditMeal().setVisible(true);
                    dispose();
                    break;
                case "search":
                    Meal temp = new Meal();
                    temp.setName(nameSearch.getText());
                    temp.setFlavor(flavorMenu.getSelectedItem().toString());
                    temp.setTexture(textureMenu.getSelectedItem().toString());
                    temp.setType(typeMenu.getSelectedItem().toString());
                    if(tryParse(prepTime.getText()) != null) temp.setPrepTime(tryParse(prepTime.getText()));
                    temp.setSpicy(spicyCheck.isSelected());
                    Meal[] matched = DealersMeal.search(temp);
                    // send matches to meals list window
                    new FoundMeals(matched).setVisible(true);
                    dispose();
                    break;
                case "pantry":
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
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameSearch)
                        .addComponent(searchButton))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(flavorLabel)
                        .addComponent(flavorMenu))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textureLabel)
                        .addComponent(textureMenu))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(typeLabel)
                        .addComponent(typeMenu))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(prepLabel).addGap(10)
                        .addComponent(prepTime))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(spicyCheck)
                        .addComponent(useOwned))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pantryButton)
                        .addComponent(addMealButton))
            );
            layout.setHorizontalGroup(
                layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(flavorLabel)
                        .addComponent(textureLabel)
                        .addComponent(typeLabel)
                        .addComponent(prepLabel)
                        .addComponent(pantryButton)
                        .addComponent(spicyCheck))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameSearch)
                        .addComponent(flavorMenu)
                        .addComponent(textureMenu)
                        .addComponent(typeMenu)
                        .addComponent(prepTime))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(searchButton)
                        .addComponent(addMealButton)
                        .addComponent(useOwned))
            );
        } catch (IllegalArgumentException iae){
            System.err.println("Group is null");
        } catch (IllegalStateException ise){
            System.err.println("Illegal State");
        }
    }

    private Integer tryParse(String text){
        try{
            return Integer.parseInt(text);
        } catch (NumberFormatException nfe){
            return null;
        }
    }
}
