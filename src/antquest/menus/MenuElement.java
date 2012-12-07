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
   protected final MenuElement self;
   protected int x, y;
   
   public MenuElement(){
      this(0, 0);
   }
   
   public MenuElement(int tx, int ty){
      x = tx;
      y = ty;
      self = this;
   }
   
   public int getX(){
      return x;
   }
   
   public int getY(){
      return y;
   }
   
   public void setX(int tx){
      x = tx;
   }
   
   public void setY(int ty){
      y = ty;
   }
   
   public abstract void render(Graphics2D g);
}
