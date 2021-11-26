import java.awt.*;

public class Rectangle extends Figure{

    protected int abscisse;
    protected int ordonnee;

    public Rectangle(int px, int py, int length, int width, Color c){
        super(new Point(px, py), c);
        this.abscisse = px;
        this.ordonnee = py;
        this.length  = length;
        this.width = width;

    }
    
    public Rectangle(Point point, int length, int width, Color c){
        super(point, c);
        this.length = length;
        this.width = width;
    }


    

    @Override
    public String toString() {
        return "Rectangle [couleur=" + color + ", longueur=" + length + ", largueur=" + width + ", d'origine "+point;
    }

    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(point.getX(),point.getY(),Math.abs(length),Math.abs(width));
        
    }
}
