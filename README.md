# snake_game
对于Java学习进行一个简单的练习小游戏
# 1.  准备工作

JDK版本：8.0

# 2.  设计游戏图纸

实现700*900

宽度值为700像素，每个格子为25像素，共计有28个格子

高度值为900像素，每个格子为25像素，共计有36 个格子

# 3.  在窗口上添加画布

新建一个类MyPanel画布，同时继承JPanel

编写两个方法：无参构造方法和重写画组件，其中参数看作是一个画笔

方法体中编写代码：先调用父类方法做一些基本工作，然后再设置背景颜色，最后在main方法的窗口中添加画布

执行思路：当添加画布时，执行无参构造方法，然后再自动执行重写画组件的方法

# 4.  在画布上添加黑色游戏区

使用画笔填满整个区域，四个参数分别是：在画布中x坐标，在画布中y坐标，以及宽度和高度值

# 5.  实现静态贪吃蛇

声明右侧头部图片

声明身体图片

添加右侧头部

添加两个身体

# 6.  定义蛇的数据

当游戏运行后，蛇的身体会不断变长，蛇的位置也会不断的发生改变，因此需要将蛇的长度和蛇的位置存放起来，目前使用数组完成。

具体操作步骤如下：

声明一个初始值，表示蛇的初始长度为3

声明蛇的x坐标和y坐标

当创建对象执行无参构造方法时，完成蛇的右侧头部和身体位置初始化

此时就不需要之前编写静态蛇身体的代码，通过编写循环遍历数组即可

# 7.  控制蛇头方向

蛇头可以进行上下左右移动

操作步骤：

定义一个枚举方向，有上、下、左、右四个取值

​    分别声明向上、向下和向左的三个蛇头图片

​    声明一个枚举类型变量，标识蛇头的方向

​    通过更改枚举方向的值，来更改蛇头的方向 

# 8.  设置提示操作

在重写画组件的方法中，使用画笔就可以完成

# 9. 按空格键开始游戏

1.声明一个boolean类型变量isStart为false标记游戏的状态

2.判断，当isStart值为false时，显示开始提示文字

3.在无参构造方法中设置获取焦点为true，也就是：可以获取键盘的事件

4.获取键盘事件后谁来监听，添加监听this.addKeyListener(this);

 其中this代表自身，但是目前还没有处理监听事件

 则需要在MyPanel类实现KeyListener接口，并且重写三个方法，分别是：keyTyped()、keyPressed()、keyReleased()

 在keyPressed()方法或keyReleased()方法中都可以实现

 其中参数keyEvent表示按了哪个键，按不同的键获取不同的数字，则通过e.getKeyCode()获取当前按键对应的数字，然后判断，如果按的是空格键或者数字32，则将当前标记isStart值取反，同时没有开始游戏的提示信息，需要调用repaint()方法，表示重新画组件

# 10. 关于蛇的优化

1. 创建一个定时器Timer,第一个参数为多长时间比如：100毫秒，第二个参数当时间到了以后找谁this

需要实现ActionListener接口，重写actionPerformed()方法，也就是当时间到了调用actionPerformed()方法

2. 在构造方法中启动定时器，当到100毫秒就调用重写actionPerformed()方法

3. 在重写actionPerformed()方法体中，实现蛇移动

移动蛇的思路：

假如蛇水平向右移动，最后一个身体移动到前面一个身体的位置，也就是x坐标更改，y坐标不动

假如蛇的头部也水平向右移动，蛇的头部x坐标应该在当前位置+25

4. 调用重新画的方法，再重新启动定时器

# 11.  实现暂停

在重写actionPerformed()方法中进行判断，当标记的值为true时，则蛇进行移动

# 12. 实现转向功能

1. 通过键盘按键更改变量direction的值

2. 在actionPerformed()方法中，通过判断变量direction方向进行蛇头的上下左右移动

# 13. 添加食物

1. 随机生成食物，声明两个变量foodX和foodY表示食物的位置，声明一个随机的变量random，声明食物图片food

2. 在无参构造方法中，生成食物foodX和foodY的坐标

foodX = 25 + 25 * random.nextInt(20);

foodY = 25 + 25 * random.nextInt(20);

3. 在paintComponect()方法中添加食物

# 14. 吃掉食物

当蛇的头部和食物的坐标完全重叠时，则表示吃到食物，同时蛇的长度加1，并且生成一个新的食物

具体实现思路如下：

1. 在actionPerformed()方法中，判断蛇头x的坐标与食物x坐标foodX相同，并且蛇头y坐标与食物y坐标foodY相同，则长度加1

2. 再重新生成食物的x和y坐标

 
