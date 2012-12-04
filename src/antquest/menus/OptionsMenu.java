/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import antquest.AQEngine;
import antquest.GameMode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin
 */
public class OptionsMenu extends LiveMenu {
   
   public OptionsMenu(GameMode p, BufferedImage bi){
      super(p, bi);
      final OptionsMenu t = this;
      lr = false;
      System.out.println(parent);
      System.out.println(backdrop);
      System.out.println(p);
      System.out.println(bi);
      System.out.println(this);
      if(backdrop == null){
         backdrop = new BufferedImage(AQEngine.getWidth(), AQEngine.getHeight(), BufferedImage.TYPE_INT_RGB);
         Graphics g = backdrop.getGraphics();
         g.setColor(Color.MAGENTA);
         g.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
      }
      int cx = 50, cy = 50, cw = AQEngine.getWidth()-100, ch = AQEngine.getHeight()-100;
      MenuBlock block = new MenuBlock(this, cx, cy, cw, ch);
      blocks.add(block);
      MenuElement element = new ProgressbarElement(cx+10, cy+10, cw-20, 32, 100, AQEngine.randDouble()*100);
      block.add(element);
      element = new SelectableElement("FIRST OPTION (Key bindings)", TextElement.MENU_FONT, cx+cw/2, cy+ch/5, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
            whereTo = new KeybindingMenu(t, backdrop);
            leaving = true;
         }
      };
      block.add(element);
      element = new SelectableElement("The next one!", TextElement.MENU_FONT, cx+cw/2, cy+2*ch/5, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("And a THIRD!", TextElement.MENU_FONT, cx+cw/2, cy+3*ch/5, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("WAT AM I DOIN HURR?", TextElement.MENU_FONT, cx+cw/2, cy+4*ch/5, TextElement.CENTER) {

         @Override
         public void confirm() {
            whereTo = parent;
            leaving = true;
         }
      };
      block.add(element);
      selectDefault();
   }
}
