import java.awt.*;

public class Ellipse extends Figure{

    public Ellipse(Point point, int length, int width, Color c){
        super(point, c);
        this.length = length;
        this.width = width;
        
    }
    
        @Override
        public String toString() {
            return "Ellipse de couleur=" + color + ", demi grand axe=" + length/2 + ", demi petit axe=" + width/2 + ", de centre " + point;
        }
    
        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(point.getX(),point.getY(),Math.abs(length),Math.abs(width));
        }


}
