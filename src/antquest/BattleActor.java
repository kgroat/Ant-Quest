/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;

/**
 *
 * @author Kevin
 */
public class BattleActor implements Comparable {

   protected BattleMode battle;
   protected Entity entity;
   protected Hex location;
   protected int speed;
   protected int team;
   protected boolean playable;
   private Color one, two;

   public BattleActor(Entity e, int tteam, boolean play) {
      entity = e;
      location = null;
      team = tteam;
      playable = play;
      int hi = (int)(Math.random()*3);
      int mid = (int)(Math.random()*3);
      while(mid == hi){
         mid = (int)(Math.random()*3);
      }
      int[] rgb = new int[3];
      rgb[hi] = 255;
      rgb[mid] = (int)(Math.random()*255);
      one = new Color(rgb[0], rgb[1], rgb[2]);
      for(int i=0; i<3; i++){
         rgb[i] = 255 - rgb[i];
      }
      two = new Color(rgb[0], rgb[1], rgb[2]);
   }

   @Override
   public int compareTo(Object t) {
      return speed - ((BattleActor) t).speed;
   }

   public void setLoc(Hex h) {
      location = h;
   }

   public boolean isAt(int tx, int ty) {
      return location.xpos == tx && location.ypos == ty;
   }

   public Point getPos() {
      return new Point(location.xpos, location.ypos);
   }

   public Hex getLoc() {
      return location;
   }

   public int getX() {
      return location.xpos;
   }

   public int getY() {
      return location.ypos;
   }

   public void render(Graphics2D g, int px, int py) {
//      g.setPaint(new RadialGradientPaint(px + BattleMode.HEX_SIZE/2f + .5f, py + BattleMode.HEX_SIZE/2f + .5f, BattleMode.HEX_SIZE/2f - 1, new float[]{0, 1}, new Color[]{one, two}));
//      g.fillRect(px + 3, py + 3, BattleMode.HEX_SIZE - 5, BattleMode.HEX_SIZE - 5);
   }
}
