package src;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    ImageIcon body = new ImageIcon("body.png");
    ImageIcon down = new ImageIcon("down.png");
    ImageIcon food = new ImageIcon("food.png");
    ImageIcon left = new ImageIcon("left.png");
    ImageIcon right = new ImageIcon("right.png");
    ImageIcon up = new ImageIcon("up.png");

    // 蛇的数据结构设计
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    int len = 3;
    String direction = "R";

    Random r = new Random();
    int foodX = r.nextInt(34) * 25 + 25;
    int foodY = r.nextInt(24) * 25 + 75;
    boolean isFailed;
    boolean isStarted;

    public void initSnake() {
        len = 3;
        direction = "R";
        isStarted = false;
        isFailed = false;
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
    }

    public GamePanel() {
        initSnake();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    Timer timer = new Timer(150, this);

    public void paint(Graphics g) {

        this.setBackground(Color.BLACK);

        g.fillRect(25, 75, 850, 600);

        if (!isStarted) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimeRomans", Font.ITALIC, 30));
            g.drawString("Press space button to start or pause", 200, 360);
        }
        if (isFailed) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimeRomans", Font.ITALIC, 30));
            g.drawString("Press space button to play again", 200, 360);
        }

        if (direction.equals("R"))
            right.paintIcon(this, g, snakex[0], snakey[0]);
        else if (direction.equals("L"))
            left.paintIcon(this, g, snakex[0], snakey[0]);
        else if (direction.equals("U"))
            up.paintIcon(this, g, snakex[0], snakey[0]);
        else if (direction.equals("D"))
            down.paintIcon(this, g, snakex[0], snakey[0]);

        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }

        food.paintIcon(this, g, foodX, foodY);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            isStarted = !isStarted;
        } else if (code == KeyEvent.VK_UP && !direction.equals("D"))
            direction = "U";
        else if (code == KeyEvent.VK_DOWN && !direction.equals("U"))
            direction = "D";
        else if (code == KeyEvent.VK_LEFT && !direction.equals("R"))
            direction = "L";
        else if (code == KeyEvent.VK_RIGHT && !direction.equals("L"))
            direction = "R";

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void actionPerformed(ActionEvent e) {

        if (isStarted) {
            for (int i = len - 1; i > 0; i--) {
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }

            if (direction.equals("R")) {
                snakex[0] += 25;
                if (snakex[0] > 850)
                    snakex[0] = 25;
            } else if (direction.equals("L")) {
                snakex[0] -= 25;
                if (snakex[0] < 25)
                    snakex[0] = 850;
            } else if (direction.equals("U")) {
                snakey[0] -= 25;
                if (snakey[0] < 75)
                    snakey[0] = 650;
            } else if (direction.equals("D")) {
                snakey[0] += 25;
                if (snakey[0] > 650)
                    snakey[0] = 75;
            }

        }

        repaint();
    }

}