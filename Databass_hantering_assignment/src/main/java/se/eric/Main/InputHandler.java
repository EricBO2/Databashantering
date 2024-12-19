package se.eric.Main;

import java.util.Scanner;

public class InputHandler {                      //input handler to only use one scanner

    static Scanner scan;
    static {                                    //creates scanner
        scan  = new Scanner(System.in);
    }
    public static String getString(){           //standard get string
        return scan.nextLine();
    }
    public static double getDouble(){           //maks sure it gets a double
        boolean aloud = true;
        while (aloud) {
            if (scan.hasNextDouble()) {
                return scan.nextDouble();
            }
            else{
                System.out.println("Please enter a double");
            }
            scan.nextLine();
        }
        return 0.0;
    }
    public static int getInt(){                 //maks sure it gets a int
        boolean aloud = true;
        while (aloud) {
            if (scan.hasNextInt()) {
                return scan.nextInt();
            }
            else{
                System.out.println("Please enter an int");
            }
            scan.nextLine();
        }
        return 0;

    }
    public static void close(){
        scan.close();
    }
}
