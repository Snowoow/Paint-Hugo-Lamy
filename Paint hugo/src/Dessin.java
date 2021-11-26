import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;



public class Dessin extends JPanel implements MouseInputListener {
    
    public int x,y;
    public int tailleCurseur = 10;
    public Color couleur;
    public String figureSuivante;
    public Point origine, fin1, fin2, origine2;
    public ArrayList<Figure> listeDeFigure= new ArrayList<Figure>();    // liste pour permettre l'ouverture et l'enregistrement des fichiers
    public ArrayList<Figure> listeDragged = new ArrayList<Figure>();    // liste pour afficher l'image en continu
    public ArrayList<Figure> listeDeRestauration = new ArrayList<Figure>();   
    


    public Dessin(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setTaille(int taille){
        this.tailleCurseur = taille;
    }

    public ArrayList<Figure> getlisteDeFigure() {
        return listeDeFigure;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setfigureSuivante(String figureSuivante) {
        this.figureSuivante = figureSuivante;
    }


    public void annuleLaDerniereFigure(){                                               //ici c'est pour le retour
        listeDeRestauration.add(listeDeFigure.get(listeDeFigure.size()-1));
        listeDeFigure.remove(listeDeFigure.size()-1);
        paintComponent(this.getGraphics());

    }

    public void reafficheLaDerniereFigure(){                                            // et là pour le restaure
        listeDeFigure.add(listeDeRestauration.get(listeDeRestauration.size()-1));
        listeDeRestauration.remove(listeDeRestauration.size()-1);
        paintComponent(this.getGraphics());
    }
    
    

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {   // aquí podemos recuperar los coordonnées d'origine de la mouse

        origine = new Point(e.getX(),e.getY());  
        /*if ((couleur != null) && (figureSuivante == "A la main") ){
            Cercle cercle = new Cercle(origine,Math.abs(tailleCurseur),couleur); 
                    listeDeFigure.add(cercle);
                }
        paintComponent(this.getGraphics());  //dessine la liste listeDeFigure*/
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) { 
        fin1 = new Point(e.getX(),e.getY()); 

        int width = fin1.getX()-origine.getX();            // ici on récupère les dimensions de la figure
        int height = fin1.getY()-origine.getY(); 
        
        if (fin1.getX()<origine.getX() && fin1.getY()<origine.getY()){   // et là on s'arrange pour pouvoir la tracer correctement, car par défaut le fillOval et fillRect remplissent d'en haut à gauche vers en bas à droite
            origine = fin1;
        }
        
        if (fin1.getX()<origine.getX() && fin1.getY()>origine.getY()){
            int nvorigineX = origine.getX() + width;
            origine.setX(nvorigineX);
        }
        if (fin1.getY()<origine.getY() && fin1.getX()>origine.getX()){
            int nvorigineY = origine.getY()+ height;
            origine.setY(nvorigineY);
        }


        if ((couleur != null) && (figureSuivante != null) && (width != 0)){     // on sélectionne nos boutons et on trace la figure
            if (figureSuivante == "Ellipse"){ 
                Ellipse ellipse = new Ellipse(origine,Math.abs(width), Math.abs(height),couleur);
                listeDeFigure.add(ellipse); 
            }
            if (figureSuivante == "Rectangle"){
                Rectangle rectangle = new Rectangle(origine, Math.abs(width),Math.abs(height),couleur); 
                listeDeFigure.add(rectangle); 
            }
            
            if (figureSuivante == "Carré"){
                Carre carre = new Carre(origine, Math.abs(width),couleur); 
                listeDeFigure.add(carre); 
            }

            if (figureSuivante == "Cercle"){
                int diametre;
                if (height > width){
                    diametre = height;
                    Cercle cercle = new Cercle(origine,Math.abs(diametre),couleur); 
                    listeDeFigure.add(cercle);} 
                else {
                    
                    Cercle cercle = new Cercle(origine,Math.abs(width),couleur); 
                    listeDeFigure.add(cercle);

                }
            }
            paintComponent(this.getGraphics());  //dessine la liste listeDeFigure

        }   
        
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {   // ici on s'occupe de la visualisation en live du tracé de la figure, c'est comme dans le released mais avec une autre liste et on dessine l'avant dernière figure
        fin2 = new Point(e.getX(),e.getY()); 
        int width2 = fin2.getX()-origine.getX();
        int height2 = fin2.getY()-origine.getY(); 
        origine2 =origine;

        if ((fin2.getX()<origine.getX()) && (fin2.getY()>origine.getY())){
            origine2 = new Point(fin2.getX(),origine.getY());
        } 
        if ((fin2.getX()<origine.getX()) && (fin2.getY()<origine.getY())){
            origine2 = fin2;
        } 
        if ((fin2.getX()>origine.getX()) && (fin2.getY()<origine.getY())){
            origine2 = new Point(origine.getX(),fin2.getY());
        }
        




        if ((couleur != null) && (figureSuivante == "A la main") ){                 // ici on dessine à la main, je me sers du cercle pour faire la ligne
            Cercle cercle = new Cercle (fin2,Math.abs(tailleCurseur),couleur); 
                    listeDeFigure.add(cercle);
                }
        
        
               
        
        if ((couleur != null) && (figureSuivante != null) && (width2 != 0)){            // et là on remplit la liste drag pour la figure en continu
            if (figureSuivante == "Ellipse"){ 
                Ellipse ellipse = new Ellipse(origine2,Math.abs(width2), Math.abs(height2),couleur);
                listeDragged.add(ellipse); 
            }
            if (figureSuivante == "Rectangle"){
                Rectangle rectangle = new Rectangle(origine2, Math.abs(width2),Math.abs(height2),couleur); 
                listeDragged.add(rectangle); 
            }
            
            if (figureSuivante == "Carré"){
                Carre carre = new Carre(origine2, Math.abs(width2),couleur); 
                listeDragged.add(carre); 
            }

            if (figureSuivante == "Cercle"){
                int diametre;
                if (height2 > width2){
                    diametre = height2;
                    Cercle cercle = new Cercle(origine2,Math.abs(diametre),couleur); 
                    listeDragged.add(cercle);} 
                else {
                    
                    Cercle cercle = new Cercle(origine2,Math.abs(width2),couleur); 
                    listeDragged.add(cercle);

                }
            }
        }
        paintComponentDragged(this.getGraphics()); // dessine l'avant derniere figure de la liste listeDragged
        
    }


    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < listeDeFigure.size(); i++) { 
        listeDeFigure.get(i).draw(g);
        }

    }

    public void paintComponentDragged(Graphics g) {          // affiche l'avant dernière et dernière figure des listes pour 
        super.paintComponent(g);
        for (int i = 0; i < listeDragged.size(); i++) { 
            listeDragged.get(listeDragged.size()-1).draw(g); 
            listeDeFigure.get(i).draw(g);
        }
    }
    

    public void reinitialisationDuDessin(){  // fct explicite par son nom
        this.listeDeFigure.clear();
        paintComponent(this.getGraphics());
    }


    public void ouvreFichier(ArrayList<Figure> liste){    // fct explicite par son nom
        this.listeDeFigure.clear(); 
        listeDeFigure = liste;
        paintComponent(this.getGraphics()); 
    }

    



}