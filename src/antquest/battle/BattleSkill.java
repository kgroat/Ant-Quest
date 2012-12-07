/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle;

import antquest.battle.areas.AreaTemplate;
import antquest.numerical.Gradient;
import antquest.numerical.NumberRange;

/**
 *
 * @author Kevin
 */
public interface BattleSkill {
   
   public static enum Element { wood, fire, earth, metal, water };
    
   public String getName();
   public String getDescription();
   public BattleAnimEffect getAnimation(int x, int y, double... vals);
   public BattleAnimEffect apply(BattleEntity user, BattleMode battle, int x, int y, double... vals);
   public void apply(BattleEntity user, Hex[] list);
   public Gradient getGradient();
   public NumberRange[] getAreaTemplateRanges();
   public String[] getRangeNames();
   public boolean canMoveOrigin();
   public AreaTemplate getAreaTemplate(BattleMode battle, int x, int y, double... vals);
}
