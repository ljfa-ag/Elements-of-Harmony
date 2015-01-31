package ljfa.elofharmony.challenges;

public class ChallengeRegistry {
    public static ChallengeGenerosity generosity;
    
    private static Challenge[] challenges;
    
    public static void initChallenges() {
        challenges = new Challenge[6];
        challenges[4] = generosity = new ChallengeGenerosity(4);
    }
    
    public static Challenge fromId(int id) {
        return challenges[id];
    }
}
