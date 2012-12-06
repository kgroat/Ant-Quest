/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.numerical;

import java.awt.Color;

/**
 *
 * @author Kevin
 */
public class Gradient {
   protected int r1, g1, b1, a1;
   protected int r2, g2, b2, a2;
   protected int dr, dg, db, da;
   
   public Gradient(Color one, Color two){
      this(one.getRed(), one.getGreen(), one.getBlue(), one.getAlpha(), two.getRed(), two.getGreen(), two.getBlue(), two.getAlpha());
   }
   public Gradient(int tr1, int tg1, int tb1, int tr2, int tg2, int tb2){
      this(tr1, tg1, tb1, 255, tr2, tg2, tb2, 255);
   }
   public Gradient(int tr1, int tg1, int tb1, int ta1, int tr2, int tg2, int tb2, int ta2){
      //<editor-fold defaultstate="collapsed" desc="validate colors">
      if(tr1 > 255 || tr1 < 0){
         throw new IllegalArgumentException("r1 must be between 0 and 255, inclusive.");
      }
      if(tg1 > 255 || tg1 < 0){
         throw new IllegalArgumentException("g1 must be between 0 and 255, inclusive.");
      }
      if(tb1 > 255 || tb1 < 0){
         throw new IllegalArgumentException("b1 must be between 0 and 255, inclusive.");
      }
      if(ta1 > 255 || ta1 < 0){
         throw new IllegalArgumentException("a1 must be between 0 and 255, inclusive.");
      }
      if(tr2 > 255 || tr2 < 0){
         throw new IllegalArgumentException("r2 must be between 0 and 255, inclusive.");
      }
      if(tg2 > 255 || tg2 < 0){
         throw new IllegalArgumentException("g2 must be between 0 and 255, inclusive.");
      }
      if(tb2 > 255 || tb2 < 0){
         throw new IllegalArgumentException("b2 must be between 0 and 255, inclusive.");
      }
      if(ta2 > 255 || ta2 < 0){
         throw new IllegalArgumentException("a2 must be between 0 and 255, inclusive.");
      }
      //</editor-fold>
      
      r1 = tr1;
      g1 = tg1;
      b1 = tb1;
      a1 = ta1;
      r2 = tr2;
      g2 = tg2;
      b2 = tb2;
      a2 = ta2;
      dr = r2 - r1;
      dg = g2 - g1;
      db = b2 - b1;
      da = a2 - a1;
   }
   
   public Color get(double frac){
      if(frac < 0){
         return new Color(r1, g1, b1, a1);
      }else if(frac > 1){
         return new Color(r2, g2, b2, a2);
      }else{
         return new Color((int)(r1 + frac * dr), (int)(g1 + frac * dg), (int)(b1 + frac * db), (int)(a1 + frac * da));
      }
   }
}
