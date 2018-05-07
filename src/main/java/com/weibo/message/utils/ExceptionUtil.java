package com.weibo.message.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

public class ExceptionUtil {

	/**
	 * 获取异常的堆栈信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}

	/**
     * 各种id生成策略
     * <p>Title: IDUtils</p>
     * <p>Description: </p>
     * <p>Company: www.itcast.com</p>
     * @author	入云龙
     * @date	2015年7月22日下午2:32:10
     * @version 1.0
     */
    public static class IDUtils {

        /**
         * 图片名生成
         */
        public static String genImageName() {
            //取当前时间的长整形值包含毫秒
            long millis = System.currentTimeMillis();
            //long millis = System.nanoTime();
            //加上三位随机数
            Random random = new Random();
            int end3 = random.nextInt(999);
            //如果不足三位前面补0
            String str = millis + String.format("%03d", end3);

            return str;
        }

        /**
         * 商品id生成
         */
        public static long genItemId() {
            //取当前时间的长整形值包含毫秒
            long millis = System.currentTimeMillis();
            //long millis = System.nanoTime();
            //加上两位随机数
            Random random = new Random();
            int end2 = random.nextInt(99);
            //如果不足两位前面补0
            String str = millis + String.format("%02d", end2);
            long id = new Long(str);
            return id;
        }

        public static void main(String[] args) {
            for(int i=0;i< 100;i++)
            System.out.println(genItemId());
        }
    }
}
