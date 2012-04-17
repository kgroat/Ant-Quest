package antquest;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * FullScreenView.java
 * <p>
 * The a singleton Frame which will be painted to the screen for FSEM when the
 * program is executed.
 * <p>
 * Author: Kevin Groat
 * Language: Java
 *
 * @author Kevin Groat
 * @version 0.1.0
 */
public class FullScreenView extends JFrame {

   /** The singleton instance of FullScreenView. */
   private static FullScreenView instance;
   /** The original DisplayMode of the monitor on which this program is being
    * viewed.  This is necessary to return back from FSEM. */
   private DisplayMode originalDisplayMode;
   /** The GraphicsDevice which is being rendered through. */
   private GraphicsDevice screen;
   /** The width (in pixels) of the display. */
   private int screenWidth;
   /** The height (in pixels) of the display. */
   private int screenHeight;
   private int insetLeft;
   private int insetTop;
   private boolean isFullScreen;
   private static boolean tryFull = false;

   /**
    * Getter method for the singleton instance of FullScreenView.
    * @return The singleton instance of FullScreenView.
    */
   public static FullScreenView instance() {
      return instance;
   }

   /**
    * Main method.  Starts the program, by initializing the singleton
    * instance of FullScreenView.
    * @param args - unused - -
    */
   public static void main(String[] args) throws IOException {
      if (instance == null) {
         instance = new FullScreenView(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
      }
   }
   
   /**
    * The only constructor available for FullScreenView, private because it
    * is a singleton.  It is called only in the main method.
    * @param screenDevice the GraphicsDevice which controls the user's monitor.
    */
   private FullScreenView(GraphicsDevice screenDevice) {
      setIgnoreRepaint(true);
      addKeyListener(new KeyAdapter() {

         @Override
         public void keyPressed(KeyEvent ke) {
            press(ke);
         }

         @Override
         public void keyReleased(KeyEvent ke) {
            release(ke);
         }
      });
      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      addWindowListener(new WindowAdapter(){
         @Override
         public void windowClosing(WindowEvent e) {
            GameMode gm;
            if((gm = AQEngine.getCurrentMode()) instanceof QuittingMode)
               closeProgram();
            else
               AQEngine.setMode(new QuittingMode(gm));
         }
      });

      screen = screenDevice;
      initializeGraphics();

      java.awt.EventQueue.invokeLater(new Runnable() {

         public void run() {
            fullScreenMode();
         }
      });
   }

   /**
    * Called by the KeyListener whenever a keyboard key is pressed.
    * @param e the KeyEvent containing the key information.
    */
   public void press(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE && e.isShiftDown()) {
         closeProgram();
      } else {
         AQEngine.pressKey(e);
      }
   }

   /**
    * Called by the KeyListener whenever a keyboard key is released.
    * @param e the KeyEvent containing the key information.
    */
   public void release(KeyEvent e) {
      AQEngine.releaseKey(e);
   }

   /**
    * Gets the screen dimension information, and makes an Image to buffer with.
    */
   private void initializeGraphics() {
      screenWidth = screen.getDisplayMode().getWidth();
      screenHeight = screen.getDisplayMode().getHeight();
   }

   /**
    * Sets the graphics of the screen to FSEM, if possible.
    * <p>
    * <FONT COLOR="#FF0000"><b>Do not alter this method!</b></FONT>
    */
   private static void fullScreenMode() {
      if (instance == null) {
         java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
               fullScreenMode();
            }
         });
      } else {
         instance.setTitle("FullScreenView");
         instance.originalDisplayMode = instance.screen.getDisplayMode();
         try {
            if (instance.tryFull && instance.screen.isFullScreenSupported()) {
               instance.setUndecorated(true);
               instance.setResizable(false);
               instance.screen.setFullScreenWindow(instance);
               instance.validate();
               instance.isFullScreen = true;
               instance.insetLeft = instance.insetTop = 0;
            } else {
               instance.setBounds(50, 50, instance.screenWidth-100, instance.screenHeight-100);
               instance.setUndecorated(false);
               instance.setVisible(true);
               instance.setResizable(false);
               Insets i = instance.getInsets();
               instance.screenWidth = instance.getWidth() - (i.right + i.left);
               instance.screenHeight = instance.getHeight() - (i.top + i.bottom);
               instance.isFullScreen = false;
               instance.insetLeft = i.left;
               instance.insetTop = i.top;
            }
         } catch (Exception e) {
            instance.closeProgram();
            System.err.println(e);
         }
         System.out.println("View initialized");
         AQEngine.start();
      }
   }

   /**
    * Getter method that returns the width dimension of the screen, in pixels.
    * @return The horizontal resolution of the screen.
    */
   public int getScreenWidth() {
      return screenWidth;
   }

   /**
    * Getter method that returns the height dimension of the screen, in pixels.
    * @return The vertical resolution of the screen.
    */
   public int getScreenHeight() {
      return screenHeight;
   }

   /**
    * Returns the graphics of the screen to the standard graphics, and
    * terminates the processing of the program.
    * <p>
    * <FONT COLOR="#FF0000"><b>Do not alter this method!</b></FONT>
    */
   void closeProgram() {
      AQEngine.stop();
      if (isFullScreen) {
         screen.setDisplayMode(originalDisplayMode);
      }
      System.exit(0);
   }

   /**
    * Draws a given image to the screen, stretching as necessary (using 
    * nearest neighbor algorithm).
    * @param in the image to be drawn to the screen.
    */
   public void drawImage(BufferedImage in) {
      Graphics g = this.getGraphics();
      g.drawImage(in, insetLeft, insetTop, screenWidth, screenHeight, this);
   }
}
