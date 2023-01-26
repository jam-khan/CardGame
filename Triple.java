/**
 * This class models a Triple hand. This hand consists of three cards with the same rank.
 * @author jamkabeeralikhan
 *
 */
public class Triple extends Hand {

	/**
	 * 
	 * @param player
	 * @param cards
	 */
	public Triple(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * Checks whether this hand is a valid triple or not.
	 */
	public boolean isValid() {
		sort(); // sorting card before validating.
		
	
		// If size is not 3, then it must not be a valid triple.
		if (size() != 3)
			return false;
		
		// If any 2 consecutive cards do not have same rank, then it is not a valid triple.
		for (int i = 1; i < 3; i++) 
			if (adjustRank(getCard(i - 1).rank) != adjustRank(getCard(i).rank)) 
				return false;
			
		
		return true; // If not yet invalid, then must be valid.
		
	}
	
	/**
	 * Returns the type of Hand.
	 */
	public String getType() {
		return "Triple";
	}
	

	/**
	 * Checks whether this hand can beat another hand. Compares both Hand
	 * @param hand: Potential Hand to beat.
	 */
	public boolean beats(Hand hand) {
		// Sorting both hands for better comparison.
		sort();
		hand.sort();
		
		// If size is not same, then definitely can't beat.
		if (hand.size() != 3) 
			return false;
		
		// If both types are same and rank of Top card is greater than the rank of Top Card of the
		// other hand, then it must beat the other hand.
		return getType() == hand.getType() &&
				adjustRank(getTopCard().rank) > adjustRank(hand.getTopCard().rank);
		
		
	};
	
	
	
}
