package com.demo;

/**
 * @author xue
 * @org 北京信安世纪股份有限公司
 * @Description TODO
 * @createTime 2023/04/21 17:24:00
 * @since
 */
public class Threadtest {

    public static void main(String[] args) {
        System.out.println("start test");
        new Thread(() -> {
            for(int i = 0; i < 100; i++){
                System.out.println("output Data is "+ i);
            }
        }
        ).start();

        System.out.println("start end");
    }
}
