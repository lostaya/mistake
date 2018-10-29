package service;

import org.springframework.stereotype.Service;

/**
 * Created by yp on 18-10-18.
 */
public interface ReceiveService {

    VedioService receiveJmsconvert(int bitrate,String format,int framerate);

}
