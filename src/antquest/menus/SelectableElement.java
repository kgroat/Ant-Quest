/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Kevin
 */
public abstract class SelectableElement extends TextElement{
   
   protected boolean enabled;
   
   public SelectableElement(String s, Font f, int tx, int ty){
      super(s, f, tx, ty);
      enabled = true;
   }
   
   public SelectableElement(String s, Font f, int tx, int ty, int loc){
      super(s, f, tx, ty, loc);
      enabled = true;
   }
   
   public void setEnabled(boolean b){
      enabled = b;
   }
   
   public boolean isDisabled(){
      return !enabled;
   }
   
   public boolean isEnabled(){
      return enabled;
   }
   
   @Override
   public void render(Graphics2D g){
      super.render(g);
   }
   
   public Rectangle getBounds(){
      return new Rectangle(trfX(), trfY(), width, height);
   }
   
   public abstract void confirm();
}
