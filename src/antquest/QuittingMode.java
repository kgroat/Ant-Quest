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
public class QuittingMode extends GameMode{

   @Override
   public void press(KeyEvent e) {
      //Do nothing
   }

   @Override
   public void release(KeyEvent e) {
      //Do nothing
   }

   @Override
   public void render(Graphics2D g) {
      //Do nothing
   }

   @Override
   public void update() {
      //ready to close
      FullScreenView.instance().closeProgram();
   }

   @Override
   public GameMode escape() {
      return this;
   }
   
}
