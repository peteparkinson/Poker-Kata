public class Main {
	public static void main(String[] args){

		Player black = new Player("JS KH KD QH QD");
		Player white = new Player("7S 7H 7D 7C AD");
		
		//check duplicates with opponent
		black.checkDuplicates(white);
		
		//start
		if(black.getRank() > white.getRank()){
			System.out.println("Black wins. - with " + black.getHand());
		} else if (black.getRank() < white.getRank()){
			System.out.println("White wins. - with " + white.getHand());			
		} else if(black.checkTie(white)){
			System.out.println("Tie.");				
		} else {
			//Tie Breaker
			if(black.checkHighKicker(white)){
				System.out.println("Black wins. - with high card: " + black.getHighCard());
			} else {
				System.out.println("White wins. - with high card: " + white.getHighCard());		
			}
		}
	}
}
