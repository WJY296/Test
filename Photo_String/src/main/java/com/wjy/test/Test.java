package com.wjy.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        String photoString = getPhotoString("C:\\Users\\86176\\Pictures\\壁纸\\wallhaven-j83y3p.jpg");
        System.out.println("字符串长度： " + photoString.length());
        boolean b = GenerateImage(photoString);
        System.out.println(b);

    }

    //把图片转换为String
    public static String getPhotoString(String photoPath) {
        if (photoPath == null || photoPath == "") {
            return "";
        }

        File file = new File(photoPath);

        if (!file.exists()) {
            return "";
        }

        byte[] data = null;
        try {
            InputStream in = new FileInputStream(file);
            data = getByte(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String imageString = Base64.encodeToString(data, Base64.DEFAULT);

        BASE64Encoder encoder = new BASE64Encoder();
        String photoString = encoder.encode(data);
        return photoString;
    }

    //把输入流装换为字节数组
    public static byte[] getByte(InputStream in) {
        if (in == null) {
            return null;
        }
        int sumSize = 0;
        List<byte[]> totalBytes = new ArrayList<byte[]>();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = in.read(buffer)) != -1) {
                sumSize += length;
                byte[] tmp = new byte[length];
                System.arraycopy(buffer, 0, tmp, 0, length);
                totalBytes.add(tmp);
            }
            byte[] data = new byte[sumSize];
            int start = 0;
            for (byte[] tmp : totalBytes) {
                System.arraycopy(tmp, 0, data, start, tmp.length);
                start += tmp.length;
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //把Base64 字符串转换为图片
    public static boolean GenerateImage(String imgStr) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) {
            return false;
        } //图像数据为空
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            //新生成的图片
            String imgFilePath = "F:/new.jpg";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
