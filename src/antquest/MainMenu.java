/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin
 */
public class MainMenu extends LiveMenu{

   public MainMenu(){
      super(null, null);
      if(backdrop == null){
         backdrop = FileUtility.loadImage("MainMenuBacksplash.png");
      }
      if(backdrop == null){
         backdrop = new BufferedImage(AQEngine.getWidth(), AQEngine.getHeight(), BufferedImage.TYPE_INT_RGB);
         Graphics g = backdrop.getGraphics();
         g.setColor(Color.MAGENTA);
         g.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
      }
      parent = this;
   }
   
   @Override
   public void press(KeyEvent e) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void release(KeyEvent e) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void render(Graphics2D g) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void update() {
      throw new UnsupportedOperationException("Not supported yet.");
   }
   
   @Override
   public GameMode escape(){
      return new QuittingMode();
   }
}
