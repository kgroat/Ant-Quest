/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package antquest;

/**
 *
 * @author Clem
 */
public class Hex {
    
    protected Entity occupant;
    protected int xpos, ypos;
    protected String terrain; //might be a separate class later?
    
    public Hex(int x, int y, String earth)
    {
        terrain = earth;
        xpos=x;
        ypos=y;
        occupant=null;
    }
    
    public void setOccupant(Entity e)
    {
        occupant = e;
    }
    
    public Entity getOccupant()
    {
        return occupant;
    }
    
    public int getX()
    {
        return xpos;
    }
    
    public int getY()
    {
        return ypos;
    }
    
    public void setTerrain(String g)
    {
        terrain = g;
    }
    
    public String getTerrain()
    {
        return terrain;
    }

}
