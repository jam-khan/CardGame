
/**
 * The Hand class is a subclass of the CardList class and is used to model a hand of cards
 * @author jamkabeeralikhan
 *
 */

abstract class Hand extends CardList {
	
	// the player who plays this hand.
	private CardGamePlayer player;
//	private CardList hand;
	
	/**
	 * A constructor for building a hand with the specified player and list of cards.
	 * @param CardGamePlayer: player of the card
	 * @param CardList: list of cards
	 */
	public Hand(CardGamePlayer player, CardList cards) {
		this.player = player;
		
		for (int i = 0; i < cards.size(); i++)
			addCard(cards.getCard(i));
		
	}
	
	
	/**
	 * A method for retrieving the player of this hand
	 * @return Player of this hand
	 */
	public CardGamePlayer getPlayer() {
		
		return this.player;
		
	}
	
	/**
	 * A method for retrieving the top card of this hand. 
	 * @return top card of hand
	 */
	public Card getTopCard() {
		
		sort();
		
		return getCard(size()-1);
		
	}
	
	/**
	 * This re-calibrates the rank of the cards so that 2 > A > K > Q > J > 10 > 9 > 8 > 7 > 6 > 5 > 4 > 3.
	 * @param rank: rank of the card
	 * @return new calibrated rank. Changes rank of 2 from 1 to 14. Changes rank of A from 0 to 13.
	 */
	public int adjustRank(int rank) {
		
		if (rank == 0 || rank == 1) return 13 + rank;
		
		return rank;
	}
	
	/**
	 * A method for checking if this is a valid hand 
	 */
	public abstract boolean isValid();
	
	/**
	 * A method for returning a string specifying the type of this hand
	 */
	public abstract String getType();
	
	/**
	 * A method for checking if this hand beats a specified hand.
	 * @return true or false
	 */
	public abstract boolean beats(Hand hand);
	

}
