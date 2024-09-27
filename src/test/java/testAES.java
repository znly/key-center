import com.crayon.KeyCenterApplication;
import com.crayon.facade.AESFacade;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: znly
 * @Description:
 * @Date: 2024/5/23 11:11
 */
@SpringBootTest(classes = KeyCenterApplication.class)
@RunWith(SpringRunner.class) //要加 不然无法注入bean
@Slf4j
public class testAES {

    @Autowired
    private AESFacade aesFacade;


    @Test
    public void testEncrypt() throws Exception
    {
        String encrypt = aesFacade.encrypt("123456789");
        log.info("加密后：{}", encrypt);
        String decrypt = aesFacade.decrypt(encrypt);
        log.info("解密后：{}", decrypt);
    }

    @Test
    public void testEncryptWithRandomKey() throws Exception
    {
        String vectorKey = AESFacade.generateVectorKey();
        log.info("偏移量：{}",vectorKey);
        String encrypt = aesFacade.encrypt("123456789",vectorKey);
        log.info("加密后：{}", encrypt);
        String decrypt = aesFacade.decrypt(encrypt,vectorKey);
        log.info("解密后：{}", decrypt);
    }

}
