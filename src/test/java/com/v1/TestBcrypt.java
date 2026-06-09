package com.v1;

import com.v1.utils.MD5Utils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBcrypt {
    // 使用md5加密算法进行加密
    @Test
    public void testMD5() {
        String pwd1 = MD5Utils.MD5EncodeUtf8("12345");
        String pwd2 = MD5Utils.MD5EncodeUtf8("12345");
        // 不管加密多少次，加密的密文都是一样的
        System.out.println(pwd1); //827CCB0EEA8A706C4C34A16891F84E7B
        System.out.println(pwd2); //827CCB0EEA8A706C4C34A16891F84E7B
        //
    }

    // 使用bcrypt进行密码加密
    @Test public void testBcrypt(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd1 = encoder.encode("123456");
        String pwd2 = encoder.encode("123456");
        String pwd3 = encoder.encode("123456");
        // 每次加密的结果都不一样
        System.out.println(pwd1); //$2a$10$Mb/oAyIogriivWlGosS30.zpfchHr4r7EtarKCGK/eUBnMD2HbwlS
        System.out.println(pwd2); //$2a$10$g1EgxdQKeZrP1MoU2yh9Se./A/2mdQeh1C5l9dZBDf7fDwx1xL3Km
        System.out.println(pwd3); //$2a$10$12pTdhl/dOLNFdMNDD9m8.b1kX0mu61bUMgdWuYpP1AEYUEJxwtOu

        boolean result1 = encoder.matches("123456", "$2a$10$4ccZJcYym4k6ZdNwZK6CSOGuqtsKmWlSsmA.DbyNIJSPOwIwtVYVi");
        boolean result2 = encoder.matches("123456", "$2a$10$JG6OLtoJZaioeUqlnKRC1uZOzEzgjx/3TpJtNxnr2gCsqABlpKGXC");
        boolean result3 = encoder.matches("123456", "$2a$10$REXIY.IrMmOZxatuLipfA.DkwDxeZWUK/QEzb0CG9ZL1L.6iuMER6");
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}