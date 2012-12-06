/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import antquest.AQEngine;
import antquest.AudioClip;
import antquest.FullScreenView;
import antquest.GameMode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class ConfirmMenu extends LiveMenu{
   public static final Color FEINT = new Color(0, 0, 0, 100);
   
   protected Runnable onConfirm;

   public ConfirmMenu(GameMode p, Runnable r){
      super(p, null);
      lr = false;
      backdrop = AQEngine.getImage();
      onConfirm = r;
      int cx = AQEngine.getWidth()/2-110, cy = AQEngine.getHeight()/2-50;
      MenuBlock block = new MenuBlock(this, cx, cy, 220, 100);
      blocks.add(block);
      MenuElement element = new TextElement("Are you sure?", TextElement.HEADING, cx+110, cy+10, TextElement.CENTER);
      block.add(element);
      element = new SelectableElement("FUCK YES!", null, cx+110, cy+65, TextElement.CENTER) {

         @Override
         public void confirm() {
            onConfirm.run();
            whereTo = null;
            leaving = true;
         }
      };
      block.add(element);
      element = new SelectableElement("Wait! I forgot about...", null, cx+110, cy+45, TextElement.CENTER) {

         @Override
         public void confirm() {
            whereTo = null;
            leaving = true;
         }
      };
      block.add(element);
      selectDefault();
      parentRendering = true;
      feint = FEINT;
   }
   
   @Override
   public GameMode escape() {
      return parent;
   }
   
   public boolean isWaiting(){
      return !leaving;
   }
   
}
