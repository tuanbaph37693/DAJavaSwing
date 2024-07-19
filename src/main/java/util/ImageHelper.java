/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class ImageHelper {

    public static Image logoApp() {
        File file = new File("src/image/logo", "fpt.png");
        Image image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
        return image;
    }

    public static void saveImage(File file) {
        File dir = new File("src/image", file.getName());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(dir.getAbsolutePath());
            System.out.println(destination);
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon readImage(String fileName) {
        if (fileName == null) {
            return null;
        }
        File path = new File("src/image", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(331, 331, Image.SCALE_DEFAULT));
    }
}
