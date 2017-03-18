/**
 * @todo TODO
 * 模拟浏览器
 */
package wang.w2017年3月12日.TCPPractice;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**@author 王必伟
 *
 * @date 2017年3月12日
 *
 * @tags 
 */
public class 模拟浏览器
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket = new Socket("www.baidu.com", 80);
			
			OutputStream outputStream = socket.getOutputStream();
			String str = "GET / HTTPS/1.1\r\n" + "Host: www.baidu.com\r\n"
					+ "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0\r\n"
					+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
					+ "Accept-Language: zh-CN,en-US;q=0.7,en;q=0.3\r\n" + "Accept-Encoding: gzip, deflate\r\n"
					+ "Connection: keep-alive\r\n" + "Upgrade-Insecure-Requests: 1\r\n\r\n\r\n";
			
			outputStream.write(str.getBytes());
			
			InputStream inputStream = socket.getInputStream();
			
			BufferedReader bufr = new BufferedReader(new InputStreamReader(inputStream));
			String temp = null;
			while ((temp = bufr.readLine()) != null)
			{
				System.out.println(temp);
			}
			socket.close();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
