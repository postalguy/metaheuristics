package Genetique;

import java.util.ArrayList;
import java.util.Random;
import Environement.GrapheGenerer;
import GloutonPack.Glouton;


public class Genetique {
	private GrapheGenerer gr;
    private int NombreDeCentres;
	private ArrayList<Integer> pere,mere,fils;
	private int n;
		

		public int getN() {
			return n;
		}

		public void setN(int n) {
			this.n = n;
		}

public Genetique(GrapheGenerer g, int nbc){
	this.gr=g;
	this.NombreDeCentres = nbc;
	Random ran = new Random();
	int a= ran.nextInt(g.getTaille()-1);
	int b=0;
   do{	 b =  ran.nextInt(g.getTaille()-1);
   }while(a == b);
	
 	this.pere=new Glouton(gr,NombreDeCentres,a).getCentres();
	this.mere=new Glouton(gr,NombreDeCentres,b).getCentres();
}		

public void GO(GrapheGenerer g)
{

	System.out.println("\n\n********************* Methode Genetique *************************************\n ");
	long t1 = System.currentTimeMillis();
	Random ran = new Random();
	int a= ran.nextInt(g.getTaille()-1);
	int b=0;
   do{	 b =  ran.nextInt(g.getTaille()-1);
   }while(a == b);

	Glouton gloutonPere =new Glouton(gr,NombreDeCentres,a);
	Glouton gloutonMere=new Glouton(gr,NombreDeCentres,b);
	fils= new ArrayList<Integer>();
	ArrayList<Integer> centrespere = gloutonPere.getCentres();
	ArrayList<Integer> centresmere = gloutonMere.getCentres();
	fils=Reproduction(centrespere, centresmere); 
	Glouton gloutonFils = new Glouton(gr,NombreDeCentres,fils);
    
	System.out.println("Liste des centres du Glouton pere  :");
	gloutonPere.AfficherLesCentres();
	System.out.println("Les Affiliations du Glouton pere  :");
	gloutonPere.AfficherLesAffiliations();
	System.out.println("Le cout du Gouton pere  :");
	gloutonPere.AfficherCout();
	
	
	System.out.println("Liste des centres du Glouton mere  :");
	gloutonMere.AfficherLesCentres();
	System.out.println("Les Affiliations du Glouton mere  :");
	gloutonMere.AfficherLesAffiliations();
	System.out.println("Le cout du Glouton mere  :");
	gloutonMere.AfficherCout();
	
	System.out.println("Liste des centres du Gouton fils  :");
	gloutonFils.AfficherLesCentres();
	System.out.println("Les Affiliations du Gouton pere  :");
	gloutonFils.AfficherLesAffiliations();
	System.out.println("Le cout du Gouton pere  :");
	gloutonFils.AfficherCout();
		
}

public ArrayList<Integer> Reproduction(ArrayList<Integer> pere, ArrayList<Integer> mere){
			ArrayList<Integer> fils =new ArrayList<Integer>();
			Integer r;
			for (int i=0;i<this.getN();i++){
			   r= new Random().nextInt(2);
			   if(r==0){
				    if(!fils.contains(pere.get(i))) 
				         	fils.add(pere.get(i));
				    else {
				    	if(!fils.contains(mere.get(i))){
				    		fils.add(mere.get(i));
				          }else{
				        	   if(!fils.contains(mere.get(0))){
				        		       fils.add(mere.get(0));
				        	          }else{fils.add(pere.get(i));} 
		                       }
	     	              }
				 }else{
					 if(!fils.contains(mere.get(i))){
				    		fils.add(mere.get(i));
					 }else {
				    	if(!fils.contains(pere.get(i))){
				    		fils.add(pere.get(i));
				          }else{
				        	   if(!fils.contains(mere.get(0))){
				        		       fils.add(mere.get(0));
				        	          }else{fils.add(pere.get(i));} 
		                       }
	  	              }
				    }
				        	  
				}				
			return fils;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tailleduprobleme = 6;
		int nombreDeCentres = 2; 
		GrapheGenerer Graph = new GrapheGenerer(tailleduprobleme);
		Genetique ge=new Genetique(Graph, nombreDeCentres);
		ge.GO(Graph);
		
		}

}
