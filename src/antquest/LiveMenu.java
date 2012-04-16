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
public class LiveMenu extends GameMode{
   public static final Color SELECT_COLOR = new Color(150, 150, 250, 100);
   public static final int FRAME_LENGTH = 10;
   protected GameMode parent;
   protected ArrayList<MenuBlock> blocks;
   protected SelectableElement selected;
   protected int frame;
   protected boolean leaving;
   protected BufferedImage backdrop;

   public LiveMenu(GameMode p, BufferedImage bd){
      parent = p;
      backdrop = bd;
      frame = 0;
      leaving = false;
      blocks = new ArrayList<MenuBlock>();
      selected = null;
   }
     
   public void parse(Scanner in){ //Maybe should be a URL or String?
      //TODO: parse document for menu specification
   }
   
   @Override
   public void press(KeyEvent e) {
      int trans = InputHelper.transform(e);
      if((trans & InputHelper.CONFIRM) != 0){
         if(selected != null){
            selected.confirm();
            //TODO: Play confirm sound
         }else{
            //TODO: Play error sound
         }
      }
      if((trans & InputHelper.UP) != 0){
         //<editor-fold defaultstate="collapsed" desc="Move cursor north">
         if(selected != null){
            ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
            for(int i=0; i<blocks.size(); i++){
               sel.addAll(blocks.get(i).getSelectable());
            }
            int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
            SelectableElement curr;
            for(int i=0; i<sel.size(); i++){
               curr = sel.get(i);
               dirDist = selected.y - curr.y;
               currDist = Math.abs(selected.x - curr.x);
               if(curr != selected){
                  //Make sure in the north quadrant
                  if(dirDist >= currDist){
                     currDist += dirDist;
                     if(currDist < bestDist){
                        best = i;
                     }
                  }
               }
            }
            if(best == -1){
               //TODO: Play error sound
            }else{
               selected = sel.get(best);
               //TODO: Play cursor move sound
            }
         }else{
            if(!selectDefault()){
               //TODO: Play error sound
            }
         }
         //</editor-fold>
      }
      if((trans & InputHelper.DOWN) != 0){
         //<editor-fold defaultstate="collapsed" desc="Move cursor south">
         if(selected != null){
            ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
            for(int i=0; i<blocks.size(); i++){
               sel.addAll(blocks.get(i).getSelectable());
            }
            int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
            SelectableElement curr;
            for(int i=0; i<sel.size(); i++){
               curr = sel.get(i);
               dirDist = curr.y - selected.y;
               currDist = Math.abs(selected.x - curr.x);
               if(curr != selected){
                  //Make sure in the south quadrant
                  if(dirDist >= currDist){
                     currDist += dirDist;
                     if(currDist < bestDist){
                        best = i;
                     }
                  }
               }
            }
            if(best == -1){
               //TODO: Play error sound
            }else{
               selected = sel.get(best);
               //TODO: Play cursor move sound
            }
         }else{
            if(!selectDefault()){
               //TODO: Play error sound
            }
         }
         //</editor-fold>
      }
      if((trans & InputHelper.LEFT) != 0){
         //<editor-fold defaultstate="collapsed" desc="Move cursor west">
         if(selected != null){
            ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
            for(int i=0; i<blocks.size(); i++){
               sel.addAll(blocks.get(i).getSelectable());
            }
            int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
            SelectableElement curr;
            for(int i=0; i<sel.size(); i++){
               curr = sel.get(i);
               dirDist = selected.x - curr.x;
               currDist = Math.abs(selected.y - curr.y);
               if(curr != selected){
                  //Make sure in the west quadrant
                  if(dirDist >= currDist){
                     currDist += dirDist;
                     if(currDist < bestDist){
                        best = i;
                     }
                  }
               }
            }
            if(best == -1){
               //TODO: Play error sound
            }else{
               selected = sel.get(best);
               //TODO: Play cursor move sound
            }
         }else{
            if(!selectDefault()){
               //TODO: Play error sound
            }
         }
         //</editor-fold>
      }
      if((trans & InputHelper.RIGHT) != 0){
         //<editor-fold defaultstate="collapsed" desc="Move cursor east">
         if(selected != null){
            ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
            for(int i=0; i<blocks.size(); i++){
               sel.addAll(blocks.get(i).getSelectable());
            }
            int best = -1, bestDist = Integer.MAX_VALUE, dirDist, currDist;
            SelectableElement curr;
            for(int i=0; i<sel.size(); i++){
               curr = sel.get(i);
               dirDist = curr.x - selected.x;
               currDist = Math.abs(selected.y - curr.y);
               if(curr != selected){
                  //Make sure in the east quadrant
                  if(dirDist >= currDist){
                     currDist += dirDist;
                     if(currDist < bestDist){
                        best = i;
                     }
                  }
               }
            }
            if(best == -1){
               //TODO: Play error sound
            }else{
               selected = sel.get(best);
               //TODO: Play cursor move sound
            }
         }else{
            if(!selectDefault()){
               //TODO: Play error sound
            }
         }
         //</editor-fold>
      }
      if((trans & InputHelper.PAUSE) != 0){
         AQEngine.setMode(escape());
      }
   }
   
   public boolean selectDefault(){
      ArrayList<SelectableElement> sel = new ArrayList<SelectableElement>();
      for(int i=0; i<blocks.size(); i++){
         sel.addAll(blocks.get(i).getSelectable());
      }
      if(sel.size() > 0){
         selected = sel.get(0);
         return true;
      }else{
         return false;
      }
   }

   @Override
   public void release(KeyEvent e) {
      //Do nothing
   }

   public void select(int block, int num){
      MenuElement tmp;
      if((tmp = blocks.get(block).get(num)) instanceof SelectableElement){
         selected = (SelectableElement)tmp;
         //TODO: Play move cursor sound
      }else{
         //TODO: Play error sound
      }
   }
   
   @Override
   public void render(Graphics2D g) {
      final int round = 3;
      g.drawImage(backdrop, 0, 0, null);
      for(int i=0; i<blocks.size(); i++){
         blocks.get(i).render(g);
      }
      if(selected != null){
         g.setColor(SELECT_COLOR);
         Rectangle rect = selected.getBounds();
         g.fillRoundRect(rect.x-round, rect.y-round, rect.width+round*2, rect.height+round*2, round*2, round*2);
      }
   }

   @Override
   public void update() {
      if(frame < FRAME_LENGTH && !leaving){
         frame ++;
      }
      if(leaving){
         if(frame > 0){
            frame --;
         }else{
            AQEngine.setMode(parent);
         }
      }
   }
   
   @Override
   public GameMode escape(){
      leaving = true;
      return this;
   }
}
