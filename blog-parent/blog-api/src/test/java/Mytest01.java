import com.yomo.blog.BlogApp;
import com.yomo.blog.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.yomo.blog.utils.Constants.*;

/**
 * <p>
 * TODO:请用一句话描述
 * </p>
 *
 * @author yomo
 * @since 2022/4/11
 */
@SpringBootTest(classes = BlogApp.class)
public class Mytest01 {

    @Test
    void test() {
        String s = DigestUtils.md5Hex("123456" + SALT);
        System.out.println(s);
    }

}
