import java.awt.Color;

public class Coordenadas implements Comparable<Coordenadas>{
   int x;
   int y;
   Color c;
   
   public Coordenadas(int x, int y){
      this.x = x;
      this.y = y;
   }
   public Coordenadas(){
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public Color getC() {
      return c;
   }
   public void setC(Color c) {
      this.c = c;
   }

   @Override
   public String toString(){
      return "Coordenadas{" + "x=" + x + ", y=" + y + '}';
   }

   @Override
   public int compareTo(Coordenadas o) {
      return this.y-o.y;
   }

 
   
}
