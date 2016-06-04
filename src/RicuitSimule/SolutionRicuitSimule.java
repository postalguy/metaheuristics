package RicuitSimule;

import java.util.Random;

import Environement.GrapheGenerer;
import GloutonPack.Glouton;

public class SolutionRicuitSimule {

    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int taille_prob = 4;
        int nb_centres = 2;
        Random ran = new Random();
        int sommetDep = ran.nextInt(taille_prob - 1);
        System.out.println("---------------------------------------------------------------");
        System.out.println("--------------Debut du ricuit Simule---------------");
        long debut = System.currentTimeMillis();
        System.out.println("Solution Initiale : ");
        GrapheGenerer g1 = new GrapheGenerer(taille_prob);
        g1.afficher();

        Glouton Solution_Courante = new Glouton(g1, nb_centres, sommetDep);
        Solution_Courante.AfficherCout();
        // Set initial temp
        double temp = 100;
        // Cooling rate
        double coolingRate = 0.003;

        Glouton Meilleur = Solution_Courante;
        int sometvoisin = 1;

        while (temp > 1) {
            while (sometvoisin == Solution_Courante.getCentre_initiale()) {
                sometvoisin = ran.nextInt(taille_prob);
            }
            System.out.println("on cherche dans un voisin :");
            Glouton voisin = new Glouton(g1, nb_centres);
            voisin.AfficherCout();
            int currentEnergy = Solution_Courante.getCout();
            int neighbourEnergy = voisin.getCout();

            // Decide if we should accept the neighbour
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                Solution_Courante = voisin;
            }

            // Keep track of the best solution found
            if (Solution_Courante.getCout() < Meilleur.getCout()) {
                Meilleur = Solution_Courante;

            }

            // Cool system
            temp *= 1 - coolingRate;

        }

        System.out.println("Cout Optimal trouvé " + Meilleur.getCout());
        System.out.print("Les Centres : ");

        long fin = System.currentTimeMillis() - debut;
        Meilleur.AfficherLesCentres();
        Meilleur.AfficherLesAffiliations();
        System.out.println();
        System.out.println("Temps de résoution : " + fin + " ms");

    }
}
