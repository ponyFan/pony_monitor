package com.pony.common.ioStream;

import java.io.*;

/**
 * Created by zelei.fan on 2017/6/19.
 *
 * io流：
 *      字节流：InputStream,OutputStream
 *      字符流：reader，writer
 */
public class IoStreamTest {

    public void inputStreamTest() throws IOException {
        FileInputStream inputStream;
        FileOutputStream outputStream;
        File file = new File("D:\\BugReport.txt");
        int count = 0;
        /*读取文件流，这种方法是一个个的读取字节，文件越大则需要读取很多次
        * */
        inputStream = new FileInputStream(file);
        while (inputStream.read() != -1){
            count ++;
        }
        /*读取文件字节长度,实现文件复制*/
        System.out.println("文件长度为：" + count + "字节");

        /*通过读取缓冲区，先将读取的字节存在字节数组中*/
        byte[] bytes = new byte[1];
        int len = 0;
        inputStream = new FileInputStream(file);
        File fileCopy = new File("D:\\test.txt");
        outputStream = new FileOutputStream(fileCopy);
        while ((len = inputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, len);
        }
        fileCopy.delete();


        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        bufferedInputStream = new BufferedInputStream(inputStream, 1);
        bufferedInputStream.read();
    }

    public static void main(String[] args) throws Exception{

        IoStreamTest test = new IoStreamTest();
        test.inputStreamTest();
    }
}
