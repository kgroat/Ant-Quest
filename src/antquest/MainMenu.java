/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin
 */
public class MainMenu extends LiveMenu{

   public MainMenu(){
      super(null, null);
      if(backdrop == null){
         backdrop = FileUtility.loadImage("MainMenuBacksplash.png");
      }
      if(backdrop == null){
         backdrop = new BufferedImage(AQEngine.getWidth(), AQEngine.getHeight(), BufferedImage.TYPE_INT_RGB);
         Graphics g = backdrop.getGraphics();
         g.setColor(Color.MAGENTA);
         g.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
      }
      parent = this;
      int cx = AQEngine.getWidth()/2-100, cy =AQEngine.getHeight()/2;
      MenuBlock block = new MenuBlock(this, cx, cy, 200, 150);
      blocks.add(block);
      MenuElement element = new ProgressbarElement(cx+10, cy+10, 180, 32, 100, 27);
      block.add(element);
      element = new SelectableElement("Main Menu", null, cx+10, cy+45) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("Other Bit", null, cx+10, cy+65) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
   }
   
   @Override
   public GameMode escape(){
      return new QuittingMode();
   }
}
