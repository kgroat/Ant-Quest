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
 * @author Kevin, Clem
 */
public class OptionsMenu extends LiveMenu {
   
    int bgm, sfx;  //track the old settings for BGM and SFX volume.  Used for cancelling.
    
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
      element = new SelectableElement("Keybindings", TextElement.MENU_FONT, cx+cw/2, cy+ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
            whereTo = new KeybindingMenu(t, backdrop);
            leaving = true;
         }
      };
      block.add(element);
      element = new SelectableElement("SFX Volume", TextElement.MENU_FONT, cx+cw/2, cy+2*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("BGM Volume", TextElement.MENU_FONT, cx+cw/2, cy+3*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
         }
      };
      block.add(element);
      element = new SelectableElement("Save and Return", TextElement.MENU_FONT, cx+cw/2, cy+4*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            whereTo = parent;
            leaving = true;
         }
      };
      block.add(element);
      element = new SelectableElement("Cancel!", TextElement.MENU_FONT, cx+cw/2, cy+4*ch/8, TextElement.CENTER)
      {
          @Override
          public void confirm()
          {
              whereTo = parent;
              leaving = true;
          }
      };
      block.add(element);
      selectDefault();
   }
}
