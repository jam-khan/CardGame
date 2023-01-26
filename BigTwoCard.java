
/**
 * The BigTwoCard class is a subclass of the Card class 
 * and is used to model a card used in a Big Two card game
 * @author jamkabeeralikhan
 */

public class BigTwoCard extends Card {
	
	/**
	 * a constructor for building a card with the specified suit and rank. 
	 * Suit is an integer between 0 and 3, and rank is an integer between 0 and 12.
	 * @param suit
	 * @param rank
	 */
	public BigTwoCard(int suit, int rank) {
			super(suit, rank);	
	}
	
	
	/**
	 * a method for comparing the order of this card with the specified card.
	 * This takes into account following order 3 < 4 < 5 < 6 < 7 < 8 < 9 < 10 < J < Q < K < A < 2.
	 * @return -1, 0 or 1 when this card is less than, equal to, or greater than specified card.
	 */
	public int compareTo(Card card) {
		
		int cmpRank1 = this.rank;
		int cmpRank2 = card.rank;
		
		if (cmpRank1 == 0 || cmpRank1 == 1)
			cmpRank1 = 13 + cmpRank1;
		if (cmpRank2 == 0 || cmpRank2 == 1)
			cmpRank2 = 13 + cmpRank2;
		
		
		
		if (cmpRank1 > cmpRank2) {
			return 1;
		} else if (cmpRank1 < cmpRank2) {
			return -1;
		} else if (this.suit > card.suit) {
			return 1;
		} else if (this.suit < card.suit) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
