/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

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
   public static final Color DEFAULT_DISABLED = new Color(100, 100, 100, 150);
   
   protected boolean enabled;
   protected Color disabledColor;
   
   public SelectableElement(String s, Font f, int tx, int ty){
      super(s, f, tx, ty);
   }
   
   public SelectableElement(String s, Font f, int tx, int ty, int loc){
      super(s, f, tx, ty, loc);
   }
   
   public void setEnabled(boolean b){
      enabled = b;
   }
   
   @Override
   public void render(Graphics2D g){
      super.render(g);
   }
   
   public Rectangle getBounds(){
      return new Rectangle(x, y, width, height);
   }
   
   public abstract void confirm();
}
