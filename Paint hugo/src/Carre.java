import java.awt.* ;

public class Carre extends Rectangle{


    public Carre ( Point point , int cote, Color color){
        super( point, cote, cote, color );
        this.length = cote;
        this.width = cote;
    }

 
    
    @Override
    public String toString() {
        return "Carre de couleur=" + color + ", longueur=" + length + ", largueur=" + width + ", d'origine (" + abscisse +" , "+ ordonnee +")]";
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        
    }
}
