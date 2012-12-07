/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle;

import antquest.battle.BattleMode;

/**
 *
 * @author Clem
 */
public class Hex {

   public static final int TERRAIN_WET = 1;
   public static final int TERRAIN_DARK = 2;
   public static final int TERRAIN_BRIGHT = 4;
   public static final int TERRAIN_GRASSY = 8;
   public static final int TERRAIN_HIGH = 16;
   public static final int TERRAIN_LOW = 32;
   
   protected BattleMode parent;
   protected BattleEntity occupant;
   protected int xpos, ypos, zpos;
   protected int terrain; //might be a separate class later?

   public Hex(BattleMode place, int x, int y, int z, int earth) {
      parent = place;
      terrain = earth;
      xpos = x;
      ypos = y;
      zpos = z;
      occupant = null;
   }
   
   public Hex(Hex h){
      parent = h.parent;
      occupant = h.occupant;
      xpos = h.xpos;
      ypos = h.ypos;
      zpos = h.zpos;
      terrain = h.terrain;
   }

   public void setOccupant(BattleActor e) {
      if(occupant != null)
         occupant.setLoc(null);
      occupant = e;
      occupant.setLoc(this);
   }

   public BattleEntity getOccupant() {
      return occupant;
   }

   public int getX() {
      return xpos;
   }

   public int getY() {
      return ypos;
   }
   
   public int getZ(){
      return zpos;
   }

   public void setTerrain(int g) {
      terrain = g;
   }

   public int getTerrain() {
      return terrain;
   }
   
   @Override
   public boolean equals(Object o){
      if(o instanceof Hex){
         Hex h = (Hex)o;
         return xpos == h.xpos && ypos == h.ypos;
      }else{
         return false;
      }
   }
}
