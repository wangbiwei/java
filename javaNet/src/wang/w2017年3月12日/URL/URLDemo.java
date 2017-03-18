/**
 * @todo TODO
 * urlDemo
 */
package wang.w2017年3月12日.URL;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**@author 王必伟
 *
 * @date 2017年3月12日
 *
 * @tags 
 */
public class URLDemo
{
	public static void main(String[] args)
	{
		try
		{
			URL url = new URL("http://www.baidu.com");
			
			URLConnection openConnection = url.openConnection();
			System.out.println(url.getHost());
			System.out.println(url.getPort());
			System.out.println(url.getProtocol());
			System.out.println(url.getPath());
			System.out.println(url.getFile());
			System.out.println(url.getContent());
			
			InputStream inputStream = openConnection.getInputStream();
			
			byte[] buf = new byte[1024];
			int len = inputStream.read(buf);
			System.out.println(new String(buf, 0, len));
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
