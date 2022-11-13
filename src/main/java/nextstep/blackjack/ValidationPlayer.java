package nextstep.blackjack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidationPlayer {

    public static boolean validPlayerNames(String[] classifierPlayers) {
        Set<String> validClassifierPlayers = new HashSet<>(Arrays.asList(classifierPlayers));
        return classifierPlayers.length == validClassifierPlayers.size();
    }

}
