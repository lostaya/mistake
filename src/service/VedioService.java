package service;

import org.bytedeco.javacpp.presets.opencv_core;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public interface VedioService {
    //转换
    String doConvert(HttpServletRequest request) throws FrameGrabber.Exception, FrameRecorder.Exception, IOException;

    //记录视频信息
    void logVeidoInf();

    //输出视频信息
    String outVedioInf();
}
