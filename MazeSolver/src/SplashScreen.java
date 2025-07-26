import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {
    public SplashScreen() {
        JLabel splashLabel = new JLabel(new ImageIcon("icon/background.jpg"));
        splashLabel.setLayout(new BorderLayout());

        JLabel title = new JLabel("TREASURE MAZE", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 48));
        title.setForeground(Color.YELLOW);
        splashLabel.add(title, BorderLayout.SOUTH);

        getContentPane().add(splashLabel);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public void showSplash() {
        setVisible(true);
        try {
            Thread.sleep(3000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
    }
}

