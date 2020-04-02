package Utils;

import java.util.Date;
import java.util.Random;

public class RandomUtil {
    //获得一个随机的6位数
    public static String getNewId(){
        Random random=new Random(new Date().getTime());
        int temp=100000+random.nextInt(899999);
        return  ""+temp;
    }
}
