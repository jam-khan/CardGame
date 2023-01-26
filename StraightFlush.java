
/**
 * This class models a StraightFlush hand.
 * This hand consists of five cards with consecutive ranks and the same suit.
 * @author jamkabeeralikhan
 *
 */
public class StraightFlush extends Hand  {

	
	/**
	 * Constructor. Calls the Super constructor.
	 * @param player: Player who played the hand.
	 * @param cards: List of Cards played by the player.
	 */
	public StraightFlush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * Checks whether this hand is valid or not.
	 */
	public boolean isValid() {
		
		sort(); // sorts before validation
		
		// must have size 5.
		if (size() != 5)
			return false;

		// Rank of previous card + 1 must be equal to the rank of next card.
		for (int i = 0; i < size() - 1; i++) 
			if (adjustRank(getCard(i).rank) + 1 != adjustRank(getCard(i + 1).rank)) 
				return false;
		
		// If suit of any two consecutive cards is not same, then it is not valid.
		for (int i = 0; i < size() - 1; i++) {
			if (getCard(i).suit != getCard(i+1).suit)
				return false;
		}
		
		
		return true; // if not invalid yet, then must be valid.
	}
	
	
	/**
	 * Returns the type of this hand as String.
	 */
	public String getType() {
		return "StraightFlush";
	}


	/**
	 * Checks whether this hand can beat another hand. Compares both Hand
	 * @param hand: Potential Hand to beat.
	 */
	public boolean beats(Hand hand) {
		
		// sorts before comparison.
		hand.sort();
		sort();
		
		// size must be 5 for beating.
		if(hand.size() != 5)
			return false;
		// StraightFlush beats any other type with size of 5.
		if (hand.getType() != getType())
			return true;
		
		int rank = adjustRank(getTopCard().rank);
		int suit = getTopCard().suit;
		
		
		// If rank of top card is same, then suit will be compared of top card.
		// Otherwise, rank is compared.
		if (rank == adjustRank(hand.getTopCard().rank))
			return suit > hand.getTopCard().suit; 
		else
			return rank > adjustRank(hand.getTopCard().rank);
		
		
		
	}
	
	
}
