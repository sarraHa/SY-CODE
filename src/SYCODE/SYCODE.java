
package SYCODE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import sun.java2d.loops.DrawLine;


public class SYCODE extends Application {
    
	// ce tableau va nous dire si cet emplacement est utilisé ou pas,
	//quand un emplacement est utilisé on met 1 à la place de -1
    // tab_emplacement_1 et pour la premiere partie du SY-CODE
    // et tab_emplacement_12 et pour la 2eme partie
    public static int[] tab_emplacement_1 = new int[150];
    public static int[] tab_emplacement_2 = new int[150];

    public static StringBuilder emplacement_1 = new StringBuilder() ;
    public static StringBuilder emplacement_2 = new StringBuilder() ;

    
    public static void initialisationTab_emplacement(){
   
       for( int i = 0 ; i < 150 ; i++){
           
           tab_emplacement_1[i] = -1;
           tab_emplacement_2[i] = -1;

       }
          
   } 
    public static int emplacementALeatoire(){

      Random ran = new Random();     
      int e =  ran.nextInt( 150 );
      
      while( tab_emplacement_1[e] != -1  ){

        e =  ran.nextInt( 150 );
      }

      tab_emplacement_1[e] = 1;
      System.out.println( e );       
      return e;
    }
    
    //c'est pour la 2eme partie de la matrice
    public static int emplacementALeatoire_2(){

	  Random ran = new Random();     
	  int e =  ran.nextInt( 150 );

	  while( tab_emplacement_2[e] != -1  ){

		  e =  ran.nextInt( 150 );
	  }

	  tab_emplacement_2[e] = 1;
	  System.out.println( e );       
	  return e;
    }
       
    public static void stockEmplacement( int e ){
       
       emplacement_1.append( convertToBinaryUsingString( e  ).toString() ) ;
    }
    
    public static void stockEmplacement_2( int e ){
       
       emplacement_2.append( convertToBinaryUsingString( e  ).toString() ) ;
    }
    
    // on met les tableaux d'empalcements 1 et 2 dans leur matrice respectives
    public static void stockEmplacement( int matr_emplacement[][] ){
    
        int compteur = 0 ;
        for(int k = 0; k < 25 ; k++){
            for( int j = 0 ; j < 48 ; j++){
           
                if( compteur == emplacement_1.length() )
                    return ;
                else {
                    matr_emplacement[k][j] = Character.getNumericValue(emplacement_1.charAt(compteur));
                    compteur++;
                }     
            }
           
        }

    }
    
    public static void stockEmplacement_2( int matr_emplacement_2[][] ){
    
        int compteur = 0 ;
        for(int k = 0; k < 25 ; k++){
            for( int j = 0 ; j < 48 ; j++){
           
                if( compteur == emplacement_2.length() )
                    return ;
                else {
                    matr_emplacement_2[k][j] = Character.getNumericValue(emplacement_2.charAt(compteur));
                    compteur++;
                }     
            }
           
        }

    }
    
    //  cette fonction prend un caractere et son empalcement et le stocke
    public static void stockDonnees( int e , String c , int matr_Donnees_1[][] ){
        
        e = e * 8 ;
        int i = e / 48 ;
        int j = e - i * 48;

        System.out.println( i );     
        System.out.println( j );       
       
        StringBuilder donnees =  ChaineToBinaire( c );        
        System.out.println( donnees.toString()  );       

        int compteur = 0 ;
        for(int k = j; k < j+8 ; k++){
            
            matr_Donnees_1[i][k] = Character.getNumericValue(donnees.charAt(compteur));
            compteur++;

        }
    }
    
     public static void stockDonnees(  String s, int matr_Donnees_1[][] , int matr_Donnees_2[][] ){

        for (char c: s.toCharArray()) {
           
            String caractere = String.valueOf(c);  
           // je prends un nombre aleatoire
           int nb  = emplacementALeatoire() ;
           int nb2 = emplacementALeatoire_2();
           stockEmplacement( nb );
           stockEmplacement_2( nb2 );


           stockDonnees( nb, caractere , matr_Donnees_1 );  
           stockDonnees( nb2, caractere , matr_Donnees_2 );     

        }
    }
    
