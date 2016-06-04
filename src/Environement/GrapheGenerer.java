package Environement;

public class GrapheGenerer {

    private int taille;
    private int graphe[][];

    public GrapheGenerer(int taille) {
        this.taille = taille;

        graphe = new int[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = i; j < taille; j++) {
                if (i == j)
                    graphe[i][j] = 0;
                else {
                    this.graphe[i][j] = 1;
                    this.graphe[j][i] = 1;
                }
            }
        }
        this.Generation_Couts();
    }

    public int[][] getGraphe() {
        return this.graphe;
    }

    public int getTaille() {
        return this.taille;
    }

    public void Generation_Couts() {
        for (int i = 0; i < taille; i++) {
            for (int j = i; j < taille; j++) {
                if (i == j)
                    graphe[i][j] = 0;
                else {
                    int s = (int) (Math.random() * 20);
                    this.graphe[i][j] = s;
                    this.graphe[j][i] = s;
                }
            }
        }
    }

    public void afficher() {
        System.out.println("");
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                System.out.print("\t" + graphe[i][j]);
            }
            System.out.println("");
        }
    }

}
