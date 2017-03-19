/**
 * @todo 
 * ServerSocketStudy
 */
package wang.w2017年3月11日.TCPsocket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author 王必伟
 *
 * @date 2017年3月11日
 *
 * @tags
 */
public class ServiceSocketStudy
{
	public static void main(String[] args)
	{
		ServerSocket serverSocket = null;
		try
		{
			serverSocket = new ServerSocket(10000);
			while (true)
			{
				// 拿到连接的那个客户端的socket；
				Socket accept = serverSocket.accept();
				System.out.println(accept.getLocalSocketAddress());
				
				InputStream inputStream = accept.getInputStream();
				OutputStream outputStream = accept.getOutputStream();
				
				byte[] buf = new byte[1024];
				int size = 0;
				// while ((size = inputStream.read(buf)) != -1)
				// {
				size = inputStream.read(buf);
				System.out.println(new String(buf, 0, size));
				//302状态跳转
				// 跳转百度1(outputStream);
				跳转百度2(outputStream);
				// 获取请求头的信息
				// outputStream.write(buf, 0, size);
				System.out.println();
				System.out.println(new String(buf, 0, size));
				accept.close();
				// }
				
				System.out.println();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				serverSocket.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 服务器直接请求
	 * @param output
	 */
	private static void 跳转百度2(OutputStream output)
	{
		try
		{
			Socket socket = new Socket("www.baidu.com", 80);
			
			OutputStream outputStream = socket.getOutputStream();
			String str = "GET / HTTP/1.1\r\n" + "Host: www.baidu.com\r\n"
					+ "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0\r\n"
					+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
					+ "Accept-Language: zh-CN,en-US;q=0.7,en;q=0.3\r\n" + "Accept-Encoding: gzip, deflate\r\n"
					+ "Connection: keep-alive\r\n" + "Upgrade-Insecure-Requests: 1\r\n\r\n\r\n";
			
			outputStream.write(str.getBytes());
			
			InputStream inputStream = socket.getInputStream();
			
			// int temp;
			// while ((temp = inputStream.read()) != -1)
			// {
			// output.write(temp);
			// }
			
			BufferedReader bufr = new BufferedReader(new InputStreamReader(inputStream));
			String temp = null;
			while ((temp = bufr.readLine()) != null)
			{
				if (temp.contains("302 Moved Temporarily"))
				{
					temp = temp + "\r\n" + "Refresh:10;";
				}
				// if (temp.contains("Location"))
				// {
				// continue;
				// }
				// if (temp.contains("Set-Cookie:"))
				// {
				// continue;
				// }
				
				output.write((temp + "\r\n").getBytes());
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 浏览器再次请求，
	 * @param outputStream
	 */
	private static void 跳转百度1(OutputStream outputStream)
	{
		PrintStream printStream = new PrintStream(outputStream);
		printStream.println("HTTP/1.1 302 Moved Temporarily");
		printStream.println("Location: https://www.baidu.com/");
	}
	
	private static void getByteArray(byte[] array)
	{
		for (byte b : array)
		{
			System.out.print((char) b);
		}
		System.out.println();
	}
}

