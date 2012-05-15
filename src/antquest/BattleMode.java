/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class BattleMode extends GameMode {

   //Even y-value means it is shifted to the right
   public static final int DEFAULT_WIDTH = 20;
   public static final int DEFAULT_HEIGHT = 15;
   protected int mapWidth, mapHeight;
   protected int cx, cy;
   protected ArrayList<BattleActor> actors;
   protected BattleActor current;
   protected Hex[][] battlemap;

   public BattleMode() {
      battlemap = new Hex[DEFAULT_WIDTH][DEFAULT_HEIGHT];
      for (int i = 0; i < battlemap.length; i++) {
         for (int j = 0; j < battlemap[i].length; j++) {
            battlemap[i][j] = new Hex(this, i, j, (int) (Math.random() * 10 - 5), (int) (Math.random() * 64));
         }
      }
      mapWidth = DEFAULT_WIDTH;
      mapHeight = DEFAULT_HEIGHT;
      cx = 0;
      cy = 0;
   }

   @Override
   public void press(KeyEvent e) {
      int trans = InputHelper.transform(e);
      if ((trans & InputHelper.LEFT) != 0) {
         cx--;
         if (cx < 0) {
            ERROR.tryPlay(true, false);
            cx = 0;
         } else {
            CURSOR.forcePlay(true, false);
         }
      }
      if ((trans & InputHelper.UP) != 0) {
         cy--;
         if (cy < 0) {
            ERROR.tryPlay(true, false);
            cy = 0;
         } else {
            CURSOR.forcePlay(true, false);
         }
      }
      if ((trans & InputHelper.RIGHT) != 0) {
         cx++;
         if (cx >= mapWidth) {
            ERROR.tryPlay(true, false);
            cx = mapWidth - 1;
         } else {
            CURSOR.forcePlay(true, false);
         }
      }
      if ((trans & InputHelper.DOWN) != 0) {
         cy++;
         if (cy >= mapHeight) {
            ERROR.tryPlay(true, false);
            cy = mapHeight - 1;
         } else {
            CURSOR.forcePlay(true, false);
         }
      }
      if ((trans & InputHelper.CONFIRM) != 0) {
         BattleActor selected = actorAtCursor();
         if (selected != null) {
            //Confirm actor
            CONFIRM.forcePlay(true, false);
         } else {
            ERROR.tryPlay(true, false);
         }
      }
      throw new UnsupportedOperationException("Not supported yet.");
   }

   public BattleActor actorAtCursor() {
      return battlemap[cx][cy].getOccupant();
   }

   @Override
   public void release(KeyEvent e) {
      //throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void render(Graphics2D g) {
      final int TILE_WIDTH = 20;
      Hex h;
      int terr, rd, gr, bl, tx, ty;
      Color c;
      for (int y = 0; y < mapHeight; y++) {
         for (int x = 0; x < mapWidth; x++) {
            h = battlemap[x][y];
            terr = h.getTerrain();
            tx = x * TILE_WIDTH + 75;
            ty = y * TILE_WIDTH * 3 / 4 + 50;
            if (y % 2 == 0) {
               tx += TILE_WIDTH / 2;
            }
            drawHex(tx, ty, TILE_WIDTH, terr, h.getZ(), g);
         }
      }
      //throw new UnsupportedOperationException("Not supported yet.");
   }

   public void drawHex(int px, int py, int size, int terr, int pz, Graphics2D g) {
      int rd, gr, bl;
      if ((terr & Hex.TERRAIN_WET) == 0) {
         //Not wet
         if ((terr & Hex.TERRAIN_GRASSY) == 0) {
            //Dirt
            //Brown
            rd = 190;
            gr = 120;
            bl = 50;
         } else {
            //Grass
            //Green
            rd = 30;
            gr = 120;
            bl = 0;
         }
      } else {
         //Wet
         if ((terr & Hex.TERRAIN_GRASSY) == 0) {
            //Dirt
            //Dark Brown
            rd = 120;
            gr = 70;
            bl = 30;
         } else {
            //Grass
            //Darker Bluish Green
            rd = 0;
            gr = 90;
            bl = 30;
         }
      }
      if ((terr & Hex.TERRAIN_BRIGHT) != 0) {
         //Brighten
         rd = Math.min(rd + 30, 255);
         gr = Math.min(gr + 30, 255);
         bl = Math.min(bl + 30, 255);
      }
      if ((terr & Hex.TERRAIN_DARK) != 0) {
         //Darken
         rd = Math.max(0, rd - 30);
         gr = Math.max(0, gr - 30);
         bl = Math.max(0, bl - 30);
      }
      Color c = new Color(rd, gr, bl);
      Color d1 = c.darker();
      Color d2 = d1.darker();
      py -= pz;
      g.setColor(d1);
      g.fillRect(px, py + size * 3 / 4, size / 2 + 1, size * 2);
      g.setColor(d2);
      g.fillRect(px + size / 2 + 1, py + size * 3 / 4, size / 2 - 1, size * 2);
      g.setColor(c);
      g.fillRect(px, py + size / 4, size, size / 2);
      for (int i = 0; i <= size / 4; i++) {
         g.fillRect(px + size / 2 - i * 2, py + i, 4 * i, 1);
         g.fillRect(px + size / 2 - i * 2, py + size - i, 4 * i, 1);
      }
      g.setColor(c);
      g.drawLine(px, py + size *3 / 4, px + size / 2, py + size);
      g.drawLine(px + size, py + size * 3 / 4, px + size / 2, py + size);
      g.setColor(Color.BLACK);
      g.fillRect(px, py + size / 4, 1, size * 5 / 2);
      g.fillRect(px + size, py + size / 4, 1, size * 5 / 2);
      g.drawLine(px, py + size / 4, px + size / 2, py);
      g.drawLine(px + size, py + size / 4, px + size / 2, py);
   }

   public Hex[] adjascents(int x, int y) {
      Hex[] parts = new Hex[6];
      int i = 0;

      int shift = x % 2;
      if (x > 0) {
         parts[i] = battlemap[x - 1][y];
         i++;
      }
      if (x < mapWidth - 1) {
         parts[i] = battlemap[x + 1][y];
         i++;
      }
      if (x + shift >= 0) {
         if (y > 0) {
            parts[i] = battlemap[x + shift][y - 1];
            i++;
         }
         if (y < mapHeight - 1) {
            parts[i] = battlemap[x + shift][y + 1];
            i++;
         }
      }
      if (x + shift + 1 < mapWidth) {
         if (y > 0) {
            parts[i] = battlemap[x + shift + 1][y - 1];
            i++;
         }
         if (y < mapHeight - 1) {
            parts[i] = battlemap[x + shift + 1][y + 1];
            i++;
         }
      }

      Hex[] temp = new Hex[i];
      for (i = 0; i < temp.length; i++) {
         temp[i] = parts[i];
      }
      return temp;
   }

   public Hex[] radius(int x, int y, int rad) {
      class HexMapping {

         Hex hex;
         int dist;

         HexMapping(Hex h, int d) {
            hex = h;
            dist = d;
         }

         @Override
         public boolean equals(Object o) {
            if (o instanceof HexMapping) {
               return hex.equals(((HexMapping) (o)).hex);
            } else if (o instanceof Hex) {
               return hex.equals((Hex) o);
            } else {
               return false;
            }
         }
      }
      ArrayList<HexMapping> tmp1 = new ArrayList<HexMapping>();
      tmp1.add(new HexMapping(battlemap[x][y], 0));
      Hex[] tmp2;
      int tx, ty, dist;
      HexMapping h;

      for (int i = 0; i < tmp1.size(); i++) {
         h = tmp1.get(i);
         tx = h.hex.xpos;
         ty = h.hex.ypos;
         dist = h.dist;
         if (dist < rad) {
            tmp2 = adjascents(tx, ty);
            for (int j = 0; j < tmp2.length; j++) {
               if (tmp1.contains(tmp2[j])) {
                  h = tmp1.get(tmp1.indexOf(tmp2[j]));
                  h.dist = Math.min(h.dist, dist + 1);
               } else {
                  tmp1.add(new HexMapping(tmp2[j], dist + 1));
               }
            }
         }
      }

      tmp2 = new Hex[tmp1.size()];
      for (int i = 0; i < tmp2.length; i++) {
         tmp2[i] = tmp1.get(i).hex;
      }
      return tmp2;
   }

   @Override
   public void update() {
      //throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public GameMode escape() {
      throw new UnsupportedOperationException("Not supported yet.");
   }
}
