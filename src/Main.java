

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez le nombre de coureurs : ");
        int nbCoureurs = sc.nextInt();


        Jeu tableau = new Jeu(nbCoureurs);


        tableau.addCoureur();
        tableau.affichage();

        boolean flag = false;

        do {

            String answer = sc.nextLine();

            switch (answer) {
                case "aff":
                    tableau.afficheCoureurs();
                    tableau.CompactAff();
                    break;
                case "arr":
                    tableau.CoureurArrive();
                    tableau.CompactAff();
                    break;
                case "abd":
                    tableau.CoureurAbandon();
                    tableau.CompactAff();
                    break;
                case "dsq":
                    tableau.CoureurDisqualif();
                    tableau.CompactAff();
                    break;
                case "tar":
                    tableau.CoureurTemps();
                    tableau.CompactAff();
                    break;
                case "tg":
                    tableau.getTemps();
                    tableau.CompactAff();
                    break;
                case "diff":
                    tableau.DiffTemps();
                    tableau.CompactAff();
                    break;
                case "prov":
                    tableau.classProv();
                    tableau.CompactAff();
                    break;
                case "det":
                    tableau.affichage();
                    break;
                case "QUIT":
                    flag = true;
                    tableau.quit();
                    break;
            }
        }
        while(flag = true);
    }
}

