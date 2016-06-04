package Utilitaires;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DijkstraCalculator {

    private int distances[];
    private Set<Integer> settled;
    private Set<Integer> unsettled;
    private int nombreNoeuds;
    private int Matrice[][];

    public DijkstraCalculator(int Matrix[][], int taille)

    {

        this.nombreNoeuds = taille;
        Matrice = new int[taille][taille];
        Matrice = Matrix;
        distances = new int[nombreNoeuds];
        settled = new HashSet<Integer>();
        unsettled = new HashSet<Integer>();
    }

    public void dijkstra_algorithm(int adjacency_matrix[][], int source) {
        int evaluationNode;
        for (int i = 0; i < nombreNoeuds; i++)
            for (int j = 0; j < nombreNoeuds; j++)
                Matrice[i][j] = adjacency_matrix[i][j];

        for (int i = 0; i < nombreNoeuds; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        unsettled.add(source);
        distances[source] = 0;
        while (!unsettled.isEmpty()) {
            evaluationNode = getNodeWithMinimumDistanceFromUnsettled();

            unsettled.remove(evaluationNode);

            settled.add(evaluationNode);

            evaluateNeighbours(evaluationNode);
        }
    }

    public int minTriangularDistance(int adjacency_matrix[][], int source, int dest) {
        this.dijkstra_algorithm(adjacency_matrix, source);
        return this.distances[dest];
    }

    private int getNodeWithMinimumDistanceFromUnsettled() {
        int min;
        int node = 0;
        Iterator<Integer> iterator = unsettled.iterator();
        node = iterator.next();
        min = distances[node];
        for (int i = 0; i < distances.length; i++) {
            if (unsettled.contains(i)) {
                if (distances[i] <= min) {
                    min = distances[i];
                    node = i;
                }
            }
        }
        return node;
    }

    private void evaluateNeighbours(int evaluationNode)

    {
        int edgeDistance = -1;
        int newDistance = -1;
        for (int destinationNode = 0; destinationNode < nombreNoeuds; destinationNode++) {
            if (!settled.contains(destinationNode)) {
                if (Matrice[evaluationNode][destinationNode] != Integer.MAX_VALUE) {
                    edgeDistance = Matrice[evaluationNode][destinationNode];
                    newDistance = distances[evaluationNode] + edgeDistance;
                    if (newDistance < distances[destinationNode]) {
                        distances[destinationNode] = newDistance;
                    }
                    unsettled.add(destinationNode);
                }
            }
        }
    }
}
