package arbre;

import java.util.LinkedList;

public class ArbreAr {
	private String x;
	private ArbreAr filsgauche, filsdroit;
       //CONSTRUCTEURS
       public ArbreAr(){	 }
       public ArbreAr(String val){
         this.x = val;
       }
	 
       // ACCESSEURS
        public String getValeur() {
            return(x);
        }
	    //SETTEURS
       public ArbreAr getSousArbreGauche() {
              return(filsgauche);
          }   
          public ArbreAr getSousArbreDroit() {
              return(filsdroit);
          }
       public void setfilsgauche(ArbreAr arbr){
         this.filsgauche = arbr;
       }	 
       public void setfilsdroit(ArbreAr arbr){
         this.filsdroit = arbr;
       }
       //---------------------------------------------------------
		
	 /**
	  * Calcul de la haueur de l'arbre 
	  * @param racine
	  * @return int
	  */
	 public static int hauteur(ArbreAr racine) {
			if (racine == null)
			    return 0;
			else
			    return (1 + Math.max(hauteur(racine.getSousArbreGauche()), hauteur(racine.getSousArbreDroit())));
		    }
        
	   /**
	     * Afficher l'arbre selon un parcours en largeur
	     */
	    public static void ParcoursLargeur(ArbreAr ab) {
	    	LinkedList<ArbreAr> file = new LinkedList<ArbreAr>();
		file.add(ab);
		ArbreAr a,b;
		while (!file.isEmpty()) {
		    a = (ArbreAr) file.removeFirst();
		    System.out.print(a.getValeur()+" ");
		    b = a.getSousArbreGauche();
		    if (b != null)
			file.add(b);
		    b = a.getSousArbreDroit();
		    if (b != null)  
			file.add(b);
		}
		System.out.print("\n\n");
		return;
	    } 
	    
	    // AFFICHAGE DE L'ARBRE-----------------
	    public String toString() {
	        return toString("\t");
	    }

	    public String toString(String s) {
		if (filsgauche!=null) {
		if (filsdroit!=null) 
		    return(s+"_("+x+")___\n"+filsgauche.toString(s+"\t")+filsdroit.toString(s+"\t"));
		else
		    return(s+x+"\n"+filsgauche.toString(s+"\t")+"\n");
	        }
	        else 
		if (filsdroit!=null) 
		    return(s+x+"\n\n"+filsdroit.toString(s+"\t"));
		else
		    return(s+x+" \n");
	    }
   }
