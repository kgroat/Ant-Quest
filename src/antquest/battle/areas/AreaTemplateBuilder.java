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
public interface AreaTemplateBuilder {
   
   public AreaTemplate build(BattleMode battle, int x, int y, double... vals);
   
}
