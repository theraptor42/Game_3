package Runtime;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ColorTest
{
    public static void main(String[] args) throws Exception
    {
        Display dTest = new Display(new Dimension(100,100));


        int type = BufferedImage.TYPE_INT_RGB;
        int width = dTest.frameDimension.width;
        int height = dTest.frameDimension.height;
        int frames = 16;
        Random random = new Random();
        BufferedImage[] imgArr = new BufferedImage[frames];
        Color[] colorArr = new Color[frames];
        for (int i = 0; i < frames; i++)
        {
            float colorSeparation = (float)1.0 / frames;
            imgArr[i] = new BufferedImage(width, height, type);
            colorArr[i] = Color.getHSBColor((float)(i*colorSeparation), (float)1, (float)1);
            //colorArr[i] = Color.getHSBColor(random.nextFloat(), (float)1, (float)1);

        }

        //TimeUnit.SECONDS.sleep(5);
        int count = 0;
        while (true)
        {

            for (int i = 0; i < frames; i++)
            {
                colorArr[i] = nextHsbColor(colorArr[i]);
            }

            for (int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {

                    for (int frame = 0; frame < frames; frame++)
                    {
                        imgArr[frame].setRGB(i, j, colorArr[frame].getRGB());
                        //TimeUnit.MICROSECONDS.sleep(10);
                    }
                    //TimeUnit.MICROSECONDS.sleep(10);
                }


            }
            dTest.displayMany(imgArr);
            //TimeUnit.MICROSECONDS.sleep(10);
        }
    }

    public static Color nextColor(Color color)
    {
        /*if (color == Color.RED)
        {
            System.out.println("Red: " + String.format("0x%08x", Color.red.getRGB()));
            return color.BLUE;
        }
        if (color == Color.BLUE)
        {
            System.out.println("Blue: " + String.format("0x%08x", Color.blue.getRGB()));
            return Color.GREEN;
        }
        if (color == Color.GREEN)
        {
            System.out.println("Green: " + String.format("0x%08x", Color.green.getRGB()));
            return Color.RED;
        }

        return Color.RED;*/
        System.out.println();
        return new Color(color.getRGB() + 16843009);
    }
    public static Color nextColorTransition(Color color)
    {
        float[] arr = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        Color ret = Color.getHSBColor(arr[0] + (float).1, arr[1], arr[2]);
        System.out.println(String.format("0x%08x", ret.getRGB()) + " : " +String.format("0x%08x", ret.getRGB()));
        return ret;
    }

    public static Color nextHsbColor(Color color)
    {
        float[] arr = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float hue = arr[0];
        if (hue == 1)
        {
            hue = 0;
        }
        hue += (float).0005;
        Color ret = Color.getHSBColor(hue, arr[1], arr[2]);

        return ret;
    }
}
