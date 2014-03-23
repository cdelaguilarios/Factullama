package com.llamita.factullamita.encode;

import org.apache.commons.codec.binary.Base64;
/**
 * Created by fabiosalasm on 23/03/14.
 */
public class Prueba {

    public static void main(String[] args) {
        String total = "64.506";
        System.out.println("Total sin encriptar: " + total);

        byte[] totalEncoded = Base64.encodeBase64(total.getBytes());
        String totalEncodedS = new String(totalEncoded);

        System.out.println("Total encriptado, segunda forma: " + new String(totalEncoded));

        byte[] totalDecoded = Base64.decodeBase64(totalEncodedS.getBytes());
        System.out.println("Total desencriptado: " + new String(totalDecoded));
    }
}
