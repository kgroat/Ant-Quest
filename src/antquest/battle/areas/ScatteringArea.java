/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle.areas;

import antquest.battle.Hex;
import antquest.battle.areas.AreaTemplate;
import java.util.Random;

/**
 *
 * @author Kevin
 */
public class ScatteringArea extends AreaTemplate{

   protected AreaTemplate parent;
   protected double factor;
   protected long seed;
   
   public ScatteringArea(AreaTemplate tParent, double tFactor, long tSeed){
      parent = tParent;
      factor = Math.max(0, Math.min(tFactor, 1));
      seed = tSeed;
      update();
   }
   
   public void setFactor(double tFactor){
      factor = Math.max(0, Math.min(tFactor, 1));
      update();
   }
   
   public double getFactor(){
      return factor;
   }
   
   @Override
   public void update() {
      parent.update();
      listOut.clear();
      final int numOut = (int)(parent.size()*factor);
      Hex tmp;
      Random r = new Random(seed);
      for(int i=0; i<numOut; i++){
         tmp = parent.get(r.nextInt(parent.size()));
         if(listOut.contains(tmp)){
            i--;
         }else{
            listOut.add(tmp);
         }
      }
   }
   
}
