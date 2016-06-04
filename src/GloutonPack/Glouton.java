package GloutonPack;

import java.util.ArrayList;
import java.util.Random;

import Environement.GrapheGenerer;

public class Glouton {

    private int centre_initiale;
    private ArrayList<Integer> Centers;
    private GrapheGenerer gr;
    private int NombreDeCentres;
    private ArrayList<Integer> NonCentres;
    private int cout;
    private ArrayList<Integer> DistancesAffiliations;

    public Glouton() {

    }

    public Glouton(GrapheGenerer g, int nbc) {
        this.gr = g;
        this.NombreDeCentres = nbc;
        this.Centers = new ArrayList<Integer>();
        this.NonCentres = new ArrayList<Integer>();
        this.DistancesAffiliations = new ArrayList<Integer>();
        this.CentreInitiale();
        while (this.getCentres().size() < this.NombreDeCentres) {
            for (int i = 0; i < this.getCentres().size(); i++) {
                GetMaxDistancesFromCenters(this.getCentres().get(i));
            }
        }
        this.NonCentres = this.SavoirNonCentres();
        this.AfficherLesNonCentres();
        AfficherLesAffiliations();
        this.calculeCout();
        // System.out.println("Cout : " + this.getCout());
    }

    public Glouton(GrapheGenerer g, int nbc, int sommetdep) {
        this.gr = g;
        this.NombreDeCentres = nbc;
        this.Centers = new ArrayList<Integer>();
        this.NonCentres = new ArrayList<Integer>();
        this.DistancesAffiliations = new ArrayList<Integer>();
        this.CentreInitiale(sommetdep);
        this.CentreInitiale();
        while (this.getCentres().size() < this.NombreDeCentres) {
            for (int i = 0; i < this.getCentres().size(); i++) {
                GetMaxDistancesFromCenters(this.getCentres().get(i));
            }
        }
        this.NonCentres = this.SavoirNonCentres();
        this.AfficherLesNonCentres();
        AfficherLesAffiliations();
        this.calculeCout();
    }

    public void AfficherCout() {
        System.out.println("Cout : " + this.getCout());
    }

    public int getCout() {
        return cout;
    }

    public int getNombreDeCentres() {
        return NombreDeCentres;
    }

    public void setNombreDeCentres(int nombreDeCentres) {
        NombreDeCentres = nombreDeCentres;
    }

    public int getCentre_initiale() {
        return centre_initiale;
    }

    public void setCentre_initiale(int centre_initiale) {
        this.centre_initiale = centre_initiale;
    }

    public ArrayList<Integer> getCentres() {
        return Centers;
    }

    public void setCentres(ArrayList<Integer> centres) {
        Centers = centres;
    }

    public GrapheGenerer getGr() {
        return gr;
    }

    public void setGr(GrapheGenerer graphe) {
        this.gr = graphe;
    }

    // choix d'un centre aléatoire
    public void CentreInitiale() {
        Random rand = new Random();
        int cent = rand.nextInt(getGr().getTaille() - 1);
        this.setCentre_initiale(cent);
        this.Centers.add(this.centre_initiale);
        System.out.println("premier centre est la ville = " + cent);
    }

    // choix centre de départ fixé
    public void CentreInitiale(int c) {
        this.setCentre_initiale(c);
        this.Centers.add(this.centre_initiale);
    }

    // Distance avec Djikstra
    /*
     * public int Distance(int a, int b) { DijkstraCalculator Cal = new DijkstraCalculator(this.gr.getGraphe(),
     * this.gr.getTaille()); return Cal.minTriangularDistance(this.gr.getGraphe(), a, b); }
     */
    public int Distance(int a, int b) {

        return this.getGr().getGraphe()[a][b];
    }

