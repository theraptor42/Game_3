package Runtime;

import java.awt.*;
import java.util.Random;

public class Head
{
    Square current;
    Square.Team team;
    World world;
    World.Direction trappedDirection;

    public Head(Square current, Square.Team team, World world)
    {
        this.current = current;
        this.team = team;
        this.world = world;
        this.trappedDirection = World.Direction.randomDirection();
        world.setStartSquare(current.location, team);
    }

    public void takeTurn()
    {
        Random random = new Random();
        Square newLocation = world.getRandomAdjacentSquare(this.getSquare());


        if (current.getStrength(team) < 150 || random.nextInt(100) < 40)
        {
            current.reinforceSquare(team);
        }

        else if (newLocation.getOwner() == team)
        {
            if (current.isEdge(world.getWorldDimension(), this.trappedDirection))
            {
                this.trappedDirection = World.Direction.opposite(this.trappedDirection);
                newLocation = world.getAdjacentSquare(current, this.trappedDirection);
            }
            if (isLocked())
            {
                newLocation = world.getAdjacentSquare(current, this.trappedDirection);
            }
        }
        world.moveStrength(this.getLocation(), newLocation.location, this.getTeam(),
                (int)this.getSquare().getStrength(this.getTeam()) / 2);
        this.setSquare(newLocation);
    }

    public Dimension getLocation()
    {
        return current.location;
    }

    public Square.Team getTeam()
    {
        return team;
    }

    public Square getSquare()
    {
        return current;
    }

    public void setSquare(Square newLocation)
    {
        this.current = newLocation;
    }

    public boolean isLocked()
    {
        if (world.getAdjacentSquare(current, World.Direction.NORTH).getOwner() == team
                && world.getAdjacentSquare(current, World.Direction.SOUTH).getOwner() == team
                && world.getAdjacentSquare(current, World.Direction.EAST).getOwner() == team
                && world.getAdjacentSquare(current, World.Direction.WEST).getOwner() == team)
        {
            return true;
        }
        return false;
    }
}
