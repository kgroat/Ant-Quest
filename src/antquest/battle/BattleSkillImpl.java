/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle;

import antquest.battle.areas.AreaTemplateBuilder;
import antquest.numerical.Gradient;

/**
 *
 * @author Kevin
 */
public abstract class BattleSkillImpl implements BattleSkill {

   String name, descrip;
   Gradient grad;
   boolean canMoveOrigin;
   
   public BattleSkillImpl(String tName, String tDescrip, Gradient tGrad, boolean move){
      name = tName;
      descrip = tDescrip;
      grad = tGrad;
      canMoveOrigin = move;
   }
   
   public String getName() {
      return name;
   }

   public String getDescription() {
      return descrip;
   }

   public Gradient getGradient() {
      return grad;
   }
   
   public boolean canMoveOrigin(){
      return canMoveOrigin;
   }
   
   public BattleAnimEffect apply(BattleEntity user, BattleMode battle, int x, int y, double... vals){
      apply(user, this.getAreaTemplate(battle, x, y, vals).toArray());
      return getAnimation(x, y, vals);
   }
}
