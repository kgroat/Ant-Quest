/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class BattleMode extends GameMode{
   
   protected int cx, cy;
   protected ArrayList<BattleActor> actors;
   protected BattleActor current;
   protected ArrayList<Hex> battlemap;

   @Override
   public void press(KeyEvent e) {
      int trans = InputHelper.transform(e);
      if((trans & InputHelper.CONFIRM) != 0){
         BattleActor selected = actorAtCursor();
         if(selected != null){
            //Confirm actor
            CONFIRM.forcePlay(true, false);
         }else{
            ERROR.tryPlay(true, false);
         }
      }
      throw new UnsupportedOperationException("Not supported yet.");
   }
   
   public BattleActor actorAtCursor(){
      BattleActor out = null;
      for(int i=0; i<actors.size() && out == null; i++){
         if(actors.get(i).isAt(cx, cy)){
            out = actors.get(i);
         }
      }
      return out;
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
   public GameMode escape() {
      throw new UnsupportedOperationException("Not supported yet.");
   }
   
}
