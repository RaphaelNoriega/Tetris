import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Contol implements KeyListener{
   
   Pieza actual;
   int pinix=5;
   int piniy=0;
   int pfinalx;
   int pfinaly;
   //constructor
   public Contol(){
      actual = new Pieza();
      crearPieza();
      
   } 

   public void crearPieza(){
      Random r = new Random();
      int npieza = r.nextInt(actual.lnombrepieza.length);
      actual = new Pieza(npieza);
      this.moverPiezaaInicio();
   }

   public void moverPiezaaInicio(){
      for(Coordenadas c : actual.getBody()){
            int px = c.getX();
            int py = c.getY();
            int cx = px+pinix;
            int cy = py+piniy;
            c.setX(cx);
            c.setY(cy);
      }
   }

   public void bajarPieza(){
      for(Coordenadas c : actual.getBody()){
            int py = c.getY();
            int cy = py+1;
            c.setY(cy);
      }
   }

   public boolean hayFinalTablero(){
      boolean condicion = false;
       for(Coordenadas c : actual.getBody()){
          if(c.getY()+1 == this.pfinaly ){
            return true;
          }
      }
      return condicion;
   }

   public void keyTyped(KeyEvent e){
   }

   public void keyPressed(KeyEvent e){
   }

   public void keyReleased(KeyEvent e){
   }

   public Pieza getActual() {
      return actual;
   }

   public void setActual(Pieza actual) {
      this.actual = actual;
   }

   
   public int getPfinalx() {
      return pfinalx;
   }

   public void setPfinalx(int pfinalx) {
      this.pfinalx = pfinalx;
   }

   public int getPfinaly() {
      return pfinaly;
   }

   public void setPfinaly(int pfinaly) {
      this.pfinaly = pfinaly;
   }

   public void ejecutarFrame() {
      if(!this.hayFinalTablero()){
         this.bajarPieza();
      }
      
   }

   
   
}
