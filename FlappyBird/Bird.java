import java.awt.*;
import javax.swing.*;

public class Bird {
    public int x, y, width, height;
    public int velocity;
    public int gravity = 1;
    public int jumpPower = -8;

    private Image[] birdFrames;
    private int frameIndex = 0;
    private int frameDelay = 0;

    public Bird(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = 0;

        String basePath = System.getProperty("user.dir") + "/sprites/";
        System.out.println("Bird images from: " + basePath);

        birdFrames = new Image[3];
        birdFrames[0] = new ImageIcon(basePath + "bluebird-upflap.png").getImage();
        birdFrames[1] = new ImageIcon(basePath + "bluebird-midflap.png").getImage();
        birdFrames[2] = new ImageIcon(basePath + "bluebird-downflap.png").getImage();
    }

    public void update() {
        velocity += gravity;
        y += velocity;

        frameDelay++;
        if (frameDelay % 5 == 0) {
            frameIndex = (frameIndex + 1) % 3;
        }
    }

    public void jump() {
        velocity = jumpPower;
    }

    public void checkBounds(int panelHeight) {
        if (y < 0) {
            y = 0;
            velocity = 0;
        }
        if (y + height > panelHeight) {
            y = panelHeight - height;
            velocity = 0;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(birdFrames[frameIndex], x, y, width, height, null);
    }
}
