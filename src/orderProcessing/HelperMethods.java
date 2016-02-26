/*
 * Order Processing App – IST 411
 */

package orderProcessing;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Helper Methods for Order Processing System
 * @author J. Barclay Walsh and Matt White
 */
public class HelperMethods {
    
    public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        return formatter.format(price);
    }

    public static String priceWithoutDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }

    public static String priceToString(Double price) {
        String toShow = priceWithoutDecimal(price);
        if (toShow.indexOf(".") > 0) {
            return priceWithDecimal(price);
        } else {
            return priceWithoutDecimal(price);
        }
    }
    
    public static int randomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    
    public static int randomIntegerWithSeed(int min, int max, long seed) {
        Random random = new Random(seed);
        return random.nextInt(max - min) + min;
    }
    
}
