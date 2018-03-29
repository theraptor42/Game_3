package Runtime;

public class TerritoryBlock
{
    World world;
    Square.Team team;
    Square homeSquare;

    TerritoryBlock(Square.Team team, World world, Square startSquare)
    {
        this.world = world;
        this.team = team;
        this.homeSquare = startSquare;
    }


}
