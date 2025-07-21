import java.awt.*;
import javax.swing.*;

public class Pipe {
    public int x, width, gap, topHeight, bottomHeight;
    public int speed = 4;
    public int panelHeight;

    private Image pipeImg;

    public Pipe(int startX, int panelHeight) {
        this.x = startX;
        this.width = 60;
        this.gap = 150;
        this.panelHeight = panelHeight;

        String basePath = System.getProperty("user.dir") + "/sprites/";
        System.out.println("Pipe images from: " + basePath);
        pipeImg = new ImageIcon(basePath + "pipe-green.png").getImage();

        setRandomHeights();
    }

    public void setRandomHeights() {
        topHeight = 50 + (int)(Math.random() * 200);
        bottomHeight = panelHeight - topHeight - gap;
    }

    public void update() {
        x -= speed;
        if (x + width < 0) {
            x = 400;
            setRandomHeights();
        }
    }

    public void draw(Graphics g) {
        // Top pipe (flipped vertically)
        g.drawImage(pipeImg, x, topHeight - pipeImg.getHeight(null), width, pipeImg.getHeight(null), null);
        // Bottom pipe
        g.drawImage(pipeImg, x, topHeight + gap, width, pipeImg.getHeight(null), null);
    }
}
