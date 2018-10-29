package controller;

import ThreadTest.ReciveMessage;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.VedioService;
import util.TestMQConsumerQueue;
import util.TestMQProducerQueue;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Controller
@RequestMapping("/converter")
public class VedioController extends HttpServlet {

    @Resource
    private VedioService vedioService;

    private TestMQProducerQueue testmq;
    private TestMQConsumerQueue testgetmq;
    private ReciveMessage mb;

    @RequestMapping("/doConverter")
    public void doConverter(HttpServletRequest request, HttpServletResponse response) throws FrameGrabber.Exception, FrameRecorder.Exception,ServletException, IOException {

        //ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //VedioService service=(VedioService) context.getBean("vedioService");
        //VedioService helloWorld=service.getVsService();
        //helloWorld.doConvert();

        //TEST
        System.out.println("done");

        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

        try {
            String output = vedioService.doConvert(request);

            PrintWriter out = response.getWriter();
            out.print(output);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/doMessage")
    public void doMessage() throws Exception {
        System.out.println("now we send message for test!");

        try {
            //发送
            testmq = new TestMQProducerQueue();
            testmq.testMQproducerQueue();
            //testmq.testMQproducerQueue();
            //testmq.testMQproducerQueue();

            //接受
            testgetmq = new TestMQConsumerQueue();
            testgetmq.TestMQconsumerQueue();

            ////接受
            //mb = new ReciveMessage();
            //mb.reciveMessage();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
