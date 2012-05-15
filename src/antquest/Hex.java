/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

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
   protected BattleActor occupant;
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

   public void setOccupant(BattleActor e) {
      occupant = e;
   }

   public BattleActor getOccupant() {
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
}
