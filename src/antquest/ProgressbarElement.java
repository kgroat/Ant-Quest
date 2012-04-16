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
public class ProgressbarElement extends MenuElement{
   public static final Color DEFAULT_BACKGROUND = new Color(80, 80, 80);
   public static final Color DEFAULT_OUTLINE = Color.BLACK;
   public static final Color DEFAULT_FOREGROUND = Color.RED;
   protected int width, height;
   protected double max, value;
   protected Color outline, background, foreground;
   
   public ProgressbarElement(int tx, int ty, int twidth, int theight, double tmax, double tval){
      super(tx, ty);
      width = twidth;
      height = theight;
      max = tmax;
      value = tval;
      outline = DEFAULT_OUTLINE;
      background = DEFAULT_BACKGROUND;
      foreground = DEFAULT_FOREGROUND;
   }
   
   @Override
   public void render(Graphics2D g) {
      g.setColor(DEFAULT_OUTLINE);
      g.fillRoundRect(x, y, width, height, height/2, height/2);
      g.setColor(background);
      g.fillRoundRect(x+1, y+1, width-2, height-2, height/2-1, height/2-1);
      g.setColor(foreground);
      g.fillRoundRect(x+1, y+1, (int)((width-2)*(value/max)), height-2, height/2-1, height/2-1);
   }
   
}
