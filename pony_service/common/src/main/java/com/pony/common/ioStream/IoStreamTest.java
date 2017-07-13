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
        File file = new File("E:\\test.txt");
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
        byte[] bytes = new byte[31];/*byte设置的字节数表示一读取所能读取的字节*/
        int len = 0;
        inputStream = new FileInputStream(file);
        File fileCopy = new File("E:\\testCopy.txt");
        outputStream = new FileOutputStream(fileCopy);
        while ((len = inputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, len);
        }
        fileCopy.delete();

        /*缓冲字节流，减少io，BufferedInputStream默认一次读取8192个字节，如果文件大小大于8192个字节
        * 那么当读完8192之后会进行第二次读取，不是像FileInputStream一个一个的读取；
        * BufferedOutputStream也是同样的道理
        * */
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        int len1 = 0;
        byte[] bytes1 = new byte[8192];
        bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedOutputStream = new BufferedOutputStream(outputStream);
        while ((len1 = bufferedInputStream.read(bytes1)) != -1){
            bufferedOutputStream.write(bytes1, 0, len1);
        }
    }

    public void objectStreamTest() throws IOException {
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        File file = new File("E:\\test.txt");
        /*将对象写进文件，注意：因为要写进磁盘对象需要实现序列化*/
        outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(new Person(1, "哈哈", 12));
        outputStream.writeObject(new Person(2, "www", 13));
        outputStream.writeObject(new Person(3, "gg", 14));
        outputStream.writeObject(new Person(4, "vv", 15));
        outputStream.writeObject(new Person(5, "qq", 16));

        /*读取文件内容*/
        inputStream = new ObjectInputStream(new FileInputStream(file));
        for (int i = 0; i < 5; i ++){
            try {
                System.out.println(inputStream.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    /*缓冲字符流, readLine()可以读取整行*/
    public void bufferReaderTest()throws IOException{
        BufferedReader reader;
        BufferedWriter writer;
        String str = null;
        File fileCopy = new File("E:\\testCopy.txt");
        File file = new File("E:\\test.txt");
        writer = new BufferedWriter(new FileWriter(fileCopy));
        reader = new BufferedReader(new FileReader(file));
        while ((str = reader.readLine()) != null){
            writer.write(str);
            System.out.println(str);
        }
        writer.flush();//使用缓冲区中的方法，将数据刷新到目的地文件中去。
        writer.close();
    }


    public static void main(String[] args) throws Exception{

        IoStreamTest test = new IoStreamTest();
        /*test.inputStreamTest();*/
        /*test.objectStreamTest();*/
        test.bufferReaderTest();
    }
}
