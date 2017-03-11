/**
 * @todo 
 * TCP练习
 * 1. 创建socket服务（因为是连接性的，要指定ip和端口）
 */
package wang.w2017年3月11日.TCPsocket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


/**
 * @author 王必伟
 *
 * @date 2017年3月11日
 *
 * @tags
 */
public class SocketStudy
{
	public static void main(String[] args)
	{
	}
	
	public static void TCPsend(String str)
	{
		try
		{
			// 1.创建指定Socket的连接
			Socket socket = new Socket("localhost", 10000);
			
			// 获取socket中的流
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			
			outputStream.write(str.getBytes());
			
			byte[] buf = new byte[1024];
			int size = 0;
			// while ((size = inputStream.read(buf)) != -1)//
			// read方法阻塞，如果服务端没有返回数据，客户端将一直阻塞在这儿
			// {
			inputStream.read(buf);
			System.out.println("这是服务端返回:" + new String(buf));
			// }
			// 关闭资源
			socket.close();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void TCPsendWithKeyboard()
	{
		System.out.print("请输入:");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String temp = null;
		try
		{
			while (true)
			{
				temp = bufferedReader.readLine();
				if ("886".equals(temp.trim()))
				{
					break;
				}
				TCPsend(temp);
				System.out.print("请输入:");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
