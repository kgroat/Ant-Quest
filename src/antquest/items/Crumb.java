/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.items;
import antquest.Entity;

/**
 *
 * @author Clem
 */
public class Crumb extends Consumable {
    
    protected int healme;
    
    public Crumb()
    {
        destroyme=true;
        healme=200;
    }
   public void onUse(Entity user, Entity target)
   {
       target.heal(healme);//upon actual rendering, we're dividing by 100
   }
}
