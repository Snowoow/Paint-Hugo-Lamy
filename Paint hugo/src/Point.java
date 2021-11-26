import java.io.Serializable; 

public class Point  implements Serializable{   // Serializable à ne pas oublier : erreur que j'ai commise qui m'a coûté bcp de temps
    protected int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
        }
    public void setX(int x) {
        this.x = x;
        }
    public void setY(int y) {
        this.y = y;
        }
    @Override
    public String toString(){
        return "("+ x + "," + y + ")" ;
    }
}
