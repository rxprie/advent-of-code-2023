package rmp.day2;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.api.factory.Bags;

import java.util.function.Consumer;

public class CubeGame2 implements Consumer<String> {

    private long powerSum;


    public long getPowerSum() {
        return powerSum;
    }

    @Override
    public void accept(String line) {
        powerSum += powerSumFor(line);
    }

    private long powerOf(MutableBag<String> optimalBag) {
        ProductSumProcedure productSumProc = new ProductSumProcedure();
        optimalBag.forEachWithOccurrences(productSumProc);
        return productSumProc.getProduct();
    }

    private long powerSumFor(String line) {
        final MutableBag<String> optimalBag = Bags.mutable.empty();
        for (String draw : line.substring(line.indexOf(":") + 2).split(";")) {
            optimise(draw, optimalBag);
        }
        return powerOf(optimalBag);
    }

    private void optimise(String draw, MutableBag<String> optimalBag) {
        for (String drawByColour : draw.split(",")) {
            optimiseColourDraw(drawByColour, optimalBag);
        }
    }

    private void optimiseColourDraw(String drawByColour, MutableBag<String> optimalBag) {
        final String[] numColour = drawByColour.trim().split(" ");
        final int number = Integer.parseInt(numColour[0]);
        final String colour = numColour[1];
        if (optimalBag.occurrencesOf(colour) < number) {
            optimalBag.setOccurrences(colour, number);
        }
    }

    private static class ProductSumProcedure implements ObjectIntProcedure<String> {

        long product = 1L;

        @Override
        public void value(String value, int occurances) {
            product *= occurances;
        }

        public long getProduct() {
            return product;
        }
    }
}
