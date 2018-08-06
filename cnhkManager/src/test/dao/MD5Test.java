package dao;

import org.junit.Assert;
import org.junit.Test;
import store.cnhk.utils.MD5;

public class MD5Test {
    @Test
    public void test() {
        String s = "handong";
        String md5 = MD5.MD5(s);
        Assert.assertEquals(md5, "D2EE2F384B18DB1B5B9A99321A0E256C");
    }
}
