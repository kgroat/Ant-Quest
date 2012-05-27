/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import antquest.menus.MainMenu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Kevin
 */
public final class AQEngine {

   private static int LOGIC_DELAY = 33;
   private static int PAINT_DELAY = 33;
   public static final Object MODE_KEY = new Object();
   private static Timer mainLoop, renderLoop;
   private static GameMode currentMode;
   private static BufferedImage buffer1, buffer2;
   private static boolean running;
   private static int vWidth, vHeight;
   private static boolean renderExtras1, nice;
   private static long lastRenderFrame, lastPlayFrame;
   private static int renderFrameTotal, playFrameTotal;
   private static ArrayList<Integer> renderFrameLengths, playFrameLengths;
   private static Random rand;

   private AQEngine() {
      //DO NOTHING
   }

   static void start() {
      running = true;
      resize();
      nice = false;
      renderFrameLengths = new ArrayList();
      playFrameLengths = new ArrayList();
      currentMode = new MainMenu();
      mainLoop = new Timer(LOGIC_DELAY, new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            update();
         }
      });
      renderLoop = new Timer(PAINT_DELAY, new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
            FullScreenView.instance().drawImage(render());
         }
      });
      mainLoop.start();
      renderLoop.start();
      rand = new Random();
   }

   static void stop() {
      running = false;
   }

   static void resize() {
      vWidth = FullScreenView.instance().getScreenWidth() / 2;
      vHeight = FullScreenView.instance().getScreenHeight() / 2;
      buffer1 = new BufferedImage(vWidth, vHeight, BufferedImage.TYPE_INT_RGB);
      buffer2 = new BufferedImage(vWidth, vHeight, BufferedImage.TYPE_INT_RGB);
   }

   static void pressKey(KeyEvent e) {
      synchronized (MODE_KEY) {
         if (currentMode != null) {
            currentMode.press(e);
         }
         if (e.getKeyCode() == KeyEvent.VK_F1) {
            renderExtras1 = true;
         }else if (e.getKeyCode() == KeyEvent.VK_F2){
            nice = !nice;
         }
      }
   }

   static void releaseKey(KeyEvent e) {
      synchronized (MODE_KEY) {
         if (currentMode != null) {
            currentMode.release(e);
         }
         if (e.getKeyCode() == KeyEvent.VK_F1) {
            renderExtras1 = false;
         }
      }
   }

   public static void setMode(GameMode g) {
      synchronized (MODE_KEY) {
         currentMode = g;
      }
   }

   public static BufferedImage render() {
      synchronized (MODE_KEY) {
         if (currentMode != null) {
            BufferedImage temp = buffer2;
            Graphics2D g = buffer1.createGraphics();
            if(nice){
               g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            }else{
               g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
            }
            currentMode.render(g);
            buffer2 = buffer1;
            buffer1 = temp;
         }
         renderExtras();
         return buffer2;
      }
   }

   public static BufferedImage getImage() {
      return buffer2;
   }

   public static void update() {
      synchronized (MODE_KEY) {
         if (currentMode != null) {
            long currentPlayFrame = System.currentTimeMillis();
            int currentPlayLength = (int) (currentPlayFrame - lastPlayFrame);
            playFrameTotal += currentPlayLength;
            playFrameLengths.add(currentPlayLength);
            if (playFrameLengths.size() > 100) {
               playFrameTotal -= playFrameLengths.remove(0);
            }
            lastPlayFrame = currentPlayFrame;
            currentMode.update();
         }
      }
   }

   public static int getWidth() {
      return vWidth;
   }

   public static int getHeight() {
      return vHeight;
   }

   public static GameMode getCurrentMode() {
      synchronized (MODE_KEY) {
         return currentMode;
      }
   }

   public static void renderExtras() {
      if (renderExtras1) {
         long currentRenderFrame = System.currentTimeMillis();
         int currentRenderLength = (int) (currentRenderFrame - lastRenderFrame);
         renderFrameTotal += currentRenderLength;
         renderFrameLengths.add(currentRenderLength);
         if (renderFrameLengths.size() > 10) {
            renderFrameTotal -= renderFrameLengths.remove(0);
         }

         lastRenderFrame = currentRenderFrame;
         Graphics2D bufferGraphics = buffer2.createGraphics();
         Object txt, aa;
         txt = bufferGraphics.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
         aa = bufferGraphics.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
         bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         bufferGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//         bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.fillRect(0, 0, vWidth, vWidth / 45);
         bufferGraphics.setColor(Color.BLACK);
         bufferGraphics.setFont(new Font("", Font.PLAIN, vWidth / 60));
         Runtime r = Runtime.getRuntime();
         r.runFinalization();
         bufferGraphics.drawString(String.format("Curr Mem Use: %010d   Curr Avail Mem: %010d   Max Mem: %010d   Vis: %05.2fFPS   Comp: %05.2fFPS", r.totalMemory() - r.freeMemory(), r.totalMemory(), r.maxMemory(), renderFrameLengths.size() * 1000f / renderFrameTotal, playFrameLengths.size() * 1000f / playFrameTotal), 3, vWidth / 60);
         bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, aa);
         bufferGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, txt);
      }
   }
   
   public static double randDouble(){
      return rand.nextDouble();
   }
   
   public static int randInt(){
      return rand.nextInt();
   }
   
   public static int randInt(int top){
      return rand.nextInt(top);
   }
   
   public static long randLong(){
      return rand.nextLong();
   }
}
