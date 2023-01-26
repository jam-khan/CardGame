
import java.util.ArrayList;

/**
 * The BigTwo class implements the CardGame interface and is used to model a Big Two card game.
 * @author jamkabeeralikhan
 */

public class BigTwo implements CardGame {
	
	/**
	 * a method for starting a Big Two card game
	 * @param args
	 */
	public static void main(String args[]) {
		
		BigTwo game = new BigTwo();
		BigTwoDeck deck = new BigTwoDeck();
		
		game.start(deck);
			
		
		
	}
	/**
	 * A method for returning a valid hand from the specified list of cards of the player
	 * @param player
	 * @param cards
	 * @return Valid Hand of Cards
	 */
	public static Hand composeHand(CardGamePlayer player, CardList cards) {
		
		Hand hand;
		
		if ((new Single(player, cards)).isValid())
			hand = new Single(player, cards);
		else if ((new Pair(player, cards)).isValid())
			hand = new Pair(player, cards);
		else if ((new Triple(player, cards)).isValid())
			hand = new Triple(player, cards);
		else if ((new Straight(player, cards)).isValid())
			hand = new Straight(player, cards);
		else if ((new Flush(player, cards)).isValid())
			hand = new Flush(player, cards);
		else if ((new FullHouse(player, cards)).isValid())
			hand = new FullHouse(player, cards);
		else if ((new Quad(player, cards)).isValid())
			hand = new Quad(player, cards);
		else if ((new StraightFlush(player, cards)).isValid())
			hand = new StraightFlush(player, cards);
		else
			return null;
		
		return hand;
		
	}
	
	
	//an int specifying the number of players.
	private int numOfPlayers;
	
	// a deck of cards.
	private Deck deck;
	
	// a list of players
	private ArrayList<CardGamePlayer> playerList;
	
	// a list of hands played on the table.
	private ArrayList<Hand> handsOnTable;
	
	// an integer specifying the index of the current player.
	
	private int currentPlayerIdx;
	
	// a BigTwoUI object for providing the user interface.
	
	private BigTwoGUI gui;
	
	/**
	 * a constructor for creating a Big Two card game
	 */
	public BigTwo() {
		 
		playerList = new ArrayList<CardGamePlayer>();
		handsOnTable = new ArrayList<Hand>();
		
		// creating 4 players and adding them to the list of players
		for (int i = 0; i < 4; i++) {
			playerList.add(new CardGamePlayer());
		}
		// big two UI object created for providing the user interface
		
		gui = new BigTwoGUI(this);
		
	}
	
	/**
	 * a method for getting the number of players.
	 * @return number of players
	 */
	public int getNumOfPlayers() {
		return this.numOfPlayers;
	}
	
	/**
	 * a method for retrieving the deck of cards being used.
	 * @return Deck of Cards
	 */
	public Deck getDeck() {
		return this.deck;
	}
	
	/**
	 * a method for retrieving the list of players
	 * @return ArrayList of CardGamePlayer objects
	 */
	
	public ArrayList<CardGamePlayer> getPlayerList() {
		return this.playerList;
	}
	
	/**
	 * A method for retrieving the list of hands played on the table
	 * @return list of hands on the table
	 */
	public ArrayList<Hand> getHandsOnTable() {
		return this.handsOnTable;
	}
	
	/**
	 * a method for retrieving the index of the current player
	 * @return current player index
	 */
	public int getCurrentPlayerIdx() {
		return currentPlayerIdx;
	}
	
	/**
	 * A method for starting/restarting the game with a given shuffled deck of cards
	 * @param deck: Deck Object
	 */
	public void start(Deck deck) {
		this.deck = deck;
		
//		handsOnTable = new ArrayList<Hand>(); // Removed cards from the table
		for (int i = 0; i < playerList.size(); i++)
			playerList.get(i).removeAllCards();
		
		this.deck.initialize();
		this.deck.shuffle();
		
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 13; j++) 
				playerList.get(i).addCard(this.deck.getCard(i * 13 + j));
		
		
		// identifying player with 3 of diamonds
		for (int k = 0; k < playerList.size(); k++) {
			CardGamePlayer player = playerList.get(k);	
			if (player.getCardsInHand().contains(new BigTwoCard(0, 2))) {
				this.currentPlayerIdx = k;
//				ui.setActivePlayer(this.currentPlayerIdx);
				gui.setActivePlayer(this.currentPlayerIdx);
			}
		}
		 
//		ui.repaint();
//		ui.promptActivePlayer();
			
