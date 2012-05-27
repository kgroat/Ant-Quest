/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle.areas;

import antquest.battle.Hex;
import antquest.battle.BattleMode;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class HexArcArea extends HexArea {

   protected double angle, width;

   public HexArcArea(BattleMode mode) {
      this(mode, mode.getMapWidth() / 2, mode.getMapHeight() / 2, 5, 0, Math.PI / 3, true);
   }

   public HexArcArea(BattleMode mode, int tx, int ty, int tRad, double tAng, double tWid, boolean tOrigin) {
      super(mode, tx, ty, tRad, tOrigin);
      angle = tAng;
      width = tWid + .01;
      update();
   }

   public void setAngle(double tAngle){
      angle = tAngle;
      update();
   }
   
   public void setWidth(double tWidth){
      width = tWidth;
      update();
   }
   
   @Override
   public void update() {
      super.update();
      ArrayList<Hex> out = new ArrayList();
      double min = (angle - width / 2 + Math.PI * 2) % (Math.PI * 2);
      double max = min + width;
      double ang1, ang2;
      for (Hex each : listOut) {
         ang1 = (BattleMode.hexAng(x, y, each.getX(), each.getY()) + Math.PI * 2) % (Math.PI * 2);
         ang2 = ang1 + Math.PI * 2;
         if ((ang1 > min && ang1 < max) || (ang2 > min && ang2 < max)) {
            out.add(each);
         }
      }
      listOut = out;
   }
}
