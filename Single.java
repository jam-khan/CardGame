/**
 * Models a Single Hand. This hand consists of only one single card.
 * Inherits Hand Class.
 * @author jamkabeeralikhan
 *
 */
public class Single extends Hand {

	/**
	 * Constructor for Single. Calls Super Constructor.
	 * @param player
	 * @param cards
	 */
	public Single(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * Checks whether card if valid or not.
	 */
	public boolean isValid() {
		
		return size() == 1; // Card must be valid as long as size is 1.
		
	}
	
	/**
	 * Returns the type of Hand.
	 */
	public String getType() {
		return "Single";
	}
	
	/**
	 * Checks whether this hand can beat another hand. Compares both Hand
	 * @param hand: Potential Hand to beat.
	 */
	public boolean beats(Hand hand) {

		// Sorting both hands for best comparison.
		sort();
		hand.sort();
		
		int rank = adjustRank(getTopCard().rank);
		int suit = getTopCard().suit;
		
		// Single can only beat hand with size 1.
		if (hand.size() != 1)
				return false;
		
		if (hand.getType() != "Single") 
			return false;
		// If rank is same, then suit is compared. Otherwise, ranks are compared.
		else if (rank == adjustRank(hand.getTopCard().rank))
			return suit > hand.getTopCard().suit;
		else
			return rank > adjustRank(hand.getTopCard().rank);
		
		
	}
	
}
