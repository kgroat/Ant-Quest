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
public abstract class Equippable extends Item{
   
   public abstract void onEquip(PlayableCharacter c);
   
   public abstract void onUnequip(PlayableCharacter c);
}
