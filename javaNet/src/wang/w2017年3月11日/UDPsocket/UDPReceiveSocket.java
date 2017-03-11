/**
 * @todo TODO UDP练习
 * 1. 通过udp传输，接收数据
 * 		1.先建立udpSocket服务(在定义接收端的时候，给定一个监听的端口，不然，接收不到数据报)
 * 		2.构造接收数据的数据报
 * 		3.接收数据
 * 		4.处理数据
 * 		5.关闭资源
 */
package wang.w2017年3月11日.UDPsocket;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


/**
 * @author 王必伟
 *
 * @date 2017年3月11日
 *
 * @tags
 */
public class UDPReceiveSocket
{
	public static void ReceiveSocket(int listenPort)
	{
		try
		{
			// 1.建立服务
			// int port = 10000;
			DatagramSocket datagramSocket = new DatagramSocket(listenPort);
			
			// 2. 构造接收数据的数据报S
			byte[] buf = new byte[1024];
			DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
			
			while (true)
			{
				// 3.接收数据并放在数据报中
				datagramSocket.receive(datagramPacket);
				// 4.处理数据报中的数据。
				showDatagramPacket(datagramPacket);
				buf = new byte[1024];
				datagramPacket = new DatagramPacket(buf, buf.length);
			}
			// 如果不是无限循环，需要关闭资源
			// datagramSocket.close();
		} catch (SocketException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param datagramPacket
	 */
	private static void showDatagramPacket(DatagramPacket datagramPacket)
	{
		System.out.println("发送地址(ip地址)：" + datagramPacket.getAddress());
		System.out.println("发送地址(端口)：" + datagramPacket.getPort());
		System.out.println("发送地址(ip + 端口)：" + datagramPacket.getSocketAddress());
		System.out.println("数据量：" + datagramPacket.getLength());
		System.out.println("数据：" + new String(datagramPacket.getData()));
		System.out.println();
	}
}
