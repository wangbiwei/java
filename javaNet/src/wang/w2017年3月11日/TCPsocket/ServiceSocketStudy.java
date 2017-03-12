/**
 * @todo 
 * ServerSocketStudy
 */
package wang.w2017年3月11日.TCPsocket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
				outputStream.write(buf, 0, size);
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
}
