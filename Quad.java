
/**
 * This class models Quad hand. This hand consists of five cards, with four having the same rank.
 * @author jamkabeeralikhan
 *
 */
public class Quad extends Hand {

	/**
	 * Constructor calling super constructor to initialize Object.
	 * @param player: Player of the hand.
	 * @param cards: Cards played by the player.
	 */
	public Quad(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	
	/**
	 * Checks whether this hand is valid or not.	
	 */
	public boolean isValid() {
		
		sort(); // sorts the hand before validation.
		
		
		// If size is not 5, then not valid.
		if (size() != 5)
			return false;
		
		// Getting first two different ranks in the hand.
		
		int rank1 = adjustRank(getCard(0).rank);
		int rank2 = -1;
		
		for (int i = 1; i < size(); i++) {
			if (rank1 != adjustRank(getCard(i).rank)) {
				rank2 = adjustRank(getCard(i).rank);
				break;
			}
		}
		
		// Getting the rank count for each rank
		int rankCount1 = 0;
		int rankCount2 = 0;
		
		for (int i = 0; i < size(); i++) {
			if (rank1 == adjustRank(getCard(i).rank))
				rankCount1++;
			if (rank2 == adjustRank(getCard(i).rank))
				rankCount2++;
		}
		// If one rank has 4 cards and other rank has 1 card, then it is a Quad.
		return ((rankCount1 == 1 && rankCount2 == 4)
				|| (rankCount1 == 4 && rankCount2 == 1));
		
	}
	
	/**
	 * Returns the type of the hand.
	 */
	public String getType() {
		return "Quad";
	}

	
	/**
	 * Checks whether this hand can beat another hand. Compares both Hand
	 * @param hand: Potential Hand to beat.
	 */
	public boolean beats(Hand hand) {
		
		// sorts both hands before comparison.
		sort();
		hand.sort();
		
		Card topCardThis;
		Card topCardHand;
		
		if (hand.size() != 5) 
			return false;
		
		// Quad beats any Straight, Flush and FullHouse.
		if (hand.getType() == "Straight" || hand.getType() == "Flush" || hand.getType() == "FullHouse")
			return true;
		else if (hand.getType() == "StraightFlush")
			return false;
		
		// Gets the rank of topCard of four cards with same rank.
		if (getCard(3).rank != getCard(4).rank)
			topCardThis = getCard(3);
		else
			topCardThis = getCard(4);
		
		// Gets the rank of topCard for other hand.
		if (hand.getCard(3).rank != hand.getCard(4).rank)
			topCardHand = hand.getCard(3);
		else
			topCardHand = hand.getCard(4);
		
		
		// Returns the top card comparison result.
		return adjustRank(topCardThis.rank) > adjustRank(topCardHand.rank);

	}
	
}
