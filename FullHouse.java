/**
 * This models a FullHouse. This hand consists of five cards, 
 * with two having the same rank and three having another same rank.
 * @author jamkabeeralikhan
 *
 */
public class FullHouse extends Hand {


	/**
	 * Calls super constructor
	 * @param player: Player of the hand
	 * @param cards: List of cards played by the player
	 */
	public FullHouse(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	/**
	 * Checks whether the hand is a valid Flush hand or not.
	 */
	public boolean isValid() {
		
		sort(); // sorting hand before validation.
		
		// size must be 5, otherwise it is false.
		if (size() != 5)
			return false;
		
		
		// Below code, finds first two different ranks in the Hand.
		int rank1 = adjustRank(getCard(0).rank);
		int rank2 = -1;
		
		for (int i = 1; i < size(); i++) {
			if (rank1 != adjustRank(getCard(i).rank)) {
				rank2 = adjustRank(getCard(i).rank);
				break;
			}
		}
		
		// Figures out which rank is that of triplet and which is that of pair.
		
		
		int rankCount1 = 0;
		int rankCount2 = 0;
		
		for (int i = 0; i < size(); i++) {
			if (rank1 == adjustRank(getCard(i).rank))
				rankCount1++;
			if (rank2 == adjustRank(getCard(i).rank))
				rankCount2++;
		}
		
		// rank count of 2 ranks, rank1 and rank2, must either be 3 or 2.
		// If not, then that means it is not a Full House.
		
		return ((rankCount1 == 3 && rankCount2 == 2) || (rankCount1 == 2 && rankCount2 == 3));
		
		
	}
	
	/**
	 * Returns the type of this hand
	 */
	public String getType() {
		return "FullHouse";
	}


	/**
	 * Checks whether this hand can beat another hand. Compares both Hand
	 * @param hand: Potential Hand to beat.
	 */
	
	public boolean beats(Hand hand) {
		
		// Sorts both hands before comparison.
		sort();
		hand.sort();
		
		
		int rank = adjustRank(getTopCard().rank);
		int suit = getTopCard().suit;
		
		// Size must be 5, otherwise can't be beaten.
		if (hand.size() != 5)
			return false;
		
		// FullHouse beats any Straight or Flush.
		if (hand.getType() == "Straight" || hand.getType() == "Flush")
			return true;
		
		// Any StraightFlush or Quad beats any FullHouse.
		if (hand.getType() == "StraightFlush" || hand.getType() == "Quad")
			return false;
		
		Card topCard_this;
		Card topCard_hand;
		
		// Getting Top Card of the Triplet in this FullHouse.
		// Depending on the position of Triplet and Pair.
		if (getCard(2).rank == getCard(3).rank)
				topCard_this = getTopCard();
		else
			topCard_this = getCard(2);
		

		// Getting Top Card of the Triplet on the other FullHouse.
		// Depending on the position of Triplet and Pair.
		if (hand.getCard(2).rank == hand.getCard(3).rank)
			topCard_hand = hand.getTopCard();
		else
			topCard_hand = hand.getCard(2);
		
		// Ranks of Top Card of Triplet in the FullHouse are compared.
		return adjustRank(topCard_this.rank) > adjustRank(topCard_hand.rank); 
	
	}
	
}
