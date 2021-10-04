package dbProject.data;

import java.util.List;

public class Users {
    private static List<String> usersList = List.of("arunas@student.com", "arunas@teacher.com", "petras@student.com", "ona@student.com", "egle@student.com");

    public static UserTypes getUserType(String userEmail){

        UserTypes res;

        if(! usersList.contains(userEmail)){
            res = UserTypes.NOT_REGISTERED;
        } else {
            if(userEmail.contains("@student")){
                res = UserTypes.STUDENT;
            } else if (userEmail.contains("@teacher")){
                res = UserTypes.TEACHER;
            } else {
                res = UserTypes.NOT_REGISTERED;
            }
        }
        return res;
    }

}