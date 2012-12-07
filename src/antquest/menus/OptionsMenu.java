/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import antquest.AQEngine;
import antquest.AudioClip;
import antquest.GameMode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin, Clem
 */
public class OptionsMenu extends LiveMenu {
   
   private float bgm, sfx;//track the old settings for BGM and SFX volume.  Used for cancelling.
   private float current_bgm, current_sfx;
    
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
      current_bgm = bgm = AudioClip.getSubGain(AudioClip.ClipType.music);
      current_sfx = sfx = AudioClip.getSubGain(AudioClip.ClipType.sfx);
      MenuBlock block = new MenuBlock(this, cx, cy, cw, ch);
      blocks.add(block);
      MenuElement element;
      element = new SelectableElement("Keybindings", TextElement.MENU_FONT, cx+cw/2, cy+ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            System.out.println(text);
            whereTo = new KeybindingMenu(t, backdrop);
            leaving = true;
         }
      };
      block.add(element);
      element = new SelectableElement("SFX Volume Up", TextElement.MENU_FONT, cx+cw/2, cy+2*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            current_sfx = (float)Math.min(current_sfx + .1, 1);
            AudioClip.setSubGain(AudioClip.ClipType.sfx, current_sfx);
         }
      };
      block.add(element);
      element = new SelectableElement("SFX Volume Down", TextElement.MENU_FONT, cx+cw/2, cy+3*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            current_sfx = (float)Math.max(0, current_sfx - .1);
            AudioClip.setSubGain(AudioClip.ClipType.sfx, current_sfx);
         }
      };
      block.add(element);
      element = new SelectableElement("BGM Volume Up", TextElement.MENU_FONT, cx+cw/2, cy+4*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            current_bgm = (float) Math.min(current_bgm + .1, 1);
            AudioClip.setSubGain(AudioClip.ClipType.music, current_bgm);
         }
      };
      block.add(element);
      element = new SelectableElement("BGM Volume Down", TextElement.MENU_FONT, cx+cw/2, cy+5*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            current_bgm = (float) Math.max(0, current_bgm - .1);
            AudioClip.setSubGain(AudioClip.ClipType.music, current_bgm);
         }
      };
      block.add(element);
      element = new SelectableElement("Save and Return", TextElement.MENU_FONT, cx+cw/2, cy+6*ch/8, TextElement.CENTER) {

         @Override
         public void confirm() {
            whereTo = parent;
            leaving = true;
         }
      };
      block.add(element);
      element = new SelectableElement("Cancel!", TextElement.MENU_FONT, cx+cw/2, cy+7*ch/8, TextElement.CENTER)
      {
          @Override
          public void confirm()
          {
              AudioClip.setSubGain(AudioClip.ClipType.music, bgm);
              AudioClip.setSubGain(AudioClip.ClipType.sfx, sfx);
              whereTo = parent;
              leaving = true;
          }
      };
      block.add(element);
      selectDefault();
   }
}
