package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener {
    //声明右侧蛇头和身体图片
    ImageIcon right = new ImageIcon("images/right.png");
    ImageIcon body = new ImageIcon("images/body.png");

    //声明其余方向图片
    ImageIcon top = new ImageIcon("images/top.png");
    ImageIcon bottom = new ImageIcon("images/bottom.png");
    ImageIcon left = new ImageIcon("images/left.png");

    //声明一个初始蛇的长度
    int len = 3;
    //声明数组存放坐标位置
    int[] snakeX = new int[1008]; //最大值 = 宽度*高度
    int[] snakeY = new int[1008]; //最大值 = 宽度*高度

    //声明一个枚举类型的变量，标识蛇头方向
    Direction direction = Direction.right;

    //声明一个变量，标识游戏是否开始
    boolean isStart = false;

    //创建一个定时器对象
    Timer timer = new Timer(100, this);

    //声明两个变量表示食物的坐标位置
    int foodX;
    int foodY;
    Random random = new Random();
    //声明食物图片
    ImageIcon food = new ImageIcon("images/food.png");


    public MyPanel() {
        //设定蛇的头部和身体的初始位置
//        int headX = (int) (Math.random() * 100 + 50);
//        int headY = (int) (Math.random() * 100 + 50);
        int headX = 200;
        int headY = 200;
        snakeX[0] = headX;
        snakeY[0] = headY;

        snakeX[1] = headX - 25;
        snakeY[1] = headY;

        snakeX[2] = headX - 50;
        snakeY[2] = headY;

        //获取键盘事件
        this.setFocusable(true);
        //添加监听事件
        this.addKeyListener(this);

        //启动定时器
        timer.start();

        //生成食物坐标
        foodX = 25 + 25 * random.nextInt(20);
        foodY = 25 + 25 * random.nextInt(20);


    }

    //重写画组件方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //设置背景颜色
        this.setBackground(Color.BLUE);
        //在画布中添加游戏区
        g.fillRect(0, 0, 700, 900);

        //根据枚举变量方向值进行判断，显示不同方向的蛇头
        switch (direction) {
            case top:
                top.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case left:
                left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case right:
                right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case bottom:
                bottom.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        //添加身体
        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //放上提示信息，设置对应样式
        //判断当前游戏是否开始
        if (!isStart) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体", Font.BOLD, 50));
            g.drawString("按空格开始", 200, 500);
        }

        //添加食物
        food.paintIcon(this, g, foodX, foodY);
    }

    /**
     * 表示按键不同的数字
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 32) {
            isStart = !isStart;
            //重新画组件
            repaint();
        } else if (key == KeyEvent.VK_UP) {
            direction = Direction.top;
        } else if (key == KeyEvent.VK_DOWN) {
            direction = Direction.bottom;
        } else if (key == KeyEvent.VK_LEFT) {
            direction = Direction.left;
        } else if (key == KeyEvent.VK_RIGHT) {
            direction = Direction.right;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //判断为true时游戏开始
        if (isStart) {
            //移动身体
            for (int i = len - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //通过方向值direction进行判断，移动蛇头
            switch (direction) {
                case top:
                    snakeY[0] -= 25;
                    //判断当前蛇头的值超出屏幕，则x从0开始
                    if (snakeY[0] <= 0) {
                        snakeY[0] = 900;
                    }
                    break;
                case bottom:
                    snakeY[0] += 25;
                    //判断当前蛇头的值超出屏幕，则x从0开始
                    if (snakeY[0] >= 900) {
                        snakeY[0] = 0;
                    }
                    break;
                case right:
                    snakeX[0] += 25;
                    //判断当前蛇头的值超出屏幕，则x从0开始
                    if (snakeX[0] >= 700) {
                        snakeX[0] = 0;
                    }
                    break;
                case left:
                    snakeX[0] -= 25;
                    //判断当前蛇头的值超出屏幕，则x从0开始
                    if (snakeX[0] <= 0) {
                        snakeX[0] = 700;
                    }
                    break;
            }

            //判断是否吃到食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                len++;
                //重新生成食物坐标
                foodX = 25 + 25 * random.nextInt(20);
                foodY = 25 + 25 * random.nextInt(20);
            }
            repaint();
            timer.start();
        }

    }
}
