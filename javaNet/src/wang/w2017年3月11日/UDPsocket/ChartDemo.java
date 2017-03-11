/**
 * @todo 
	编写聊天程序
	1. 有数据发送和数据接收部分 
	2. 多线程同时执行
 */
package wang.w2017年3月11日.UDPsocket;

/**
 * @author 王必伟
 *
 * @date 2017年3月11日
 *
 * @tags
 */
public class ChartDemo
{
	public static void main(String[] args)
	{
		
		new Thread(new OneSAndR("localhost", 10000, 10001)).start();
		// new Thread(new OneSAndR("localhost", 10001, 10000)).start();
	}
	
	/**
	 * 在junit4中测试时，直接停止了，暂时不知道如何在junit只支持多线程
	 * @param ip
	 * @param port
	 * @param listenPort
	 */
	public static void oneClientAndServer(String ip, int port, int listenPort)
	{
		
	}
}


class OneSAndR implements Runnable
{
	String ip;
	int port;
	int listenPort;
	
	/**
	 * 
	 */
	public OneSAndR(String ip, int port, int listenPort)
	{
		this.ip = ip;
		this.port = port;
		this.listenPort = listenPort;
	}
	
	/*
	 *
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		new Thread(new Send(ip, port)).start();
		new Thread(new Revice(listenPort)).start();
	}
	
}


class Send implements Runnable
{
	private String ip;
	private int port;
	
	public Send(String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}
	
	/*
	 * 发送信息
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		UDPSendsocket.sendSocketWithKeybooard(ip, port);
	}
}


class Revice implements Runnable
{
	int listenPort;
	
	public Revice(int listenPort)
	{
		this.listenPort = listenPort;
	}
	
	/*
	 * 接收信息
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		UDPReceiveSocket.ReceiveSocket(listenPort);
	}
}
