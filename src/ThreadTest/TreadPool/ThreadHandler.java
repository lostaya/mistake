package ThreadTest.TreadPool;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;
import service.VedioService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class ThreadHandler implements Callable<String>,VedioService{

    private boolean isMultipart;
    //最大文件大小 1G
    private int maxFileSize = 1024 * 1024 * 1024;
    private int maxMemSize = 100 * 1024;
    private HttpServletRequest request;
    private String codeRate;
    private int VideoCodec;
    private String Format;
    private int AudioCodec;
    private int AudioBitrate;
    private int SampleRate;


    //可配置参数构造器
    public ThreadHandler(HttpServletRequest request, String codeRate, int videoCodec, String format, int audioBitrate, int audioCodec, int sampleRate) {
        this.request = request;
        this.codeRate = codeRate;
        VideoCodec = videoCodec;
        Format = format;
        AudioCodec = audioCodec;
        AudioBitrate = audioBitrate;
        SampleRate = sampleRate;
    }

    //默认构造器
    public ThreadHandler(HttpServletRequest request){
        this.request = request;
    }


    @Override
    public  String call() throws FrameGrabber.Exception, FrameRecorder.Exception, IOException{

        String fileName[] = new String[1];
        //码率
        //String codeRate = "3000000";

        //报错信息
        String messageSpecial = null;
        //输出文件名称前
        String outputFileFirstName = null;
        //输出路径
        String outputPath = null;

        //检查标识符
        boolean checkOk = true;
        String realpath = request.getSession().getServletContext().getRealPath("/input");
        if (!(new File(realpath).exists())) {
            new File(realpath).mkdirs();
        }

        System.out.println("realpath: " + realpath);

        // 检查是否有上传请求
        isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 文件大小的最大值将被存储在内存中
            factory.setSizeThreshold(maxMemSize);
            factory.setRepository(new File(realpath));
            // 创建一个新的文件上传处理程序
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 允许上传的文件大小的最大值
            upload.setSizeMax(maxFileSize);
            upload.setHeaderEncoding("utf-8");

            // 解析请求，获取文件项
            List<?> fileItems = null;
            try {
                fileItems = upload.parseRequest(request);
            } catch (FileUploadException e) {
                messageSpecial = "文件上传失败！";
                checkOk = false;
                return messageSpecial;
            }
            // 处理上传的文件项
            Iterator<?> i = fileItems.iterator();

            int j = 0;
            while (i.hasNext()) {
                FileItem item = (FileItem) i.next();
                if (!item.isFormField()) {
                    // 获取上传文件的参数
                    fileName[j] = item.getName();

                    // 写入文件
                    File file = new File(realpath + "/" + fileName[j]);

                    try {
                        item.write(file);
                    } catch (Exception e) {
                        messageSpecial = "文件上传失败！";
                        checkOk = false;
                        return messageSpecial;
                    }

                    j++;
                }
            }
        }

        //取码率的数值，若不合法则返回
        int codeRateInt = 0;

        if (!codeRate.equals("")) {
            String codeRateNumber = null;
            codeRateNumber = codeRate;

            try {
                codeRateInt = Integer.parseInt(codeRateNumber);

            } catch (Exception e) {
                messageSpecial = "请检查码率!";
                checkOk = false;
                return messageSpecial;
            }
        }

        if (checkOk) {

            String inputFile = realpath + "/";
            String file1FileName = fileName[0];
            int inputFileLength = inputFile.length();
            //输出文件随机名
            outputFileFirstName=getRandomString(9);

            //取消随机文件名
            ///////////////////////
            outputFileFirstName = file1FileName;
            int endpoint = outputFileFirstName.lastIndexOf(".");
            outputFileFirstName = outputFileFirstName.substring(0,endpoint);
            ///////////////////////

            String outputFilePath = inputFile.substring(0, inputFileLength - 6) + "output/";
            String finalOutputFile = outputFilePath + outputFileFirstName;
            //生成输出文件
            File outputFile = new File(inputFile + outputFileFirstName + ".mp4");

            String backFilePath;
            //原始文件路径
            backFilePath = inputFile + file1FileName;

            //开始转换过程
            //获取视频源
            FFmpegFrameGrabber backGrabber = new FFmpegFrameGrabber(backFilePath);
            backGrabber.start();

            Frame backFrame = backGrabber.grabFrame();
            int imgWitdh = backGrabber.getImageWidth();
            int imgHeight = backGrabber.getImageHeight();
            System.out.println("图像宽:"+imgWitdh);
            System.out.println("图像高:"+imgHeight);

            File outputFileSub = new File(outputFilePath);
            if (!outputFileSub.exists()) {
                outputFileSub.mkdirs();
            }
            outputPath=finalOutputFile + ".mp4";


            // 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
            //宽高根据frame定义                                                                   backFrame.imageWidth, backFrame.imageHeight
            FFmpegFrameRecorder backRecorder = new FFmpegFrameRecorder(finalOutputFile + ".mp4", imgWitdh, imgHeight, 1);
            // h.264 设置编码
            backRecorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            ////设置帧率
            //backRecorder.setFrameRate(backGrabber.getFrameRate());
            ////设置码率
            //backRecorder.setVideoBitrate(codeRateInt);
            ////设置格式
            //backRecorder.setFormat("mp4");
            ////设置音频
            //backRecorder.setAudioBitrate(192000);
            //backRecorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            //backRecorder.setSampleRate(44100);


            // h.264 设置编码
            backRecorder.setVideoCodec(VideoCodec);
            ////设置帧率
            backRecorder.setFrameRate(backGrabber.getFrameRate());
            ////设置码率
            backRecorder.setVideoBitrate(codeRateInt);
            ////设置格式
            backRecorder.setFormat(Format);
            ////设置音频
            backRecorder.setAudioBitrate(AudioBitrate);
            backRecorder.setAudioCodec(AudioCodec);
            backRecorder.setSampleRate(SampleRate);



            backRecorder.start();
            if (backFrame != null) {
                while ((backFrame = backGrabber.grabFrame()) != null) {
                    backRecorder.record(backFrame);

                }
            }

            if (backRecorder != null) {
                backRecorder.stop();
            }


        }

        System.out.println("所用线程名为："+Thread.currentThread().getName());

        //TEST
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("完成时间:"+df.format(new Date()));// new Date()为获取当前系统时间
        return outputPath;
    }

    /**
     *产生随机文件名
     */
    private String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        String sb = "";
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb+=(base.charAt(number));
        }
        return sb;
    }

    @Override
    public String doConvert(HttpServletRequest request) throws FrameGrabber.Exception, FrameRecorder.Exception, IOException {
        return null;
    }

    @Override
    public void logVeidoInf() {
        System.out.println("logok");
    }

    @Override
    public String outVedioInf() {
        System.out.println("outok");
        return null;
    }



}
