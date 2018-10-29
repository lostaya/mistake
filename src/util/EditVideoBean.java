package util;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.io.File;
import java.util.List;

public class EditVideoBean implements Runnable{

	private String outputFileFirstName;
	private int width;//
	private int height;
	private int codeRateInt;
	private int outputFrameRate;
	private boolean endFlg;
	private FFmpegFrameRecorder backRecorder;
	private FFmpegFrameRecorder bufferRecorder;
	private String trainDataPath;


	public void run() {

		// 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
		// 代表h.264
		backRecorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
		backRecorder.setFrameRate(outputFrameRate);
		backRecorder.setVideoBitrate((int) (codeRateInt*0.9));
		backRecorder.setAudioBitrate((int) (codeRateInt*0.1));
		backRecorder.setFormat("mp4");


	}

	public String getOutputFileFirstName() {
		return outputFileFirstName;
	}
	public void setOutputFileFirstName(String outputFileFirstName) {
		this.outputFileFirstName = outputFileFirstName;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getCodeRateInt() {
		return codeRateInt;
	}
	public void setCodeRateInt(int codeRateInt) {
		this.codeRateInt = codeRateInt;
	}
	public int getOutputFrameRate() {
		return outputFrameRate;
	}
	public void setOutputFrameRate(int outputFrameRate) {
		this.outputFrameRate = outputFrameRate;
	}
	public boolean isEndFlg() {
		return endFlg;
	}
	public void setEndFlg(boolean endFlg) {
		this.endFlg = endFlg;
	}
	public FFmpegFrameRecorder getBackRecorder() {
		return backRecorder;
	}
	public void setBackRecorder(FFmpegFrameRecorder backRecorder) {
		this.backRecorder = backRecorder;
	}
	public FFmpegFrameRecorder getBufferRecorder() {
		return bufferRecorder;
	}
	public void setBufferRecorder(FFmpegFrameRecorder bufferRecorder) {
		this.bufferRecorder = bufferRecorder;
	}
	public String getTrainDataPath() {
		return trainDataPath;
	}
	public void setTrainDataPath(String trainDataPath) {
		this.trainDataPath = trainDataPath;
	}


}