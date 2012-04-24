/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.items;

/**
 *
 * @author Kevin
 */
public abstract class Weapon extends Hand{
   
   protected int maxAttack, minAttack;
   protected int [] hitpattern;
   /*  see DamagingEffect for explanation
    *  
    * 
    * 
    */
   
   
   /**
    * Generates a damage value for an attack with this Weapon (may be random).
    * @return 
    */
   public abstract int getAttackValue();
}
