package com.stone.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 1.缓冲区（Buffer）：在Java NIO中负责数据的存取。缓冲区就是数组，用于存储不同数据类型的数据
 * 根据数据类型不同（boolean除外），提供了相应类型的缓冲区。
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 * 
 * 2.缓冲区存取数据的2个核心方法：
 * put()：存入数据到缓冲区中
 * get()：获取缓冲区中的数据
 * 
 * 3.缓冲区中的4个核心属性
 * capacity：容量，表示缓冲区中最大存储数据的容量，一旦声明不能改变。
 * limit：界限，表示缓冲区中可以操作数据的大小。limit之后的数据不能进行读写。
 * position：位置，表示缓冲区中正在操作数据的位置。
 * mark：标记，表示记录当前position的位置，可以通过reset()恢复到mark的位置
 * mark <= position <= limit <= capacity
 * 
 * 4.直接缓冲区与非直接缓冲区
 * 非直接缓冲区，通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 * 直接缓冲区，通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中，可以提高效率
 * 
 */
public class TestBuffer {
	
	@Test
	public void test3() {
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);
		System.out.println(buf.isDirect());;
	}
	
	@Test
	public void test2() {
		String str = "abcde";
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		byteBuffer.put(str.getBytes());
		byteBuffer.flip();
		byte[] dst = new byte[byteBuffer.limit()];
		byteBuffer.get(dst, 0, 2);
		System.out.println(new String(dst, 0, 2));
		System.out.println(byteBuffer.position());
		
		//mark()：标记
		byteBuffer.mark();
		
		byteBuffer.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println(byteBuffer.position());
		
		//reset()：恢复到mark的位置
		byteBuffer.reset();
		System.out.println(byteBuffer.position());
		
		//判断缓冲区中是否还有剩余的数据
		if (byteBuffer.hasRemaining()) {
			//获取缓冲区中可以操作的数据量
			System.out.println(byteBuffer.remaining());
		}
		
	}
	
	@Test
	public void test1() {
		String str = "abcde";
		
		//1.分配一个指定大小的缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		
		System.out.println("--------------allocate()--------------");
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.capacity());
		
		//2.使用put()方法存入数据到缓冲区
		byteBuffer.put(str.getBytes());
		
		System.out.println("--------------put()--------------");
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.capacity());
		
		//3.切换读取数据模式
		byteBuffer.flip();
		
		System.out.println("--------------flip()--------------");
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.capacity());
		
		//4.使用get()读取缓冲区中的数据
		byte[] dst = new byte[byteBuffer.limit()];
		byteBuffer.get(dst);
		System.out.println(new String(dst,0,dst.length));
		
		System.out.println("--------------get()--------------");
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.capacity());
		
		//5.rewind()：可重复读
		byteBuffer.rewind();
		
		System.out.println("--------------rewind()--------------");
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.capacity());
		
		//6.clear()：清空缓冲区.但是缓冲区中的数据依然存在，处于“被遗忘”状态
		byteBuffer.clear();
		
		System.out.println("--------------clear()--------------");
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
		System.out.println(byteBuffer.capacity());
		
		System.out.println((char)byteBuffer.get());
	}
	
}
