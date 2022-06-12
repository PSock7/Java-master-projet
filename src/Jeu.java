

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Jeu {

    // tableau de coureurs: nombre de lignes = nombre de coureur
    // colonne 0: numéro dossard
    // colonne 1: nom
    // colonne 2: temps
    // colonne 3: arrivé
    // colonne 4: abandon
    // colonne 5: disqualifié
    // colonne 6: classement

    private int classement=0;
    private int nb;
    String [] []  tab;
    Scanner sc = new Scanner(System.in);

    // Conctructor
    public Jeu(int n){
        this.nb = n;
        tab = new String [this.nb] [7];
    }

// *** Methodes

    //Quitter
    protected void quit() {
        sc.close();
        System.out.println(" *** FERMETURE DE L'APPLICATION *** ");
        System.exit(1);
    }

    // Ajoute un coureur dans le tableau
    protected void addCoureur() {

        for (int i=0; i< this.nb; i++) {
            try {
                System.out.println("Saisissez le N0 de dossard du coureur " + Integer.toString(i+1) + ", puis tapez entrée : ");
                int d = sc.nextInt();
                System.out.println("Saisissez le nom du coureur " +  Integer.toString(i+1) + ", puis tapez entrée : ");
                sc.nextLine();
                String nom = sc.nextLine();

                tab[i][0] = Integer.toString(d);
                tab[i][1] = nom;
                tab[i][2] = Integer.toString(0);
            }
            catch (InputMismatchException m) {							// Gestion des erreurs provoqué par le scanner - Sinon l'appli s'arrête
                System.out.println("erreur de format dans la saisie");
                i=i-1;
                sc.nextLine();											// vide le buffer du scanner
                System.out.println("Appuyé sur entrée pour continuer");}
            catch (IndexOutOfBoundsException e) {
                System.out.println("erreur d'index");}
        }
    }

    // Affiche les coureurs rentrés dans le tableau
    protected void afficheCoureurs() {

        for (int i=0; i<this.nb; i++) {
            System.out.println("dossard N° " + tab[i][0] + " , " + tab[i][1] );}
    }



    // Enregistre l'arrivée du coureur au dossard N° d (colonne 3)
    protected void CoureurArrive() {

        System.out.println ("Arrivée coureur: ");
        int d = nDossard();
        boolean found = false;
        int rang = 0;

        for (int i=0; i<this.nb; i++) {
            try {
                if (Integer.valueOf(tab[i][0]) == d ) {
                    found = true;
                    rang = i;
                    classement++;
                    tab[i][3] = "Arr";
                    tab[i][4] = "X"; // le coureur n'a pas abandonné
                    tab[i][6] = Integer.toString(classement);
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("erreur d'index");}
        }

        if (found == true) {System.out.println("le coureur au dossard N° " + tab[rang][0] + " est arrivé" );}
        else {System.out.println("N° de dossard inconnu");}
        System.out.println("");
    }

    // Enregistre l'abandon du coureur au dossard N° d (colonne 4)
    protected void CoureurAbandon() {

        System.out.println ("Abandon coureur: ");
        int d = nDossard();
        boolean found = false;
        int rang = 0;

        for (int i=0; i<this.nb; i++) {
            try {
                if (Integer.valueOf(tab[i][0]) == d ) {
                    found = true;
                    rang = i;
                    tab[i][3] = "X"; // le coureur n'est pas arrivé
                    tab[i][4] = "Abd";
                    tab[i][5] = "X"; // Le coureur ne pourra pas etre disqualifié
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("erreur d'index");
            }
        }

        if (found == true) {	System.out.println("le coureur au dossard N° " + tab[rang][0] + " est arrivé" );}
        else {System.out.println("N° de dossard inconnu");}
        System.out.println("");
    }

    // Enregistre la disqualifiction du coureur au dossard N° d (colonne 5)
    protected void CoureurDisqualif() {
        System.out.println ("Disqualification coureur: ");
        int d = nDossard();
        boolean found = false;
        int rang = 0;

        for (int i=0; i<this.nb; i++) {
            try {
                if (Integer.valueOf(tab[i][0]) == d ) {
                    found = true;
                    rang = i;
                    tab[i][3] = "X"; // le coureur n'est pas arrivé
                    tab[i][4] = "X"; // le coureur n'a pas abandonné
                    tab[i][5] = "Disq";
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("erreur d'index");
            }
        }

        if (found == true) {	System.out.println("le coureur au dossard N° " + tab[rang][0] + " est disqualifié" );}
        else {System.out.println("N° de dossard inconnu");}
        System.out.println("");
    }

    // Enregistre le temps du coureur au dossard N° d (colonne 6)
    protected void CoureurTemps() {

        System.out.println ("Enregistrement temps: ");
        int d = nDossard();
        boolean found = false;
        int time = 0;
        int rang = 0;

        try {
            System.out.println("Nombre d'heures ");
            int h = sc.nextInt();
            System.out.println("Nombre de minutes ");
            int m= sc.nextInt();
            System.out.println("Nombre de secondes ");
            int s= sc.nextInt();

            time = h*3600 + m*60 +s;

            if ((h<0 || h>24) || (m<0 || m>59) || (s<0 || s>59)) {
                d=0; time = 0;
                System.out.println("Votre saisie n'est pas correcte. Entrée abandonnée");
            }
        }
        catch (InputMismatchException m) {
            System.out.println("erreur de format dans la saisie");}
        catch (NumberFormatException n  ){
            System.out.println("ce nombre doit etre un entier");}
        finally { sc.nextLine();}


        for (int i=0; i<this.nb; i++) {
            try {
                if (Integer.valueOf(tab[i][0]) == d ) {
                    found = true;
                    tab[i][2] = Integer.toString(time);
                    rang = i;
                }
            }
            catch (IndexOutOfBoundsException e ) {
                System.out.println("erreur d'index");
            }
        }

        if (found == true) {System.out.println("temps du  coureur au dossard N° " + tab[rang][0] + " enregistré" );}
        else {System.out.println("N° de dossard inconnu, ou votre saisie est incorrecte");}
        System.out.println("");
    }

    protected String getTemps() {

        System.out.println ("Temps du coureur: ");
        String time="";
        int d = nDossard();
        boolean found = false;

        for (int i=0; i<this.nb; i++) {
            try {
                if (Integer.valueOf(tab[i][0]) == d ) {
                    found = true;
                    int t = Integer.valueOf(tab[i][2]);
                    if ((t==0) && (tab[i][3]== "X")) {System.out.println(" Le coureur a abandonné ou a été disqualifié"); }
                    else {
                        int h = (int) Math.floor(t/3600);
                        int mn = (int) Math.floor((t -h*3600)/60);
                        int s = (int) Math.floor(t - h*3600 - mn*60);

                        time = Integer.toString(h) + "h " + Integer.toString(mn) + "mn " + Integer.toString(s) + "s";
                        System.out.println(" temps du dossard " + Integer.toString(d) + " : " +  time);

                    }
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("erreur d'index");
            }
        }
        if (found != true) { System.out.println(" votre requete n'a pas pu etre executee -- Ce coureur n'a pas participe");}

        System.out.println("");

        return time;
    }

    protected String DiffTemps() {

        System.out.println ("Difference de temps - indiquez les 2 coureurs ");
        int time1 =0;
        int time2=0;
        int diff=0;
        int d1 =0;
        int d2=0;
        boolean found1 = false;
        boolean found2 = false;

        try {
            System.out.println("Quel est le dossard du 1er coureur? ");
            d1 = sc.nextInt();
            System.out.println("Quel est le dossard du 2nd coureur? ");
            d2 = sc.nextInt();
        }
        catch (InputMismatchException m) {
            System.out.println("erreur de format dans la saisie");}
        finally { sc.nextLine();}

        for (int i=0; i<this.nb; i++) {
            try {
                if (Integer.valueOf(tab[i][0]) == d1 ) {
                    found1 = true;
                    time1 = Integer.valueOf(tab[i][2]);
                }
                if (Integer.valueOf(tab[i][0]) == d2 ) {
                    found2 = true;
                    time2 = Integer.valueOf(tab[i][2]);
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("erreur d'index");
            }
        }

        diff = Math.abs(time1 - time2);
        int h = (int) Math.floor(diff/3600);
        int mn = (int) Math.floor(diff -h*3600)/60;
        int s = (int) Math.floor(diff - h*3600 - mn*60);

        String temps = Integer.toString(h) + "h " + Integer.toString(mn) + "mn " + Integer.toString(s) + "s";

        if ((found1 == true) && (found2 == true)) {System.out.println("La difference entre le coureur dossard " + d1 + " et le coureur dossard " + d2 + " est: " + temps);}
        else { System.out.println(" votre requete n'a pas pu etre executee -- Au moins un coureur n'a pas participe");}

        System.out.println("");

        return temps;
    }

    protected void classProv(){

        int size = nbArrive();
        String [] classement = new String[size];

        // Tri des coureurs arrivés
        for (int i=0; i<this.nb; i++) {
            if (tab[i][3] == "Arr") {
                int index = (Integer.valueOf(tab[i][6]))-1; //recupere le classement du coureur et le place a cet index dans le tableau
                classement[index] = tab[i][1];
            }
        }
        System.out.println("Classement provisoire:");
        for (int i=0; i<size; i++) {
            System.out.println(Integer.toString(i+1) + " : " + classement[i]);
        }
        System.out.println("");
    }

    private int nbArrive() {

        int nbArr = 0;
        for (int i=0; i<this.nb; i++) {
            if (tab[i][3] == "Arr") { nbArr++;}
        }
        return nbArr;
    }

    // Numero dossard
    private int nDossard() {

        int d=0;
        System.out.println("Quel est le dossard du coureur? ");
        try {
            d = sc.nextInt();
        }
        catch (InputMismatchException m) {
            System.out.println("erreur de format dans la saisie");
            d=0;
            sc.nextLine();// vide le cache du scanner
            System.out.println("Appuyé sur entrée pour continuer");}

        return d;
    }

    protected void affichage() {

        System.out.println("");
        System.out.println("Commandes possibles :");
        System.out.println("-------------------");
        System.out.println("");
        System.out.println("Tapez det puis entrée pour voir les actions des commndes");
        System.out.println("Tapez aff puis entrée pour afficher la liste des coureurs");
        System.out.println("Tapez arr puis entrée pour enregistrer une arrivée");
        System.out.println("Tapez abd puis entrée pour enregister un abandon");
        System.out.println("Tapez dsq puis entrée pour enregister une disqualification");
        System.out.println("Tapez tar puis entrée pour enregister un temps d'un coureur");
        System.out.println("Tapez tg puis entrée pour obtenir le temps d'un coureur");
        System.out.println("Tapez diff puis entrée pour obtenir la difference de temps entre deux coureurs");
        System.out.println("Tapez prov puis entrée pour obtenir un classement provisoire");
        System.out.println("Tapez QUIT puis entrée pour sortir de cette application");
        System.out.println("");
    }

    protected void CompactAff() {

        System.out.println("");
        System.out.println("Commandes possibles : det - aff - arr - abd - dsq - tar - tg - diff - prov - QUIT");
        System.out.println("-------------------");
        System.out.println("");
    }
}
