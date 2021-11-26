import java.awt.* ;


public class Cercle extends Ellipse {
    protected int diametre;

    public Cercle ( Point point, int diametre, Color color){
        super( point , diametre, diametre, color );
        this.diametre = diametre;
        this.length = diametre;
        this.width = diametre;
        
    }

 

    @Override
    public String toString() {
        return "Cercle de couleur = " + color + ", de diametre" + diametre + ", et d'origine " + point ;
    }

        
    
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
    

}
    

