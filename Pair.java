
public class Pair extends Hand {

	public Pair(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	// done
	public boolean isValid() {
		sort();
		if (size() == 2 
				&& (adjustRank(getCard(0).rank) == adjustRank(getCard(1).rank))) {
			return true;
		}
		return false;
		
	}
	
	public String getType() {
		return "Pair";
	}



	/**
	 * Checks whether this hand can beat another hand. Compares both Hand
	 * @param hand: Potential Hand to beat.
	 */
	
	public boolean beats(Hand hand) {
		// sorting both hands for better comparison.
		sort();
		hand.sort();
		
		// only pair can beat another pair.
		if (hand.getType() != "Pair")
			return false;
		
		int suit = getTopCard().suit;
		int rank = adjustRank(getTopCard().rank);
		
		// If ranks of bother pairs are same. Then, suits of top card are compared.
		// Otherwise, ranks are compared.
		if (rank == adjustRank(hand.getTopCard().rank)) 
			return suit > hand.getTopCard().suit;
		else
			return rank > adjustRank(hand.getTopCard().rank);
		
	}
	
}
