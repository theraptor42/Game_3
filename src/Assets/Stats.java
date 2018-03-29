package Assets;

public class Stats
{
    //All stats are based on 
    //Attack
    //Defense
    //Speed
    
    int baseAttack;
    int baseDefense;
    int baseSpeed;
    int itemAttack;
    int itemDefense;
    int itemSpeed;
    
    public Stats(int baseAttack, int baseDefense, int baseSpeed)
    {
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
        this.itemAttack = 0;
        this.itemDefense = 0;
        this.itemSpeed = 0;
    }

    public int getBaseAttack()
    {
        return this.baseAttack;
    }

    public int getBaseDefense()
    {
        return this.baseDefense;
    }

    public int getBaseSpeed()
    {
        return this.baseSpeed;
    }

    //increment a stat when the user levels up
    public void levelUpBaseAttack()
    {
        this.baseAttack++;
    }

    public void levelUpBaseDefense()
    {
        this.baseDefense++;
    }

    public void levelUpBaseSpeed()
    {
        this.baseSpeed++;
    }

    //get the stats for the equipped items
    public int getItemAttack()
    {
        return this.itemAttack;
    }

    public int getItemDefense()
    {
        return this.itemDefense;
    }

    public int getItemSpeed()
    {
        return this.itemSpeed;
    }

    ////add item based stats when item is equipped
    public void addItemAttack(int itemAttack)
    {
        this.itemAttack = this.itemAttack + itemAttack;
    }

    public void addItemDefense(int itemDefense)
    {
        this.itemDefense = this.itemDefense + itemDefense;
    }

    public void addItemSpeed(int itemSpeed)
    {
        this.itemSpeed = this.itemSpeed + itemSpeed;
    }

    //remove item based stats when item is un-equipped
    public void removeItemAttack(int itemAttack)
    {
        this.itemAttack = this.itemAttack - itemAttack;
    }

    public void removeItemDefense(int itemDefense)
    {
        this.itemDefense = this.itemDefense - itemDefense;
    }

    public void removeItemSpeed(int itemSpeed)
    {
        this.itemSpeed = this.itemSpeed - itemSpeed;
    }

    //Stats actually called in battle
    public int getBattleAttack()
    {
        return (10 * this.getBaseAttack()) + this.getItemAttack();
    }

    public int getBattleDefense()
    {
        return (10 * this.getBaseDefense()) + this.getItemDefense();
    }

    public int getBattleSpeed()
    {
        return (10 * this.getBaseSpeed()) + this.getItemSpeed();
    }

}
