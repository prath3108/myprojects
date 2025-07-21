import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Bird bird;
    Timer timer;
    ArrayList<Pipe> pipes;
    int score = 0;
    boolean gameOver = false;

    int groundHeight = 100;
    private Image bgImg, groundImg, gameOverImg;

    public GamePanel() {
        setFocusable(true);

        String basePath = System.getProperty("user.dir") + "/sprites/";
        System.out.println("Game assets from: " + basePath);

        bgImg = new ImageIcon(basePath + "background-day.png").getImage();
        groundImg = new ImageIcon(basePath + "base.png").getImage();
        gameOverImg = new ImageIcon(basePath + "gameover.png").getImage();

        bird = new Bird(100, 250, 40, 40);

        pipes = new ArrayList<>();
        pipes.add(new Pipe(400, 600 - groundHeight));
        pipes.add(new Pipe(650, 600 - groundHeight));

        timer = new Timer(20, this);
        timer.start();

        addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            bird.update();
            bird.checkBounds(getHeight() - groundHeight);

            for (Pipe pipe : pipes) {
                pipe.update();
                if (checkCollision(pipe)) {
                    gameOver = true;
                    timer.stop();
                }
                if (pipe.x + pipe.width == bird.x) {
                    score++;
                }
            }

            if (bird.y + bird.height >= getHeight() - groundHeight) {
                gameOver = true;
                timer.stop();
            }
        }
        repaint();
    }

    private boolean checkCollision(Pipe pipe) {
        Rectangle birdRect = new Rectangle(bird.x, bird.y, bird.width, bird.height);
        Rectangle topRect = new Rectangle(pipe.x, 0, pipe.width, pipe.topHeight);
        Rectangle bottomRect = new Rectangle(pipe.x, pipe.topHeight + pipe.gap, pipe.width, pipe.bottomHeight);
        return birdRect.intersects(topRect) || birdRect.intersects(bottomRect);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), null);

        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        bird.draw(g);
        g.drawImage(groundImg, 0, getHeight() - groundHeight, getWidth(), groundHeight, null);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Score: " + score, 20, 40);

        if (gameOver) {
            g.drawImage(gameOverImg, (getWidth() - gameOverImg.getWidth(null)) / 2, getHeight() / 3, null);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press ENTER to Restart", 80, getHeight() / 2 + 50);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            bird.jump();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && gameOver) {
            restartGame();
        }
    }

    private void restartGame() {
        bird = new Bird(100, 250, 40, 40);
        pipes.clear();
        pipes.add(new Pipe(400, 600 - groundHeight));
        pipes.add(new Pipe(650, 600 - groundHeight));
        score = 0;
        gameOver = false;
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
