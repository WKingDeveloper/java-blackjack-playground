package nextstep.blackjack.valid;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ValidationPlayer {

    public static boolean validPlayerName(String[] classifierPlayers) {
        return validDuplicateNames(classifierPlayers) && validProhibitNames(classifierPlayers);
    }

    private static boolean validDuplicateNames(String[] classifierPlayers) {
        Set<String> validClassifierPlayers = new HashSet<>(Arrays.asList(classifierPlayers));
        return classifierPlayers.length == validClassifierPlayers.size();
    }

    private static boolean validProhibitNames(String[] classifierPlayers) {
        return !Arrays.stream(classifierPlayers).filter(name -> name.equals("dealer")).findFirst().isPresent();
    }

}
