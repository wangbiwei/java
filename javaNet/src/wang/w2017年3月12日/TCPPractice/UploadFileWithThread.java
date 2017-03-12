/**
 * @todo TODO
 * 多线程并发上传图片
 */
package wang.w2017年3月12日.TCPPractice;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class UploadFileWithThread
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
				System.out.println(Thread.currentThread().getName());
				Socket accept = serverSocket.accept();
				new Thread(new saveFile(accept)).start();
			}
		} catch (
		
		IOException e)
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


class saveFile implements Runnable
{
	private Socket socket;
	
	/**
	 * 
	 */
	public saveFile(Socket socket)
	{
		this.socket = socket;
	}
	
	/*
	 *
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		System.out.println(Thread.currentThread().getName());
		System.out.println(socket.getLocalSocketAddress());
		
		BufferedInputStream bufferedInputStream;
		try
		{
			bufferedInputStream = new BufferedInputStream(socket.getInputStream());
			
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream("E:\\桌面\\temp\\test.jpg"));
			byte[] buf = new byte[1024];
			while (bufferedInputStream.read(buf) != -1)
			{
				bufferedOutputStream.write(buf);
			}
			
			BufferedWriter result = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			result.write("上传成功");
			result.flush();// 不刷新或者不关闭客户端没有
			bufferedOutputStream.close();
			socket.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
