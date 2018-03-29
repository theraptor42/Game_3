package Runtime;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display
{
    JFrame mainFrame;
    Container contents;
    GridLayout layoutManager;
    ImagePanel[] iPanelArray;
    int iPanelArrSize;
    Dimension frameDimension;

    public Dimension getFrameDimension()
    {
        return frameDimension;
    }

    public void setFrameDimension(Dimension frameDimension)
    {
        this.frameDimension = frameDimension;
    }

    public Display(Dimension frameDimension)
    {
        mainFrame = new JFrame("This is the end");

        this.frameDimension = frameDimension;

        contents = new Container();
        layoutManager = new GridLayout(1,0);
        contents.setLayout(layoutManager);
        iPanelArrSize = 0;
        //iPanelArray = new ImagePanel[1];
        prepIPanelArray(1);

        contents.add(iPanelArray[0]);
        mainFrame.add(contents);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int topBuffer = 45;
        int sidesBuffer = 10;
        mainFrame.setSize(new Dimension(frameDimension.width + sidesBuffer, frameDimension.height + topBuffer));
        mainFrame.setVisible(true);

        mainFrame.repaint();
    }

    public void display(BufferedImage image)
    {
        if (iPanelArrSize != 1)
        {
            contents.removeAll();
            prepIPanelArray(1);

            contents.add(iPanelArray[0]);
            iPanelArray[0].setImage(image);
            mainFrame.setSize(image.getWidth(), image.getHeight());
        }
        iPanelArray[0].setImage(image);
        mainFrame.repaint();
    }

    public void displayTwo(BufferedImage [] imgArr, boolean vertical)
    {
        if (imgArr == null || imgArr.length < 2)
        {
            return;
        }
        if (vertical)
        {
            layoutManager.setRows(1);
            layoutManager.setColumns(0);

        }
        else
        {
            layoutManager.setColumns(1);
            layoutManager.setRows(0);
        }
        if (iPanelArrSize != 2)
        {
            contents.removeAll();
            prepIPanelArray(2);

            contents.add(iPanelArray[0]);
            contents.add(iPanelArray[1]);
            iPanelArray[0].setImage(imgArr[0]);
            iPanelArray[1].setImage(imgArr[1]);
            mainFrame.setSize(imgArr[0].getWidth(), (imgArr[0].getHeight() + imgArr[1].getHeight()));
        }
        iPanelArray[0].setImage(imgArr[0]);
        iPanelArray[1].setImage(imgArr[1]);

        mainFrame.repaint();
    }

    public void displayFour(BufferedImage [] imgArr)
    {
        if (imgArr == null || imgArr.length < 4)
        {
            return;
        }
        if (iPanelArrSize != 4)
        {
            layoutManager.setColumns(2);
            layoutManager.setRows(0);
            contents.removeAll();
            prepIPanelArray(4);

            contents.add(iPanelArray[0]);
            contents.add(iPanelArray[1]);
            contents.add(iPanelArray[2]);
            contents.add(iPanelArray[3]);
            iPanelArray[0].setImage(imgArr[0]);
            iPanelArray[1].setImage(imgArr[1]);
            iPanelArray[2].setImage(imgArr[2]);
            iPanelArray[3].setImage(imgArr[3]);

            mainFrame.setSize(imgArr[0].getWidth() + imgArr[1].getWidth(), (imgArr[0].getHeight() + imgArr[2].getHeight()));
        }
        iPanelArray[0].setImage(imgArr[0]);
        iPanelArray[1].setImage(imgArr[1]);
        iPanelArray[2].setImage(imgArr[2]);
        iPanelArray[3].setImage(imgArr[3]);

        mainFrame.repaint();
    }

    public void displayMany(BufferedImage [] imgArr)
    {
        if (imgArr == null || imgArr.length < 1)
        {
            return;
        }
        switch (imgArr.length)
        {
            case 1:
                display(imgArr[0]);
                return;
            case 2:
                displayTwo(imgArr, false);
                return;
            case 4:
                displayFour(imgArr);
                return;
        }

        if (iPanelArrSize != imgArr.length)
        {
            int columns = (int)Math.sqrt(imgArr.length);
            layoutManager.setColumns(columns);
            layoutManager.setRows(0);
            contents.removeAll();
            prepIPanelArray(imgArr.length);
            int frameHeight = 0;

            for (int i = 0; i < imgArr.length; i++)
            {
                if ((i+1) % columns == 1)
                {
                    frameHeight += imgArr[i].getHeight();
                }
                contents.add(iPanelArray[i]);
                iPanelArray[i].setImage(imgArr[i]);
            }
            mainFrame.setSize(imgArr[0].getWidth() + imgArr[1].getWidth(), frameHeight);
        }

        for (int i = 0; i < imgArr.length; i++)
        {
            iPanelArray[i].setImage(imgArr[i]);
        }

        mainFrame.repaint();
    }

    public void prepIPanelArray(int sizeToAdjustTo)
    {
        //clear iPanelArray, initialize empty IPanels to specified size
        iPanelArrSize = sizeToAdjustTo;

        iPanelArray = new ImagePanel[iPanelArrSize];
        for (int i = 0; i < iPanelArrSize; i++)
        {
            iPanelArray[i] = new ImagePanel();
        }
    }

}