    static void afficheMatrice( int matrice[][] , int nbLignes ){
    
        for(int i=0;i<nbLignes;i++){
           System.out.print("\n");
           for(int j=0; j<48;j++){
               System.out.print(matrice[i][j]);
           }
        }
    }
    
    static void initialisationMatrices( int matrice[][], int matr_Donnees_1[][], int matr_emplacement[][], int matr_Donnees_2[][], int matr_emplacement_2[][]   ){
    
        for(int i=0;i<25;i++){
           for(int j=0; j<48;j++){
               matrice[i][j] = -1 ;
               matr_Donnees_1[i][j] = -1 ;
               matr_emplacement[i][j] = -1 ;
               matr_Donnees_2[i][j] = -1 ;
               matr_emplacement_2[i][j] = -1 ;

           }
        }
        
        for(int i=25;i<100;i++){
            for(int j=0; j<48;j++){

                matrice[i][j] = -1 ;

            }
        }
        
        
    }
    
    static StringBuilder convertToBinaryUsingString(int no){
        StringBuilder result = new StringBuilder();

        int i =0;

        while (no > 0){
            result.append(no%2);
            i++;
            no = no/2;
        }
                
        if( result.toString().length() != 8 ){

            StringBuilder result2 = new StringBuilder();
            for( i = 0 ; i < 8- result.toString().length() ; i++ ){
            
                result2.append('0');
            }
  
            result2.append( result.toString() ) ;
            return result2 ;
        }
        
        return result ;
    }
    
    public static StringBuilder ChaineToBinaire(String chaine){
        byte[] bytes = chaine.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary;
    }
    
    public static void copieMatrices( int[][] matrice ,  int[][] matr_Donnees_1,  int[][] matr_emplacement,  int[][] matr_Donnees_2,  int[][] matr_emplacement_2, int tailleDonnees){
        
        int i , j;
        // je mets les colonnes du côté en noir pour la separation
        
        for( i = 0 ; i < 102 ;i++){
        
            for( j=48 ; j<51 ; j++){
            
				matrice[i][j] = 1;
            }
        }
        
        for( i = 0 ; i < 102 ;i++){
        
            for( j=49 ; j<50 ; j++){
            
				matrice[i][j] = 0;
            }
        }
       
        
        
        // je commance par copier l'emplacement 1
        
        for( i = 0 ; i < 25 ;i++){
        
            for( j=0 ; j<48 ; j++){
            
				matrice[i][j] = matr_emplacement[i][j];
            }
        }
        
        // apres je copie les donnees de la matrice 1
        int k = 0 ;
        for( i = 25 ; i < 50 ;i++){
        
            for( j=0 ; j<48 ; j++){
            
				matrice[i][j] = matr_Donnees_1[k][j];
            }
            k++;
        }
        
        // je mets une ligne de separation     (la ligne 50)   
        for( j=0 ; j<8; j++){

			matrice[50][j] = 1;
        }
    
        // je stocke dans cet espace le nombre de caractere saisi
    
        StringBuilder donnees =  convertToBinaryUsingString( tailleDonnees );  
        
               
        for( j=8; j<40 ; j++){

			matrice[50][j]  = 0;               
        }
        
        int compteur = 0;
        for( j=0; j< donnees.length() ; j++){

            matrice[50][8+j]  =  Character.getNumericValue(donnees.charAt(compteur));
            compteur++;
               
        }
                 
		for( j=40 ; j<48 ; j++){

			matrice[50][j] = 1;
        }
     
        
         // apres je copie encore l'emplacement 2 pour avoir de la redondance 
        k = 0 ;
        for( i = 51 ; i < 76 ;i++){
        
            for( j=0 ; j<48 ; j++){
            
				matrice[i][j] = matr_emplacement_2[k][j];
            }
            k++;
        }
        // puis je finis par copier encore les donnees pour avoir de la redondance 
        k = 0 ;
        for( i = 76 ; i < 101 ;i++){
        
            for( j=0 ; j<48 ; j++){
            
				matrice[i][j] = matr_Donnees_2[k][j];
            }
            k++;
        }
        
        // je mets une derniere ligne de separation     (la ligne 101)   
        for( j=0 ; j<48 ; j++){

			matrice[101][j] = 1;
        }
        
    }
    
