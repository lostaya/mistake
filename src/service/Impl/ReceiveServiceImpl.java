package service.Impl;

import org.springframework.stereotype.Service;
import service.ReceiveService;
import service.VedioService;

@Service
public class ReceiveServiceImpl implements ReceiveService{
    
    @Override
    public VedioService receiveJmsconvert(int bitrate, String format, int framerate) {
        return null;
    }
}
