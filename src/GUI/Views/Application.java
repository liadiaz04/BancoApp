package GUI.Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.Components.*;
import GUI.Controllers.SelectedUserManager;
import GUI.Controllers.ViewHandler;

public class Application extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;
    private ViewHandler viewHandler;
    

    public Application() {
    	
        setConfigs(); 
        setApplicationValues(); 
    }
    
    

    private void setConfigs() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 450, 300);
    } 
    
    private void setApplicationValues() {
    	contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        setContentPane(contentPane);
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        this.viewHandler = new ViewHandler(contentPane, cardLayout);
        this.viewHandler.loadViews();

    }


}
