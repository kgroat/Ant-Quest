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
public class HexArea extends AreaTemplate{

   protected int x, y, rad;
   protected boolean ignoreOrigin;
   
   public HexArea(BattleMode tBattle){
      this(tBattle, tBattle.mapWidth/2, tBattle.mapHeight/2, 3, true);
   }
   
   public HexArea(BattleMode tBattle, int tx, int ty, int radius, boolean tOrigin){
      battle = tBattle;
      x = tx;
      y = ty;
      rad = radius;
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
   
   public void setRadius(int tRadius){
      rad = tRadius;
      update();
   }
   
   @Override
   public void update() {
      class HexMapping extends Hex{

         int dist;

         HexMapping(Hex h, int d) {
            super(h);
            dist = d;
         }

      }
      listOut.clear();
      listOut.add(new HexMapping(battle.battlemap[x][y], 0));
      Hex[] tmp2;
      int tx, ty, dist;
      HexMapping h;

      for (int i = 0; i < listOut.size(); i++) {
         h = (HexMapping)listOut.get(i);
         tx = h.xpos;
         ty = h.ypos;
         dist = h.dist;
         if (dist < rad) {
            tmp2 = battle.adjascents(tx, ty);
            for (int j = 0; j < tmp2.length; j++) {
               if (listOut.contains(tmp2[j])) {
                  h = (HexMapping)listOut.get(listOut.indexOf(tmp2[j]));
                  h.dist = Math.min(h.dist, dist + 1);
               } else {
                  listOut.add(new HexMapping(tmp2[j], dist + 1));
               }
            }
         }
      }
      if(ignoreOrigin)
         listOut.remove(battle.battlemap[x][y]);
   }

}
