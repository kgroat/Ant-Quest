/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle;

import java.awt.Graphics2D;

/**
 *
 * @author Kevin
 */
public interface BattleAnimEffect {
   public boolean preApply(Graphics2D g);
   public boolean apply(Graphics2D g, int x, int y, Hex h);
   public boolean postApply(Graphics2D g);
   public boolean isDone();
}
