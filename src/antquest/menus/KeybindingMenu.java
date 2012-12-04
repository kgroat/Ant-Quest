/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import antquest.AQEngine;
import antquest.GameMode;
import antquest.InputHelper;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class KeybindingMenu extends LiveMenu {
   
   public static final Color ALT_SELECT_COLOR = new Color(200, 72, 36, 100);
   private static final String CONFIRM_2 = "Confirm";
   private static final String CANCEL = "Cancel";
   private static final String UP = "Up";
   private static final String DOWN = "Down";
   private static final String LEFT = "Left";
   private static final String RIGHT = "Right";
   private static final String MENU = "Menu";
   private static final String PAUSE = "Pause";
   private static final String RUN = "Run";
   private static final String SELECT = "Select";
   
   private int bind;
   private SelectableElement toChange;
   private Color returnSelectTo;
   
   public KeybindingMenu(GameMode p, BufferedImage bd) {
      super(p, bd);
      lr = false;
      bind = 0;
      if(backdrop == null){
         backdrop = new BufferedImage(AQEngine.getWidth(), AQEngine.getHeight(), BufferedImage.TYPE_INT_RGB);
         Graphics g = backdrop.getGraphics();
         g.setColor(Color.MAGENTA);
         g.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
      }
      int cx = (AQEngine.getWidth() - 200)/2, cy = (AQEngine.getHeight() - 255)/2, cw = 200, ch = 255;
      MenuBlock block = new MenuBlock(this, cx, cy, cw, ch);
      blocks.add(block);
      MenuElement element = new TextElement(CONFIRM_2 + ": ", TextElement.MENU_FONT, cx+5, cy, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.CONFIRM)), TextElement.MENU_FONT, cx+cw-5, cy, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.CONFIRM;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(CANCEL + ": ", TextElement.MENU_FONT, cx+5, cy+ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.CANCEL)), TextElement.MENU_FONT, cx+cw-5, cy+ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.CANCEL;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(UP + ": ", TextElement.MENU_FONT, cx+5, cy+2*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.UP)), TextElement.MENU_FONT, cx+cw-5, cy+2*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.UP;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(DOWN + ": ", TextElement.MENU_FONT, cx+5, cy+3*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.DOWN)), TextElement.MENU_FONT, cx+cw-5, cy+3*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.DOWN;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(LEFT + ": ", TextElement.MENU_FONT, cx+5, cy+4*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.LEFT)), TextElement.MENU_FONT, cx+cw-5, cy+4*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.LEFT;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(RIGHT + ": ", TextElement.MENU_FONT, cx+5, cy+5*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.RIGHT)), TextElement.MENU_FONT, cx+cw-5, cy+5*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.RIGHT;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(MENU + ": ", TextElement.MENU_FONT, cx+5, cy+6*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.MENU)), TextElement.MENU_FONT, cx+cw-5, cy+6*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.MENU;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(PAUSE + ": ", TextElement.MENU_FONT, cx+5, cy+7*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.PAUSE)), TextElement.MENU_FONT, cx+cw-5, cy+7*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.PAUSE;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(RUN + ": ", TextElement.MENU_FONT, cx+5, cy+8*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.RUN)), TextElement.MENU_FONT, cx+cw-5, cy+8*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.RUN;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      element = new TextElement(SELECT + ": ", TextElement.MENU_FONT, cx+5, cy+9*ch/10, TextElement.LEFT_JUSTIFIED);
      block.add(element);
      element = new SelectableElement(InputHelper.getText(InputHelper.get(InputHelper.SELECT)), TextElement.MENU_FONT, cx+cw-5, cy+9*ch/10, TextElement.RIGHT_JUSTIFIED) {

         @Override
         public void confirm() {
            System.out.println(text);
            bind = InputHelper.SELECT;
            toChange = (SelectableElement)self;
            returnSelectTo = getSelectColor();
            setSelectColor(ALT_SELECT_COLOR);
         }
      };
      block.add(element);
      selectDefault();
   }
   
   @Override
   public void press(KeyEvent e){
      if(bind <= 0){
         super.press(e);
      }else{
         InputHelper.set(bind, e.getKeyCode());
         toChange.setText(InputHelper.getText(e.getKeyCode()));
         bind = 0;
         toChange = null;
         setSelectColor(returnSelectTo);
         CONFIRM.forcePlay(true, true);
      }
   }

}
