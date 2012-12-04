/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.util.HashMap;

/**
 *
 * @author Kevin
 */
public final class InputHelper {
   public static final int CONFIRM =   0x00000001;
   public static final int CANCEL =    0x00000002;
   public static final int UP =        0x00000004;
   public static final int DOWN =      0x00000008;
   public static final int LEFT =      0x00000010;
   public static final int RIGHT =     0x00000020;
   public static final int MENU =      0x00000040;
   public static final int PAUSE =     0x00000080;
   public static final int RUN =       0x00000100;
   public static final int SELECT =    0x00000200;
   
   private static int confirm = VK_Z,
                       cancel = VK_X,
                           up = VK_UP,
                         down = VK_DOWN,
                         left = VK_LEFT,
                        right = VK_RIGHT,
                         menu = VK_SPACE,
                        pause = VK_ESCAPE,
                          run = VK_C,
                       select = VK_P;
   
   private static final HashMap<Integer, String> textMap;
   
   static{
      textMap = new HashMap<Integer, String>();
      //<editor-fold defaultstate="collapsed" desc="numerals">
      textMap.put(KeyEvent.VK_0, "0");
      textMap.put(KeyEvent.VK_1, "1");
      textMap.put(KeyEvent.VK_2, "2");
      textMap.put(KeyEvent.VK_3, "3");
      textMap.put(KeyEvent.VK_4, "4");
      textMap.put(KeyEvent.VK_5, "5");
      textMap.put(KeyEvent.VK_6, "6");
      textMap.put(KeyEvent.VK_7, "7");
      textMap.put(KeyEvent.VK_8, "8");
      textMap.put(KeyEvent.VK_9, "9");
      textMap.put(KeyEvent.VK_NUMPAD0, "Numpad 0");
      textMap.put(KeyEvent.VK_NUMPAD1, "Numpad 1");
      textMap.put(KeyEvent.VK_NUMPAD2, "Numpad 2");
      textMap.put(KeyEvent.VK_NUMPAD3, "Numpad 3");
      textMap.put(KeyEvent.VK_NUMPAD4, "Numpad 4");
      textMap.put(KeyEvent.VK_NUMPAD5, "Numpad 5");
      textMap.put(KeyEvent.VK_NUMPAD6, "Numpad 6");
      textMap.put(KeyEvent.VK_NUMPAD7, "Numpad 7");
      textMap.put(KeyEvent.VK_NUMPAD8, "Numpad 8");
      textMap.put(KeyEvent.VK_NUMPAD9, "Numpad 9");
      textMap.put(KeyEvent.VK_F1, "F1");
      textMap.put(KeyEvent.VK_F2, "F2");
      textMap.put(KeyEvent.VK_F3, "F3");
      textMap.put(KeyEvent.VK_F4, "F4");
      textMap.put(KeyEvent.VK_F5, "F5");
      textMap.put(KeyEvent.VK_F6, "F6");
      textMap.put(KeyEvent.VK_F7, "F7");
      textMap.put(KeyEvent.VK_F8, "F8");
      textMap.put(KeyEvent.VK_F9, "F9");
      textMap.put(KeyEvent.VK_F10, "F10");
      textMap.put(KeyEvent.VK_F11, "F11");
      textMap.put(KeyEvent.VK_F12, "F12");
      textMap.put(KeyEvent.VK_F13, "F13");
      textMap.put(KeyEvent.VK_F14, "F14");
      textMap.put(KeyEvent.VK_F15, "F15");
      textMap.put(KeyEvent.VK_F16, "F16");
      textMap.put(KeyEvent.VK_F17, "F17");
      textMap.put(KeyEvent.VK_F18, "F18");
      textMap.put(KeyEvent.VK_F19, "F19");
      textMap.put(KeyEvent.VK_F20, "F20");
      textMap.put(KeyEvent.VK_F21, "F21");
      textMap.put(KeyEvent.VK_F22, "F22");
      textMap.put(KeyEvent.VK_F23, "F23");
      textMap.put(KeyEvent.VK_F24, "F24");
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="letters">
      textMap.put(KeyEvent.VK_A, "A");
      textMap.put(KeyEvent.VK_B, "B");
      textMap.put(KeyEvent.VK_C, "C");
      textMap.put(KeyEvent.VK_D, "D");
      textMap.put(KeyEvent.VK_E, "E");
      textMap.put(KeyEvent.VK_F, "F");
      textMap.put(KeyEvent.VK_G, "G");
      textMap.put(KeyEvent.VK_H, "H");
      textMap.put(KeyEvent.VK_I, "I");
      textMap.put(KeyEvent.VK_J, "J");
      textMap.put(KeyEvent.VK_K, "K");
      textMap.put(KeyEvent.VK_L, "L");
      textMap.put(KeyEvent.VK_M, "M");
      textMap.put(KeyEvent.VK_N, "N");
      textMap.put(KeyEvent.VK_O, "O");
      textMap.put(KeyEvent.VK_P, "P");
      textMap.put(KeyEvent.VK_Q, "Q");
      textMap.put(KeyEvent.VK_R, "R");
      textMap.put(KeyEvent.VK_S, "S");
      textMap.put(KeyEvent.VK_T, "T");
      textMap.put(KeyEvent.VK_U, "U");
      textMap.put(KeyEvent.VK_V, "V");
      textMap.put(KeyEvent.VK_W, "W");
      textMap.put(KeyEvent.VK_X, "X");
      textMap.put(KeyEvent.VK_Y, "Y");
      textMap.put(KeyEvent.VK_Z, "Z");
      //</editor-fold>
      //<editor-fold defaultstate="collapsed" desc="misc">
      textMap.put(KeyEvent.VK_ESCAPE, "Esc");
      textMap.put(KeyEvent.VK_SPACE, "Space");
      textMap.put(KeyEvent.VK_ENTER, "Enter");
      textMap.put(KeyEvent.VK_PRINTSCREEN, "Print Screen");
      textMap.put(KeyEvent.VK_TAB, "Tab");
      textMap.put(KeyEvent.VK_CAPS_LOCK, "CAPS LOCK!!!");
      textMap.put(KeyEvent.VK_NUM_LOCK, "Number Lock");
      textMap.put(KeyEvent.VK_SHIFT, "Shift");
      textMap.put(KeyEvent.VK_CONTROL, "Ctrl");
      textMap.put(KeyEvent.VK_ALT, "Alt");
      textMap.put(KeyEvent.VK_MINUS, "-");
      textMap.put(KeyEvent.VK_UNDERSCORE, "_");
      textMap.put(KeyEvent.VK_EQUALS, "=");
      textMap.put(KeyEvent.VK_PLUS, "+");
      textMap.put(KeyEvent.VK_COMMA, ",");
      textMap.put(KeyEvent.VK_PERIOD, ".");
      textMap.put(KeyEvent.VK_SEMICOLON, ";");
      textMap.put(KeyEvent.VK_QUOTE, "\'");
      textMap.put(KeyEvent.VK_ASTERISK, "*");
      textMap.put(KeyEvent.VK_SLASH, "/");
      textMap.put(KeyEvent.VK_BACK_SLASH, "\\");
      textMap.put(KeyEvent.VK_BACK_SPACE, "Backspace");
      textMap.put(KeyEvent.VK_INSERT, "Insert");
      textMap.put(KeyEvent.VK_DELETE, "Delete");
      textMap.put(KeyEvent.VK_HOME, "Home");
      textMap.put(KeyEvent.VK_END, "End");
      textMap.put(KeyEvent.VK_PAGE_UP, "Page Up");
      textMap.put(KeyEvent.VK_PAGE_DOWN, "Page Down");
      textMap.put(KeyEvent.VK_UP, "Up");
      textMap.put(KeyEvent.VK_DOWN, "Down");
      textMap.put(KeyEvent.VK_LEFT, "Left");
      textMap.put(KeyEvent.VK_RIGHT, "Right");
      textMap.put(KeyEvent.VK_OPEN_BRACKET, "[");
      textMap.put(KeyEvent.VK_CLOSE_BRACKET, "]");
      //</editor-fold>
   }
   
