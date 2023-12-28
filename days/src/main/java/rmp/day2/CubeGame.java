package rmp.day2;

import org.eclipse.collections.api.bag.ImmutableBag;

import java.util.function.Consumer;

public class CubeGame implements Consumer<String> {

    private final ImmutableBag<String> bag;

    private long validGameIdSum;

    public CubeGame(ImmutableBag<String> bag) {
        this.bag = bag;
    }

    public long getValidGameIdSum() {
        return validGameIdSum;
    }

    @Override
    public void accept(String line) {
        validGameIdSum += validGameId(line);
    }

    private long validGameId(String line) {
        final int endIndex = line.indexOf(":");
        int id = Integer.parseInt(line.substring("Game ".length(), endIndex));
        String[] draws = line.substring(endIndex + 1).split(";");
        if (isValid(draws)) {
            return id;
        }
        return 0;
    }

    private boolean isValid(String[] draws) {
        for (String draw : draws) {
            if (!isValid(draw)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(String draw) {
        for (String drawByColour : draw.split(",")) {
            if (!isValidColourDraw(drawByColour))
                return false;
        }
        return true;
    }

    private boolean isValidColourDraw(String drawByColour) {
        final String[] numColour = drawByColour.trim().split(" ");
        final int number = Integer.parseInt(numColour[0]);
        final String colour = numColour[1];
        return bag.occurrencesOf(colour) >= number;
    }
}
