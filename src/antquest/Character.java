
package antquest;
import java.util.*;
import antquest.items.*;


abstract public class Character extends Entity
{
    protected Weapon weapon, offhand;
    protected UpperArmor uarm;
    protected LowerArmor larm;
    protected Accessory acc1, acc2, acc3;
    
    public Weapon getMainWep()
    {
        return weapon;
    }
    
    public Weapon getSecondaryWep()
    {
        return offhand;
    }
    
    public UpperArmor getUarmor()
    {
        return uarm;
    }
    
    public LowerArmor getLamror()
    {
        return larm;
    }
    
    public Accessory getAcc1()
    {
        return acc1;
    }
    
    public Accessory getAcc2()
    {
        return acc2;
    }
    
    public Accessory getAcc3()
    {
        return acc3;
    }
}
