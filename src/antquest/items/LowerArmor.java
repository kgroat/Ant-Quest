/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.items;

import antquest.entities.characters.PlayableCharacter;
/**
 *
 * @author Kevin
 */
public abstract class LowerArmor extends Armor{
   public static final LowerArmor CHAINMAIL = new LowerArmor(){

        public void onEquip(PlayableCharacter c) {
        }

        public void onUnequip(PlayableCharacter c) {
        }
       
   };
}
