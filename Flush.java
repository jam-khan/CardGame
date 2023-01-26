/**
 * This class models Flush hand. This hand consists of five cards with the same suit.
 * Inherits Hand Class
 * @author jamkabeeralikhan
 *
 */
public class Flush extends Hand {

	/**
	 * Calls super constructor
	 * @param player: Player of the hand
	 * @param cards: List of cards played by the player
	 */
	public Flush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * Checks whether the hand is a valid Flush hand or not.
	 */
	public boolean isValid() {
		sort(); // Sorting for ease and avoiding potential errors.
		
		// Flush must have a size of 5 cards.
		if (size() != 5)
			return false;
		// Checking whether all card have same suit or not.
		// If any two consecutive cards do not belong to same suit then not a flush.
		for (int i = 0; i < size() - 1; i++) 
			if (getCard(i).suit != getCard(i+1).suit)
				return false;
		
		return true; // If not returned false yet, then it must be a valid Flush hand.
	}
	
	/**
	 * Returns type of Hand.
	 */
	public String getType() {
		return "Flush";
	}
	
	/**
	 * Checks whether this can beat the other hand or not.
	 * @param hand: Hand to be beaten by this Flush hand.
	 */
	public boolean beats(Hand hand) {
		
		// sorting both hands for best comparison.
		sort();
		hand.sort(); 
		
		int rank = adjustRank(getTopCard().rank);
		int suit = getTopCard().suit;
		
		// Other hand must have size of 5, else this can't beat it.
		if (hand.size() != 5)
			return false;
		
		// Flush always beats any Straight
		if (hand.getType() == "Straight")
			return true;
		// Flush is always beaten by FullHouse, Quad and Straight Flush.
		else if (hand.getType() == "FullHouse" || hand.getType() == "Quad" || hand.getType() == "StraightFlush")
			return false;
		// else if both types are not same then, can't beat. Only above cases entertain this case when size() is same.
		else if (hand.getType() != getType())
			return false;
		// If suit is same for both. Then, ranks of top cards are compared.
		else if (suit == hand.getTopCard().suit)
			return rank > adjustRank(hand.getTopCard().rank);
		else
			return suit > hand.getTopCard().suit; // If suit is not the same, then suits are compared.
		
		
		
	}
}
