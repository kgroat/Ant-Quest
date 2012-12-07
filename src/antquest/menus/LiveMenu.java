/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import antquest.AQEngine;
import antquest.GameMode;
import antquest.InputHelper;
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

   public static final Color DEFAULT_SELECT_COLOR = new Color(150, 150, 250, 100);
   public static final Color DEFAULT_DISABLED_COLOR = new Color(100, 100, 100, 150);
   
   public static final int FRAME_LENGTH = 10;
   protected Color selectColor, disabledColor, feint;
   protected GameMode parent;
   protected ArrayList<MenuBlock> blocks;
   protected SelectableElement selected;
   protected int frame;
   protected boolean leaving, parentRunning, parentRendering, ud, lr;
   protected BufferedImage backdrop, parentBuffer;
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
      ud = lr = true;
      selectColor = DEFAULT_SELECT_COLOR;
      disabledColor = DEFAULT_DISABLED_COLOR;
   }

   public void parse(Scanner in) { //Maybe should be a URL or String?
      //TODO: parse document for menu specification
   }

   @Override
   public void press(KeyEvent e) {
      boolean pressed = false;
      if (frame == FRAME_LENGTH) {
         int trans = InputHelper.transform(e);
         if ((trans & InputHelper.CONFIRM) != 0) {
            if (selected != null) {
               pressed = true;
               selected.confirm();
               CONFIRM.forcePlay(true, true);
            } else {
               pressed = true;
               ERROR.tryPlay(true, true);
            }
         }
         if(ud){
            if ((trans & InputHelper.UP) != 0) {
               pressed = true;
               //<editor-fold defaultstate="collapsed" desc="Move cursor north">
               if (selected != null) {
                  if(lr){
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = selected.getCenterY() - curr.getCenterY();
                        currDist = Math.abs(selected.getCenterX() - curr.getCenterX());
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
                  }else{
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = selected.getCenterY() - curr.getCenterY();
                        if (curr != selected && dirDist > 0) {
                           currDist = Math.abs(dirDist);
                           if (currDist < bestDist) {
                              best = i;
                              bestDist = currDist;
                           }
                        }
                     }
                     if (best == -1) {
                        ERROR.tryPlay(true, true);
                     } else {
                        selected = sel.get(best);
                        CURSOR.forcePlay(true, true);
                     }
                  }
               } else {
                  if (!selectDefault()) {
                     ERROR.tryPlay(true, true);
                  }
               }
               //</editor-fold>
            }
            if ((trans & InputHelper.DOWN) != 0) {
               pressed = true;
               //<editor-fold defaultstate="collapsed" desc="Move cursor south">
               if (selected != null) {
                  if(lr){
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = curr.getCenterY() - selected.getCenterY();
                        currDist = Math.abs(selected.getCenterX() - curr.getCenterX());
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
                  }else{
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = curr.getCenterY() - selected.getCenterY();
                        if (curr != selected && dirDist > 0) {
                           currDist = Math.abs(dirDist);
                           if (currDist < bestDist) {
                              best = i;
                              bestDist = currDist;
                           }
                        }
                     }
                     if (best == -1) {
                        ERROR.tryPlay(true, true);
                     } else {
                        selected = sel.get(best);
                        CURSOR.forcePlay(true, true);
                     }
                  }
               } else {
                  if (!selectDefault()) {
                     ERROR.tryPlay(true, true);
                  }
               }
               //</editor-fold>
            }
         }
         if(lr){
            if ((trans & InputHelper.LEFT) != 0) {
               pressed = true;
               //<editor-fold defaultstate="collapsed" desc="Move cursor west">
               if (selected != null) {
                  if(ud){
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = selected.getCenterX() - curr.getCenterX();
                        currDist = Math.abs(selected.getCenterY() - curr.getCenterY());
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
                  }else{
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = selected.getCenterX() - curr.getCenterX();
                        if (curr != selected && dirDist > 0) {
                           currDist = Math.abs(dirDist);
                           if (currDist < bestDist) {
                              best = i;
                              bestDist = currDist;
                           }
                        }
                     }
                     if (best == -1) {
                        ERROR.tryPlay(true, true);
                     } else {
                        selected = sel.get(best);
                        CURSOR.forcePlay(true, true);
                     }
                  }
               } else {
                  if (!selectDefault()) {
                     ERROR.tryPlay(true, true);
                  }
               }
               //</editor-fold>
            }
            if ((trans & InputHelper.RIGHT) != 0) {
               pressed = true;
               //<editor-fold defaultstate="collapsed" desc="Move cursor east">
               if (selected != null) {
                  if(ud){
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = curr.getCenterX() - selected.getCenterX();
                        currDist = Math.abs(selected.getCenterY() - curr.getCenterY());
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
                  }else{
                     ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
                     for (int i = 0; i < blocks.size(); i++) {
                        sel.addAll(blocks.get(i).getSelectable());
                     }
                     int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
                     SelectableElement curr;
                     for (int i = 0; i < sel.size(); i++) {
                        curr = sel.get(i);
                        dirDist = selected.getCenterX() - curr.getCenterX();
                        if (curr != selected && dirDist > 0) {
                           currDist = Math.abs(dirDist);
                           if (currDist < bestDist) {
                              best = i;
                              bestDist = currDist;
                           }
                        }
                     }
                     if (best == -1) {
                        ERROR.tryPlay(true, true);
                     } else {
                        selected = sel.get(best);
                        CURSOR.forcePlay(true, true);
                     }
                  }
               } else {
                  if (!selectDefault()) {
                     ERROR.tryPlay(true, true);
                  }
               }
               //</editor-fold>
            }
         }
         if ((trans & InputHelper.PAUSE) != 0) {
            pressed = true;
            leaving = true;
         }
         if(!pressed){
            ERROR.tryPlay(true, true);
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
   
   public Color getSelectColor(){
      return selectColor;
   }
   
   public void setSelectColor(Color sel){
      selectColor = sel;
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
      if(parentRendering && parentBuffer == null){
         parentBuffer = new BufferedImage(AQEngine.getWidth(), AQEngine.getHeight(), BufferedImage.TYPE_INT_ARGB);
      }
      if (frame < FRAME_LENGTH && !leaving) {
         frame++;
      }else if(leaving && frame > 0){
         frame--;
      }
      final int round = 3;
      if (parentRendering) {
         Graphics2D g2 = parentBuffer.createGraphics();
         parent.render(g2);
         if(feint != null){
            g2.setColor(feint);
            g2.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
         }
         g.drawImage(parentBuffer, 0, 0, null);
      }
      if (backdrop != null) {
         int x = (AQEngine.getWidth()-backdrop.getWidth())/2;
         int y = (AQEngine.getHeight()-backdrop.getHeight())/2;
         if(!parentRendering){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, AQEngine.getWidth(), y);
            g.fillRect(0, AQEngine.getHeight()-y-1, AQEngine.getWidth(), y+1);
            g.fillRect(0, y, x, AQEngine.getHeight()-2*y);
            g.fillRect(AQEngine.getWidth()-x-1, y, x+1, AQEngine.getHeight()-2*y);
         }
         g.drawImage(backdrop, x, y, null);
      }
      for (int i = 0; i < blocks.size(); i++) {
         blocks.get(i).render(g);
      }
      if (selected != null && frame >= FRAME_LENGTH) {
         if(selected.isEnabled()){
            g.setColor(selectColor);
         }else{
            g.setColor(disabledColor);
         }
         Rectangle rect = selected.getBounds();
         g.fillRoundRect(rect.x - round, rect.y - round, rect.width + round * 2, rect.height + round * 2, round * 2, round * 2);
      }
   }

   @Override
   public void update() {
      if (parentRunning) {
         parent.update();
      }
      if (leaving) {
         if (frame <= 0){
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