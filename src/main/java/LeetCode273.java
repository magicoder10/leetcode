import java.util.ArrayList;
import java.util.List;

public class LeetCode273 {
    private static final String[] oneDigitWords = new String[]{
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
    };

    private static final String[] twoDigitsWords = new String[]{
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    };

    private static final String[] tensWords = new String[]{
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    public String numberToWords(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num cannot be empty");
        }

        if (num == 0) {
            return "Zero";
        }

        List<String> result = new ArrayList<>();
        int billions = num / 1_000_000_000;
        num = num % 1_000_000_000;
        if (billions > 0) {
            result.add(formatThreeDigits(billions));
            result.add("Billion");
        }

        int millions = num / 1_000_000;
        num = num % 1_000_000;
        if (millions > 0) {
            result.add(formatThreeDigits(millions));
            result.add("Million");
        }

        int thousands = num / 1_000;
        num = num % 1_000;
        if (thousands > 0) {
            result.add(formatThreeDigits(thousands));
            result.add("Thousand");
        }

        if (num > 0) {
            result.add(formatThreeDigits(num));
        }

        return String.join(" ", result);
    }

    private String formatThreeDigits(int num) {
        List<String> result = new ArrayList<>();
        int handreds = num / 100;
        num = num % 100;
        if (handreds > 0) {
            result.add(formatOneDigit(handreds));
            result.add("Hundred");
        }

        if (num > 0) {
            result.add(formatTwoDigits(num));
        }

        return String.join(" ", result);
    }

    private String formatTwoDigits(int num) {
        int tens = num / 10;
        num = num % 10;
        if (tens == 1) {
            return twoDigitsWords[num];
        }

        List<String> result = new ArrayList<>();
        if (tens > 1) {
            result.add(tensWords[tens - 2]);
        }

        if (num > 0) {
            result.add(formatOneDigit(num));
        }

        return String.join(" ", result);
    }

    private String formatOneDigit(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException(String.format("num must be greater than 0: %d\n", num));
        }

        return oneDigitWords[num - 1];
    }
}
