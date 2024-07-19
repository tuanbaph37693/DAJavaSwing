/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.ChiTietSanPham;

/**
 *
 * @author Admin
 */
public class QRCodeHelper {

    public static String generateQRCode(String data) {
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, 0);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 100, 100, hintMap);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }

            saveQRCodeImage(data + ".png", image);

            // Hiển thị hình ảnh mã QR
//            ImageIcon icon = new ImageIcon(image);
//            JOptionPane.showMessageDialog(null, null, "QR Code", JOptionPane.INFORMATION_MESSAGE, icon);
            return data;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveQRCodeImage(String filename, BufferedImage image) {
        try {
            // Specify the directory where you want to save the images
            String directoryPath = "src/qr/imageChiTietSanPham"; // Update this with your desired directory

            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file path
            String filePath = directoryPath + File.separator + filename;

            // Save the image to the specified file path
            ImageIO.write(image, "png", new File(filePath));

            System.out.println("QR Code image saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon readQRCodeImage(String filePath) {
        filePath = "src/qr/imageChiTietSanPham/" + filePath + ".png";
        System.out.println(filePath);
        try {
            // Đọc hình ảnh từ đường dẫn file
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));

            // Chuyển đổi hình ảnh thành đối tượng LuminanceSource
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Sử dụng MultiFormatReader để đọc mã QR từ hình ảnh
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(bitmap);

            // Lấy thông tin từ kết quả đọc được
            String qrCodeData = result.getText();

            // Tạo ImageIcon từ hình ảnh đọc được
            ImageIcon imageIcon = new ImageIcon(bufferedImage);

            return imageIcon;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
