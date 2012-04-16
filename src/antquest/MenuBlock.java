/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kevin
 */
public class MenuBlock extends MenuElement{
   public static final Color DEFAULT_BACKDROP = new Color(200, 160, 80);
   protected LiveMenu parent;
   protected ArrayList<MenuElement> elements;
   protected Color backdropColor; //Temporary?
   protected int width, height;
   
   public MenuBlock(LiveMenu p, int tx, int ty, int tw, int th){
      parent = p;
      x = tx;
      y = ty;
      width = tw;
      height = th;
      backdropColor = DEFAULT_BACKDROP;
      elements = new ArrayList<MenuElement>();
   }
   
   public void parse(Scanner in){ //Maybe should be a URL or String?
      //TODO: parse document for menu specification
   }
   
   @Override
   public void render(Graphics2D g) {
      final boolean RAISED = true;
      if(parent.frame < LiveMenu.FRAME_LENGTH){
         //When menu first opens, blocks grow to final size
         //TODO: Draw scaled backdrop
         //Temporary scaled backdrop
         g.setColor(backdropColor);
         int var = Math.min(width, height);
         int tx = x+var-(var*parent.frame/LiveMenu.FRAME_LENGTH);
         int ty = y+var-(var*parent.frame/LiveMenu.FRAME_LENGTH);
         int tw = width-(var*parent.frame/LiveMenu.FRAME_LENGTH);
         int th = height-(var*parent.frame/LiveMenu.FRAME_LENGTH);
         g.fill3DRect(tx, ty, tw, th, RAISED);
      }else{
         //When blocks finish growing, they show their content
         //TODO: Draw backdrop
         //Temporary backdrop
         g.setColor(backdropColor);
         g.fill3DRect(x, y, width, height, RAISED);
         for(int i=0; i<elements.size(); i++){
            elements.get(i).render(g);
         }
      }
   }
   
   public MenuElement get(int i){
      return elements.get(i);
   }
   
   public ArrayList<SelectableElement> getSelectable(){
      ArrayList<SelectableElement> out = new ArrayList<SelectableElement>();
      MenuElement tmp;
      for(int i=0; i<elements.size(); i++){
         if((tmp = elements.get(i)) instanceof SelectableElement){
            out.add((SelectableElement)tmp);
         }
      }
      return out;
   }
}
