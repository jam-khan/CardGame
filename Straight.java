/**
 * This class models a Straight hand. This hand consists of five cards with consecutive ranks.
 * @author jamkabeeralikhan
 *
 */
public class Straight extends Hand {

	/**
	 * Constructor. Calls the Super constructor.
	 * @param player: Player who played the hand.
	 * @param cards: List of Cards played by the player.
	 */
	public Straight(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	
	/**
	 * Checks whether the hand is a valid Straight or not.
	 */
	public boolean isValid() {
		sort(); // Sorts the hand before validation.
		
		
		// Size must be 5 for a valid Straight.
		if (size() != 5)
			return false;
		
		// If the next card rank is not equal to previous card rank + 1, 
		// then this hand is not a valid Straight.
		for (int i = 0; i < size() - 1; i++) 
			if (adjustRank(getCard(i).rank) + 1 != adjustRank(getCard(i + 1).rank)) 
				return false;
		
		return true; // If not yet invalid, then must be valid.
		
		
	}
	
	/**
	 * Returns the type of hand.
	 */
	public String getType() {
		return "Straight";
	}


	/**
	 * Checks whether this hand can beat another hand. Compares both Hand
	 * @param hand: Potential Hand to beat.
	 */
	public boolean beats(Hand hand) {
		
		// Sorting both hands before comparison.
		sort();
		hand.sort();
		
		// Size must be 5 for valid comparison.
		if (hand.size() != 5)
			return false;
		
		// Straight is always beaten by any FullHouse, Quad, StraightFlush and Flush.
		if (hand.getType() == "FullHouse" || hand.getType() == "Quad" || hand.getType() == "StraightFlush" || hand.getType() == "Flush")
			return false;
		
		// In case, other hand is still not a Straight. Then, can't beat it. This may be redundant, but is included for
		// potential edge cases.
		if (hand.getType() != getType())
			return false;
		
		
		int rank = adjustRank(getTopCard().rank);
		int suit = getTopCard().suit;
		
		// If rank of top Card is same, then it compares suit of top card. Higher suit beats lower suit. 
		// Otherwise, rank of top card card is compared. Higher rank beats lower rank.
		if (rank == adjustRank(hand.getTopCard().rank))
			return (suit > hand.getTopCard().rank);
		else
			return rank > adjustRank(hand.getTopCard().rank);
		
		
	}
	
}
