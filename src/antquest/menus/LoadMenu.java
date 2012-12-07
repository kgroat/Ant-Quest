/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package antquest.menus;

import antquest.GameMode;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import antquest.AQEngine;

/**
 *
 * @author clemon.yueh
 */
public class LoadMenu extends LiveMenu {
    
    public LoadMenu(GameMode p, BufferedImage bg)
    {
        super(p, bg);
        final LoadMenu t = this;
        ud=true;
        lr=false;
        System.out.println(parent);
        System.out.println(backdrop);
        System.out.println(p);
        System.out.println(bg);
        System.out.println(this);
        
        if(backdrop == null)
        {
            backdrop = new BufferedImage(AQEngine.getWidth(), AQEngine.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = backdrop.getGraphics();
            g.setColor(new Color(166, 128, 100));
            g.fillRect(0, 0, AQEngine.getWidth(), AQEngine.getHeight());
        }
        
        int cx = 50, cy = 50, cw = AQEngine.getWidth()-100, ch = AQEngine.getHeight()-100;
        ListBlock load_block = new ListBlock(this, cx, cy, cw, ch, TextElement.MENU_FONT);
        blocks.add(load_block);
        int top=1, bottom=10;
        for (int j=top; j<bottom+1; j++)
        {
            SelectableElement element = new SelectableElement(String.valueOf(j), TextElement.MENU_FONT, cx+cw, cy+2*ch/13, TextElement.LEFT_JUSTIFIED)
            {
                @Override
                public void confirm()
                {
                    System.out.println("This function doesn't exist yet, sorry.\n");
                }
            };
            load_block.put(element);
        }
        
    }
    
}
