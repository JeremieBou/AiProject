import java.util.Random;

/**
 * Created by jeremie on 18/03/17.
 */
public class OrganicPlayer implements PokerSquaresPlayer{

    private static Random rnd = new Random();

    private final int SIZE = 5;
    private final int MAX_MOVES = 25;
    private final int NUM_POS = SIZE * SIZE;
    private final int NUM_CARDS = Card.NUM_CARDS;

    private static int rootOrganismsCreated = 0;
    private String organismName;

    private Chromosome[] chromosomes;

    private int moves;
    private Card[][] grid = new Card[SIZE][SIZE];

    private double scoreUtility = 1;

    private PokerSquaresPointSystem system;


    public OrganicPlayer(){
        rootOrganismsCreated += 1;
        this.organismName = "OrganicPlayer #" + rootOrganismsCreated;

    }

    @Override
    public void setPointSystem(PokerSquaresPointSystem system, long millis) {
        this.system = system;
    }

    @Override
    public void init() {
        for (int row = 0; row < SIZE; row++)
            for (int col = 0; col < SIZE; col++)
                grid[row][col] = null;


        this.chromosomes = new Chromosome[25];

        for(int i = 0; i < MAX_MOVES; i++){
            this.chromosomes[i] = new Chromosome();
        }

        moves = 0;


    }

    @Override
    public int[] getPlay(Card card, long millisRemaining) {
        int maxX = -1;
        int maxY = -1;
        double maxUtility = -1000000;

        for(int i = 0; i < SIZE; i++){

            for(int j = 0; j < SIZE; j++){

                if(grid[i][j] == null){
                    grid[i][j] = card;

                    double utility = chromosomes[moves].getUtility(grid, this.system);


                    if(utility > maxUtility){
                        maxUtility = utility;
                        maxX = i;
                        maxY = j;
                    }

                    grid[i][j] = null;

                }
            }

        }



       grid[maxX][maxY] = card;

        int[] play = {maxX, maxY};
        return play;
    }

    @Override
    public String getName() {
        return this.organismName;
    }

    public static void main(String[] args){
        PokerSquaresPointSystem system = PokerSquaresPointSystem.getBritishPointSystem();
        System.out.println(system);
        new PokerSquares(new OrganicPlayer(), system).play(); // play a single game
    }
}
