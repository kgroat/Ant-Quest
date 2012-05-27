/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Kevin
 */
public abstract class MenuElement {
   protected int x, y;
   
   public MenuElement(){
      this(0, 0);
   }
   
   public MenuElement(int tx, int ty){
      x = tx;
      y = ty;
   }
   
   public int getX(){
      return x;
   }
   
   public int getY(){
      return y;
   }
   
   public abstract void render(Graphics2D g);
}
