/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle.areas;

import antquest.battle.BattleMode;
import antquest.battle.areas.AreaTemplate;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class CircleArea extends AreaTemplate {

   protected int x, y;
   protected double rad;
   protected boolean ignoreOrigin;
   
   public CircleArea(BattleMode tBattle){
      this(tBattle, tBattle.getMapWidth()/2, tBattle.getMapHeight()/2, 3, true);
   }
   
   public CircleArea(BattleMode tBattle, int tx, int ty, double tRad, boolean tOrigin){
      super(tBattle);
      x = tx;
      y = ty;
      rad = tRad;
      ignoreOrigin = tOrigin;
      update();
   }
   
   public void setX(int tx){
      x = tx;
      update();
   }
   
   public void setY(int ty){
      y = ty;
      update();
   }
   
   public void setLoc(int tx, int ty){
      x = tx;
      y = ty;
      update();
   }
   
   public void setRadius(double tRadius){
      rad = tRadius;
      update();
   }
   
   @Override
   public void update(){
      listOut.clear();
      for(int i=0; i<battle.getMapWidth(); i++){
         for(int j=0; j<battle.getMapHeight(); j++){
            if(BattleMode.hexDist(i, j, x, y) < rad){
               listOut.add(battle.getBattlemap()[i][j]);
            }
         }
      }
      listOut.remove(battle.getBattlemap()[x][y]);
      if(!ignoreOrigin)
         listOut.add(battle.getBattlemap()[x][y]);
   }
   
}
