
package antquest;
import java.util.*;


public class Entity 
{
    protected int hp, ap, def, mdef, react, atk, mage, acc;
    protected String character_name;
    protected ArrayList<String> skills;
    
    public String getName()
    {
        return character_name;
    }
    
    public int getHP()
    {
        return hp;
    }
    
    public int getAP()
    {
        return ap;
    }
    
    public int getDef()
    {
        return def;
    }
    
    public int getMdef()
    {
        return mdef;
    }
    
    public int getReact()
    {
        return react;
    }
    
    public int getAtk()
    {
        return atk;
    }
    
    public int getMagic()
    {
        return mage;
    }
    
    public int getAcces()
    {
        return acc;
    }
    
    public ArrayList<String> getSkills()
    {
        return skills;
    }
    
    public void Heal(int hpheal)
    {
        hp += hpheal;
    }
}
