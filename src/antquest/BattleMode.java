/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class BattleMode extends LiveMenu {

   //Even y-value means it is shifted to the right
   public static final int DEFAULT_WIDTH = 4;
   public static final int DEFAULT_HEIGHT = 4;
   public static final double PERIOD = 50;
   public static final int MENU_WIDTH = 100;
   public static final int MENU_HEIGHT = 80;
   public static final int HEX_SIZE = 32;
   public static final int MODE_RENDER = 0;
   public static final int MODE_CURSOR = 1;
   public static final int MODE_PLAYER = 2;
   public static final int MODE_COMPUTER = 3;
   public final Color BG_COLOR;
   protected final int mapWidth, mapHeight;
   protected int cx, cy;
   protected ArrayList<BattleActor> actors;
   protected BattleActor current;
   protected Hex[][] battlemap;
   protected double frame;
   protected int currentMode;
   protected TextElement debug1;
   protected int brightness;
   protected Color[] cols;
   protected boolean moved, cursor;

   public BattleMode() {
      this((int)(Math.random() - .5)*60);
   }

   public BattleMode(int bright){
      super(AQEngine.getCurrentMode(), null);
      debug1 = new TextElement(10, AQEngine.getHeight() - 100);
      brightness = bright;
      currentMode = MODE_CURSOR;
      battlemap = new Hex[DEFAULT_WIDTH][DEFAULT_HEIGHT];
      mapWidth = DEFAULT_WIDTH;
      mapHeight = DEFAULT_HEIGHT;
      System.out.println("Brightness: " + brightness);
      for (int i = 0; i < mapWidth; i++) {
         for (int j = 0; j < mapHeight; j++) {
            int terr = (int) ((Math.random() - .5) * 31) + brightness;
            terr = terr < -5 ? Hex.TERRAIN_DARK : terr > 5 ? Hex.TERRAIN_BRIGHT : 0;
            battlemap[i][j] = new Hex(this, i, j, (int) (Math.random() * 10 - 5), terr);
            if (Math.random() < .2) {
               battlemap[i][j].setOccupant(new BattleActor(null, 0, false));
            }
         }
      }
      cx = mapWidth / 2;
      cy = mapHeight / 2;
      frame = 0;
      MenuBlock temp;
      blocks.add(temp = new MenuBlock(this, 5, AQEngine.getHeight() - 105, AQEngine.getWidth() - 10, 100));
      temp.add(debug1);
      blocks.add(temp = new MenuBlock(this, 5, 5, 100, AQEngine.getHeight() - 115));
      blocks.add(temp = new MenuBlock(this, AQEngine.getWidth() - 105, 5, 100, AQEngine.getHeight() - 115));
      setDebugText();
      cols = new Color[64];
      for (int i = 0; i < 64; i++) {
         cols[i] = hexColor(i);
      }
      moved = true;
      cursor = true;
      double pow = 1/5.;
      double tb = Math.signum(brightness)*Math.pow(Math.abs(brightness), pow)*Math.pow(30, 1-pow);
      System.out.println("HERE: "+tb);
      int r = (int)(90 + 2.5*tb);
      int g = (int)(90 + 2.5*tb);
      int b = (int)(128 + 3*tb);
      BG_COLOR = new Color(r, g, b);
   }
   
   void setDebugText() {
      debug1.setText(cx + " / " + cy + " / " + battlemap[cx][cy].zpos);
   }

   @Override
   public void press(KeyEvent e) {
      int trans = InputHelper.transform(e);

      if(e.getKeyCode() == KeyEvent.VK_F3){
         System.out.println("PRINTING MAP:");
         for(int y=0; y<mapHeight; y++){
            for(int x=0; x<mapWidth; x++){
               System.out.println("  ["+x+"]["+y+"]: "+battlemap[x][y].zpos);
            }
         }
         System.out.println("CURSOR ON: ["+cx+"]["+cy+"]");
      }
      
      //<editor-fold defaultstate="collapsed" desc="MODE_RENDER">
      if (currentMode == MODE_RENDER) {
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="MODE_CURSOR">
      if (currentMode == MODE_CURSOR) {
         if ((trans & InputHelper.LEFT) != 0) {
            cx--;
            if (cx < 0) {
               ERROR.tryPlay(true, true);
               cx = 0;
            } else {
               CURSOR.forcePlay(true, true);
            }
            moved = true;
         }
         if ((trans & InputHelper.UP) != 0) {
            cy--;
            if (cy < 0) {
               ERROR.tryPlay(true, true);
               cy = 0;
            } else {
               CURSOR.forcePlay(true, true);
            }
            moved = true;
         }
         if ((trans & InputHelper.RIGHT) != 0) {
            cx++;
            if (cx >= mapWidth) {
               ERROR.tryPlay(true, true);
               cx = mapWidth - 1;
            } else {
               CURSOR.forcePlay(true, true);
            }
            moved = true;
         }
         if ((trans & InputHelper.DOWN) != 0) {
            cy++;
            if (cy >= mapHeight) {
               ERROR.tryPlay(true, true);
               cy = mapHeight - 1;
            } else {
               CURSOR.forcePlay(true, true);
            }
            moved = true;
         }
         if ((trans & InputHelper.CONFIRM) != 0) {
            BattleActor selected = actorAtCursor();
            if (selected != null) {
               //Confirm actor
               CONFIRM.forcePlay(true, true);
            } else {
               ERROR.tryPlay(true, true);
            }
         }
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="MODE_PLAYER">
      if (currentMode == MODE_PLAYER) {
         super.press(e);
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="MODE_COMPUTER">
      if (currentMode == MODE_COMPUTER) {
      }
      //</editor-fold>

      if ((trans & InputHelper.PAUSE) != 0) {
         leaving = true;
      }

      setDebugText();
      //throw new UnsupportedOperationException("Not supported yet.");
   }

   public BattleActor actorAtCursor() {
      return battlemap[cx][cy].getOccupant();
   }

   @Override
   public void release(KeyEvent e) {
      int trans = InputHelper.transform(e);

      //<editor-fold defaultstate="collapsed" desc="MODE_RENDER">
      if (currentMode == MODE_RENDER) {
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="MODE_CURSOR">
      if (currentMode == MODE_CURSOR) {
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="MODE_PLAYER">
      if (currentMode == MODE_PLAYER) {
      }
      //</editor-fold>

      //<editor-fold defaultstate="collapsed" desc="MODE_COMPUTER">
      if (currentMode == MODE_COMPUTER) {
      }
      //</editor-fold>

      //throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void render(Graphics2D g) {
      g.setColor(BG_COLOR);
      g.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
      Hex h;
      int terr, rd, gr, bl, tx, ty;
      Color c;
      Shape s;
      BattleActor actor;
      float cos = (float) (Math.cos(frame / PERIOD * Math.PI * 2) / 2 + .5);
      float bty;
      int diffx = AQEngine.getWidth()/2 - HEX_SIZE*3/4;//(AQEngine.getWidth() - 2 * MENU_WIDTH - HEX_SIZE * 3 / 2 - 10) * cx) / mapWidth + 10 + MENU_WIDTH;
      int diffy = (AQEngine.getHeight()-MENU_HEIGHT-10)/2;//(AQEngine.getHeight() - MENU_HEIGHT - HEX_SIZE * 3 / 2 - 30) * cy) / mapHeight + 10;
      for (int y = 0; y < mapHeight; y++) {
         ty = (y - cy) * HEX_SIZE * 3 / 4 + diffy + battlemap[cx][cy].zpos;
         for (int x = 0; x < mapWidth; x++) {
            h = battlemap[x][y];
            terr = h.getTerrain();
            tx = (x - cx) * HEX_SIZE + diffx;
            //            if ((y - cy) % 2 != 0) {
            //               tx += HEX_SIZE / 2 * (((y + mapHeight * 2) % 2) * 2 - 1);
            //            }
            if (y % 2 == 0) {
               tx += HEX_SIZE / 2;
            }
            //            drawHex(tx, ty, HEX_SIZE, terr, h.getZ(), 0, g);
            if (y < mapHeight - 1) {
               int min = battlemap[x][y + 1].zpos;
               if (x > 0) {
                  min = Math.min(battlemap[x - 1][y + 1].zpos, min);
               }
               if (x < mapWidth - 1) {
                  min = Math.min(battlemap[x + 1][y + 1].zpos, min);
               }
               if (x == 0 || x == mapWidth - 1) {
                  if (y < mapHeight - 2) {
                     min = Math.min(battlemap[x][y + 2].zpos - HEX_SIZE * 3 / 4, min);
                  } else {
                     min = battlemap[x][y].zpos - AQEngine.getHeight() / 2;
                  }
               }
               min = battlemap[x][y].zpos - min + HEX_SIZE / 4;
               min = Math.max(0, min);

               drawHex(tx, ty, HEX_SIZE, terr, h.getZ(), min, g);
            } else {
               drawHex(tx, ty, HEX_SIZE, terr, h.getZ(), AQEngine.getHeight() / 2, g);
            }

            if (cx == x && cy == y) {
               g.setColor(new Color((int) (180 + 75 * cos), (int) (180 + 75 * cos), (int) (255 * cos)));
               int ity = (int) (ty - h.getZ() - cos * HEX_SIZE/2f - .5f);
               g.drawLine(tx, ity + HEX_SIZE / 4, tx + HEX_SIZE / 2, ity);
               g.drawLine(tx + HEX_SIZE, ity + HEX_SIZE / 4, tx + HEX_SIZE / 2, ity);
            }
            if ((actor = h.getOccupant()) != null) {
               actor.render(g, tx, ty - h.getZ() - HEX_SIZE / 3);
            }
         }
         if (cy == y) {
            h = battlemap[cx][cy];
            tx = diffx;
            ty = diffy + battlemap[cx][cy].zpos;
            if (y % 2 == 0) {
               tx += HEX_SIZE / 2;
            }
            g.setColor(new Color((int) (180 + 75 * cos), (int) (180 + 75 * cos), (int) (255 * cos)));
            bty = ty - h.getZ() - cos * HEX_SIZE/2f - .5f;
            int ity = (int) bty;
            g.drawLine(tx, ity + HEX_SIZE * 3 / 4, tx + HEX_SIZE / 2, ity + HEX_SIZE);
            g.drawLine(tx + HEX_SIZE, ity + HEX_SIZE * 3 / 4, tx + HEX_SIZE / 2, ity + HEX_SIZE);
            g.drawLine(tx, ity + HEX_SIZE / 4, tx, ity + HEX_SIZE * 3 / 4);
            g.drawLine(tx + HEX_SIZE, ity + HEX_SIZE / 4, tx + HEX_SIZE, ity + HEX_SIZE * 3 / 4);
         }
      }

      frame = (frame + 1) % PERIOD;
      super.render(g);
      //throw new UnsupportedOperationException("Not supported yet.");
   }

   public void drawHex(int px, int py, int size, int terr, int pz, int dropHeight, Graphics2D g) {
      Color c = cols[terr];
      py -= pz;
      if (dropHeight > 0) {
         Color d1 = c.darker();
         Color d2 = d1.darker();
         g.setColor(d1);
         g.fillRect(px, py + size * 3 / 4, size / 2 + 1, dropHeight);
         g.setColor(d2);
         g.fillRect(px + size / 2 + 1, py + size * 3 / 4, size / 2 - 1, dropHeight);
      }
      g.setColor(c);
      g.fillRect(px, py + size / 4, size, size / 2);
      for (int i = 0; i <= size / 4; i++) {
         g.fillRect(px + size / 2 - i * 2, py + i, 4 * i, 1);
         g.fillRect(px + size / 2 - i * 2, py + size - i, 4 * i, 1);
      }
      g.drawLine(px, py + size * 3 / 4, px + size / 2, py + size);
      g.drawLine(px + size, py + size * 3 / 4, px + size / 2, py + size);
      g.setColor(Color.BLACK);
      g.fillRect(px, py + size / 4, 1, dropHeight + size / 2);
      g.fillRect(px + size, py + size / 4, 1, dropHeight + size / 2);
      g.drawLine(px, py + size / 4, px + size / 2, py);
      g.drawLine(px + size, py + size / 4, px + size / 2, py);
   }

   public Color hexColor(int terr) {
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
      rd = Math.max(0, Math.min(rd + brightness, 255));
      gr = Math.max(0, Math.min(gr + brightness, 255));
      bl = Math.max(0, Math.min(bl + brightness, 255));
      return new Color(rd, gr, bl);
   }

   public Hex[] adjascents(int x, int y) {
      Hex[] parts = new Hex[6];
      int i = 0;

      int shift = -y % 2;
      if (x > 0) {
         //Directly to the left
         parts[i] = battlemap[x - 1][y];
         i++;
      }
      if (x < mapWidth - 1) {
         //Directly to the right
         parts[i] = battlemap[x + 1][y];
         i++;
      }
      if (x + shift >= 0) {
         //To the left
         if (y > 0) {
            //And up
            parts[i] = battlemap[x + shift][y - 1];
            i++;
         }
         if (y < mapHeight - 1) {
            //And down
            parts[i] = battlemap[x + shift][y + 1];
            i++;
         }
      }
      if (x + shift + 1 < mapWidth) {
         //To the right
         if (y > 0) {
            //And up
            parts[i] = battlemap[x + shift + 1][y - 1];
            i++;
         }
         if (y < mapHeight - 1) {
            //And down
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

   public void testAdjacents(int tx, int ty) {
      Hex[] adj = adjascents(tx, ty);
      System.out.println("Test for: " + tx + " / " + ty);
      for (int i = 0; i < adj.length; i++) {
         System.out.println("  " + i + ": " + adj[i].xpos + " / " + adj[i].ypos);
      }
      System.out.println();
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

   public static double hexDist(int x1, int y1, int x2, int y2) {
      double dx = x1 - x2;
      double dy = y1 - y2;
      if (y1 % 2 == 0) {
         dx -= .5;
      }
      if (y2 % 2 == 0) {
         dx += .5;
      }
      dy = dy * 3 / 4;
      return Math.sqrt(dx * dx + dy * dy);
   }

   @Override
   public void update() {
      super.update();
      //throw new UnsupportedOperationException("Not supported yet.");
   }

   public static BattleMode makeAsymptoteMap(int points, double zRange, double mult, int bright) {
      BattleMode temp = zeroBattleMap(bright);
      class HeightPoint {

         double z;
         int x, y;

         HeightPoint(int tx, int ty, double tz) {
            z = tz;
            x = tx;
            y = ty;
         }
      }
      if (mult > 0) {
         HeightPoint[] pts = new HeightPoint[points];
         for (int i = 0; i < points; i++) {
            pts[i] = new HeightPoint((int) (temp.mapWidth * Math.random()), (int) (temp.mapHeight * Math.random()), (Math.random() - .5) * zRange);
         }
         double dist, total;
         for (int i = 0; i < temp.mapWidth; i++) {
            for (int j = 0; j < temp.mapHeight; j++) {
               total = 0;
               for (int k = 0; k < points; k++) {
                  dist = hexDist(i, j, pts[k].x, pts[k].y) + mult;
                  total += pts[k].z * mult / dist;
               }
               temp.battlemap[i][j].zpos = (int) total;
            }
         }
      }
      return temp;
   }

   public BattleMode setTerrainByHeight(double zRange) {
      for (int i = 0; i < mapWidth; i++) {
         for (int j = 0; j < mapWidth; j++) {
            if (this.battlemap[i][j].zpos < zRange / 2) {
               this.battlemap[i][j].terrain |= Hex.TERRAIN_GRASSY;
            } else {
               this.battlemap[i][j].terrain &= -1 - Hex.TERRAIN_GRASSY;
            }
            if (this.battlemap[i][j].zpos < -zRange / 2) {
               this.battlemap[i][j].terrain |= Hex.TERRAIN_WET;
            } else {
               this.battlemap[i][j].terrain &= -1 - Hex.TERRAIN_WET;
            }
         }
      }
      return this;
   }

   public static BattleMode smooth(BattleMode in) {
      Hex[] adj;
      int total;
      for (int i = 0; i < in.mapWidth; i++) {
         for (int j = 0; j < in.mapHeight; j++) {
            adj = in.adjascents(i, j);
            for (int k = 0; k < adj.length; k++) {
               in.battlemap[i][j].zpos += adj[k].zpos;
            }
            in.battlemap[i][j].zpos /= adj.length + 1;
         }
      }
      return in;
   }

   public BattleMode smooth() {
      return smooth(this);
   }

   public static BattleMode zeroBattleMap(int bright) {
      BattleMode temp = new BattleMode(bright);
      for (int i = 0; i < temp.mapWidth; i++) {
         for (int j = 0; j < temp.mapHeight; j++) {
            temp.battlemap[i][j].zpos = 0;
         }
      }
      return temp;
   }
}
