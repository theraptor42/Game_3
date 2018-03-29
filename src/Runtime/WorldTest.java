package Runtime;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WorldTest
{
    public static void main(String[] args) throws InterruptedException
    {
        Dimension worldSize = new Dimension(250, 250);
        Display display = new Display(worldSize);
        int worldCount = 4;

        World[] worldArray = new World[worldCount];
        for (int i = 0; i < worldCount; i++)
        {
            worldArray[i] = getTheWorldThings(worldSize);
        }



        int count = 0;
        int resolveCount = 0;
        while (true)
        {

            for (int world = 0; world < worldCount; world++)
            {
                Head[] heads = worldArray[world].getHeads();
                for (int i = 0; i < heads.length; i++)
                {
                    heads[i].takeTurn();
                }
                for (int i = 0; i < heads.length; i++)
                {
                    //heads[i].getSquare().resolveSquare();
                }
            }
            if (count > 300)
            {
                BufferedImage[] imgArr = new BufferedImage[worldCount];
                for (int i =0; i < worldCount; i++)
                {
                    imgArr[i] = worldArray[i].getWorldBufferedImage();
                }
                display.displayMany(imgArr);
                count = 0;
            }
            else
            {
                count++;
            }
        }
    }

    public static World getTheWorldThings(Dimension worldSize)
    {

        //World[] worldArray = new World[]
        World world = new World(new Dimension(worldSize));
        Head[] heads = new Head[9];
        int x = worldSize.width;
        int y = worldSize.height;

        heads[0] = new Head(world.getSquare(new Dimension(x-x,y-y)), Square.Team.GREEN, world);
        heads[1] = new Head(world.getSquare(new Dimension(x/2,y-y)), Square.Team.GREEN, world);
        heads[2] = new Head(world.getSquare(new Dimension(x-1,y-y)), Square.Team.GREEN, world);
        heads[3] = new Head(world.getSquare(new Dimension(x-x,y-1)), Square.Team.BLUE, world);
        heads[4] = new Head(world.getSquare(new Dimension(x/2,y-1)), Square.Team.BLUE, world);
        heads[5] = new Head(world.getSquare(new Dimension(x-1,y-1)), Square.Team.BLUE, world);
        heads[6] = new Head(world.getSquare(new Dimension(x-x,y/2)), Square.Team.RED, world);
        heads[7] = new Head(world.getSquare(new Dimension(x/2,y/2)), Square.Team.RED, world);
        heads[8] = new Head(world.getSquare(new Dimension(x-1,y/2)), Square.Team.RED, world);

        world.setHeads(heads);
        return world;
    }
}
