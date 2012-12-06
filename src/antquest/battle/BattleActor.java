/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.battle;

import antquest.AQEngine;
import antquest.entities.Entity;
import antquest.battle.Hex;
import antquest.battle.BattleMode;
import antquest.battle.areas.*;
import antquest.numerical.Gradient;
import antquest.numerical.NumberRange;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class BattleActor implements Comparable, BattleEntity {

   private static int total = 0;
   private int count;
   
   protected BattleMode battle;
   protected Entity entity;
   protected Hex location;
   protected int speed;
   protected int team;
   protected boolean playable;
   private Color one, two;
   
   private static final ArrayList<BattleSkill> skills;
   
   static{
      skills = new ArrayList<BattleSkill>();
      //<editor-fold defaultstate="collapsed" desc="Laser">
      skills.add(new BattleSkillImpl("LAZORZ!!!1!11!1", "Pew! Pew!", new Gradient(255, 255, 0, 255, 0, 0), false) {
         
         public void apply(BattleEntity user, Hex[] list) {
            for(Hex each: list){
               BattleEntity be;
               if((be = each.getOccupant()) != null && !be.equals(user)){
                  System.out.println(user.getName() + " says: I'MMA FIRIN MAH LAZOR AT " + be.getName());
               }
            }
         }
         
         public AreaTemplate getAreaTemplate(BattleMode battle, int x, int y, double... vals){
            return new LineAreaBuilder(1, 1).build(battle, x, y, vals[0], vals[1]);
         }
         
         public NumberRange[] getAreaTemplateRanges() {
            return new NumberRange[]{
               new NumberRange(-20, 20, 0, 1, false),
               new NumberRange(-20, 20, 0, -1, false)
            };
         }
         
         public String[] getRangeNames(){
            return new String[]{
               "X location of other point",
               "Y location of other point"
            };
         }

         public BattleAnimEffect getAnimation(int x, int y, double... vals) {
            return null;
         }
      });
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="Explosion">
      skills.add(new BattleSkillImpl("Explosion", "Kaboom", new Gradient(192, 0, 0, 64, 0, 0), true) {
         
         public void apply(BattleEntity user, Hex[] list) {
            for(Hex each: list){
               BattleEntity be;
               if((be = each.getOccupant()) != null && !be.equals(user)){
                  System.out.println(user.getName() + " blows " + be.getName() + " the F**K up!");
               }
            }
         }
         
         public AreaTemplate getAreaTemplate(BattleMode battle, int x, int y, double... vals){
            return new CircleAreaBuilder(3.5).build(battle, x, y, 0);
         }
         
         public NumberRange[] getAreaTemplateRanges() {
            return new NumberRange[0];
         }
         
         public String[] getRangeNames(){
            return new String[0];
         }

         public BattleAnimEffect getAnimation(int x, int y, double... vals) {
            return null;
         }
      });
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="Ice Breath">
      skills.add(new BattleSkillImpl("Ice Breath", "BAAAAAAWWWW!", new Gradient(150, 200, 255, 0, 50, 255), false) {
         
         public void apply(BattleEntity user, Hex[] list) {
            boolean hits = false;
            for(Hex each: list){
               BattleEntity be;
               if((be = each.getOccupant()) != null && !be.equals(user)){
                  System.out.println(user.getName() + " blows " + be.getName());
                  hits = true;
               }
            }
            if(hits){
               System.out.println("OH! I mean... well... he freezes them.\n*blush*");
            }
         }
         
         public AreaTemplate getAreaTemplate(BattleMode battle, int x, int y, double... vals){
            return new CircleArcAreaBuilder(7, Math.PI / 3).build(battle, x, y, vals[0], 1);
         }
         
         public NumberRange[] getAreaTemplateRanges() {
            return new NumberRange[]{
               new NumberRange(0, Math.PI * (2 - 1.0/24), 0, Math.PI / 12, true)
            };
         }
         
         public String[] getRangeNames(){
            return new String[]{
               "Angle"
            };
         }

         public BattleAnimEffect getAnimation(int x, int y, double... vals) {
            return null;
         }
      });
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="OMFG">
      skills.add(new BattleSkillImpl("OMFG!", "OMFG!", new Gradient(255, 255, 255, 255, 0, 0, 0, 0), true) {
         
         public void apply(BattleEntity user, Hex[] list) {
            boolean hits = false;
            for(Hex each: list){
               BattleEntity be;
               if((be = each.getOccupant()) != null && !be.equals(user)){
                  System.out.println(user.getName() + " FUCKS THE HELL OUT OF " + be.getName());
                  hits = true;
               }
            }
            if(hits){
               System.out.println("OH! I mean... well... HE FUCKS THE HELL OUT OF THEM!\n*blush*");
            }
         }
         
         public AreaTemplate getAreaTemplate(BattleMode battle, int x, int y, double... vals){
            return new CircleArcAreaBuilder(vals[1], vals[0]).build(battle, x, y, vals[2], 1);
         }
         
         public NumberRange[] getAreaTemplateRanges() {
            return new NumberRange[]{
               new NumberRange(0, Math.PI, 0, Math.PI / 12, false),
               new NumberRange(0, 8, 4, .5, false),
               new NumberRange(0, Math.PI * (2 - 1.0/24), 0, Math.PI / 12, true)
            };
         }
         
         public String[] getRangeNames(){
            return new String[]{
               "Width",
               "Radius",
               "Angle"
            };
         }

         public BattleAnimEffect getAnimation(int x, int y, double... vals) {
            return null;
         }
      });
      //</editor-fold>
   }

   public BattleActor(Entity e, int tteam, boolean play) {
      count = total;
      total++;
      entity = e;
      location = null;
      team = tteam;
      playable = play;
      int hi = AQEngine.randInt(3);
      int mid = AQEngine.randInt(3);
      while(mid == hi){
         mid = AQEngine.randInt(3);
      }
      int[] rgb = new int[3];
      rgb[hi] = 255;
      rgb[mid] = AQEngine.randInt(255);
      one = new Color(rgb[0], rgb[1], rgb[2]);
      for(int i=0; i<3; i++){
         rgb[i] = 255 - rgb[i];
      }
      two = new Color(rgb[0], rgb[1], rgb[2]);
   }

   @Override
   public int compareTo(Object t) {
      return speed - ((BattleActor) t).speed;
   }

   public void setLoc(Hex h) {
      location = h;
   }

   public boolean isAt(int tx, int ty) {
      return location.xpos == tx && location.ypos == ty;
   }

   public Point getPos() {
      return new Point(location.xpos, location.ypos);
   }

   public Hex getLoc() {
      return location;
   }

   public int getX() {
      return location.xpos;
   }

   public int getY() {
      return location.ypos;
   }

   public void render(Graphics2D g, int px, int py) {
      g.setPaint(new RadialGradientPaint(px + BattleMode.HEX_SIZE/2f + .5f, py + BattleMode.HEX_SIZE/2f + .5f, BattleMode.HEX_SIZE/2f - 1, new float[]{0, 1}, new Color[]{one, two}));
      g.fillRect(px + 3, py + 3, BattleMode.HEX_SIZE - 5, BattleMode.HEX_SIZE - 5);
   }

   public int getHP() {
      return 0;
   }

   public int getMaxHP() {
      return 0;
   }

   public void setHP(int hp) {
      //do nothing
   }

   public int getAP() {
      return 0;
   }
   
   @Override
   public boolean equals(Object o){
      if(o instanceof BattleActor){
         return count == ((BattleActor) o).count;
      }else{
         return false;
      }
   }

   public ArrayList<BattleSkill> getSkills() {
      return skills;
   }

   public String getName() {
      return "Battle Actor " + count;
   }
}
