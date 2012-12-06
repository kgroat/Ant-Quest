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
public class LineAreaBuilder implements AreaTemplateBuilder {

   double width;
   int ray;
   
   public LineAreaBuilder(double wid, int tRay){
      width = wid;
      ray = tRay;
   }
   
   public AreaTemplate build(BattleMode battle, int x, int y, double... vals) {
      if(vals.length >= 2){
         return new LineArea(battle, x, y, x + (int)vals[0], y + (int)vals[1], width, ray);
      }else{
         return null;
      }
   }
   
}
