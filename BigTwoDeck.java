
/**
 * The BigTwoDeck class is a subclass of the Deck class 
 * and is used to model a deck of cards used in a Big Two card game
 * @author jamkabeeralikhan
 */
public class BigTwoDeck extends Deck {
	
	/**
	 * A method for initializing a deck of Big Two cards.
	 */
	public BigTwoDeck() {
		initialize();
	}
	
	public void initialize() {
		removeAllCards(); // Removing all cards from the deck.
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				BigTwoCard card = new BigTwoCard(i, j); // Creating new Big Two Card
				addCard(card); // Adding it to the deck
			}
		}
	}
	
	

}
