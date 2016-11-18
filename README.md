# CustomView
自己写的一些自定义控件
## CustomView1(一个类似于音频软件的自定义控件)
### 他的样子：
![](https://github.com/jiushi555/CustomView/raw/master/CustomView1/ys1.png)<br/>
上面的图中，没过300ms刷新一次，每一个条的高度都是随机产生的随机数。同时每一条的颜色就是两种颜色的渐变形成的，这两种颜色和背景色都是在自定义属性中设置的。
如下代码：

    <xml.org.customview1.CumtomView
        android:layout_marginTop="100dp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        custom:MainBackGround="@android:color/black"
        custom:RectBackGround1="@android:color/holo_orange_light"
        custom:RectBackGround2="@android:color/holo_blue_dark"
        />

## CircleView(一个自定义的计时器)
### 他的样子：
![](https://github.com/jiushi555/CustomView/raw/master/CircleView/circle_ys.jpg) <br/>
上面的图中，中间显示的秒数和外面的环形角度对应，同时，当秒数倒计到0时，View将不在变化。同时中间圆形的颜色，字体的大小和颜色，外面环形的宽度和颜色，都是自定义属性，可以直接设置。<br/>
## CustomTimepiece(一个自定义时针)
### 他的样子：
![](https://github.com/jiushi555/CustomView/raw/master/CustomTimepiece/timepiece_ys.jpg)<br/>
在这个自定控件中主要是通过画布坐标系的移动和旋转完成绘制的。同时view没过1s刷新一次，实现秒针走动的样式，分针展示没有实现走动。
