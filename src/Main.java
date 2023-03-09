import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        // Hier mag je je code scrijven voor de hoofd-opdracht
        int[] numeric = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String[] alphabetic = {"een", "twee", "drie", "vier", "vijf", "zes", "zeven", "acht", "negen", "nul"};

        class Translator {
            Map<Integer, String> numericAlpha = new HashMap<Integer, String>();

            public Translator(String[] alphabetic, int[] numeric) {
                for (int i = 0; i < numeric.length; i++) {
                    numericAlpha.put(numeric[i], alphabetic[i]);
                }
            }

            public String translate(Integer number) {
                return numericAlpha.get(number);
            }
        }

        Translator translator = new Translator(alphabetic, numeric);
        boolean play = true;
        String ongeldig = "ongeldige invoer";
        Scanner scanner = new Scanner(System.in);

        while (play) {
            System.out.println("Type 'x' om te stoppen \nType 'v' om te vertalen");
            String input = scanner.nextLine();

            if (input.equals("x")) {
                play = false;
            } else if (input.equals("v")) {
                System.out.println("Type een cijfer in van 0 t/m 9");
                int number = scanner.nextInt();
                scanner.nextLine();
                if (number < 10 && number >= 0) {
                    String result = translator.translate(number);
                    System.out.println("De vertaling van " + number + " is " + result);
                } else {
                    System.out.println(ongeldig);
                }
            } else {
                System.out.println(ongeldig);
            }
        }


        HashSet<Integer> secretnumber = randomNumberGenerator();
        String stringnumber = setToStringConverter(secretnumber);
        System.out.println(stringnumber);
        feedback(stringnumber);


    }

    /*
     Deze methode is voor de bonus opdracht.
     */
    public static void feedback(String stringnumber) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder feedback = new StringBuilder();

        boolean playMasterMind = true;

        while (playMasterMind) {
            System.out.println("+ = juiste nummer op de juiste plek, O = juiste nummer verkeerde plek, X = verkeerde nummer");
            System.out.println("Doe een gok, Let op vul 4 getallen in.");
            String guess = scanner.nextLine();

            if (Objects.equals(guess, stringnumber)) {
                System.out.println("gefeliciteerd je hebt het goed");
                playMasterMind = false;
                continue;
            } else {
                for (int i = 0; i < 4; i++) {
                    if (guess.substring(i, i + 1).equals(stringnumber.substring(i, i + 1))) {
                        feedback.append("+");
                    } else if (stringnumber.contains(guess.substring(i, i + 1))) {
                        feedback.append("0");
                    } else {
                        feedback.append("X");
                    }
                }
            }
            System.out.println(feedback);
            System.out.println("Wilt u het nog een keer proberen? Vul 'N' in om te stoppen of druk op 'Enter' om door te gaan om door te gaan");
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("n")) {
                playMasterMind = false;
            }

        }
    }

    public static HashSet<Integer> randomNumberGenerator() {
        Random rand = new Random();
        HashSet<Integer> secretnumber = new HashSet<>();
        while (secretnumber.size() < 4) {
            int randomnumber = rand.nextInt(9) + 1;
            secretnumber.add(randomnumber);
        }
        return secretnumber;
    }

    public static String setToStringConverter(HashSet<Integer> secretnumber) {
        StringBuilder stringnumber = new StringBuilder();
        for (Integer element : secretnumber) {
            stringnumber.append(element.toString());
        }
        return stringnumber.toString();
    }
}

//HashSet houd de invoegvolgorde niet aan. Elementen worden ingevoegd op basis van hun hashcode.