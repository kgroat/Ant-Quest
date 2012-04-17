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
      element = new SelectableElement("I'm 12.  What is this?", TextElement.MENU_FONT, cx+100, cy+45, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("I am ultra-pro!", TextElement.MENU_FONT, cx+100, cy+70, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("Y  U  NO...?", TextElement.MENU_FONT, cx+100, cy+95, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("Toodleoo", TextElement.MENU_FONT, cx+100, cy+120, TextElement.CENTER) {

         @Override
         public void confirm() {
            AQEngine.setMode(new QuittingMode(parent));
         }
      };
      block.add(element);
      selectDefault();
   }
   
   @Override
   public GameMode escape(){
      return new QuittingMode(this);
   }
}
