package com.EntranceX.mm.EntranceX.config;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class QRCodeGenerator {

    public static byte[] generateQRCode(String qrCodeData, int qrCodeSize) throws Exception {

        String qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + qrCodeData + "&size=" + qrCodeSize + "x" + qrCodeSize;

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix bitMatrix = writer.encode(qrCodeUrl, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);

        return outputStream.toByteArray();
    }
}