		gui.repaint();
		gui.promptActivePlayer();
	
	}
	
	
	
	
	
	/**
	 * Method for making a move by a player with the specified index 
	 * using the cards specified by the list of indices.
	 * @param playerIdx: int
	 * @param cardIdx: int array
	 */
	public void makeMove(int playerIdx, int[] cardIdx) {
		checkMove(playerIdx, cardIdx);
		
	}
	
	// Below method checks whether current player is same as the last player who played the latest
	// hand on the table.
	private boolean isLastPlayer(ArrayList<Hand> handsOnTable, CardGamePlayer player) {
		
		return player.getName() == handsOnTable.get(handsOnTable.size() - 1).getPlayer().getName();
		
	}
	
	// Checks whether three of Diamond is in the cards played.
	private boolean containsThreeDiamond(int playerIdx, int[] cardIdx) {
		
		if (cardIdx == null)
			return false;
		else {
			for (int i = 0; i < cardIdx.length; i++) {
				
				Card card = this.playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]);
				if (card.rank == 2 && card.suit == 0)
					return true;
				
			}
		}
		
		return false;
	}
	

	/**
	 * Method for checking a move made by a player. 
	 * @param playerIdx
	 * @param cardIdx
	 */
	public void checkMove(int playerIdx, int[] cardIdx) {
		
		// Deal with the case that move is empty: basically pass
		
		if (cardIdx == null) {
			
			if (this.handsOnTable.isEmpty()) 
				gui.printMsg("Not a legal move!!!\n");
//				ui.printMsg("Not a legal move!!!\n");
			else if (isLastPlayer(this.handsOnTable, playerList.get(playerIdx))) 
				gui.printMsg("Not a legal move!!!\n");
			else {
				gui.printMsg("{Pass}\n");
				this.currentPlayerIdx = (this.currentPlayerIdx + 1) % 4;
			}
			
		} else if (this.handsOnTable.isEmpty() && !containsThreeDiamond(playerIdx, cardIdx)) {
			gui.printMsg("Not a legal move!!!\n");
		} else {
			
			
			CardList cardsPlayed = new CardList();
			
			for (int i = 0; i < cardIdx.length; i++) 
				cardsPlayed.addCard(playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]));
			
			Hand handPlayed = composeHand(playerList.get(playerIdx), cardsPlayed);
			
			if (handPlayed == null)
				gui.printMsg("Not a legal move!!!\n");
			else if (this.handsOnTable.isEmpty() || handPlayed.beats(this.handsOnTable.get(this.handsOnTable.size() - 1)) || isLastPlayer(this.handsOnTable, playerList.get(playerIdx))) {
				handsOnTable.add(handPlayed);
				gui.printMsg("{" + handPlayed.getType() + "} " + cardsPlayed.toString() + "\n");
//				cardsPlayed.print();
				playerList.get(playerIdx).removeCards(cardsPlayed);
				this.currentPlayerIdx = (this.currentPlayerIdx + 1) % 4;
			} else {
				gui.printMsg("Not a legal move!!!\n");
			}
			
		}
		
		

		gui.setActivePlayer(this.currentPlayerIdx);
		
		
		if (endOfGame()) {
//			gui.repaint();
			gui.printMsg("\n");
			gui.printMsg("Game ends\n");
			
			for (int i = 0; i < 4; i++) {
				if (playerList.get(i).getCardsInHand().size() == 0)
					gui.printMsg("Player " + i + " wins the game.\n");
				else
					gui.printMsg("Player " + i + " has " + playerList.get(i).getCardsInHand().size() + " cards in hand.\n");
			}
			
			gui.disable();
			
		} else {
			gui.repaint();
			gui.promptActivePlayer();
		}
		
	}
	
	/**
	 * A method for checking if the game ends
	 * @return state of the game: true if game ends else false.
	 */
	
	public boolean endOfGame() {
		
		for (int i = 0; i < 4; i++) 
			if (playerList.get(i).getCardsInHand().isEmpty()) 
				return true;
		
		
		return false;
	}
	
	
	
}
