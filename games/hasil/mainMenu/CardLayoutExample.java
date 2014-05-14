//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class CardLayoutExample {
    JFrame guiFrame;
    CardLayout cards;
    JPanel cardPanel;


    public static void main(String[] args) {
     
         //Use the event dispatch thread for Swing components
         EventQueue.invokeLater(new Runnable()
         {
             
            @Override
             public void run()
             {
                 
                 new CardLayoutExample();         
             }
         });
              
    }
    
    public CardLayoutExample()
    { 
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("CardLayout Example");
        guiFrame.setSize(400,300);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setLayout(new BorderLayout());
        
        //creating a border to highlight the JPanel areas
        Border outline = BorderFactory.createLineBorder(Color.black);
        
        JPanel tabsPanel = new JPanel();
        tabsPanel.setBorder(outline);
        JButton switchCards = new JButton("Switch Card");
        switchCards.setActionCommand("Switch Card");
        switchCards.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                cards.next(cardPanel);
            }
        });
        tabsPanel.add(switchCards);
        
        guiFrame.add(tabsPanel,BorderLayout.NORTH);
        
        
        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);
        cards.show(cardPanel, "Fruits");
        
        JPanel firstCard = new JPanel();
        firstCard.setBackground(Color.GREEN);
        addButton(firstCard, "APPLES");
        addButton(firstCard, "ORANGES");
        addButton(firstCard, "BANANAS");
        
        JPanel secondCard = new JPanel();
        secondCard.setBackground(Color.BLUE);
        addButton(secondCard, "LEEKS");
        addButton(secondCard, "TOMATOES");
        addButton(secondCard, "PEAS");
        
        cardPanel.add(firstCard, "Fruits");
        cardPanel.add(secondCard, "Veggies");
        
        guiFrame.add(tabsPanel,BorderLayout.NORTH);
        guiFrame.add(cardPanel,BorderLayout.CENTER);
        guiFrame.setVisible(true);
    }
    
    //All the buttons are following the same pattern
    //so create them all in one place.
    private void addButton(Container parent, String name)
    {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        parent.add(but);
    }
}

