package nextstep.blackjack.valid;

import java.util.HashMap;
import java.util.Map;

public class ValidationPlayer {
    public boolean validNames(String names) {
        Map<String, Boolean> validName = new HashMap<>();
        String[] splitNames = names.split(",");

        for (String name : splitNames) {
            if(name.equals("dealer")) return false;
            if(validName.get(name) != null) return false;
            validName.put(name, true);
        }

        return true;
    }
}
