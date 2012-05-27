package antquest.items;
import antquest.entities.characters.PlayableCharacter;

public class Scythe extends Weapon
{
    public Scythe(int atkm, int atkmin)
    {
        maxAttack = atkm;
        minAttack = atkmin;
    }
 
    @Override
    public int getAttackValue()
    {
        return 0;
    }
    
    @Override
    public void onEquip(PlayableCharacter c)
    {
        
    }
   
    @Override
    public void onUnequip(PlayableCharacter c)
    {
        
    }
}