    // trouve une distance max entre le centre initiale et les autres noeuds puis rend le noeud le plus loin comme un centre.
    public void GetMaxDistancesFromCenters(int center) {
        int maxDist = 0;
        ArrayList<Integer> Distances = new ArrayList<Integer>();
        for (int i = 0; i < this.getGr().getTaille() && !this.getCentres().contains(Integer.valueOf(i)); i++) {

            Distances.add(Distance(i, center));
            for (int j = 0; j < Distances.size(); j++) {
                if (Distances.get(j) > maxDist) {
                    maxDist = Distances.get(j);
                    System.out.println("nouveau centre trouvé: " + i);
                    this.Centers.add(i);
                }
            }

        }
    }

    public void AfficherLesCentres() {
        System.out.println("Les centres Choisis sont les Sommets :");
        System.out.print("{ ");
        for (int i = 0; i < this.getCentres().size(); i++) {
            System.out.print("Ville " + this.getCentres().get(i) + ",");
        }
        System.out.print("}");
        System.out.println("");
    }

    // Partie Affiliation

    public ArrayList<Integer> SavoirNonCentres() {
        for (int i = 0; i < this.getGr().getTaille(); i++) {
            if (!this.getCentres().contains(i)) {
                this.NonCentres.add(i);
            }
        }
        return NonCentres;
    }

    public void AfficherLesNonCentres() {
        System.out.println("Les villes restantes sont les Sommets :");
        System.out.print("[");
        for (int i = 0; i < this.NonCentres.size(); i++) {
            System.out.print("Ville: " + this.NonCentres.get(i) + " ");
        }
        System.out.print("]");
        System.out.println("");
    }

    public Integer Affilie(int point) {
        ArrayList<Integer> Distances_tempon = new ArrayList<>();
        for (int j = 0; j < this.getCentres().size(); j++) {
            Distances_tempon.add(this.Distance(point, this.getCentres().get(j)));
        }
        return this.indexOfminFromArraylist(Distances_tempon);
    }

    public void AfficherLesAffiliations() {
        System.out.println(" Affiliations apère la solution:");
        for (int i = 0; i < this.NonCentres.size(); i++) {
            int Affil = this.Affilie(this.NonCentres.get(i));
            System.out.println("la ville:" + this.NonCentres.get(i) + " affiliée à :" + Affil);
            this.DistancesAffiliations.add(this.Distance(this.NonCentres.get(i), Affil));
        }
        System.out.println("");
    }

    public Integer maxFromArraylist(ArrayList<Integer> ar) {
        Integer max = ar.get(0);
        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i) > max) {
                max = ar.get(i);
            }
        }
        return max;
    }

    public Integer IndexOfmaxFromArraylist(ArrayList<Integer> ar) {
        Integer max = ar.get(0);
        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i) > max) {
                max = ar.get(i);
            }
        }
        return ar.indexOf(max);
    }

    public Integer minFromArraylist(ArrayList<Integer> ar) {
        Integer min = ar.get(0);
        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i) < min) {
                min = ar.get(i);
            }
        }
        return min;
    }

    public Integer indexOfminFromArraylist(ArrayList<Integer> ar) {
        Integer min = ar.get(0);
        for (int i = 0; i < ar.size(); i++) {
            if (ar.get(i) < min) {
                min = ar.get(i);
            }
        }
        return ar.indexOf(min);
    }

    public void calculeCout() {
        this.cout = this.maxFromArraylist(this.DistancesAffiliations);
    }

    // Test du Glouton
    /*
     * public static void main(String[] args) { int tailleduprobleme = 5; int nombreDeCentres = 2; GrapheGenerer Graph = new
     * GrapheGenerer(tailleduprobleme); System.out.println("Problème de K Centres Avec Algorithme Glouton :");
     * System.out.println("Graphe de la problématique :"); Graph.afficher(); Glouton gl = new Glouton(Graph, nombreDeCentres);
     * System.out.println("cout = " + gl.getCout()); System.out.println("\n \n Second Essaie : "); Glouton gl2 = new
     * Glouton(Graph, nombreDeCentres); }
     */
}
