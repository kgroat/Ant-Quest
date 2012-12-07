/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import antquest.FullScreenView;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ListBlock extends MenuBlock{
   
   private static final String testString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
   protected Font fontToUse;
   protected int nx, ny;
   protected int colWidth;
   protected int xMargin, yMargin;
   protected int lineHeight;
   
   public ListBlock(LiveMenu p, int tx, int ty, int tw, int th, Font f){
      super(p, tx, ty, tw, th);
      xMargin = 3;
      yMargin = 3;
      colWidth = tw;
      fontToUse = f;
      nx = x + xMargin;
      ny = y + yMargin;
      lineHeight = (int)Math.ceil(findLineHeight(f, testString));
   }
   
   public void put(TextElement te){
      te.setFont(fontToUse);
      te.setX(nx);
      te.setY(ny);
      te.setJustification(TextElement.LEFT_JUSTIFIED);
      if(nx + colWidth > x + width){
         nx = x + xMargin;
         ny += lineHeight;
      }
      this.add(te);
   }
   
   @Override
   public void clear(){
      nx = x + xMargin;
      ny = y + yMargin;
      super.clear();
   }
   
   public Font getFont(){
      return fontToUse;
   }
   
   private static double findLineHeight(Font f, String testString){
      Graphics2D g = (Graphics2D)FullScreenView.instance().getGraphics();
      g.setFont(f);
      FontRenderContext frc = g.getFontRenderContext();
      Rectangle2D r = f.getStringBounds(testString, frc);
      return r.getHeight();
   }
   
}
