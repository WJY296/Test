package com.wjy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wangjingyang
 * @date 2020-12-18 9:12
 */
@Slf4j
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        TwoThread twoThread = new TwoThread();
        twoThread.start();
        Thread.sleep(2000);
        twoThread.stop();
    }

}
@Slf4j
class TwoThread{
    private Thread thread = null;

    public void start(){
        thread = new Thread(()->{
            while (true){
                if(thread.isInterrupted()){
                    log.info("处理后事--------");
                    break;
                }
                log.info("循环中=====");
                Thread currentThread = Thread.currentThread();
                try {
                    currentThread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    currentThread.interrupt();
                }

            }
        },"t1");
        thread.start();
    }
    public void stop(){
        thread.interrupt();
    }
}