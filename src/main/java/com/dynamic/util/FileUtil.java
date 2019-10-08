package com.dynamic.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Copyright
 *
 * @author conanju
 * @since 2019/9/20 17:05
 */
public class FileUtil {
    private static final String FILE_NAME = "C:\\zhukenan\\data\\data.zip";

    public static void main(String[] args) throws Exception {
//        BufferedImage image = ImageIO.read(new File("C:\\zhukenan\\data\\data\\timg.jpg"));
//        ImageIO.write(image, "jpg", new File("C:\\zhukenan\\data\\data\\timg_cpy.jpg"));
        readZipFile(FILE_NAME);
    }

    public static String readZipFile(String imgZipfile) throws IOException {
        ZipFile zf = new ZipFile(imgZipfile);
//   FileInputStream fis = new FileInputStream(imgZipfile);
        FileInputStream fis = new FileInputStream(imgZipfile);
        InputStream in = new BufferedInputStream(fis);
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (!ze.getName().contains("jpg")) {
                System.out.println("========不输出" + ze.getName());
                continue;
            }

            long size = ze.getSize();
            if (size <= 0) {
                continue;
            }

            System.out.println("+++++++++输出" + ze.getName());
            BufferedImage image = ImageIO.read(zf.getInputStream(ze));
            ImageIO.write(image, "jpg", new File("C:\\zhukenan\\data\\" + ze.getName()));
        }
        in.close();
        zin.close();
        zf.close();
        return "success";
    }
}