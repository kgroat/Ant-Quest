/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class CircleArea extends AreaTemplate {

   protected int x, y;
   protected double rad;
   protected boolean ignoreOrigin;
   
   public CircleArea(BattleMode mode, int tx, int ty, double tRad, boolean tOrigin){
      battle = mode;
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
      for(int i=0; i<battle.mapWidth; i++){
         for(int j=0; j<battle.mapHeight; j++){
            if(BattleMode.hexDist(i, j, x, y) < rad){
               listOut.add(battle.battlemap[i][j]);
            }
         }
      }
      if(ignoreOrigin)
         listOut.remove(battle.battlemap[x][y]);
   }
   
}
