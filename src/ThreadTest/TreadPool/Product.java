package ThreadTest.TreadPool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Runnable{

    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        //0-2000
        long times = (long) (Math.random()*1000*2);
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        System.out.println("处理时间："+times+"...已经生产了名字为："+name+"的产品...系统时间："+df.format(new Date()));
        System.out.println("生产所用线程名为："+Thread.currentThread().getName());
    }
}
