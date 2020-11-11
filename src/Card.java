public class Card {

	final String[] VALUES = new String[] { "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "Jack", "Queen", "King", "Ace" };

	private char suit;
	private int value;

	public Card() {
	}
	
	public Card(String name) {
		super();
		this.suit = name.charAt(1);
		if(name.charAt(0) == 'T'){
			this.value = 10;
		} else  if(name.charAt(0) == 'J'){
			this.value = 11;			
		} else  if(name.charAt(0) == 'Q'){			
			this.value = 12;
		} else  if(name.charAt(0) == 'K'){			
			this.value = 13;
		} else  if(name.charAt(0) == 'A'){			
			this.value = 14;
		} else  {
			this.value = Integer.parseInt(String.valueOf(name.charAt(0)));
		}
		System.out.println("created new card: " + VALUES[this.value - 1]
				+ " of " + this.suit);
	}

	public char getSuit() {
		return suit;
	}

	public void setSuit(char suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	public boolean matches(Card other) {
		if(this.suit == other.suit && this.value == other.value){
			return true;
		}
		return false;
	}


}
