
package Interface;

import java.awt.event.*;
import javax.swing.*;

import Controller.Controller;
import Network.TCP_Client;
import Network.TCP_Server;

public class ActiveUsers extends JFrame implements ActionListener {
    final static int START_INDEX = 0;
    JPanel mainPanel;
    JPanel selectPanel;
    JComboBox<String> UsersList = null;
    JButton chatButton;

    public ActiveUsers() {
    	
        selectPanel = new JPanel();
        addWidgets();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(selectPanel);
        
		chatButton = new JButton("CHAT");
		chatButton.setBounds(10, 10, 100, 30);
		chatButton.addActionListener(this);
		mainPanel.add(chatButton);
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setVisible(true);
    }


    private void addWidgets() {

    	String[] init = {};
    	UsersList = new JComboBox<String>(init);
    	
    	//use addItem to add active user to the list
    	//UDP
        UsersList.addItem("192.168.1.15");
        UsersList.addItem("192.168.1.6");
        UsersList.addItem("192.168.1.22");
        UsersList.addItem("192.168.1.255");
        
        UsersList.setSelectedIndex(START_INDEX);

        selectPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Select user"), 
            BorderFactory.createEmptyBorder(10,10,10,10)));
        selectPanel.add(UsersList);
        UsersList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if ("comboBoxChanged".equals(event.getActionCommand())) {
        	String s=String.valueOf(UsersList.getSelectedItem());
        }
        if(event.getSource() == chatButton) {
        	String s=String.valueOf(UsersList.getSelectedItem());
	        MainWindow window = null;
	        window = new MainWindow();
	        
	        //remarque importante:
	        //faut-il se connecter � tout le monde ?
	        //pour �tre toujours pret � recevoir des msg de la part de 
	        //tout le monde
			TCP_Server t1 = new TCP_Server(Controller.TCPport_local);
			t1.start();
			TCP_Client t2 = new TCP_Client(s,Controller.TCPport_local);
			t2.start();
//	        TCP_Client.connect(s,Controller.TCPport_local);
        }
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        ActiveUsers window = new ActiveUsers();

        JFrame mainFrame = new JFrame("Active Users");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        mainFrame.setContentPane(window.mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

}