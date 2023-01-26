import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * It is a class to run the GUI for BigTwo card game.
 * It is used to build a GUI for the Big Two card game and handle all user actions.
 * BigTwoGUI implements CardGameUI interface
 * @author jamkabeeralikhan
 *
 */

public class BigTwoGUI implements CardGameUI{
	
	private final static int MAX_CARD_NUM = 13;
	
	private ArrayList<CardGamePlayer> playerList; // the list of players
	private ArrayList<Hand> handsOnTable; // the list of hands played on the

	// a Big Two card game associates with this GUI
	private BigTwo game = null;
	
	private boolean[] selected = new boolean[MAX_CARD_NUM]; // a boolean array indicating which cards are being selected.
	private int activePlayer = -1; // an integer specifying the index of the active player.
	private JFrame frame = new JFrame(); // the main window of the application.	
	private JPanel bigTwoPanel; // a panel for showing the cards of each player and the cards played on the table.
	private JButton playButton = new JButton("Play"); // a “Play” button for the active player to play the selected cards
	private JButton passButton = new JButton("Pass"); // a “Pass” button for the active player to pass his/her turn to the next player
	private JTextArea msgArea = new JTextArea(25, 30); // a text area for showing the current game status as well as end of game messages.
	private JTextArea chatArea = new JTextArea(25, 30); // a text area for showing chat messages sent by the players.
	private JTextField chatInput = new JTextField(1); // a text field for players to input chat messages.
	
	
	
	// Below method will generate the Text Panel 
	// which holds the msgArea, chatArea and chatInput.
	private JPanel makeTextPanel() {
		
		// initialization of the textPanel and layout setup
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		
		// adding chatArea and msgArea to the panel
		textPanel.add(chatArea);
		textPanel.add(msgArea);
		
		// making the msgArea scrollable
		msgArea.setLineWrap(true);
		JScrollPane scrollerMsg = new JScrollPane(msgArea);
		scrollerMsg.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollerMsg.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textPanel.add(scrollerMsg);
		
		// making the chatArea scrollable
		chatArea.setLineWrap(true);
		JScrollPane scrollerChat = new JScrollPane(chatArea);
		scrollerChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollerChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textPanel.add(scrollerChat);
		
		// making both msgArea and chatArea un-editable so that only text can be displayed and 
		// user can not edit the text
		
		msgArea.setEditable(false);
		chatArea.setEditable(false);
		
		// making border for both msgArea and chatArea
		msgArea.setBorder(BorderFactory.createLineBorder(Color.black));
		chatArea.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// adding the ChatInputListener to the chatInput so that text
		// entered can be displayed on the screen.
		chatInput.addActionListener(new ChatInputListener());
		
		// Message Panel
		
		// creating a seperate msgPanel to hold chatInput along with its label "Message: "
		JPanel msgPanel = new JPanel();
		
		// BoxLayout.X_AXIS will allow chatInput and Label "Message: " displayed horizontally
		msgPanel.setLayout(new BoxLayout(msgPanel, BoxLayout.X_AXIS));
		
		msgPanel.setSize(250, 100);
		msgPanel.add(new JLabel("Message: "));
		msgPanel.add(chatInput);
		
		textPanel.add(msgPanel);
		
		// returning textPanel
		return textPanel;
	}
	
	// Below method will return the buttonPanel consisting of Play and Pass button
	private JPanel makeButtonPanel() {
		JPanel buttonPanel = new JPanel();
		// Setting up layout
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setSize(600, 100);
		// adding the action listeners to the play and pass button
		playButton.addActionListener(new PlayButtonListener());
		passButton.addActionListener(new PassButtonListener());
		// adding play and pass button to the panel
		buttonPanel.add(playButton);
		buttonPanel.add(passButton);
		// returning the panel
		return buttonPanel;
		
	}
	
	// below will return the JMenuBar after adding the restart and quit menu item
	private JMenuBar makeMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		// creating the game menu
		JMenu menu = new JMenu("Game");
		
		// making restart menu item and adding the action listener
		JMenuItem menuItemRestart = new JMenuItem("Restart");
		menuItemRestart.addActionListener(new RestartMenuItemListener());
		
		// making quit menu item and adding the action listener
		JMenuItem menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.addActionListener(new QuitMenuItemListener());
		
		// adding the menu items to the menu
		menu.add(menuItemRestart);
		menu.add(menuItemQuit);
		
