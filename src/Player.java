public class Player {

	final String[] HANDS = new String[] { "high card", "pair", "two pairs", "three of a kind", "straight", "flush",
			"full house", "four of a kind", "straight flush" };

	// Instance variables
	private Card[] cards = new Card[5];
	private int rank;
	private int highCard;

	// Constructors
	public Player() {
	}

	public Player(String cards) {
		String[] split = cards.split("\\s+");
		if (split.length != 5) {
			System.out.println("Invalid hand, please enter a hand with 5 cards");
			// don't use System.exit() on a web application
			System.exit(1);
		}
		for (int i = 0; i < 5; i++) {
			this.cards[i] = new Card(split[i]);
		}

		// sort hand
		sortHand();

		// establish rank
		if (straightFlush()) {
			this.rank = 8;
		} else if (fourOfAKind()) {
			this.rank = 7;
		} else if (fullHouse()) {
			this.rank = 6;
		} else if (flush()) {
			this.rank = 5;
		} else if (straight()) {
			this.rank = 4;
		} else if (threeOfAKind()) {
			this.rank = 3;
		} else if (pairCount() == 2) {
			this.rank = 2;
		} else if (pairCount() == 1) {
			this.rank = 1;
		} else {
			this.rank = 0;
			this.highCard = this.cards[4].getValue();
		}
		System.out.println("rank " + this.rank + "\n");
	}

	

	/***************************************
	 * Hand ranking methods
	 **************************************/
	private int pairCount() {
		int count = 0;
		int[] values = new int[14];
		for (int i = 0; i < 5; i++) {
			values[this.cards[i].getValue() - 1]++;
		}
		for (int i = 0; i < 14; i++) {
			if (values[i] == 2) {
				this.highCard = i + 1;
				count++;
			}
		}
		return count;
	}

	private boolean threeOfAKind() {
		int[] values = new int[14];
		for (int i = 0; i < 5; i++) {
			values[this.cards[i].getValue() - 1]++;
		}
		for (int i = 0; i < 14; i++) {
			if (values[i] == 3) {
				this.highCard = i + 1;
				return true;
			}
		}
		return false;
	}

	private boolean straight() {
		for (int i = 0; i < 4; i++) {
			if (this.cards[i].getValue() != (this.cards[i + 1].getValue()) - 1) {
				return false;
			}
		}
		this.highCard = this.cards[4].getValue();
		return true;
	}

	private boolean flush() {
		for (int i = 1; i < 5; i++) {
			if (this.cards[i].getSuit() != this.cards[0].getSuit()) {
				return false;
			}
		}
		this.highCard = this.cards[4].getValue();
		return true;
	}

	private boolean fullHouse() {
		if (threeOfAKind() && pairCount() == 1) {
			this.highCard = this.cards[4].getValue();
			return true;
		}
		return false;
	}

	private boolean fourOfAKind() {
		int[] values = new int[14];
		for (int i = 0; i < 5; i++) {
			values[this.cards[i].getValue() - 1]++;
		}
		for (int i = 0; i < 14; i++) {
			if (values[i] == 4) {
				this.highCard = i + 1;
				return true;
			}
		}
		return false;
	}

	private boolean straightFlush() {
		if (straight() && flush()) {
			this.highCard = this.cards[4].getValue();
			return true;
		}
		return false;
	}
	
	
	
	/***************************************
	 * "check" and sort methods
	 **************************************/
	
	// outputs high kicker
	public boolean checkHighKicker(Player opponent) {
		if(this.highCard == opponent.highCard){
			int[] thisValues = new int[14];
			int[] oppValues = new int[14];
			for (int i = 0; i < 5; i++) {
				thisValues[this.cards[i].getValue() - 1]++;
				oppValues[opponent.cards[i].getValue() - 1]++;
			}
			for (int i = 13; i >= 0; i--) {
				if(thisValues[i] == 1) {
					this.highCard = i + 1;
				}
				if(oppValues[i] == 1) {
					opponent.highCard = i + 1;
				}
				if(this.highCard != opponent.highCard){
					return this.highCard > opponent.highCard;
				} else {
					//reset
					this.highCard = 0;
					opponent.highCard = 0;
				}
			}
		}
		return this.highCard > opponent.highCard;
	}

	public boolean checkTie(Player opponent) {
		for (int i = 0; i < 5; i++) {
			if (this.cards[i].getValue() != opponent.cards[i].getValue()) {
				return false;
			}
		}
		return true;
	}

	public void checkDuplicates(Player other) {
		for(int i = 0; i < 5; i++){
			//check against selves
			for(int j = i + 1; j < 5; j++){
				if(this.cards[i].matches(this.cards[j]) 
						|| other.cards[i].matches(other.cards[j])){
					System.out.println("There is a duplicate card, please retry.");
					System.exit(1);
				}
			}
			//check against opponent
			for(int j = 0; j < 5; j++){
				if(this.cards[i].matches(other.cards[j])){
					System.out.println("There is a duplicate card, please retry.");
					System.exit(1);
				}
			}
		}		
	}

	private void sortHand() {
		for (int i = 0; i < this.cards.length; i++) {
			for (int j = i + 1; j < this.cards.length; j++) {
				if (this.cards[i].getValue() > this.cards[j].getValue()) {
					Card temp = this.cards[i];
					this.cards[i] = this.cards[j];
					this.cards[j] = temp;
				}
			}
		}
	}

	/***************************************
	 * Getters / Setters
	 **************************************/
	public String getHand() {
		return HANDS[this.rank];
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getHighCard() {
		if(this.highCard == 11){
			return "Jack";
		} else  if(this.highCard == 12){		
			return "Queen";
		} else  if(this.highCard == 13){		
			return "King";
		} else  if(this.highCard == 14){			
			return "Ace";
		}
		return String.valueOf(highCard);
	}

	public void setHighCard(int highCard) {
		this.highCard = highCard;
	}

}
