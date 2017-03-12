/**
 * @todo TODO
 * 多线程模拟用户登录
 * 
 */
package wang.w2017年3月12日.TCPPractice;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 王必伟
 *
 * @date 2017年3月12日
 *
 * @tags
 */
public class LoadWithThread
{
	public static void main(String[] args)
	{
		try
		{
			BufferedReader bufr = new BufferedReader(new FileReader("user.txt"));
			
			List<String> users = new ArrayList<String>();
			String temp = null;
			
			while ((temp = bufr.readLine()) != null)
			{
				users.add(temp);
			}
			
			ServerSocket serverSocket = new ServerSocket(10000);
			
			System.out.println(serverSocket.getInetAddress());
			
			Socket accept = serverSocket.accept();
			
//			InputStream inputStream = accept.getInputStream();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
			
			String temp1 = null;
			while ((temp1 = bufReader.readLine()) != null)
			{
				
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}


class LoginClient
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket = new Socket("127.0.0.1", 10000);
			
			OutputStream outputStream = socket.getOutputStream();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			
			String temp = null;
			while ((temp = bufferedReader.readLine()) != null)
			{
				if ("886".equals(temp))
				{
					break;
				}
				outputStream.write(temp.getBytes());
			}
			
			InputStream inputStream = socket.getInputStream();
			
			BufferedReader bufr1 = new BufferedReader(new InputStreamReader(inputStream));
			while ((temp = bufr1.readLine()) != null)
			{
				System.out.println(temp);
			}
			

			bufferedReader.close();
			socket.close();
			
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}