/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle.areas;

import antquest.battle.BattleMode;
import antquest.battle.areas.AreaTemplate;

/**
 *
 * @author Kevin
 */
public class FullArea extends AreaTemplate{

   public FullArea(BattleMode tBattle){
      super(tBattle);
      update();
   }
   
   @Override
   public void update() {
      listOut.clear();
      for(int i=0; i<battle.getMapWidth(); i++){
         for(int j=0; j<battle.getMapHeight(); j++){
            listOut.add(battle.getBattlemap()[i][j]);
         }
      }
   }
   
}
