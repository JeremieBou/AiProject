/**
 * Created by jeremie on 18/03/17.
 */
public class Chromosome {
    private final static int TOTAL_GENES = 10;
    public Gene[] genes;
    public Card[][] hands;

    public Chromosome(){
        genes = new Gene[TOTAL_GENES];

        for(int i = 0; i < TOTAL_GENES; i++){
            genes[i] = new Gene();
        }

    }

    private void generateHands(Card[][] grid){
        hands = new Card[10][5];

        for(int i = 0; i < 5; i++){
            hands[i] = grid[i];
        }

        for(int i = 0; i < 5; i++){
            Card[] hand = new Card[5];
            for(int j = 0; j < 5; j++){
                hand[j] = grid[j][i];
            }
            hands[i + 5] = hand;
        }
    }

    public double getUtility(Card[][] grid, PokerSquaresPointSystem pointSystem){
        double utility = 0;

        this.generateHands(grid);

        for(int i = 0; i < TOTAL_GENES; i++){
            HandDescription desc = new HandDescription(grid, hands[i], pointSystem.getHandScore((hands[i])));

            double u = genes[i].getUtility(desc);

            utility += u;


        }

        return utility;
    }

    public static Chromosome generate(){
        return null;
    }
}
