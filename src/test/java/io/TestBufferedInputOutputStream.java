package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBufferedInputOutputStream {
	public static void main(String[] args) {
		byte[] buf = new byte[4096];
		BufferedInputStream bufIn = null;
		BufferedOutputStream bufOut = null;
		try {
			bufIn = new BufferedInputStream(
					new FileInputStream(new File("D:/eclipse-jee-mars-2-win32-x86_64/workspace/spaceOut.mp4")));
			// 不能写出到文件夹中，同理读取也不能读文件夹
			// bufOut = new BufferedOutputStream(new FileOutputStream(new
			// File("C:/Users/Administrator/Desktop")));
			bufOut = new BufferedOutputStream(
					new FileOutputStream(new File("C:/Users/Administrator/Desktop/test2.mp4")));
			while (bufIn.read(buf) != -1) {
				bufOut.write(buf);
			}
			bufOut.flush();// 刷新此输出流，并强制将所有已缓冲的输出字节写入该流中。FilterOutputStream 的
							// flush 方法调用其基础输出流的 flush 方法

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufIn != null) {
					bufIn.close();
				}
				if (bufOut != null) {
					bufOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
