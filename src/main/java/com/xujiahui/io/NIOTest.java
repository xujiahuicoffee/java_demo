package com.xujiahui.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.springframework.core.codec.ByteBufferEncoder;

import com.jayway.jsonpath.spi.cache.Cache;

/**
 * 漫画编程20190701  BIO  NIO  AIO
 * @author xujiahui
 * 2019年7月4日下午10:40:28
 */
public class NIOTest {

	public static void main(String[] args) {
		writeNIO();
	}
	
	public static void readNIO() {
		String pathname = "D://jiangnan.jpg";
		FileInputStream fin = null;
		
		try {		
			fin = new FileInputStream(new File(pathname));
			FileChannel channel = fin.getChannel();
			
			int capacity = 100;//字节
			ByteBuffer bf = ByteBuffer.allocate(capacity);
			int length = -1;
			
			while ((length = channel.read(bf)) != -1) {
				bf.clear();
				byte[] bytes = bf.array();
				System.out.write(bytes, 0, length);
				System.out.println();
			}		
			channel.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	private static void writeNIO() {
		String pathName = "D://你好_gbk.txt";
		FileOutputStream fos = null;
		
		try {
			
			fos = new FileOutputStream(new File(pathName));
			FileChannel channel = fos.getChannel();			
			ByteBuffer src = Charset.forName("gbk").encode("你好你好你好你好你好");
			
			int length = 0;
			
			while ((length = channel.write(src)) != 0) {
				System.out.println("写入长度："+length);
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
