/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.items;

/**
 *
 * @author Kevin
 */
public abstract class Item {
   private static long curr = 0;
   protected final long key;
   
   public Item(){
      key = curr;
      curr++;
   }
}
