package antquest.items;
import java.util.*;


public abstract class Weapon extends Hand{
   
   protected int maxAttack, minAttack;
   protected int [] hitpattern;
   protected Hashtable<String,Integer> onhiteff;
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
