/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kevin
 */
public class LiveMenu extends GameMode {

   public static final Color SELECT_COLOR = new Color(150, 150, 250, 100);
   public static final int FRAME_LENGTH = 10;
   protected GameMode parent;
   protected ArrayList<MenuBlock> blocks;
   protected SelectableElement selected;
   protected int frame;
   protected boolean leaving, parentRunning, parentRendering;
   protected BufferedImage backdrop;
   protected GameMode whereTo;

   public LiveMenu(GameMode p, BufferedImage bd) {
      parent = p;
      backdrop = bd;
      frame = 0;
      leaving = false;
      blocks = new ArrayList<MenuBlock>();
      selected = null;
      parentRunning = false;
      parentRendering = false;
   }

   public void parse(Scanner in) { //Maybe should be a URL or String?
      //TODO: parse document for menu specification
   }

   @Override
   public void press(KeyEvent e) {
      if (frame == FRAME_LENGTH) {
         int trans = InputHelper.transform(e);
         if ((trans & InputHelper.CONFIRM) != 0) {
            if (selected != null) {
               selected.confirm();
               CONFIRM.forcePlay(true, true);
            } else {
               ERROR.tryPlay(true, true);
            }
         }
         if ((trans & InputHelper.UP) != 0) {
            //<editor-fold defaultstate="collapsed" desc="Move cursor north">
            if (selected != null) {
               ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
               for (int i = 0; i < blocks.size(); i++) {
                  sel.addAll(blocks.get(i).getSelectable());
               }
               int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
               SelectableElement curr;
               for (int i = 0; i < sel.size(); i++) {
                  curr = sel.get(i);
                  dirDist = selected.getY() - curr.getY();
                  currDist = Math.abs(selected.getX() - curr.getX());
                  if (curr != selected) {
                     //Make sure in the north quadrant
                     if (dirDist >= currDist) {
                        currDist += dirDist;
                        if (currDist < bestDist) {
                           best = i;
                           bestDist = currDist;
                        }
                     }
                  }
               }
               if (best == -1) {
                  ERROR.tryPlay(true, true);
               } else {
                  selected = sel.get(best);
                  CURSOR.forcePlay(true, true);
               }
            } else {
               if (!selectDefault()) {
                  ERROR.tryPlay(true, true);
               }
            }
            //</editor-fold>
         }
         if ((trans & InputHelper.DOWN) != 0) {
            //<editor-fold defaultstate="collapsed" desc="Move cursor south">
            if (selected != null) {
               ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
               for (int i = 0; i < blocks.size(); i++) {
                  sel.addAll(blocks.get(i).getSelectable());
               }
               int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
               SelectableElement curr;
               for (int i = 0; i < sel.size(); i++) {
                  curr = sel.get(i);
                  dirDist = curr.getY() - selected.getY();
                  currDist = Math.abs(selected.getX() - curr.getX());
                  if (curr != selected) {
                     //Make sure in the south quadrant
                     if (dirDist >= currDist) {
                        currDist += dirDist;
                        if (currDist < bestDist) {
                           best = i;
                           bestDist = currDist;
                        }
                     }
                  }
               }
               if (best == -1) {
                  ERROR.tryPlay(true, true);
               } else {
                  selected = sel.get(best);
                  CURSOR.forcePlay(true, true);
               }
            } else {
               if (!selectDefault()) {
                  ERROR.tryPlay(true, true);
               }
            }
            //</editor-fold>
         }
         if ((trans & InputHelper.LEFT) != 0) {
            //<editor-fold defaultstate="collapsed" desc="Move cursor west">
            if (selected != null) {
               ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
               for (int i = 0; i < blocks.size(); i++) {
                  sel.addAll(blocks.get(i).getSelectable());
               }
               int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
               SelectableElement curr;
               for (int i = 0; i < sel.size(); i++) {
                  curr = sel.get(i);
                  dirDist = selected.getX() - curr.getX();
                  currDist = Math.abs(selected.getY() - curr.getY());
                  if (curr != selected) {
                     //Make sure in the west quadrant
                     if (dirDist >= currDist) {
                        currDist += dirDist;
                        if (currDist < bestDist) {
                           best = i;
                           bestDist = currDist;
                        }
                     }
                  }
               }
               if (best == -1) {
                  ERROR.tryPlay(true, true);
               } else {
                  selected = sel.get(best);
                  CURSOR.forcePlay(true, true);
               }
            } else {
               if (!selectDefault()) {
                  ERROR.tryPlay(true, true);
               }
            }
            //</editor-fold>
         }
         if ((trans & InputHelper.RIGHT) != 0) {
            //<editor-fold defaultstate="collapsed" desc="Move cursor east">
            if (selected != null) {
               ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
               for (int i = 0; i < blocks.size(); i++) {
                  sel.addAll(blocks.get(i).getSelectable());
               }
               int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
               SelectableElement curr;
               for (int i = 0; i < sel.size(); i++) {
                  curr = sel.get(i);
                  dirDist = curr.getX() - selected.getX();
                  currDist = Math.abs(selected.getY() - curr.getY());
                  if (curr != selected) {
                     //Make sure in the east quadrant
                     if (dirDist >= currDist) {
                        currDist += dirDist;
                        if (currDist < bestDist) {
                           best = i;
                           bestDist = currDist;
                        }
                     }
                  }
               }
               if (best == -1) {
                  ERROR.tryPlay(true, true);
               } else {
                  selected = sel.get(best);
                  CURSOR.forcePlay(true, true);
               }
            } else {
               if (!selectDefault()) {
                  ERROR.tryPlay(true, true);
               }
            }
            //</editor-fold>
         }
         if ((trans & InputHelper.PAUSE) != 0) {
            leaving = true;
         }
      }
   }

