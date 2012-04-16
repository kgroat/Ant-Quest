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
   public static final Color DEFAULT_BACKGROUND = Color.BLACK;
   public static final Color DEFAULT_OUTLINE = Color.BLACK;
   public static final Color DEFAULT_FOREGROUND = Color.RED;
   protected int width, height;
   protected double max, value;
   protected Color outline, background, foreground;
   
   @Override
   public void render(Graphics2D g) {
      g.setColor(DEFAULT_OUTLINE);
      g.fillRoundRect(x, y, width, height, height/2, height/2);
      g.setColor(background);
      g.fillRoundRect(x+1, y+1, width-2, height-2, height/2-1, height/2-1);
      g.setColor(background);
      g.fillRoundRect(x+1, y+1, (width-2)*(int)(value/max), height-2, height/2-1, height/2-1);
   }
   
}
