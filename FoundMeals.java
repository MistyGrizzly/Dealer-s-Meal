// Author : John Whitmore
// Date Created : 02 / 23 / 25
// Date Modified : 02 / 23 / 25
// File Name : FoundMeals.java
// Purpose : Provides list of found meals after search finishes

import javax.swing.*;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class FoundMeals extends JFrame{
    
    // fields
    private String title = "Search Results";
    private JPanel panel, innerPanel;
    private JButton back;
    private JScrollPane scroll;
    private final int HEIGHT = 500, WIDTH = 300;

    public FoundMeals(Meal[] meals){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(title);
        setSize(WIDTH, HEIGHT);
        panelCreation(meals);
        add(panel);
        setLocationRelativeTo(null);
    }

    private void panelCreation(Meal[] meals){        
        ButtonListener button = new ButtonListener();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        back = new JButton("Return");
        back.addActionListener(button);
        back.setActionCommand("return");
        panel.add(back);

        innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.PAGE_AXIS));
        innerPanel.add(Box.createRigidArea(new Dimension(2, meals.length)));

        for(Meal m : meals){
            JButton b = new JButton(m.getName());
            b.addActionListener(button);
            b.setActionCommand(m.getName());
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            innerPanel.add(b);
        }

        scroll = new JScrollPane(innerPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroll);

    }


    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if(cmd.equals("return")){
                new SearchWindow().setVisible(true);
                dispose();
            } else {
                new RecipeWindow(DealersMeal.getMeal(cmd)).setVisible(true);
            }
        }
    }
}
