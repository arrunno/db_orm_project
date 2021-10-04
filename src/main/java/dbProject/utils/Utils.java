package dbProject.utils;

import java.util.Scanner;

public class Utils {
    public static String getInpString(Scanner sc, String inputMessage){
        if (!inputMessage.isEmpty()) {
            System.out.println(inputMessage);
        }
        return sc.nextLine();
    }

    public static int getInpIntOr0(Scanner sc, String inputMessage){
        if (!inputMessage.isEmpty()) {
            System.out.println(inputMessage);
        }
        String input = sc.nextLine();
        if(isNumeric(input)){
            return Integer.parseInt(input);
        }
        return 0;
    }

    public static void showIfNotEmpty(String message){
        if (!message.isEmpty()){
            System.out.println(message);
        }
    }

    public static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }


}
