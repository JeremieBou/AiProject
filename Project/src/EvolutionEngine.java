import java.util.ArrayList;

/**
 * Created by jeremie on 21/03/17.
 */
public class EvolutionEngine {
    final int POPULATION_SIZE = 128;
    final int MAX_GENERATIONS = 16384;
    final float CROSSOVER_RATE = 0.8f;
    final float ELITISM_RATIO = 0.1f;
    final float MUTATION_RATIO = 0.03f;

    final int TOURNAMENT_SIZE = 100;//minimum games per population member

    final PokerSquaresPointSystem DEFAULT_SYSTEM = PokerSquaresPointSystem.getBritishPointSystem();

    private ArrayList<PokerSquaresPlayer> players;
    private PokerSquaresPointSystem system;

    public EvolutionEngine(){
        this.players = new ArrayList<PokerSquaresPlayer>();

        for(int i = 0; i < POPULATION_SIZE; i++){
            this.players.add(new OrganicPlayer());
        }

        this.system = DEFAULT_SYSTEM;
        
    }

    public void runOneGeneration(){
    	ArrayList<PokerSquaresPointSystem> systems = new ArrayList<PokerSquaresPointSystem>();
    	systems.add(this.system);
    	long timeA = System.currentTimeMillis();
    	// scores = PokerSquares.playTournament(this.players, systems, TOURNAMENT_SIZE, 0L);
        long timeB = System.currentTimeMillis();
        double[] scores = PokerSquares.playMultiThreadedTournament(this.players, this.system, TOURNAMENT_SIZE, 0L);
        long timeC = System.currentTimeMillis();
        
        System.out.println("Time for non threaded: " + (timeB - timeA));
        System.out.println("Time for threaded: " + (timeC - timeB));
        
       
        for (int i = 0; i < scores.length; i++){
            //System.out.println(scores[i]);
        }
    }
    public static void main(String[] args){
        EvolutionEngine engine = new EvolutionEngine();
        engine.runOneGeneration();

    }

}
