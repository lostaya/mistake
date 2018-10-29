package controller;

import ThreadTest.ReciveMessage;
import ThreadTest.TreadPool.*;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.Impl.VedioServiceImpl;
import service.VedioService;
import util.TestMQConsumerQueue;
import util.TestMQProducerQueue;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;

@Controller
@RequestMapping("/download")
public class FileController extends HttpServlet {

    @RequestMapping("/vedio")
    public void doConverter(HttpServletRequest request, HttpServletResponse response) throws FrameGrabber.Exception, FrameRecorder.Exception, ServletException, IOException {

        //response.setHeader("Content-Type", "text/html; charset=UTF-8");
        //TEST
        System.out.println("done");

        //TEST
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("开始时间:"+df.format(new Date()));// new Date()为获取当前系统时间


        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");


        try {
            //非单例
            //BlockingQueue queue = new LinkedBlockingQueue<>();
            //ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(8, 8, 60, TimeUnit.DAYS, queue);

            //单例模式线程池
            ExecutorService threadPoolExecutor2 = ThreadPoolUtils.getThreadPoolExecutor2();

            //参数化构造 码率;编码格式;格式;音频比特率;音频编码格式;采样率
            ThreadHandler threadHandler = new ThreadHandler(request, "17010", avcodec.AV_CODEC_ID_H264, "mp4", 192000, avcodec.AV_CODEC_ID_AAC, 44100);

            //默认构造
            //ThreadHandler threadHandler = new ThreadHandler(request);

            //用多线程HANDLER实现
            //Future<String> output = threadPoolExecutor2.submit(new ThreadHandler(request));
            Future<String> output = threadPoolExecutor2.submit(threadHandler);

            System.out.println(output.get());

            PrintWriter out = response.getWriter();
            out.print(output.get());
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("/file")
    public void doDownload(HttpServletRequest request, HttpServletResponse resp) throws IOException {

        DownloadService downloadService = new DownloadService();
        downloadService.doFile(request, resp);


    }
}