import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JOptionPane;
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

   Comparator comparator = new ComparadorPersonal();
   ArrayList<Coordenadas> lpiezas = new ArrayList<Coordenadas>();
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

   public boolean gameOver(){
      boolean condicion=false;
      this.ordenarCoordenadas();
      if(this.getLpiezas().size()>pfinalx){
      for(int i=0;i<this.pfinalx;i++){
          Coordenadas c=this.getLpiezas().get(i);
          if(c.getY()==1){
              condicion=true;
          }
      }
      }
      return condicion;
  }

   public void bajarPieza(){
      for(Coordenadas c : actual.getBody()){
            int py = c.getY();
            int cy = py+1;
            c.setY(cy);
      }
   }

   public boolean hayLinea(){
      int contador=0;
      boolean condicion=false;
      for(int i=0;i<pfinaly;i++){
          for(int j=0;j<this.lpiezas.size();j++){
              Coordenadas c=lpiezas.get(j);
              if(i==c.getY()){
                  contador++;
                  
                  if(contador==pfinalx){//se ha encontrado linea
                      int desde=(j+1)-(pfinalx);
                      condicion=true;
                      
                      for(int x=0;x<pfinalx;x++){
                          this.lpiezas.remove(desde);
                      }
                      this.bajarTodasDesde(i);
                      contador=0;
                      j=0;
                      i=0;
                      break;
                  }
              }else{
                  contador=0;
              }
          }
      }
      return condicion;
  }

  public void bajarTodasDesde(int py){
   for(Coordenadas c:this.getLpiezas()){
       if(c.getY()<py){
       int y=c.getY();
       y++;
       c.setY(y);
       }
   }
}

   public void rotarPieza(){
      actual.rotarPieza();
   }

   public void moverDerecha(){
      for(Coordenadas c : actual.getBody()){
            int x = c.getX();
            x=x+1;
            c.setX(x);
      }
   }

   public void moverIzquierda(){
      for(Coordenadas c : actual.getBody()){
            int x = c.getX();
            int y = c.getY();
            x=x-1;
            System.out.println(x);
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
            for(Coordenadas ct : this.lpiezas){
               for(Coordenadas cp : actual.getBody()){
                  if ((cp.getY()+1 == ct.getY()) && (cp.getX() == ct.getX())) {
                     condicion = true;
                  }
               }
            }
         
      return condicion;
   }

   public void ordenarCoordenadas(){
      this.lpiezas.sort(comparator);
   }
    public void imprimir(){
      Iterator<Coordenadas> itera = this.lpiezas.iterator();
      System.out.println("----------");
      while (itera.hasNext()) {
         Coordenadas c=itera.next();
         System.out.println(c.toString());
      }
      System.out.println("----------");
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

    public boolean nohayFicha(){
      boolean condicion=false;
      for(Coordenadas ct:this.lpiezas){
               for(Coordenadas cp:actual.getBody()){
      if(accion==Accion.RIGHT){
                        if((cp.getX()+1==ct.getX())&&(cp.getY()==ct.getY())){
                           condicion=true;
                        }
                  }
                  if((accion==Accion.LEFT)&&(cp.getY()==ct.getY())){
                        if(cp.getX()-1==ct.getX()){
                           condicion=true;
                        }
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
         case ' ' : {accion = Accion.SPACE;} break;
      }
   }

   public void keyReleased(KeyEvent e){
      char tecla = e.getKeyChar();
      System.out.println(tecla);
      switch(tecla){
         case 'a' : {accion = Accion.NOTHING;} break;
         case 'd' : {accion = Accion.NOTHING;} break;
         case ' ' : {accion = Accion.NOTHING;} break;
      }
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

   
  
   public ArrayList<Coordenadas> getLpiezas() {
      return lpiezas;
   }

   public void setLpiezas(ArrayList<Coordenadas> lpiezas) {
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

      if(!this.gameOver()){
        
         if((!this.hayFinalTablero())&&(!this.hayColisioncontraPieza())){
         if((this.hayMover())&&(!this.nohayFicha())){
         
         if(accion==Accion.LEFT){
             this.moverIzquierda();
         }
         if(accion==Accion.RIGHT){
             this.moverDerecha();
         }
         if(accion==Accion.SPACE){
             this.rotarPieza();
         }
             accion = Accion.NOTHING;
         }
        this.bajarPieza();
         }else{         
             this.getLpiezas().addAll(actual.getBody());
             this.crearPieza();
             this.ordenarCoordenadas();
             //this.imprimir();
             this.hayLinea();
         }
     }else{
             JOptionPane.showMessageDialog(null, "GAMEOVER!!");
             System.exit(0);
         }
     }
      
   }

   
   

