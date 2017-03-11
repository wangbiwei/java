/**
 * @todo TODO UDP练习
 * 1. 通过udp传输，将信息发送出去
 * 		1.先建立udpSocket服务
 * 		2.提供数据包
 * 		3.发送数据
 * 		4.关闭资源
 */
package wang.w2017年3月11日.UDPsocket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * @author 王必伟
 *
 * @date 2017年3月11日
 *
 * @tags
 */
public class UDPSendsocket
{
	/**
	 * UDP发送数据报
	 * @param ip 发送ip地址
	 * @param sendPort 发送端口
	 * @param sendInfo 发送的信息
	 */
	public static void sendSocket(String ip, int port, String sendInfo)
	{
		try
		{
			// 1.创建udpSocket服务
			int sendPort = 10001;
			DatagramSocket datagramSocketnew = new DatagramSocket(sendPort);
			
			// 2.确定数据，并封装成数据包
			byte[] buf = sendInfo.getBytes();
			InetAddress address = InetAddress.getByName(ip);
			// int port = 10000;
			DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, port);
			
			// 3. 发送数据
			System.out.println(new String(datagramPacket.getData()));
			datagramSocketnew.send(datagramPacket);
			
			// 4. 关闭资源
			datagramSocketnew.close();
			
		} catch (SocketException e)
		{
			e.printStackTrace();
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过键盘发送udp信息
	 * @param ip 发送的Ip
	 * @param port 发送的端口
	 */
	public static void sendSocketWithKeybooard(String ip, int port)
	{
		System.out.print("请输入:");
		BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));
		String temp = null;
		try
		{
			while ((temp = bufRead.readLine()) != null)
			{
				sendSocket(ip, port, temp);
				if ("886".equals(temp))
				{
					break;
				}
				System.out.print("请输入:");
				temp = "";
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				bufRead.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
	}
}
