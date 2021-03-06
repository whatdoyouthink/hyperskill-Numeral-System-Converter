package converter;
import java.util.Scanner;

public class Main {
    private static boolean isPoint = false;

    private static double toDecimal(String source, int radix) {
        String intPart;
        String fracPart;
        if (source.contains(".")) {
            isPoint = true;
            String[] tmp = source.split("\\.");
            intPart = tmp[0];
            fracPart = tmp[1];
        } else {
            intPart = source;
            fracPart = "";
        }

        double number;
        if (radix == 1) {
            number = source.length();

        } else {
            number = Long.parseLong(intPart, radix);
        }
        for (int i = 0; i < fracPart.length(); i++) {
            number += Integer.parseInt("" + fracPart.charAt(i), radix) / Math.pow(radix, i + 1);
        }

        return number;
    }

    private static String toRadix(double number, int targetRadix) {
        long intPart = (long) number;
        double fracPart = number - intPart;
        int round = 5;
        StringBuilder res = new StringBuilder();
        if (targetRadix == 1) {
            for (int i = 0; i < intPart; i++) {
                res.append('1');
            }
        } else {
            res.append(Long.toString(intPart, targetRadix));

            if (isPoint) {
                res.append('.');
                if (fracPart == 0) {
                    res.append("0");
                }
            }
            while (round > 0 && fracPart > 0) {
                round--;
                fracPart *= targetRadix;
                res.append(Long.toString((int) fracPart, targetRadix));
                fracPart -= (int) fracPart;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("What number system do you want to translate from?\n" +
                    "(Enter a number from 1 to 36");
            int sourceRadix = scanner.nextInt();
            if(sourceRadix < 1 || sourceRadix > 36){
                throw new IllegalArgumentException();
            }
            System.out.println("Enter a number in system with base " + sourceRadix);
            String sourceNumber = scanner.next();
            System.out.println("What number system do you want to translate?\n" +
                    "(Enter a number from 1 to 36");
            int targetRadix = scanner.nextInt();
            if (targetRadix < 1 || targetRadix > 36) {
                throw new IllegalArgumentException();
            }
            double numberInDec = toDecimal(sourceNumber, sourceRadix);
            System.out.println("Answer is "+toRadix(numberInDec, targetRadix));
        } catch (Exception e) {
            System.out.println("Illegal format");
        }


    }
}
