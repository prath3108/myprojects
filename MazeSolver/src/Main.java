import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Treasure Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Treasure Maze Game", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 36));
        title.setForeground(new Color(50, 50, 50));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(title, BorderLayout.NORTH);

        MazePanel mazePanel = new MazePanel();
        frame.add(mazePanel, BorderLayout.CENTER);

        // Button panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.decode("#f4f4f4"));
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton upButton = createIconButton("icon/up.jpg");
        JButton downButton = createIconButton("icon/down.jpeg");
        JButton leftButton = createIconButton("icon/left.png");
        JButton rightButton = createIconButton("icon/right.png");
        JButton resetButton = createIconButton("icon/refresh.jpg");
        JButton changeMazeButton = new JButton("Change Maze");
        changeMazeButton.setFont(new Font("Arial", Font.BOLD, 18));
        changeMazeButton.setPreferredSize(new Dimension(200, 70));

        // Actions
        upButton.addActionListener(e -> mazePanel.movePlayer(mazePanel.getPlayer().x - 1, mazePanel.getPlayer().y));
        downButton.addActionListener(e -> mazePanel.movePlayer(mazePanel.getPlayer().x + 1, mazePanel.getPlayer().y));
        leftButton.addActionListener(e -> mazePanel.movePlayer(mazePanel.getPlayer().x, mazePanel.getPlayer().y - 1));
        rightButton.addActionListener(e -> mazePanel.movePlayer(mazePanel.getPlayer().x, mazePanel.getPlayer().y + 1));
        resetButton.addActionListener(e -> mazePanel.resetPlayer());
        changeMazeButton.addActionListener(e -> mazePanel.generateRandomMaze());

        // Add buttons
        controlPanel.add(upButton);
        controlPanel.add(downButton);
        controlPanel.add(leftButton);
        controlPanel.add(rightButton);
        controlPanel.add(resetButton);
        controlPanel.add(changeMazeButton);

        frame.add(controlPanel, BorderLayout.SOUTH);

        // Full screen mode
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        mazePanel.requestFocusInWindow();
    }

    private static JButton createIconButton(String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImg = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(scaledImg));
        button.setPreferredSize(new Dimension(70, 70));
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        return button;
    }
}
