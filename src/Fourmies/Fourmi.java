package Fourmies;

import java.util.ArrayList;

public class Fourmi {

    private int colonie;
    private ArrayList<Integer> villesConsultes;

    public Fourmi(int colonie) {
        super();
        this.colonie = colonie;
        this.villesConsultes = new ArrayList<>();
        this.villesConsultes.add(colonie);
    }

    public int getColonie() {
        return colonie;
    }

    public void setColonie(int colonie) {
        this.colonie = colonie;
    }

    public ArrayList<Integer> getVillesConsultes() {
        return villesConsultes;
    }

    public void setVillesConsultes(ArrayList<Integer> villesConsultes) {
        this.villesConsultes = villesConsultes;
    }

    public void eclairer(int ville) {
        this.villesConsultes.add(ville);

    }

}