    static int type;
    static boolean isOk = true;
    static String texteDonnees = "";
    @Override
    public void start(Stage primaryStage) {
        
        Label label3 = new Label(" Entrez vos données : (Texte,numéro,coordonnées gps...)");
        TextArea text = new TextArea("Données à entrer ici, ne pas dépasser 150 caractères.");

        ToggleGroup radioGroup = new ToggleGroup();

        Button btn = new Button();
        btn.setText("Generer");

        btn.setOnAction(new EventHandler<ActionEvent>() {
       
            //String texteDonnees;
               @Override
                public void handle(ActionEvent event) {

                    type = 0;
                    texteDonnees = text.getText();

                    if(texteDonnees.length() > 150){
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Trop de caractères");

                        // alert.setHeaderText("Results:");
                        alert.setContentText("Le nombre de caractères que vous avez entré est trop grand!");
                        isOk = false;
                        alert.showAndWait();
                        primaryStage.close();
                    }
                 primaryStage.close();  
                }    
        });
        
   
        FlowPane root = new FlowPane();
        
        root.getChildren().add(label3);
        root.getChildren().add(text);
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root);
    
        primaryStage.setTitle("Generer un SY CODE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // notre matrice est composée de 4 parties différente
       // 1ère partie, emplacement aleatoire des donnees
       int[][] matr_emplacement_1 = new int[25][48];
       // 2eme partie, les donnees      
       int[][] matr_Donnees_1 = new int[25][48];
       // 3eme partie, nouvel emplacement des même donnees pour faire de la rendandance
       int[][] matr_Donnees_2 = new int[25][48];
       // 4eme partie : les donnees
       int[][] matr_emplacement_2 = new int[25][48];
       
       // la matrice s'appelant "matrice" va contenir les 4 parties
       int[][] matrice = new int[102][51];

       // on initialise les 4 parties au depart à -1
       initialisationMatrices( matrice, matr_Donnees_1, matr_emplacement_1, matr_Donnees_2, matr_emplacement_2    );
      // on initialise les 2 tableaux d'emplacement
       initialisationTab_emplacement();

        launch(args);
        // cette fonction stocke les donnees dans la matrice 1 et 2
        // elle stocke aussi l'emplacement de chaque caractere dans les
        // tableaux d'emplacement
        
        stockDonnees( texteDonnees, matr_Donnees_1, matr_Donnees_2 );
        
        stockEmplacement( matr_emplacement_1  );
        stockEmplacement_2( matr_emplacement_2 );
      
        int i,j,valeur,compteur;
        if(!isOk){
        
            return;
        }

        System.out.println( "matrice de donnees 1");
        afficheMatrice( matr_Donnees_1 , 25 );
        System.out.println( "\n");

        
        System.out.println( "matrice de donnees 2");
        afficheMatrice( matr_Donnees_2 , 25 );
        System.out.println( "\n");
       
        System.out.println( "affichage du tableau d'empalcement 1");
        System.out.println( emplacement_1.toString() );
        System.out.println( "\n");       
     
        
        // on copie toutes les donnees dans la matrice        
        copieMatrices( matrice ,  matr_Donnees_1,  matr_emplacement_1,  matr_Donnees_2,  matr_emplacement_2 , texteDonnees.length() );
     
        System.out.println( "\n\nMATRICE : \n");

        afficheMatrice( matrice , 100 );
        System.out.println( "\n");

        
        //************* partie graphique *******************
        JFrame frame=new JFrame("SY CODE");
        frame.getRootPane().setBorder(BorderFactory.createDashedBorder(null,7,5,5,false));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0,51)); // on n'a pas besoin de mettre le nombre de lignes si on donne un nombre de colonnes

        for(i=0; i<102; i++) {
            for(j=0; j<51; j++) {
                valeur = matrice[i][j];
                JPanel cellule = new JPanel(); // on utilise un simple JPanel pour chaque cellule, donc on adaptera la couleur de fond (background)
                cellule.setPreferredSize(new Dimension(5,5)); 
                if ( valeur ==0 ) {
                    cellule.setBackground(Color.WHITE);
                }
                else if(valeur == 1) {
                    cellule.setBackground(Color.BLACK);
                }else{
                    cellule.setBorder(BorderFactory.createEtchedBorder());
                }
                panel.add(cellule);
           }
        }

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);  
                 
    }
    
}