   public static int transform(KeyEvent e){
      return transform(e.getKeyCode());
   }
   
   public static int transform(int in){
      int out = 0;
      if(in == confirm) out |= CONFIRM;
      if(in == cancel) out |= CANCEL;
      if(in == up) out |= UP;
      if(in == down) out |= DOWN;
      if(in == left) out |= LEFT;
      if(in == right) out |= RIGHT;
      if(in == menu) out |= MENU;
      if(in == pause) out |= PAUSE;
      if(in == run) out |= RUN;
      if(in == select) out |= SELECT;
      return out;
   }
   
   public static void set(int action, int key){
      if((action & CONFIRM) != 0) confirm = key;
      if((action & CANCEL) != 0) cancel = key;
      if((action & UP) != 0) up = key;
      if((action & DOWN) != 0) down = key;
      if((action & LEFT) != 0) left = key;
      if((action & RIGHT) != 0) right = key;
      if((action & MENU) != 0) menu = key;
      if((action & PAUSE) != 0) pause = key;
      if((action & RUN) != 0) run = key;
      if((action & SELECT) != 0) select = key;
   }
   
   public static int get(int action){
      if((action & CONFIRM) != 0) return confirm;
      if((action & CANCEL) != 0) return cancel;
      if((action & UP) != 0) return up;
      if((action & DOWN) != 0) return down;
      if((action & LEFT) != 0) return left;
      if((action & RIGHT) != 0) return right;
      if((action & MENU) != 0) return menu;
      if((action & PAUSE) != 0) return pause;
      if((action & RUN) != 0) return run;
      if((action & SELECT) != 0) return select;
      return 0;
   }
   
   public static String getText(int key){
      if(textMap.containsKey(key)){
         return textMap.get(key);
      }else{
         return "K" + Integer.toHexString(key);
      }
   }
}
