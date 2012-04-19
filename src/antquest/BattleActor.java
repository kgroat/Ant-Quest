/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Point;

/**
 *
 * @author Kevin
 */
public class BattleActor implements Comparable{
   protected Entity entity;
   protected int x, y;
   protected int speed;
   protected int team;
   protected boolean playable;
   
   public BattleActor(Entity e, int tx, int ty, int tteam, boolean play){
      entity = e;
      x = tx; y = ty;
      team = tteam;
      playable = play;
   }

   @Override
   public int compareTo(Object t) {
      return speed-((BattleActor)t).speed;
   }
   
   public void setPos(int tx, int ty){
      x = tx; y = ty;
   }
   
   public boolean isAt(int tx, int ty){
      return x == tx && y == ty;
   }
   
   public Point getPos(){
      return new Point(x, y);
   }
   
   public void setX(int tx){
      x = tx;
   }
   
   public void setY(int ty){
      y = ty;
   }
   
   public int getX(){
      return x;
   }
   
   public int getY(){
      return y;
   }
   
}
