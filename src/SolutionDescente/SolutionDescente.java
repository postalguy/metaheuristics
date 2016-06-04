package SolutionDescente;

import Environement.GrapheGenerer;
import GloutonPack.Glouton;

public class SolutionDescente {

    int CoutsolutionCourante;
    int nb_centres;
    GrapheGenerer gr;
    Glouton solution_en_tempon;

    public SolutionDescente(GrapheGenerer gg, int nbc) {
        this.gr = gg;
        this.nb_centres = nbc;
        this.solution_en_tempon = new Glouton();

    }

    public int getSolutionCourante() {
        return CoutsolutionCourante;
    }

    public void setCoutSolutionCourante(int solutionCourante) {
        this.CoutsolutionCourante = solutionCourante;
    }

    public GrapheGenerer getGr() {
        return gr;
    }

    public void setGr(GrapheGenerer gr) {
        this.gr = gr;
    }

    public Glouton Voisin(Glouton gl) {
        Glouton voisin = new Glouton(gl.getGr(), gl.getGr().getTaille());
        return voisin;
    }

    public void MethodeDescente() {
        System.out.println("Solution optimale avec Methode Descente : ");
        System.out.println("Debut avec la  solution initiale :");
        Glouton solusInitiale = new Glouton(this.getGr(), this.nb_centres);
        solusInitiale.AfficherLesCentres();
        solusInitiale.AfficherLesAffiliations();
        this.solution_en_tempon = solusInitiale;
        this.setCoutSolutionCourante(solusInitiale.getCout());
        System.out.println("qui donne un cout: " + solusInitiale.getCout());
        while (1 > 0) {

            System.out.println("solution voisine :");
            Glouton solution_voisine = new Glouton(this.getGr(), this.nb_centres);
            solution_voisine.AfficherLesCentres();
            solution_voisine.AfficherLesAffiliations();
            System.out.println("qui donne un cout: " + solution_voisine.getCout());
            System.out.println("Comparaison des couts...");
            if (this.getSolutionCourante() > solution_voisine.getCout()) {
                this.solution_en_tempon = solution_voisine;
                this.setCoutSolutionCourante(solution_voisine.getCout());
                System.out.println("en retient Celle du voisin (dernier)");
            } else
                break;
        }
        System.out.println("\n\n\tSolution finale retenue : ");
        this.solution_en_tempon.AfficherLesCentres();
        System.out.println("Avec un cout: " + this.CoutsolutionCourante);
    }
    // Test
    /*
     * public static void main(String[] args) { int tailleduprobleme = 6; int nombreDeCentres = 2; GrapheGenerer Graph = new
     * GrapheGenerer(tailleduprobleme); System.out.println("on a " + tailleduprobleme + " villes, et on cherche à créer " +
     * nombreDeCentres + " Centres."); System.out.println("Graphe de la problématique :"); Graph.afficher(); SolutionDescente sol
     * = new SolutionDescente(Graph, nombreDeCentres); sol.MethodeDescente(); }
     */

}
