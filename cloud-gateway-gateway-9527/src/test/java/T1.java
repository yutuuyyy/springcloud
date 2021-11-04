import java.time.ZonedDateTime;

/**
 * @program: springcloud
 * @description:
 * @author: gwb
 * @create: 2021-11-04 08:53
 **/
public class T1 {

    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
//        2021-11-04T08:57:30.307+08:00[Asia/Shanghai]
    }
}
