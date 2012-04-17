/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Kevin
 */
public class QuittingMode extends LiveMenu{
   public static final Color FEINT = new Color(0, 0, 0, 100);

   public QuittingMode(GameMode p){
      super(p, null);
      backdrop = AQEngine.render();
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
            AQEngine.setMode(escape());
         }
      };
      block.add(element);
      element = new SelectableElement("Screw this!", null, cx+110, cy+45, TextElement.CENTER) {

         @Override
         public void confirm() {
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
