package dbProject.gui;

import dbProject.data.UserTypes;
import dbProject.data.Users;
import dbProject.service.ExamService;
import dbProject.utils.Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static dbProject.utils.Utils.showIfNotEmpty;

public class Gui {

    private Map<String, String> users = new HashMap<>();
    private ExamService examService;
    private EntityTransaction et;
    private String userEmail;

    public Gui(EntityManager em, EntityTransaction tx){
        this.examService =  new ExamService(em);
        this.et = tx;
    }

    public void startGUI(Scanner sc) {
        System.out.println(" ___________________________________");
        System.out.println("|       DB ORM Projektas            |");
        System.out.println("|___________________________________|");
        processActions(sc, "");
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail(Scanner sc){
        return Utils.getInpString(sc,"Iveskite el. pasta:");
    }

    public void processActions(Scanner sc, String message){
        showIfNotEmpty(message);
        this.userEmail = getUserEmail(sc);
        String userType = Users.getUserType(this.userEmail).name();

        if(userType.equals(UserTypes.NOT_REGISTERED.name())){
            message = "Iveskite galiojanti el pasta";
            processActions(sc, message);
        } else if (userType. equals(UserTypes.TEACHER.name())){
            processTeachersActions(sc, "Pasirinkite:");
        } else if (userType. equals(UserTypes.STUDENT.name())) {
            processStudentsActions(sc, "Pasirinkite egzamina:");
        }

    }

    private void processTeachersActions(Scanner sc, String message) {
        showIfNotEmpty(message);
        System.out.println("1 - Paziureti egzaminu info");
        System.out.println("2 - paziureti egzaminu statistika");
        System.out.println("0 - Iseiti");
        int userChoice = Utils.getInpIntOr0(sc, "");
        switch (userChoice){
            case 1 -> {
                this.examService.getExamTakeStats();
                processTeachersActions(sc, " ");
            }
            case 2 -> {
                this.examService.getExamStats();
                processTeachersActions(sc, " ");
            }
            default -> {
                System.out.println("viso gero");
                return;
            }
        }
    }

    private void processStudentsActions(Scanner sc, String message) {
        showIfNotEmpty(message);
        System.out.println("1. Pasaulio geografija 1");
        System.out.println("2. Lietuvos geografija 1");
        System.out.println("0. Iseiti");

        int userChoice = Utils.getInpIntOr0(sc, "");
        if (userChoice == 1){
            this.examService.takeExam(sc, this.et, "Geo_01", this.userEmail, "Pasaulio geografija 1", LocalDate.now());
            processStudentsActions(sc, "\n Pasirinkte egzammina, arba 0 iseiti");
        } else if (userChoice == 2) {
            this.examService.takeExam(sc, this.et, "Geo_02", this.userEmail, "Lietuvos geografija 1", LocalDate.now());
            processStudentsActions(sc, "\n Pasirinkte egzammina, arba 0 iseiti");
        } else {
            System.out.println("viso gero");
            return;
        }
    }
    }
