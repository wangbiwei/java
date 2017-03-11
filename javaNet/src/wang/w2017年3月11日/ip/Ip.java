/**
 * @todo TODO IP学习
 */
package wang.w2017年3月11日.ip;


import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author 王必伟
 *
 * @date 2017年3月11日
 *
 * @tags
 */
public class Ip
{
	public static void main(String[] args)
	{
		try {
			InetAddress ip = InetAddress.getLocalHost();
			
			String hostName = ip.getHostName();
			String hostAddress = ip.getHostAddress();
			
			System.out.println(ip);
			System.out.println(hostName);
			System.out.println(hostAddress);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static InetAddress[] getAllInetAddress(String hostName) throws UnknownHostException
	{
		return InetAddress.getAllByName(hostName);
	}
	
}