		// adding the menu to the menubar
		menuBar.add(menu);
		
		return menuBar;
	}
	
	
	
	/**
	 * a constructor for creating a BigTwoGUI.
	 * @param game: a reference to a Big Two card game associates with this GUI
	 */
	public BigTwoGUI(BigTwo game) {
		
		// initializing the selected array
		for (int i = 0; i < 13; i++) {
			selected[i] = false;
		}
		
		
		this.game = game;
		
		// getting the playerList and handsOnTable
		// this is the data needed for the display
		playerList = game.getPlayerList();
		handsOnTable = game.getHandsOnTable();
		
		// creating bigTwoPanel
		bigTwoPanel = new BigTwoPanel();
		
		// Below is the message display panel.		
		JPanel textPanel = makeTextPanel();
		
		
		// Setting Menu bar
		JMenuBar menuBar = makeMenuBar();
		frame.setJMenuBar(menuBar);
		
		// getting the buttonsPanel
		JPanel buttonsPanel = makeButtonPanel();
		
		// setting frame size
		frame.setSize(1000, 800);
		
		// adding all the panels to the frame and setting it visible
		frame.add(bigTwoPanel, BorderLayout.CENTER);
		frame.add(textPanel, BorderLayout.EAST);
		frame.add(buttonsPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	

	/**
	 * a method for setting the index of the active player
	 */
	@Override
	public void setActivePlayer(int activePlayer) {
		// TODO Auto-generated method stub
		if (activePlayer < 0 || activePlayer >= playerList.size()) {
			this.activePlayer = -1;
		} else {
			this.activePlayer = activePlayer;
		}
		
	}
	/**
	 * a method for repainting the GUI
	 */
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		// this is for repainting the GUI
		frame.repaint();
		
	}

	/**
	 * a method for printing the specified string to the message area of the GUI
	 * @param msg: message to display by the program on message area.
	 */
	@Override
	public void printMsg(String msg) {
		// TODO Auto-generated method stub
	
		// take the message area and print the string that you get on it
		msgArea.append(msg); // adding the message to the end of the msg area for display
		
	}
	
	/**
	 * a method for printing the chat message on the chat area.
	 * @param chat: chat message to send
	 */
	public void printChatMsg(String chat) {
		chatArea.append("Player " + activePlayer + ": " + chat + "\n");
		// below code helps clearing out the text in the chat Input field after user has sent a message
		chatInput.setText("");
	}

	/**
	 * a method for clearing the message area of the GUI.
	 */
	@Override
	public void clearMsgArea() {
		// TODO Auto-generated method stub
		// setting msgArea
		msgArea.setText(""); // trying to clear up the current msgArea
		
	}

	/**
	 * a method for clearing the chat area of the GUI
	 */
	public void clearChatArea() {
		chatArea.setText(""); // trying to clear up the current msgArea
	}
	
	/**
	 * a method for resetting the GUI.
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
		// 1) Reset list of selected cards
		resetSelected();
		// 2) Clear the message and chat area
//		clearChatArea();
		clearMsgArea();
		// 3) enable user interactions
		enable();
		
		
	}
	/**
	 * a method for enabling user interactions with the GUI.
	 */
	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
		
		// enabling the play button, pass button and chat input field
		playButton.setEnabled(true);
		passButton.setEnabled(true);
		chatInput.setEnabled(true);
		
		// below enables the bigTwoPanel by calling a method implemented in the bigTwoPanel class enableMouseClick()
		((BigTwoGUI.BigTwoPanel) bigTwoPanel).enableMouseClick();
		
	}

	/**
	 * a method for disabling user interactions with the GUI.
	 */
	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
		// this will reset the selected cards
		resetSelected();
		
		// disable play button, pass button and chat input
		playButton.setEnabled(false);
		passButton.setEnabled(false);
		chatInput.setEnabled(false);
		
		((BigTwoGUI.BigTwoPanel) bigTwoPanel).disableMouseClick();
		
		// active player becomes -1. this allows repaint to display cards of all the players at the end of the
		// game just like card games since there is no point in playing any further so everyone just throws in their cards
		activePlayer = -1;
		
		// finally, everything is repainted to reflect the changes
		repaint();
		
	}
	
	
	// below is a method that will return an array indicating the selected cards.
	// this was inspired from the code given in BigTwoUI
	private int[] getSelected() {
		
		// below code traverses through the selected
		// and accordingly, makes the int[] cardIdx array which will carry the index of selected cards.
		
		int[] cardIdx = null;
		int count = 0;
		for (int j = 0; j < selected.length; j++) {
			if (selected[j]) {
				count++;
			}
		}

		if (count != 0) {
			cardIdx = new int[count];
			count = 0;
			for (int j = 0; j < selected.length; j++) {
				if (selected[j]) {
					cardIdx[count] = j;
					count++;
				}
			}
		}
		
		return cardIdx;
	}
	

	// below will simply reset the selected cards by taking it back to its initial state.
	private void resetSelected() {
		for (int j = 0; j < selected.length; j++) {
			selected[j] = false;
		}
	}
	
	
	
	/**
	 * a method for prompting the active player to select cards and make his/her move.
	 */
	@Override
	public void promptActivePlayer() {
		// TODO Auto-generated method stub
		printMsg(playerList.get(activePlayer).getName() + "'s turn: ");
		
		// below clears up any selection when a player is prompted.
		// this allows every player to select their cards again after an illegal move.
		resetSelected();		
	}
	
	/**
	 * an inner class that extends the JPanel class and implements the MouseListener interface
	 * @author jamkabeeralikhan
	 *
	 */
	class BigTwoPanel extends JPanel implements MouseListener {
		
		/**
		 * A constructor for BigTwoPanel
		 */
		public BigTwoPanel() {
			super();
			
			// this sets the background color
			this.setBackground(new Color(4, 164, 92));
			// this sets the size
			setSize(620, 695);
			
			// this will setup the mouse listener for BigTwoPanel
			enableMouseClick();
		}
		
		/**
		 * below will disable mouse click on the BigTwoPanel
		 */
		public void disableMouseClick() {
			if(getMouseListeners().length != 0)
				removeMouseListener(this);
		}
		
		/**
		 * below will enable mouse click on the BigTwoPanel
		 */
		public void enableMouseClick() {
			if (getMouseListeners().length == 0)
				addMouseListener(this);
		}
		
		/**
		 * Below is the overridden mouseReleased to implement the mouseClick functionality
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
			// getting the x and y coordinates of the mouse click
			int x = e.getX();
			int y = e.getY();
			
			// initialization of playerNumber 
			int playerNumber = -1;
			
			// finding which player cards were clicked
			for (int i = 0; i < playerList.size(); i++) {
				if (y >= (40 + i * 140) && y <= (140 + i * 140)) {
					playerNumber = i;
				}
			}
			
			// now it will check for which card is clicked 
			if (playerNumber != -1 && playerNumber == activePlayer) {

				int cardNumber = -1;
				if (playerNumber != -1) {
					int cards = playerList.get(playerNumber).getNumOfCards();
					for (int i = 0; i < cards; i++) {
						if (i != cards - 1) {
							if (x >= 120 + 15 * i && x <= 120 + 15 * (i + 1))
								cardNumber = i;
						} else {
							if (x >= 120 + 15 * i && x <= 190 + 15 * i)
								cardNumber = i;
						}
					}
				}
				
				// if a card is clicked then corresponding index in selected array 
				// is made true
				
				if (cardNumber != -1) 
					selected[cardNumber] = !selected[cardNumber];
				
				repaint(); // repainting after selection so that card position can be changed	
			}
			
		}
		
		/**
		 * overriding paintComponent method to implement
		 * the graphics needed for displaying the bigTwoPanel
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// image will hold card image and playerImage will hold avatar image
			Image image;
			Image playerImage;
			
			
			// this will traverse through the playerList. Getting each player one by one.
			for (int i = 0; i < playerList.size(); i++) {
				
				CardGamePlayer player = playerList.get(i);
				CardList cardsInHand;
				boolean showCards = false;
				
				// the names of the images are written such that it allows to extract
				// player avatar images of each player uniquely
				playerImage = new ImageIcon("images/player" + i + ".png").getImage();
				
				// draws the image at x-coordinate: 20 and y-coordinate: 40 + i * 140
				g.drawImage(playerImage, 20, 40 + i * 140, this);
				
				// if activePlayer is current player being worked upon, then "You" is written instead of "Player i"
				if (activePlayer == i) {
					g.drawString("You", 10, 30 + i * 140);
					showCards = true;
				} else if (activePlayer == -1) {
					g.drawString("Player " + i, 10, 30 + i * 140);
					showCards = true;
				} else {
					g.drawString("Player " + i, 10, 30 + i * 140);
				}
				
				// getting cards in hand and then, sorting them.
				cardsInHand = player.getCardsInHand();
				cardsInHand.sort();	

				// displaying the cards for each player
				for(int j = 0; j < cardsInHand.size(); j++) {
					if (showCards) {
						image = new ImageIcon("images/" + cardsInHand.getCard(j).rank + "-" + cardsInHand.getCard(j).suit + ".gif").getImage();
					} else {
						image = new ImageIcon("images/b.gif").getImage();
					}
					// If a card is selected then, that card will be raised by 20 in terms of y-coordinate
					if(selected[j] && activePlayer == i) {
						g.drawImage(image, 120 + j * 15, 20 + i * 140, this);
					} else {
						g.drawImage(image, 120 + j * 15, 40 + i * 140, this);
					}
				}
				// drawing a line to separate the players
				g.drawLine(0, 10 + (i + 1) * 140, 620, 10 + (i + 1) * 140);
			}
			
			// getting last hand played on table
			Hand lastHandOnTable = (handsOnTable.isEmpty()) ? null : handsOnTable.get(handsOnTable.size() - 1);
			g.drawString("Table", 20, 600);
			
			// if lastHandOnTable is not null, then last hand will be displayed, else simply "Empty" will be displayed
			if (lastHandOnTable != null) {
				
				g.drawString("Played by " + lastHandOnTable.getPlayer().getName(), 40, 620);
				for(int i = 0; i < lastHandOnTable.size(); i++) {
					image = new ImageIcon("images/" + lastHandOnTable.getCard(i).rank + "-" + lastHandOnTable.getCard(i).suit + ".gif").getImage();
					g.drawImage(image, 180 + i * 15, 600, this);					
				}
				
			} else {
				g.drawString("Empty", 40, 650);
			}
			
		}

		// below methods are not implemented
		/**
		 * Not Implemented
		 */
		@Override
		public void mouseClicked(MouseEvent e) {}

		/**
		 * Not Implemented
		 */
		@Override
		public void mousePressed(MouseEvent e) {}

		/**
		 * Not Implemented
		 */
		@Override
		public void mouseEntered(MouseEvent e) {}

		/**
		 * Not Implemented
		 */
		@Override
		public void mouseExited(MouseEvent e) {}
		
		
	}

	
	
	/**
	 * an inner class that implements the ActionListener interface
	 * @author jamkabeeralikhan
	 *
	 */
	class PlayButtonListener implements ActionListener {

		/**
		 * below method is overridden to implement the functionality
		 * of the Play Button
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int[] select = getSelected();

			// move will be made only if any cards have been selected, otherwise, it is the job of pass button
			if (select != null)
				game.makeMove(activePlayer, select);	
			
		}
	}
	
	/**
	 * an inner class that implements the ActionListener interface for Pass Button.
	 * @author jamkabeeralikhan
	 *
	 */
	class PassButtonListener implements ActionListener {

		/**
		 * below method is overridden to implement the functionality
		 * of the Pass Button
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			resetSelected();			
			game.makeMove(activePlayer, null); // null is passed to makeMove since it is null
			
		}
	}
	
	/**
	 * an inner class that implements the ActionListener interface for RestartMenu item.
	 * @author jamkabeeralikhan
	 *
	 */
	class RestartMenuItemListener implements ActionListener {
		
		/**
		 * below method is overridden to implement the functionality
		 * of the Restart Menu Item
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			BigTwoDeck deck = new BigTwoDeck();
			deck.shuffle();
			game.start(deck);
		
			reset();
			
			handsOnTable.clear();
			promptActivePlayer();
			
			
		}
		
	}
	
	/**
	 * an inner class that implements the ActionListener interface for quit menu item.
	 * @author jamkabeeralikhan
	 *
	 */
	class QuitMenuItemListener implements ActionListener {

		/**
		 * below method is overridden to implement the functionality
		 * of the Quit Menu Item
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0); // simply closes the system.
		}
		
	}
	
	/**
	 * an inner class that implements the ActionListener interface for chat input field.
	 * @author jamkabeeralikhan
	 *
	 */
	class ChatInputListener implements ActionListener {

		/**
		 * Below method is overridden to implement the functionality
		 * of the Chat Input Field
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			printChatMsg(chatInput.getText()); // text in the chatInput gets displayed on the chat area
			
		}
		
	}
}


