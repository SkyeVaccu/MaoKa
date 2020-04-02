package Utils;

import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FdfsUtil {
    @Autowired
    private StorageClient storageClient;

    public String uploadFile(byte[] data,String extname){
        try {
            String[] result = storageClient.upload_file(data, extname, null);
            return "http://192.168.79.128:8888/"+result[0]+"/"+result[1];
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
