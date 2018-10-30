package vn.five9.bi;

import org.junit.Assert;
import org.junit.Test;
import vn.five9.bi.crypt.AESAlgorithm;

public class EASAlgorithmTests {

    @Test
    public void test() throws Exception {
        AESAlgorithm aesAlgorithm = new AESAlgorithm();
        String password = "passwordAhaha";
        String encyrpt = aesAlgorithm.encyrpt("passwordAhaha");
        Assert.assertEquals(password, aesAlgorithm.decrypt(encyrpt));
    }
}
