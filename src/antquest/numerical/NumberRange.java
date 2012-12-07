/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.numerical;

/**
 *
 * @author Kevin
 */
public class NumberRange {
   double min, max;
   int current;
   double iterator;
   boolean wrap;
   
   public NumberRange(double tMin, double tMax, double tCurr, double tIterator, boolean tWrap){
      min = tMin;
      max = tMax;
      iterator = tIterator;
      if(iterator == 0){
         iterator = (max - min)/100;
      }
      current = findIt(tCurr, min, max, iterator);
      wrap = tWrap;
   }
   
   public double getMin(){
      return min;
   }
   
   public double getMax(){
      return max;
   }
   
   public double getIterator(){
      return iterator;
   }
   
   public double getCurrent(){
      return current * iterator + min;
   }
   
   public double up(){
      System.out.println("PRE:");
      System.out.println("MIN: " + min + ", MAX: " + max + ", ITERATOR: " + iterator);
      System.out.println("VAL: " + getCurrent());
      current++;
      System.out.println("POST:");
      System.out.println("MIN: " + min + ", MAX: " + max + ", ITERATOR: " + iterator);
      System.out.println("VAL: " + getCurrent());
      return checkExtents(true);
   }
   
   public double down(){
      System.out.println("PRE:");
      System.out.println("MIN: " + min + ", MAX: " + max + ", ITERATOR: " + iterator);
      System.out.println("VAL: " + getCurrent());
      current--;
      System.out.println("POST:");
      System.out.println("MIN: " + min + ", MAX: " + max + ", ITERATOR: " + iterator);
      System.out.println("VAL: " + getCurrent());
      return checkExtents(false);
   }
   
   private double checkExtents(boolean up){
      double val = getCurrent();
      
      boolean betweenMinAndMax = false;
      if(min < max){
         betweenMinAndMax = val <= max && val >= min;
      }else{
         betweenMinAndMax = val >= max && val <= min;
      }
      
      if(betweenMinAndMax){
         return val;
      }else{
         if(wrap){
            
            if(min < max ^ (up ^ iterator > 0)){
               current = 0;
               return min;
            }else{
               current = divide(max, min, iterator);
               return getCurrent();
            }
         }else{
            if(up){
               current--;
            }else{
               current++;
            }
            return getCurrent();
         }
      }
   }
   
   private static int findIt(double curr, double min, double max, double it){
      if(min < max){
         if(curr < min){
            return 0;
         }else if(curr > max){
            return divide(max, min, it);
         }else{
            return divide(curr, min, it);
         }
      }else{
         if(curr > min){
            return 0;
         }else if(curr < max){
            return divide(max, min, it);
         }else{
            return divide(curr, min, it);
         }
      }
   }
   
   public static int divide(double curr, double min, double it){
      double out = curr - min;
      return (int)(out / it);
   }
}
