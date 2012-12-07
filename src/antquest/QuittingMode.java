/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import antquest.menus.SelectableElement;
import antquest.menus.MenuElement;
import antquest.menus.TextElement;
import antquest.menus.MenuBlock;
import antquest.menus.LiveMenu;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
public class QuittingMode extends LiveMenu{
   public static final AudioClip LEAVE = AudioClip.get("Select1.ogg");
   public static final Color FEINT = new Color(0, 0, 0, 100);

   public QuittingMode(GameMode p){
      super(p, null);
      lr = false;
      backdrop = AQEngine.getImage();
      Graphics2D g = backdrop.createGraphics();
      g.setColor(FEINT);
      g.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
      int cx = AQEngine.getWidth()/2-110, cy = AQEngine.getHeight()/2-50;
      MenuBlock block = new MenuBlock(this, cx, cy, 220, 100);
      blocks.add(block);
      MenuElement element = new TextElement("Are you sure you want to quit?", TextElement.HEADING, cx+110, cy+10, TextElement.CENTER);
      block.add(element);
      element = new SelectableElement("NO!  I didn't mean to!", null, cx+110, cy+65, TextElement.CENTER) {

         @Override
         public void confirm() {
            whereTo = null;
            leaving = true;
         }
      };
      block.add(element);
      element = new SelectableElement("YES!  I'm outta here!", null, cx+110, cy+45, TextElement.CENTER) {

         @Override
         public void confirm() {
            try {
               LEAVE.forcePlay(true, true);
               Thread.sleep(LEAVE.getMillis());
            } catch (InterruptedException ex) {
               Logger.getLogger(QuittingMode.class.getName()).log(Level.SEVERE, null, ex);
            }
            FullScreenView.instance().closeProgram();
         }
      };
      block.add(element);
      selectDefault();
   }
   
   @Override
   public GameMode escape() {
      return parent;
   }
   
}
