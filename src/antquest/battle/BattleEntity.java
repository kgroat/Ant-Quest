/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public interface BattleEntity {
   
   public int getHP();
   public int getMaxHP();
   public void setHP(int hp);
   public int getAP();
   public String getName();
   public Hex getLoc();
   public void setLoc(Hex h);
   public void render(Graphics2D g, int px, int py);
   public ArrayList<BattleSkill> getSkills();
   
}
