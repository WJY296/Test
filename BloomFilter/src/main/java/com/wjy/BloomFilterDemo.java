package com.wjy;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class BloomFilterDemo {
    public static void main(String[] args) {
        final int count = 1000000;
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), count);

        //存放实际存在的key 判断key是否存在
        HashSet<String> set = new HashSet<>(count);

        //存放实际存在的key 可以取出使用
        ArrayList<String> list = new ArrayList<>(count);

        //向三个容器添加一百万个唯一字符串
        for (int i = 0; i < count; i++) {
            String s = UUID.randomUUID().toString();
            bf.put(s);
            set.add(s);
            list.add(s);
        }

        //判断正确的次数
        int right = 0;
        //判断错误的次数
        int wronf = 0;

        for (int i = 0; i < count; i++) {

            String data = i % 100 == 0 ? list.get(i / 100) : UUID.randomUUID().toString();

            if (bf.mightContain(data)) {
                if (set.contains(data)) {
                    right++;
                    continue;
                }
                wronf++;
            }

        }
        System.out.println("判断正确的： " + right);
        System.out.println("判断错误的： " + wronf);

        DecimalFormat format = new DecimalFormat("0.00%");

        System.out.println("正判率是： " + format.format(right / (double) 10000));
        System.out.println("误判率是： " + format.format(wronf / (double) 990000));


    }


}
