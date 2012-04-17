/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

/**
 *
 * @author Kevin
 */
public class TextElement extends MenuElement{
   static{
      createFont("/antquest/resources/MEANSANS.TTF");
      createFont("/antquest/resources/Something.ttf");
      createFont("/antquest/resources/Thor.ttf");
   }
   private static Font createFont(String s){
      try{
         return Font.createFont(Font.TRUETYPE_FONT, FileUtility.loadStream(s));
      }catch(Exception e){
         e.printStackTrace();
         return null;
      }
   }
   
   public static final Font THOR = createFont("/antquest/resources/Thor.ttf");
   public static final Font MEAN = createFont("/antquest/resources/MEANSANS.TTF");
   public static final Font SOMETHING = createFont("/antquest/resources/Something.ttf");
   
   public static final Font HEADING = new Font("", Font.BOLD, 15);//SOMETHING.deriveFont(Font.BOLD, 15);
   public static final Font MENU_FONT = new Font("", Font.BOLD, 20);//SOMETHING.deriveFont(Font.BOLD, 20);
   public static final Font DEFAULT_FONT = new Font("", Font.PLAIN, 12);//SOMETHING.deriveFont(Font.PLAIN, 12);
   public static final Color DEFAULT_COLOR = new Color(50, 50, 120);
   public static final int LEFT_JUSTIFIED = 0;
   public static final int CENTER = 1;
   public static final int RIGHT_JUSTIFIED = 2;
   protected Font font;
   protected String text;
   protected LineMetrics metrics;
   protected Color color;
   protected FontRenderContext frc;
   protected int width, height;
   
   public TextElement(){
      this("TextElement", DEFAULT_FONT, 0, 0, LEFT_JUSTIFIED);
   }
   
   public TextElement(int tx, int ty){
      this("TextElement", DEFAULT_FONT, tx, ty, LEFT_JUSTIFIED);
   }
   
   public TextElement(String s, Font f){
      this(s, f, 0, 0, LEFT_JUSTIFIED);
   }
   
   public TextElement(String s, Font f, int tx, int ty){
      this(s, f, tx, ty, LEFT_JUSTIFIED);
   }
   
   public TextElement(String s, Font f, int tx, int ty, int loc){
      text = s;
      if(f != null)
         font = f;
      else
         font = DEFAULT_FONT;
      x = tx;
      y = ty;
      createLineMetrics();
      switch(loc){
         case CENTER:
            x = tx - width/2;
            break;
         case RIGHT_JUSTIFIED:
            x = tx - width;
            break;
      }
      color = DEFAULT_COLOR;
   }
   
   @Override
   public void render(Graphics2D g){
      
      //FontRenderContext frc = g.getFontRenderContext();
      //Shape rend = font.createGlyphVector(frc, text).getOutline();
//      g.setPaint(new ReliefPaint(rend));
//      g.setComposite(new Composite() {
//
//         @Override
//         public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
//            throw new UnsupportedOperationException("Not supported yet.");
//         }
//      });
      //g.draw(rend);
      g.setFont(font);
      g.setColor(color);
      g.drawString(text, x, y+metrics.getAscent());
   }
   
   public void setText(String s){
      text = s;
      createLineMetrics();
   }
   
   public void setFont(Font f){
      font = f;
   }
   
   protected void createLineMetrics(){
      Graphics2D g = (Graphics2D)FullScreenView.instance().getGraphics();
      g.setFont(font);
      frc = g.getFontRenderContext();
      metrics = font.getLineMetrics(text, frc);
      Rectangle2D r = font.getStringBounds(text, frc);
      width = (int)r.getWidth();
      height = (int)r.getHeight();
   }
   
   @Override
   public int getX(){
      return x + width/2;
   }
   
   @Override
   public int getY(){
      return y + height/2;
   }
   
   private class ReliefPaint implements Paint{

      Shape shape;
      
      ReliefPaint(Shape s){
         shape = s;
      }
      
      @Override
      public PaintContext createContext(final ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
         return new PaintContext() {

            @Override
            public void dispose() {
               //Do nothing
            }

            @Override
            public ColorModel getColorModel() {
               return cm;
            }

            @Override
            public Raster getRaster(int x, int y, int w, int h) {
               SampleModel sm = cm.createCompatibleSampleModel(w, h);
               int[][] data = new int[4][w*h];
               //First pass (darks)
               for(int ty=0; ty<h; ty++){
                  for(int tx=0; tx<y; tx++){
                     if(shape.contains(tx, ty)){
                        if(tx>0 && ty>0){
                           data[0][ty*w+tx] = data[3][(ty-1)*w+(tx-1)];
                        }else{
                           data[0][ty*w+tx]=1;
                        }
                     }else{
                        data[0][ty*w+tx]=0;
                     }
                  }
               }
               //Second pass (lights)
               DataBuffer db = new DataBufferInt(data, w*h);
               Rectangle rect = new Rectangle(x, y, w, h);
               Point p = new Point(x, y);
               return Raster.createRaster(sm, db, p);
            }
         };
      }

      @Override
      public int getTransparency() {
         throw new UnsupportedOperationException("Not supported yet.");
      }
      
   }
}