   public void setParentRunning(boolean b) {
      parentRunning = b;
   }

   public void setParentRendering(boolean b) {
      parentRendering = b;
   }

   public boolean isParentRunning() {
      return parentRunning;
   }

   public boolean isParentRendering() {
      return parentRendering;
   }

   public boolean selectDefault() {
      ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
      for (int i = 0; i < blocks.size(); i++) {
         sel.addAll(blocks.get(i).getSelectable());
      }
      if (sel.size() > 0) {
         selected = sel.get(0);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void release(KeyEvent e) {
      //Do nothing
   }

   public void select(int block, int num) {
      MenuElement tmp;
      if ((tmp = blocks.get(block).get(num)) instanceof SelectableElement) {
         selected = (SelectableElement) tmp;
         CURSOR.forcePlay(true, true);
      } else {
         ERROR.tryPlay(true, true);
      }
   }

   @Override
   public void render(Graphics2D g) {
      final int round = 3;
      if (parentRendering) {
         parent.render(g);
      }
      if (backdrop != null) {
         int x = (AQEngine.getWidth()-backdrop.getWidth())/2;
         int y = (AQEngine.getHeight()-backdrop.getHeight())/2;
         g.setColor(Color.BLACK);
         g.fillRect(0, 0, AQEngine.getWidth(), y);
         g.fillRect(0, AQEngine.getHeight()-y-1, AQEngine.getWidth(), y+1);
         g.fillRect(0, y, x, AQEngine.getHeight()-2*y);
         g.fillRect(AQEngine.getWidth()-x-1, y, x+1, AQEngine.getHeight()-2*y);
         g.drawImage(backdrop, x, y, null);
      }
      for (int i = 0; i < blocks.size(); i++) {
         blocks.get(i).render(g);
      }
      if (selected != null && frame >= FRAME_LENGTH) {
         g.setColor(SELECT_COLOR);
         Rectangle rect = selected.getBounds();
         g.fillRoundRect(rect.x - round, rect.y - round, rect.width + round * 2, rect.height + round * 2, round * 2, round * 2);
      }
   }

   @Override
   public void update() {
      if (parentRunning) {
         parent.update();
      }
      if (frame < FRAME_LENGTH && !leaving) {
         frame++;
      }
      if (leaving) {
         if (frame > 0) {
            frame--;
         } else {
            System.out.println("yup");
            if (whereTo == null) {
               AQEngine.setMode(escape());
            } else {
               AQEngine.setMode(whereTo);
            }
            whereTo = null;
            leaving = false;
         }
      }
   }

   @Override
   public GameMode escape() {
      return parent;
   }
}