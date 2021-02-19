package com.wjy.test;

import org.apache.ibatis.executor.Executor;
import org.apache.poi.ss.formula.functions.MinaMaxa;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author wangjingyang
 * @date 2021-02-19 16:46
 */
public class TestZip {

    private static final String ZIP_FILE = "F:/ZIPFILE.zip";
    private static final String JPG_FILE = "F:/001.jpg";
    private static final String FILE_NAME = "test";

    /**
     * 这个是最普通的压缩
     */
    public static void zipFileNoBuffer() {
        File zipFile = new File(ZIP_FILE);
        try (
                //压缩文件输出流
                ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile))
        ) {
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0;i < 10; i++) {
                try (
                        //输入流
                        InputStream input = new FileInputStream(JPG_FILE)
                ) {
                    zipOut.putNextEntry(new ZipEntry(FILE_NAME + i+".jpg"));
                    int temp = 0;
                    while ((temp = input.read()) != -1) {
                        zipOut.write(temp);
                    }
                }
            }
            System.out.println(System.currentTimeMillis()-beginTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用Buff缓冲区压缩
     */
    public static void zipFileBuffer() {
        File zipFile = new File(ZIP_FILE);
        try (
                //压缩文件输出流
                ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        ) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut);
            //开始时间
            long beginTime = System.currentTimeMillis();

            for (int i = 0;i < 10; i++) {
                try (
                        //输入流
                        InputStream input = new FileInputStream(JPG_FILE)
                ) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(input);
                    zipOut.putNextEntry(new ZipEntry(FILE_NAME + i+".jpg"));
                    int temp = 0;
                    while ((temp = bufferedInputStream.read()) != -1) {
                        bufferedOutputStream.write(temp);
                    }
                }
            }
            System.out.println(System.currentTimeMillis()-beginTime);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用NIO 压缩
     */
    public static void zipFileChannel() {
        //开始时间
        long beginTime = System.currentTimeMillis();
        File file = new File(ZIP_FILE);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file))) {
            WritableByteChannel writableByteChannel = Channels.newChannel(zipOutputStream);
            for (int i = 0; i <10 ; i++) {
                FileChannel fileChannel = new FileInputStream(JPG_FILE).getChannel();
                zipOutputStream.putNextEntry(new ZipEntry(FILE_NAME+i+".jpg"));

                fileChannel.transferTo(0,fileChannel.size(),writableByteChannel);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //一共使用时间
        System.out.println(System.currentTimeMillis()-beginTime);

    }



    public static void main(String[] args) {
        zipFileChannel();
    }
}
