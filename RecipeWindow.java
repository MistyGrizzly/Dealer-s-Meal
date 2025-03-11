// Author : John Whitmore
// Date Created : 02 / 26 / 25
// Date Modified : 02 / 26 / 25
// File Name : RecipeWindow.java
// Purpose : Provides window to display meal recipe with return button and edit button

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class RecipeWindow extends JFrame{
    // fields
    private String title = "Recipe";
    private JPanel panel;
    private JLabel nameL;
    private JButton editB;
    private JScrollPane recipeS;
    private JTextPane recipeT;
    private final int HEIGHT = 500, WIDTH = 350;

    // constructor
    public RecipeWindow(Meal meal){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(title);
        setSize(WIDTH, HEIGHT);
        panelCreation(meal);
        add(panel);
        setLocationRelativeTo(null);
    }

    private void panelCreation(Meal meal){
        ButtonListener button = new ButtonListener();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        nameL = new JLabel(meal.getName());
        nameL.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nameL);

        editB = new JButton("Edit");
        editB.addActionListener(button);
        editB.setActionCommand(meal.getName());
        editB.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(editB);

        recipeT = new JTextPane();
        recipeT.setText(meal.toString());

        recipeS = new JScrollPane(recipeT);
        recipeS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        recipeS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(recipeS);
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            new EditMeal(DealersMeal.getMeal(cmd)).setVisible(true);
            dispose();
        }
    }
}
