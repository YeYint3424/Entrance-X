package com.EntranceX.mm.EntranceX.config;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
@Component
public class QRCodeGenerator {

    public static byte[] generateRandomQRCode(int size) throws WriterException, IOException {
        String text = generateRandomString();

        // Generate QR code using QRGen library
        ByteArrayOutputStream out = QRCode.from(text)
                .to(ImageType.PNG)
                .withSize(size, size)
                .stream();
        byte[] qrCodeBytes = out.toByteArray();

        // Generate QR code using ZXing library
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size, size);
        ByteArrayOutputStream zxingOut = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", zxingOut);
        byte[] zxingBytes = zxingOut.toByteArray();

        // Combine the QR codes by XORing the bytes
        byte[] resultBytes = new byte[qrCodeBytes.length];
        for (int i = 0; i < qrCodeBytes.length; i++) {
            resultBytes[i] = (byte) (qrCodeBytes[i] ^ zxingBytes[i]);
        }

        return resultBytes;
    }

    private static String generateRandomString() {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; // 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}








