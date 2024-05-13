package sk.kasv.degro.worldappgui.Util;

public class PrettifyLargeNumber {
    public static String prettifyNumber(long number) {
        if (number < 1000) {
            return String.valueOf(number);
        }
        int exp = (int) (Math.log(number) / Math.log(1000));
        return String.format("%.1f %c",
                number / Math.pow(1000, exp),
                "kMGTPE".charAt(exp - 1));
    }
}
