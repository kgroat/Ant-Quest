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
   public static final Color DEFAULT_COLOR = new Color(210, 210, 210);
   public static final Font DEFAULT_FONT = new Font("Times New Roman", Font.PLAIN, 12);
   protected Font font;
   protected String text;
   protected LineMetrics metrics;
   protected Color color;
   protected FontRenderContext frc;
   
   public TextElement(){
      this("TextElement", DEFAULT_FONT, 0, 0);
   }
   
   public TextElement(int tx, int ty){
      this("TextElement", DEFAULT_FONT, tx, ty);
   }
   
   public TextElement(String s, Font f){
      this(s, f, 0, 0);
   }
   
   public TextElement(String s, Font f, int tx, int ty){
      text = s;
      if(f != null)
         font = f;
      else
         font = DEFAULT_FONT;
      x = tx;
      y = ty;
      color = DEFAULT_COLOR;
      createLineMetrics();
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
