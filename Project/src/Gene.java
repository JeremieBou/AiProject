/**
 * Created by jeremie on 18/03/17.
 */
public class Gene{
    double diffRanksUtility;
    double diffSuitsUtility;
    /*
     ordered from left to right

     */
    double[] ranksRemainUtility;
    double[] suitsRemainUtility;

    double[] ranksCountUtility;
    double[] suitsCountUtility;

    double scoreUtility;

    /*
        1 Royal flush
        2 Straight flush
        3 Four of a kind
        4 Full house
        5 Flush
        6 Straight
        7 Three of a kind
        8 Two pairs
        9 One pairs
        10 High card
     */
    double[] remainingHandsUtility;

    public Gene(){
        diffRanksUtility = -0.1;
        diffSuitsUtility = -0.1;

        ranksRemainUtility = new double[5];
        ranksRemainUtility[0] = 0.2;
        ranksRemainUtility[1] = 0.2;
        ranksRemainUtility[2] = 0.2;
        ranksRemainUtility[3] = 0.2;
        ranksRemainUtility[4] = 0.2;

        ranksCountUtility = new double[5];
        ranksCountUtility[0] = 0.5;
        ranksCountUtility[1] = 0.4;
        ranksCountUtility[2] = 0.3;
        ranksCountUtility[3] = 0.2;
        ranksCountUtility[4] = 0.1;

        suitsRemainUtility = new double[5];
        suitsRemainUtility[0] = 0.2;
        suitsRemainUtility[1] = 0.2;
        suitsRemainUtility[2] = 0.2;
        suitsRemainUtility[3] = 0.2;
        suitsRemainUtility[4] = 0.2;

        suitsCountUtility = new double[5];
        suitsCountUtility[0] = 0.5;
        suitsCountUtility[1] = 0.4;
        suitsCountUtility[2] = 0.3;
        suitsCountUtility[3] = 0.2;
        suitsCountUtility[4] = 0.1;

        scoreUtility = 1;

        remainingHandsUtility = new double[10];
        remainingHandsUtility[0] = 2;
        remainingHandsUtility[1] = 1;
        remainingHandsUtility[2] = 0.5;
        remainingHandsUtility[3] = 0.25;
        remainingHandsUtility[4] = 0.25;
        remainingHandsUtility[5] = 0.2;
        remainingHandsUtility[6] = 0.15;
        remainingHandsUtility[7] = 0.1;
        remainingHandsUtility[8] = 0.5;
        remainingHandsUtility[9] = 0;
    }

    public double getUtility(HandDescription desc) {
        double utility = 0;



        //rank utility;
        //diff count utility
        utility += desc.getDiffRanksCount() * this.diffRanksUtility;

        //remaining and count utility
        for(int i = 0; i < desc.getRanks().length; i++){
            if(desc.getRanks()[i] != 0){
                utility += desc.getRanksCount()[i] * this.ranksCountUtility[i];
                utility += desc.getRanksRemaing()[i] * this.ranksRemainUtility[i];
            }
        }

        //suit utility;
        //diff count utility
        utility += desc.getDiffSuitsCount() * this.diffSuitsUtility;

        //remaining and count utility
        for(int i = 0; i < desc.getSuits().length; i++){
            if(desc.getSuits()[i] != 0){
                utility += desc.getSuitsCount()[i] * this.suitsCountUtility[i];
                utility += desc.getSuitsRemaining()[i] * this.suitsRemainUtility[i];
            }
        }

        //possible hands utility
        for(int i = 0; i < desc.getPossibleHands().length; i++){
            utility += desc.getPossibleHands()[i] * this.remainingHandsUtility[i];
        }


        //score utility
        utility += desc.getHandScore() * this.scoreUtility;

        return utility;
    }
}
