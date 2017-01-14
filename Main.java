package arbre;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static Scanner sc;
	 //-----------------------------------------------------------------xxxxxxxxxxxxxxxxxxxxxxx
	public static void main(String[] args) {
				
		ArbreAr aux, courant, racine = new ArbreAr();
		ArrayList<String> list = new ArrayList<>();
		String PostEntre;
		ArrayDeque<ArbreAr> pil = new ArrayDeque<ArbreAr>();
		int emp = 100, pro = 100;
		
		sc = new Scanner(System.in);
		
		System.out.println("NB. Separer chaque element par un espace \npour le bon fonctionnement du programme (ex: 12 7 + )\n ");
		
		while(pro!=0){
			
		while(emp!=0){			
			//emp = 0;
		  System.out.println("\n "+ "Veuillez entrer l'expression postfixée--->");		   
		  PostEntre = sc.nextLine();//capture au clavier----------------
		  if (PostEntre.isEmpty()){System.out.println("OHOH");
			  emp = 1;}
		  else {
			  list = new ArrayList<>(Arrays.asList(PostEntre.split(" ")));
		  if(isOperator(list.get(0))){
			  System.out.println("Veuillez fournir une expression Postfixee");
			  emp = 2; }
		  else{
		  
		  int diffNbOps = 0;
		  for (String expr : list){
			  if (isOperator(expr))
				  diffNbOps = diffNbOps -1 ;			
			  else{
				  diffNbOps = diffNbOps +1 ;
			  }
			            			  
		  }
		  if (diffNbOps < 1){
			  System.out.println("Erreur : il y a des signes d'operation sans assez d'operandes ");
			  emp = 3;}
		  else if (diffNbOps > 1){
			  System.out.println("Erreur : il manque des signes d'operation ");
			  emp = 4;}
		  else{
			  for (String s : list){
				  courant = new ArbreAr(s);
				  if (isOperator(s)){
					 aux =  pil.pop();
					 if (pil.isEmpty()){
						 System.out.println("--- EXPRESSION ERRONEE ---");
						 break;
						 }
					 else{
					 courant.setfilsgauche(pil.pop());
					 courant.setfilsdroit(aux);}
				  }
				  pil.push(courant);				  
			  }
			  if (!pil.isEmpty()){emp= 0;
			  racine = new ArbreAr();
			  racine=pil.pop();}

		  }
		  }//else expression prefix
		  }//else string vide
		  
		}//while emp=0		  
		
		System.out.println("\n==========Choisissez une option====================");
		  System.out.println("1- Affichez l'arbre. 2- Prefix expression. \n"
		  		+ "3- Evaluation.  4-Hauteur de l'arbre.  5- Parcour en largeuer de l'arbre.  \n"
		  		+ "6- Entrer une autre expression 0- Quittez \n");		  
		  pro = sc.nextInt();

		  
		  switch(pro){
		  case 1 : {System.out.println("\n L'arbre est : " );
		  System.out.println(racine );
		  }
			  break;
		  case 2 : Toprefix(list);
			  break;
		  case 3 : Evaluation(list);
			  break;			  
		  case 4 : System.out.println("\nLa hauteur de l'arbre est : " +ArbreAr.hauteur(racine)+"\n");
			  break;
		  case 5 : System.out.print("\nParcours en largeur de l'arbre donne: \n");ArbreAr.ParcoursLargeur(racine);
			  break;
		  case 6 : emp = 8; sc.nextLine();
		      break;
		  case 0 : System.out.println("Merci et aurevoir");
		      break;
		  default:System.out.println("\nRechoisissez\n");
		  }
		
		
	}//while pro = 0

	}
	
	//EVALUATION METHODE-----------------------------------------
	public static void Evaluation(ArrayList<String> list){
   	Double oper1, oper2,result;
		  ArrayDeque<Double> abc = new ArrayDeque<Double>();
		  if(isOperDigi(list)){
		  for (String n : list){
			  if (!isOperator(n))
				  abc.push(Double.valueOf(n));			
			  else{
				  oper2 = abc.pop();			  	
				  oper1 = abc.pop();
			  	result = operate(oper1,oper2,n);			           
			  		abc.push(result);
			  }				            			  
		  }
		  System.out.println("\nl'evaluation donne: ");
		  System.out.println(abc.pop()+"\n");
		  return;}
		  System.out.println("\nEvaluation echouee,\n veuillez entrez des entiers dans l'expression \n");
   }
	//OPERATION METHODE----------------
	 private static double operate(Double a, Double b, String op){
	        //Log.d("Calc", "w " +"operrrrr");
		 
	        switch (op){
	            case "+": return Double.valueOf(a) + Double.valueOf(b);
	            case "-": return Double.valueOf(a) - Double.valueOf(b);
	            case "*": return Double.valueOf(a) * Double.valueOf(b);
	            case "/": try{
	                return Double.valueOf(a) / Double.valueOf(b);
	            }catch (Exception e){
	                 e.getMessage();
	            }
	            default: return -1;
	        }
	    }
	
   private static boolean isOperDigi(ArrayList<String> op){
   	boolean var = true; 
   	for (String digi : op){
   		if (var==false)return var;
   		if (isOperator(digi))
   			var = true;
   		else{
   		for(int i=0;i<digi.length();i++)
   	          if(Character.isDigit(digi.charAt(i))) 
   	               var = true;else
   	     var = false;}
   		
   	}   		
       return var;
   }
	
   public static void Toprefix(ArrayList<String> list){
		  //----------------------------to prefix------------------------
		  String s1,s2,sor;
		  sc = new Scanner(System.in);
		  ArrayDeque<String> operst = new ArrayDeque<String>();
		  for (String n : list){
			  if (!isOperator(n))
				  operst.push(n);
			  else{
				s2  = operst.pop();
			  	s1  = operst.pop();
			  	sor = n +" "+ s1 +" "+ s2;
			            operst.push(sor);
			  }
			            			  
		  }
		  System.out.println("\n L'expression prefixee derivee est : ");
		  System.out.println(operst.pop()+"\n");
		  //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
   }

   //methode TEST SI C'EST UN OPPERATEUR
   private static boolean isOperator(String op){
       switch (op){
           case "+":
           case "-":
           case "*":
           case "/":return true;
           default: return false;
       }
   }

}
