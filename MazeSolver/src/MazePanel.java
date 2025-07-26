import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class MazePanel extends JPanel {
    private final int rows = 6;
    private final int cols = 6;
    private final int cellSize = 80;

    private int[][] maze = new int[rows][cols];
    private Point start = new Point(0, 0);
    private Point end = new Point(rows - 1, cols - 1);
    private Point player = new Point(start.x, start.y);

    private Image playerImg, goalImg;
    private Point animatedPos = null; // For animation
    private Timer moveTimer; // Timer for smooth animation

    public MazePanel() {
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));

        // Load images
        playerImg = new ImageIcon("icon/Player_image.jpg").getImage();
        goalImg = new ImageIcon("icon/treasure.jpg").getImage();

        generateRandomMaze();
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleMovement(e.getKeyCode());
            }
        });
    }

    private void handleMovement(int key) {
        int targetX = player.x, targetY = player.y;
        if (key == KeyEvent.VK_UP) targetX--;
        else if (key == KeyEvent.VK_DOWN) targetX++;
        else if (key == KeyEvent.VK_LEFT) targetY--;
        else if (key == KeyEvent.VK_RIGHT) targetY++;
        movePlayer(targetX, targetY);
    }

    public void movePlayer(int newX, int newY) {
        if (moveTimer != null && moveTimer.isRunning()) return; // Prevent spamming
        if (!isValidMove(newX, newY)) return;

        int startX = player.y * cellSize;
        int startY = player.x * cellSize;
        int endX = newY * cellSize;
        int endY = newX * cellSize;

        animatedPos = new Point(startX, startY);
        int steps = 20;
        int deltaX = (endX - startX) / steps;
        int deltaY = (endY - startY) / steps;

        moveTimer = new Timer(10, null);
        final int[] step = {0};

        moveTimer.addActionListener(e -> {
            step[0]++;
            animatedPos.x += deltaX;
            animatedPos.y += deltaY;
            repaint();

            if (step[0] >= steps) {
                moveTimer.stop();
                animatedPos = null;
                player.setLocation(newX, newY);
                repaint();
                if (player.equals(end)) {
                    showWinScreen();
                    resetPlayer();
                }
            }
        });

        moveTimer.start();
    }

    public Point getPlayer() {
        return player;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] == 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw maze
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                g.setColor(maze[i][j] == 1 ? Color.BLACK : Color.WHITE);
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        // Draw treasure
        if (goalImg != null) {
            g.drawImage(goalImg, end.y * cellSize + 5, end.x * cellSize + 5, cellSize - 10, cellSize - 10, this);
        } else {
            g.setColor(Color.RED);
            g.fillRect(end.y * cellSize + 10, end.x * cellSize + 10, cellSize - 20, cellSize - 20);
        }

        // Draw player (animated if moving)
        if (animatedPos != null) {
            g.drawImage(playerImg, animatedPos.x + 5, animatedPos.y + 5, cellSize - 10, cellSize - 10, this);
        } else {
            g.drawImage(playerImg, player.y * cellSize + 5, player.x * cellSize + 5, cellSize - 10, cellSize - 10, this);
        }
    }

    public void resetPlayer() {
        if (moveTimer != null && moveTimer.isRunning()) moveTimer.stop();
        player.setLocation(start.x, start.y);
        animatedPos = null;
        repaint();
    }

    public void generateRandomMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = 1;
            }
        }
        int x = start.x, y = start.y;
        maze[x][y] = 0;
        while (x < end.x || y < end.y) {
            if (Math.random() < 0.5 && x < end.x) x++;
            else if (y < end.y) y++;
            maze[x][y] = 0;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 1 && Math.random() < 0.3) maze[i][j] = 0;
            }
        }
        maze[start.x][start.y] = 0;
        maze[end.x][end.y] = 0;
        resetPlayer();
    }

    private void showWinScreen() {
        JDialog dialog = new JDialog();
        dialog.setTitle("You Won!");

        JLabel imgLabel;
        if (new File("icon/background.jpg").exists()) {
            imgLabel = new JLabel(new ImageIcon("icon/background.jpg"));
        } else {
            imgLabel = new JLabel();
            imgLabel.setBackground(Color.BLACK);
            imgLabel.setOpaque(true);
        }
        imgLabel.setLayout(new BorderLayout());

        JLabel text = new JLabel("🎉 YOU WON THE TREASURE! 🎉", SwingConstants.CENTER);
        text.setFont(new Font("Verdana", Font.BOLD, 40));
        text.setForeground(Color.ORANGE);
        text.setBackground(Color.BLACK);
        text.setOpaque(true);

        imgLabel.add(text, BorderLayout.SOUTH);
        dialog.add(imgLabel);

        dialog.setSize(800, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
}
