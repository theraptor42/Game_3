package Runtime;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class World
{
    Square[][] worldArray;
    Head [] heads;

    public World(Dimension worldSize)
    {
        this.worldArray = new Square[worldSize.width][worldSize.height];
        for (int i = 0; i < worldSize.width; i++)
        {
            for (int j = 0; j < worldSize.height; j++)
            {
                worldArray[i][j] = new Square(new Dimension(i, j));
            }
        }
    }

    public void setStartSquare(Dimension location, Square.Team team)
    {
        int startStrength = 255;
        this.worldArray[location.width][location.height] = new Square(team, startStrength, location);
    }
    public BufferedImage getWorldBufferedImage()
    {

        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage returnImage = new BufferedImage(worldArray.length, worldArray[0].length, type);
        for (int i = 0; i < returnImage.getWidth(); i++)
        {
            for (int j = 0; j < returnImage.getHeight(); j++)
            {
                worldArray[i][j].resolveSquare();
                returnImage.setRGB(i, j, worldArray[i][j].getColor().getRGB());
            }
        }
        return returnImage;
    }

    public Square getSquare(Dimension location)
    {
        return this.worldArray[location.width][location.height];
    }

    public void moveStrength(Dimension sourceDim, Dimension destDim, Square.Team team, int strength)
    {
        Square source = this.getSquare(sourceDim);
        Square dest = this.getSquare(destDim);
        if (!isAdjacent(sourceDim, destDim))
        {
            return;
        }
        int sourceStrength = source.getStrength(team);
        int maxStrength = 255 - dest.getStrength(team);
        if (strength > sourceStrength)
        {
            strength = sourceStrength;
        }
        if (strength >= maxStrength)
        {
            strength = maxStrength;
        }
        if (strength < 0)
        {
            strength = 0;
        }
        source.subtractStrength(strength, team);
        dest.addStrength(strength, team);
    }

    public boolean isAdjacent(Dimension source, Dimension dest)
    {
        int sX = source.width;
        int sY = source.height;
        int dX = dest.width;
        int dY = dest.height;

        if (sX == dX)
        {
            return Math.abs(sY - dY) == 1;
        }
        if (sY == dY)
        {
            return Math.abs(sX - dX) == 1;
        }
        return false;
    }


    public enum Direction
    {

        NORTH(0), SOUTH(1), EAST(2), WEST(3);

        private final int mapping;
        private int mapping()
        {
            return mapping;
        }
        Direction(int mapping)
        {
            this.mapping = mapping;
        }
        public static Direction getDirection(int dir)
        {
            return Direction.values()[dir % 4];
        }
        public static Direction randomDirection()
        {
            Random random = new Random();
            return Direction.getDirection(random.nextInt(1000) % 4);
        }
        public static Direction opposite(Direction current)
        {
            switch (current)
            {
                case NORTH:
                    return SOUTH;
                case SOUTH:
                    return NORTH;
                case EAST:
                    return WEST;
                case WEST:
                    return EAST;
            }
            return NORTH;
        }

    }

    public Square getAdjacentSquare(Square currentSquare, Direction direction)
    {
        int x = currentSquare.location.width;
        int y = currentSquare.location.height;
        switch (direction)
        {
            case NORTH:
                if (!currentSquare.isEdge(getWorldDimension(), Direction.NORTH))
                    return worldArray[x][y-1];
                break;
            case SOUTH:
                if (!currentSquare.isEdge(getWorldDimension(), Direction.SOUTH))
                    return worldArray[x][y+1];
                break;
            case EAST:
                if (!currentSquare.isEdge(getWorldDimension(), Direction.EAST))
                    return worldArray[x-1][y];
                break;
            case WEST:
                if (!currentSquare.isEdge(getWorldDimension(), Direction.WEST))
                    return worldArray[x+1][y];
                break;
        }
        return currentSquare;
    }

    public Dimension getWorldDimension()
    {
        return new Dimension(worldArray.length, worldArray[0].length);
    }

    public Square getRandomAdjacentSquare(Square current)
    {
        return getAdjacentSquare(current, Direction.randomDirection());
    }

    public Head[] getHeads()
    {
        return heads;
    }
    public void setHeads(Head[] heads)
    {
        this.heads = heads;
    }
}
