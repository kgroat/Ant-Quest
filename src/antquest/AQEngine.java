/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin
 */
public final class AQEngine {
   
   private static int LOGIC_DELAY = 30;
   private static int PAINT_DELAY = 30;
   
   private static Thread mainLoop, renderLoop;
   private static GameMode currentMode;
   private static BufferedImage buffer;
   
   private static boolean running;
   
   private static int vWidth, vHeight;
   
   private AQEngine(){
      //DO NOTHING
   }
   
   static void start() {
      running = true;
      resize();
      currentMode = new MainMenu();
      mainLoop = new Thread(){
         public void run(){
            while(running){
               try{
                  update();
                  renderLoop.sleep(LOGIC_DELAY);
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         }
      };
      mainLoop.setDaemon(false);
      renderLoop = new Thread(){
         public void run(){
            while(running){
               try{
                  FullScreenView.instance().drawImage(render());
                  renderLoop.sleep(PAINT_DELAY);
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         }
      };
      renderLoop.setDaemon(true);
      mainLoop.start();
      renderLoop.start();
   }
   
   static void stop(){
      running = false;
   }
   
   static void resize(){
      vWidth = FullScreenView.instance().getScreenWidth()/2;
      vHeight = FullScreenView.instance().getScreenHeight()/2;
      buffer = new BufferedImage(vWidth, vHeight, BufferedImage.TYPE_INT_RGB);
   }
   
   static void pressKey(KeyEvent e) {
      if(currentMode != null)
         currentMode.press(e);
   }

   static void releaseKey(KeyEvent e) {
      if(currentMode != null)
         currentMode.release(e);
   }

   static void setMode(GameMode g){
      currentMode = g;
   }
   
   public static BufferedImage render(){
      if(currentMode != null){
         currentMode.render(buffer.createGraphics());
      }
      return buffer;
   }
   
   public static void update(){
      if(currentMode != null)
         currentMode.update();
   }
   
   public static int getWidth(){
      return vWidth;
   }
   
   public static int getHeight(){
      return vHeight;
   }
   
   public static GameMode getCurrentMode(){
      return currentMode;
   }
   
}
