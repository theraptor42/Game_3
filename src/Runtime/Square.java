package Runtime;

import java.awt.*;
import java.util.HashMap;

public class Square
{
    public enum Team
    {
        RED, GREEN, BLUE, NEUTRAL
    }
    Color color;
    Dimension location;
    public Square(Dimension location)
    {
        this.color = Color.BLACK;
        this.location = location;
    }
    public Square(Color color, Dimension location)
    {
        this.color = color;
        this.location = location;
    }
    public Square(Team team, int startStrength, Dimension location)
    {
        Square temp = new Square(location);
        temp.setStrength(startStrength, team);
        this.color = temp.getColor();
        this.location = location;
    }

    public Color getColor()
    {
        return this.color;
    }

    public void resolveSquare()
    {
        int red = getStrength(Team.RED);
        int green = getStrength(Team.GREEN);
        int blue = getStrength(Team.BLUE);

        //here is the battle, simple
        int median = Math.max(Math.min(red,green), Math.min(Math.max(red,green),blue));
        setStrength(red - median, Team.RED);
        setStrength(green - median, Team.GREEN);
        setStrength(blue - median, Team.BLUE);
    }

    public Team getOwner()
    {
        int red = getStrength(Team.RED);
        int green = getStrength(Team.GREEN);
        int blue = getStrength(Team.BLUE);
        int max = Math.max(red, Math.max(green, blue));

        if (max == 0)
            return Team.NEUTRAL;
        if (max == red)
            return Team.RED;
        if (max == green)
            return  Team.GREEN;
        if (max == blue)
            return Team.BLUE;
        return Team.NEUTRAL;
    }

    public int getStrength(Team team)
    {
        switch (team)
        {
            case RED:
                return this.color.getRed();
            case GREEN:
                return this.color.getGreen();
            case BLUE:
                return this.color.getBlue();
            default:
                return 0;
        }
    }
    public void reinforceSquare(Team team)
    {
        int newStrength = (int)(getStrength(team) * .5);
        addStrength(newStrength, team);
    }


    public void setStrength(int strength, Team team)
    {
        if (strength < 0)
            strength = 0;
        if (strength > 255)
            strength = 255;
        switch (team)
        {
            case RED:
                this.color = new Color(strength, getStrength(Team.GREEN), getStrength(Team.BLUE));
                break;
            case GREEN:
                this.color = new Color(getStrength(Team.RED), strength, getStrength(Team.BLUE));
                break;
            case BLUE:
                this.color = new Color(getStrength(Team.RED), getStrength(Team.GREEN), strength);
                break;
        }
    }

    public void subtractStrength(int strength, Team team)
    {
        if (this.getStrength(team) - strength <= 0)
        {
            setStrength(0, team);
        }
        else
        {
            setStrength(this.getStrength(team) - strength, team);
        }

    }

    public void addStrength(int strength, Team team)
    {
        if (this.getStrength(team) + strength > 255)
        {
            setStrength(255, team);
        }
        else
        {
            setStrength(this.getStrength(team) + strength, team);
        }

    }

    public boolean isEdge(Dimension worldSize, World.Direction direction)
    {
        int x = this.location.width;
        int y = this.location.height;
        switch (direction)
        {
            case NORTH:
                if (y == 0)
                    return true;
                break;
            case SOUTH:
                if (y == worldSize.height - 1)
                    return true;
                break;
            case EAST:
                if (x == 0)
                    return true;
                break;
            case WEST:
                if (x == worldSize.width - 1)
                    return true;
                break;
        }

        return false;
    }

}
