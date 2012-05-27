/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle.areas;

import antquest.battle.Hex;
import antquest.battle.BattleMode;
import antquest.battle.areas.AreaTemplate;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Kevin
 */
public class LineArea extends AreaTemplate{

   protected int x1, y1, x2, y2;
   protected double width;
   protected Line line;
   protected int rayType;
   
   public LineArea(BattleMode tBattle, Hex tStart, Hex tFinish, double tWidth, int tRayType){
      this(tBattle, tStart.getX(), tStart.getY(), tFinish.getX(), tFinish.getY(), tWidth, tRayType);
   }
   
   public LineArea(BattleMode tBattle, int tx1, int ty1, int tx2, int ty2, double tWidth, int tRayType){
      super(tBattle);
      x1 = tx1;
      y1 = ty1;
      x2 = tx2;
      y2 = ty2;
      width = tWidth/2 + .05;
      rayType = tRayType;
      update();
   }
   
   public void setStart(Hex tStart){
      x1 = tStart.getX();
      y1 = tStart.getY();
      update();
   }
   
   public void setStart(int x, int y){
      x1 = x;
      y1 = y;
      update();
   }
   
   public void setFinish(Hex tFinish){
      x2 = tFinish.getX();
      y2 = tFinish.getY();
      update();
   }
   
   public void setFinish(int x, int y){
      x2 = x;
      y2 = y;
      update();
   }
   
   public void setWidth(double tWidth){
      width = tWidth;
      update();
   }
   
   public void setRayType(int tRayType){
      rayType = tRayType;
      update();
   }
   
   @Override
   public void update() {
      listOut.clear();
      line = new Line(battle.hexLoc(x1, y1), battle.hexLoc(x2, y2), rayType);
      for(int i=0; i<battle.getMapWidth(); i++){
         for(int j=0; j<battle.getMapHeight(); j++){
            if(line.dist(battle.hexLoc(i, j)) < width){
               listOut.add(battle.getBattlemap()[i][j]);
            }
         }
      }
   }
   
   protected static class Line{
      protected static enum RayType { segment, ray, line };
      protected double x1, x2, y1, y2;
      protected double ang, len;
      protected RayType ray;
      
      protected Line(Point2D one, Point2D two, int tRay){
         this(one.getX(), one.getY(), two.getX(), two.getY(), tRay);
      }
      
      protected Line(double tx1, double ty1, double tx2, double ty2, int tRay){
         x1 = tx1;
         y1 = ty1;
         x2 = tx2;
         y2 = ty2;
         double dx = x1 - x2, dy = y1 - y2;
         ang = (Math.atan2(dy, dx)+Math.PI*2)%(Math.PI*2);
         len = Math.sqrt(dx*dx + dy*dy);
         ray = RayType.values()[tRay];
      }
      
      public double dist(Point2D pt){
         double dx = x1-pt.getX(), dy = y1-pt.getY();
         double ang2 = (Math.atan2(dy, dx)+Math.PI*2)%(Math.PI*2);
         double len2 = Math.sqrt(dx*dx + dy*dy);
         double dAng = ang2 - ang, projection, perpendicular;
         projection = len2 * Math.cos(dAng);
         perpendicular = Math.abs(len2 * Math.sin(dAng));
         if(ray == RayType.line){
            return perpendicular;
         }
         if(faceSameDir(ang2)){
            if(projection > len && ray == RayType.segment){
               return pt.distance(x2, y2);
            }else{
               return perpendicular;
            }
         }else{
            return pt.distance(x1, y1);
         }
      }
      
      public boolean faceSameDir(double ang2){
         double diff;
         if(ang2 > ang){
            diff = ang2 - ang;
         }else{
            diff = ang - ang2;
         }
         return diff < Math.PI / 2 || diff > Math.PI * 3 / 2;
      }
   }
   
}
