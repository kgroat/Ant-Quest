/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle.areas;

import antquest.battle.BattleMode;
import antquest.numerical.NumberRange;

/**
 *
 * @author Kevin
 */
public class CircleArcAreaBuilder implements AreaTemplateBuilder {
   
   double radius, width;
   
   public CircleArcAreaBuilder(double rad, double wid){
      radius = rad;
      width = wid;
   }
   
   public AreaTemplate build(BattleMode battle, int x, int y, double... vals) {
      return new CircleArcArea(battle, x, y, radius, vals[0], width, vals.length > 1 && vals[1] != 0);
   }
}
