package com.yaic.common.util;

import javax.xml.bind.DatatypeConverter;

class Coder {

    static String encryptBASE64(byte[] key) {
        return DatatypeConverter.printBase64Binary(key);
    }

    static byte[] decryptBASE64(String key) {
        return DatatypeConverter.parseBase64Binary(key);
    }

}
