/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kevin
 */
public abstract class GameMode {
   protected String name;
   
   public abstract void press(KeyEvent e);
   
   public abstract void release(KeyEvent e);
   
   public abstract void render(Graphics2D g);
   
   public abstract void update();
   
   public abstract GameMode escape();
}
