package ThreadTest.TreadPool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consumor implements Runnable{
    private String name2;
    private Product product;
    public Consumor(String name2){
        this.name2 = name2;
    }
    public Consumor( Product product){
        this.product = product;
    }

    @Override
    public void run() {
        //0-6000
        long times = (long) (Math.random()*1000*100);
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        System.out.println("系统时间："+times+"...同时消费名字为："+product.getName()+"的产品...系统时间："+df.format(new Date()));
        System.out.println("消费所用线程名为："+Thread.currentThread().getName());

    }
}
