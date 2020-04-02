package AdditionalBean;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastDFS {

    @Bean
    public StorageClient getStorageClient() throws Exception{
        String file=this.getClass().getResource("/tracker.conf").getPath();//获得对应的配置文件的路径地址
        ClientGlobal.init(file);//初始化平台
        TrackerClient trackerClient=new TrackerClient();//创建一个TrackerClient的客户端
        TrackerServer trackerServer=trackerClient.getTrackerServer();//获得对应的TrackerServer的服务端
        StorageClient storageClient=new StorageClient(trackerServer,null);//通过服务端获得storage的服务端
        return storageClient;
    }
}
