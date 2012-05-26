/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public abstract class AreaTemplate {
   protected static BattleMode battle;
   
   protected ArrayList<Hex> listOut;
   
   public AreaTemplate(){
      listOut = new ArrayList<Hex>();
   }
   
   public abstract void update();
   
   public final Hex[] toArray(){
      Hex[] arrayOut = new Hex[listOut.size()];
      arrayOut = listOut.toArray(arrayOut);
      return arrayOut;
   }
   
   public final boolean contains(Hex h){
      return listOut.contains(h);
   }
   
   public final Hex get(int i){
      return listOut.get(i);
   }
   
   public final int indexOf(Hex h){
      return listOut.indexOf(h);
   }
   
   public final int size(){
      return listOut.size();
   }
   
   public final Hex getSimilar(Hex h){
      return listOut.get(listOut.indexOf(h));
   }
}
