import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.ActionMapUIResource;

public class Contol implements KeyListener{
   
   Pieza actual;
   public enum Accion {LEFT,RIGHT,SPACE,NOTHING};
   Accion accion;
   int pinix=5;
   int piniy=0;
   int pfinalx;
   int pfinaly;
   int limitetabd;
   int limitetabi;

   ArrayList<Pieza> lpiezas = new ArrayList<Pieza>();
   //constructor
   public Contol(){
      actual = new Pieza();
      crearPieza();
      accion = Accion.NOTHING;
      //this.moverPiezaaInicio();
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

   public void moverDerecha(){
      for(Coordenadas c : actual.getBody()){
            int x = c.getX();
            int y = c.getY();
            x=x+1;
            c.setX(x);
      }
   }

   public void moverIzquierda(){
      for(Coordenadas c : actual.getBody()){
            int x = c.getX();
            int y = c.getY();
            x=x-1;
            c.setX(x);
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

   public boolean hayColisioncontraPieza(){
      boolean condicion = false;
         //int CoordYPieza = Coordenadas.getY
         for(Pieza p : this.getLpiezas()){
            for(Coordenadas ct : p.getBody()){
               for(Coordenadas cp : actual.getBody()){
                  if ((cp.getY()+1 == ct.getY()) && (cp.getX() == ct.getX())) {
                     condicion = true;
                  }
               }
            }
         }
      return condicion;
   }

   public boolean hayMover(){
      boolean condicion = true;
      for(Coordenadas c : actual.getBody()){
          if(accion == Accion.RIGHT){
            if(c.getX()+1 == this.limitetabd){
               condicion = false;
            }
          }
          if(accion == Accion.LEFT){
            if(c.getX() == this.limitetabi){
               condicion = false;
            }
          }       
      }
      return condicion;
   }

   public void keyTyped(KeyEvent e){
   }

   public void keyPressed(KeyEvent e){
      char tecla = e.getKeyChar();
      System.out.println(tecla);
      switch(tecla){
         case 'a' : {accion = Accion.LEFT;} break;
         case 'd' : {accion = Accion.RIGHT;} break;
         case 'A' : {accion = Accion.LEFT;} break;
         case 'D' : {accion = Accion.RIGHT;} break;
         case ' ' : {accion = Accion.NOTHING;} break;
      }
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

   public ArrayList<Pieza> getLpiezas() {
      return lpiezas;
   }

   public void setLpiezas(ArrayList<Pieza> lpiezas) {
      this.lpiezas = lpiezas;
   }

   public Accion getAccion() {
      return accion;
   }

   public void setAccion(Accion accion) {
      this.accion = accion;
   }

   public int getLimitetabd() {
      return limitetabd;
   }

   public void setLimitetabd(int limitetabd) {
      this.limitetabd = limitetabd;
   }

   public int getLimitetabi() {
      return limitetabi;
   }

   public void setLimitetabi(int limitetabi) {
      this.limitetabi = limitetabi;
   }

   public void ejecutarFrame() {

      if(this.hayMover()){
         if(accion == Accion.RIGHT){
            this.moverDerecha();
         }
         if(accion == Accion.LEFT){
            this.moverIzquierda();
         }
      }

      if((!this.hayFinalTablero()) && (!this.hayColisioncontraPieza())){
         this.bajarPieza();
      }else{
         this.getLpiezas().add(actual);
         this.crearPieza();
      }
      
   }

   
   
}
