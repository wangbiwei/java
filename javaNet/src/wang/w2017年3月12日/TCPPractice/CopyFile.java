/**
 * @todo TODO
 */
package wang.w2017年3月12日.TCPPractice;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author 王必伟
 *
 * @date 2017年3月12日
 *
 * @tags
 */
public class CopyFile
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket = new Socket("127.0.0.1", 10000);
			BufferedOutputStream buf = new BufferedOutputStream(socket.getOutputStream());
			InputStream inputStream = socket.getInputStream();
			
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					new FileInputStream("C:\\Users\\Imwang\\Desktop\\044竹.jpg"));
			byte[] temp = new byte[1024];
			while (bufferedInputStream.read(temp) != -1)
			{
				buf.write(temp);
			}
			socket.shutdownOutput();
			
			System.out.println(new BufferedReader(new InputStreamReader(inputStream)).readLine());
			
			bufferedInputStream.close();
			socket.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}


class ServiceSocketTest
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
				
				BufferedInputStream bufferedInputStream = new BufferedInputStream(accept.getInputStream());
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream("E:\\桌面\\temp\\test.jpg"));
				byte[] buf = new byte[1024];
				while (bufferedInputStream.read(buf) != -1)
				{
					bufferedOutputStream.write(buf);
				}
				
				BufferedWriter result = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
				result.write("上传成功");
				result.flush();// 不刷新或者不关闭客户端没有
				bufferedOutputStream.close();
				accept.close();
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