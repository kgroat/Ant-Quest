/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antquest.battle;

import antquest.battle.areas.AreaTemplate;

/**
 *
 * @author Clem
 */
public abstract class AbilityEffect{
   
   public static enum Element { wood, fire, earth, metal, water };
    
   protected String description;
   protected Element element;
   
   public abstract void update();
   
   public abstract void affect(AreaTemplate area);
    
   public String getDescription(){
      return description;
   }
   
   @Override
   public String toString(){
      return getDescription();
   }
}
