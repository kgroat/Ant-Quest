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
   protected int width, height;
   protected Color disabledColor;
   
   public SelectableElement(String s, Font f, int tx, int ty){
      super(s, f, tx, ty);
   }
   
   public void setEnabled(boolean b){
      enabled = b;
   }
   
   @Override
   protected void createLineMetrics(){
      super.createLineMetrics();
      Rectangle2D rect = font.getStringBounds(text, frc);
      width = (int)rect.getWidth();
      height = (int)rect.getHeight();
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
