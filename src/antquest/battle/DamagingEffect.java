/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle;

import antquest.battle.areas.AreaTemplate;

/**
 *
 * @author Kevin
 */
public class DamagingEffect extends AbilityEffect{

   protected int damage;
   
   public DamagingEffect(int tDamage, Element tElement){
      damage = tDamage;
      element = tElement;
   }
   
   public void setDamage(int tDamage){
      damage = tDamage;
   }
   
   public int getDamage(){
      return damage;
   }
   
   @Override
   public void update() {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void affect(AreaTemplate area) {
      throw new UnsupportedOperationException("Not supported yet.");
   }
   
}
