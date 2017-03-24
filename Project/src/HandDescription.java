import java.util.Arrays;

/**
 * Created by jeremie on 18/03/17.
 */
public class HandDescription {
    private Card[] hand;
    private Card[][] grid;

    private int handScore;

    private int handSize;
    
    //1 - 13
    private int[] ranks;
    private int[] ranksCount;
    private int[] ranksRemaing;
    private int diffRanksCount;
    /*
     1 - cloves
     2 - diamonds
     3 - hearts
     4 - spades
     */
    private int[] suits;
    private int[] suitsCount;
    private int[] suitsRemaining;
    private int diffSuitsCount;

    /*
        0 - Royal Flush
        1 - Straight Flush
        2 - Four of a Kind
        3 - Full House
        4 - Flush
        5 - Straight
        6 - Three of a Kind
        7 - Two pairs
        8 - One Pair
        9 - High card
     */

    private int[] possibleHands;



    public HandDescription(Card[][] grid, Card[] hand, int handScore){
        this.hand = hand;
        this.grid = grid;
        this.handSize = 0;


        this.ranks = new int[5];
        this.ranksCount = new int[5];
        this.ranksRemaing = new int[5];
        Arrays.fill(this.ranksRemaing, 4);
        this.diffRanksCount = 0;

        this.suits = new int[5];
        this.suitsCount = new int[5];
        this.suitsRemaining = new int[5];
        Arrays.fill(this.suitsRemaining, 4);

        this.diffSuitsCount = 0;

        this.possibleHands = new int[10];
        Arrays.fill(this.possibleHands, 1);

        this.insertCards();

        this.getRemaining();

        this.handScore = handScore;
    }

    private void insertCards(){
        for(int i = 0; i < this.hand.length; i++){
            if(this.hand[i] != null){
                this.insertCard(this.hand[i]);
            }
        }
    }

    private void insertCard(Card card){
        this.handSize += 1;
        this.insertRank(card.getRank() + 1);
        this.insertSuite(card.getSuit() + 1);
    }

    private void insertRank(int rank){
        for(int i = 0; i < this.ranks.length; i++){
            if(this.ranks[i] == 0){
                this.ranks[i] = rank;
                this.ranksCount[i] += 1;
                this.diffRanksCount += 1;

                //check royal flush possibility
                if(rank < 10 && rank != 1){
                    this.possibleHands[0] = 0;
                }

                //check is full house and four of a kind is still possible
                if(this.diffRanksCount > 2){
                    this.possibleHands[3] = 0;
                    this.possibleHands[2] = 0;
                }
                //check if three of a kind, two pairs is still possible
                else if(this.diffRanksCount > 3){
                    this.possibleHands[6] = 0;
                    this.possibleHands[7] = 0;
                }
                //check if pair is still possible
                else if(this.diffRanksCount > 4){
                    this.possibleHands[8] = 0;
                }

                return;
            }
            else if(this.ranks[i] == rank){
                this.ranksCount[i] += 1;

                //Straight, straight flush, and royal flush are no longer possible if we add duplicate ranks
                this.possibleHands[0] = 0;
                this.possibleHands[1] = 0;
                this.possibleHands[5] = 0;



                return;
            }
        }
    }

    private void insertSuite(int suit){
        for(int i = 0; i < this.suits.length; i++){
            if(this.suits[i] == 0){
                this.suits[i] = suit;
                this.suitsCount[i] += 1;
                this.diffSuitsCount += 1;

                //Check if flush, royal flush, and straight flush are still possible
                if(this.diffSuitsCount == 2){
                    this.possibleHands[0] = 0;
                    this.possibleHands[1] = 0;
                    this.possibleHands[4] = 0;
                }


                return;
            }
            else if(this.suits[i] == suit) {
                this.suitsCount[i] += 1;

                return;
            }
        }
    }

    private void getRemaining(){
        for(int i = 0; i < this.ranks.length; i++){
            if(this.ranks[i] != 0){
                for(int k = 0; k < this.grid.length; k++){
                    for(int j = 0; j < this.grid[i].length; j++){

                        if(this.grid[i][j] != null) {
                            if (this.grid[i][j].getRank() + 1 == this.ranks[i]) {
                                this.ranksRemaing[i]--;
                            }
                        }

                        if(this.grid[i][j] != null) {
                            if (this.grid[i][j].getSuit() + 1 == this.suits[i]) {
                                this.suitsRemaining[i]--;
                            }
                        }
                    }
                }
            }
            else{
                this.ranksRemaing[i] = 0;
                this.suitsRemaining[i] = 0;
            }
        }

    }

    public int[] getRanks() {
        return ranks;
    }

    public int[] getRanksCount() {
        return ranksCount;
    }

    public int getDiffRanksCount() {
        return diffRanksCount;
    }

    public int[] getSuits() {
        return suits;
    }

    public int[] getSuitsCount() {
        return suitsCount;
    }

    public int getDiffSuitsCount() {
        return diffSuitsCount;
    }

    public int[] getPossibleHands() {
        return possibleHands;
    }

    public int[] getRanksRemaing() {
        return ranksRemaing;
    }

    public int[] getSuitsRemaining() {
        return suitsRemaining;
    }

    public int getHandScore(){
        return handScore;
    }
}
