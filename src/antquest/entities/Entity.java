package antquest.entities;

import java.util.*;

public class Entity {

   protected int hp, mhp, ap, def, mdef, react, atk, mage, acc, xp, level;
   protected int[] xpCurve;
   protected boolean disposable;
   protected String name;
   protected ArrayList<String> skills;

   /**
    * Gets the name of this Entity.
    * @return This Entity's name.
    */
   public String getName() {
      return name;
   }

   /**
    * Gets the current health (hp) of this Entity.
    * @return This Entity's hp value.
    */
   public int getHP() {
      return hp;
   }

   /**
    * Sets this Entity's health to a specific value.  This does not check to
    * see whether the health exceeds this Entity's maximum, and thus can be
    * used to overheal the Entity.  On the same note, it does not check to see
    * if the health is less than zero so this can force a negative value.  If
    * the value provided is less than or equal to zero, however, it mark this
    * Entity as disposable.
    * @param newHp The new value to be given to this Entity's hp.
    */
   public void setHP(int newHp){
      hp = newHp;
      disposable = hp <= 0;
   }
   
   /**
    * Gets the maximum hp of this Entity.
    * @return This Entity's max hp value.
    */
   public int getMaxHP() {
      return mhp;
   }

   /**
    * Gets the number of action points given to this Entity.
    * @return This Entity's action points.
    */
   public int getAP() {
      return ap;
   }

   /**
    * Gets the physical defense stat of this Entity.
    * @return This Entity's physical defene value.
    */
   public int getDef() {
      return def;
   }

   /**
    * Gets the magic defense stat of this Entity.
    * @return This Entity's magic defense value.
    */
   public int getMdef() {
      return mdef;
   }

   /**
    * Gets the reaction stat of this Entity.
    * @return This Entity's reaction value.
    */
   public int getReact() {
      return react;
   }

   /**
    * Gets the attack stat of this Entity.
    * @return This Entity's attack value.
    */
   public int getAtk() {
      return atk;
   }

   /**
    * Gets the magic stat of this Entity.
    * @return This Entity's magic value.
    */
   public int getMagic() {
      return mage;
   }

   /**
    * ??? What exactly DOES acc do?
    * @return This Entity's acc value.
    */
   public int getAcces() {
      return acc;
   }

   /**
    * Gets the amount of XP this Entity has.
    * @return This Entity's XP value.
    */
   public int getXP(){
      return xp;
   }
   
   /**
    * Gets this Entity's current level.
    * @return This Entity's current level.
    */
   public int getLevel(){
      return level;
   }
   
   /**
    * Gets the list of public skill keys associated with this Entity.  The list
    * is a shallow copy of the ArrayList of skill keys.  This means that any
    * changes in the list will be reflected by this Entity.
    * @return The list of all public keys to the Skills this Entity has
    * available to it.
    */
   public ArrayList<String> getSkills() {
      return skills;
   }

   /**
    * Heals a set amount of damage from this Entity.  If the Entity's health
    * would exceed its maximum, it is instead set to that value.  Does nothing
    * to an entity designated as disposable.
    * @param hpheal The amount of damage to be healed.
    */
   public void heal(int hpheal) {
      if (!disposable) {
         hp = Math.min(hp+hpheal, mhp);
      }
   }

   /**
    * Deals a set amount of damage to this Entity.  If the Entity's health
    * falls below zero, it is set to zero.  Checks for death conditions (if
    * health reaches zero) and sets as disposable if so.
    * @param amount The amount of damage to be dealt to this Entity.
    */
   public void damage(int amount) {
      hp = Math.max(0, hp - amount);
      if (hp == 0) {
         disposable = true;
      }
   }
   
   /**
    * Brings this Entity back to life.  Returns whether or not the Entity was
    * actually resurrected (for now, this just means whether the Entity was
    * previously dead or not).
    * @return Whether the Entity was resurrected from death.
    */
   public boolean resurrect(){
      boolean tmp = disposable;
      if(tmp){
         disposable = true;
         hp = 1;
      }
      return tmp;
   }
   
   /**
    * Gives this Entity a certain amount of XP.  If the new value equals or
    * exceeds any new level markers in the xpCurve, the Entity levels up.
    * @param amount The amount of XP to grant to this Entity.
    */
   public void gainXP(int amount){
      xp += amount;
      while(level < xpCurve.length && xpCurve[level]<=xp){
         levelUp();
      }
   }
   
   /**
    * Sets the level of this Entity.  Currently, there are no side-effects to
    * this method (i.e. no stats are affected by levels granted or removed)
    * except that the xp of this Entity is set to the base XP for the new level.
    * This will be changed later to have such side-effects.
    * @param value The new level to be given to this Entity.
    */
   public void setLevel(int value){
      level = value;
      xp = xpCurve[(level<xpCurve.length)?level:(xpCurve.length-1)];
   }
   
   /**
    * Gets the amount of XP this Entity has past the base amount for its
    * current level.
    * @return The XP remainder of this Entity.
    */
   public int xpRemainder(){
      return xp - xpCurve[(level<xpCurve.length)?level:(xpCurve.length-1)];
   }
   
   /**
    * Levels this creature up by one level.  Some Entities may override this to
    * allow for stat gains or bonuses, but currently only effects the level and
    * XP of the Entity, if necessary.
    */
   public void levelUp(){
      level ++;
      if(xpCurve[(level<xpCurve.length)?level:(xpCurve.length-1)] > xp)
         xp = xpCurve[(level<xpCurve.length)?level:(xpCurve.length-1)]+xpRemainder();
   }
}
