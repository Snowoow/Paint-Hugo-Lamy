import java.awt.*;
import java.io.Serializable;

public abstract class Figure implements Serializable {      // Serializable à ne pas oublier : erreur que j'ai commise qui m'a coûté bcp de temps
    protected Color color;
    protected Point point;
    protected int length;
    protected int width;

    public Figure(Point point, Color color) {
        this.point = point;
        this.color = color;

    }
    public Figure(Point point) {
        this.point = point;
        this.color = Color.blue;
    }
    public Color getColor(){
        return color;
            }
    public Point getPoint(){
        return point;
            }
    public void setlength(int length){
        this.length= length; 
    }
    public void setWidth(int width) {
        this.width = width;
    }     
    public abstract void draw (Graphics g);
}
