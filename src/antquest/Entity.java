
package antquest;
import java.util.*;


public class Entity 
{
    protected int hp, ap, def, mdef, react, atk, mage, acc;
    protected String character_name;
    protected ArrayList<String> skills;
    
    public int getAtk()
    {
        return atk;
    }
}
