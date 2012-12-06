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
public class CircleAreaBuilder implements AreaTemplateBuilder {

   double radius;
   
   public CircleAreaBuilder(double rad){
      radius = rad;
   }
   
   public AreaTemplate build(BattleMode battle, int x, int y, double... vals) {
      return new CircleArea(battle, x, y, radius, vals.length > 0 && vals[0] != 0);
   }
}
