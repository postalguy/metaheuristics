package Fourmies;

import java.util.ArrayList;

import Environement.GrapheGenerer;

public class FourmisSolution {

    private double Pheromone[][];
    private GrapheGenerer gr;

    private Integer Centre_Debut;
    private int nb_centres;
    private int Max_Phero;
    private final double evaporation = 0.5;

    public FourmisSolution(GrapheGenerer gr, int nb_centres) {
        this.nb_centres = nb_centres;
        this.Pheromone = new double[gr.getTaille()][gr.getTaille()];
        for (int i = 0; i < Pheromone.length; i++) {
            for (int j = 0; j < Pheromone.length; j++) {
                Pheromone[i][j] = 0;
            }
        }
        this.gr = gr;

        Max_Phero = 0;

    }

    public void Exploration(int colonie) {

        Fourmi eclaireuse = new Fourmi(colonie);
        for (int i = 0; i < this.gr.getTaille(); i++) {
            if (!eclaireuse.getVillesConsultes().contains(i)) {
                eclaireuse.eclairer(i);
                MiseajourPheromone(eclaireuse.getColonie(), i);
            }
        }
    }

    public void MiseajourPheromone(int villeA, int villeB) {
        // (1 - evaporation) Pheromone[i, j] + alpha * K
        // K = 1 / ( N * Lmv ) N nombre de villes, LMV ditance entre A et B
        this.Pheromone[villeA][villeB] = (1 - evaporation) + evaporation
                * (1 / (this.gr.getTaille() * this.gr.getGraphe()[villeA][villeB]));

    }

    public void LancerLesFourmies(int colonie) {
        ArrayList<Fourmi> Fourmies = new ArrayList<>();
        int nbFourmies = 20;
        for (int i = 0; i < nbFourmies; i++) {
            Fourmi fourmie_ = new Fourmi(colonie);
            Fourmies.add(fourmie_);
        }
    }

}
